package locallibrary

object CommandLineProperties {
  private def getProperty(propertyName: String, defaultValue: String): String ={
    Option(System.getenv(propertyName))
      //.orElse(Option(if (System.getProperty(propertyName) != null) System.getProperty(propertyName).stripPrefix("\"").stripSuffix("\"") else "" ))
      .orElse(Option(if (System.getProperty(propertyName) != null) System.getProperty(propertyName).stripPrefix("\"").stripSuffix("\"") else defaultValue ))
      .getOrElse(defaultValue)
  }
  def userCount: Int = getProperty("USERS", "1").toInt
  def pacing: Int = getProperty("PACING", "1").toInt
  def repeatCount: Int = getProperty("REPEATCOUNT", "1").toInt
  def baseURL: String = getProperty("BASEURL","NO").toString

}
