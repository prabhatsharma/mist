package io.hydrosphere.mist.ml.loaders.preprocessors

import io.hydrosphere.mist.ml.Metadata
import io.hydrosphere.mist.ml.loaders.LocalModel
import org.apache.spark.ml.Transformer
import org.apache.spark.ml.feature.IndexToString

object LocalIndexToString extends LocalModel {
  override def localLoad(metadata: Metadata, data: Map[String, Any]): Transformer = {
    println(data)
    println(metadata)
    val ctor = classOf[IndexToString].getDeclaredConstructor(classOf[String])
    ctor.setAccessible(true)
    ctor
      .newInstance(metadata.uid)
      .setLabels(metadata.paramMap("labels").asInstanceOf[List[String]].to[Array])
      .setInputCol(metadata.paramMap("inputCol").asInstanceOf[String])
      .setOutputCol(metadata.paramMap("outputCol").asInstanceOf[String])
  }
}
