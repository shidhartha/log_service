package data

import play.api.libs.json.{Format, Json}

trait LogParsingResult

case class LogData(moduleName: String, timeStamp: Long, message: List[String], level:String = "info", instanceId: Int =1) extends LogParsingResult

object LogData {
//  implicit val LogParsingResultFormat = Json.format[LogParsingResult]
  implicit val format: Format[LogData] = Json.format[LogData]
}
