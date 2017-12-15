import mist.api._
import mist.api.encoding.DefaultEncoders._
import org.apache.spark.SparkContext

object PiExample extends MistFn[Double] {

  override def handle = {
    val samples = arg[Int]("samples").validated(_ > 0, "Samples should be positive")
    withArgs(samples).onSparkContext((n: Int, sc: SparkContext) => {
      val count = sc.parallelize(1 to n).filter(_ => {
        val x = math.random
        val y = math.random
        x * x + y * y < 1
      }).count()

      val pi = (4.0 * count) / n
      pi
    })
  }
}
