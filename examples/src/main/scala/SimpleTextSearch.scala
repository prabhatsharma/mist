import io.hydrosphere.mist.lib.MistJob

object SimpleTextSearch extends MistJob {

  def doStuff(filePath: String, filters: List[String]): Map[String, Any] = {
    var data = context.textFile(filePath)

    filters.foreach { currentFilter =>
      data = data.filter(line => line.toUpperCase.contains(currentFilter.toUpperCase))
    }

    Map("result" -> data.collect())
  }
}

