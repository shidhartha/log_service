package services.prototype

import akka.http.scaladsl.model.HttpHeader.ParsingResult.Ok
import data.{LogData, LogParsingResult}
import play.api.libs.json.Json
import services.{ExecutionContextTrait, LogOrderingService, LogPersistanceService}
//import play.api.mvc.

import scala.concurrent.Future

/***
  * This is the real implentation of the log ordering service.
  * Given a module name, this service will get the dependency map of the service and then
  * collect the log for that service and chronologically order the logs and return them
  */
trait LogOrderingServiceImpl extends LogOrderingService with S3LogPersistanceServiceImpl with ExecutionContextTrait{

  override def getOrderedLog(component: String): Future[List[LogData]] = {
    val moduleLogSeq : Seq[Future[Seq[LogData]]] = getDependencyList(component).map { module =>
      getLogFromStorage(module)
    }
    Future.sequence(moduleLogSeq).map{ data =>
      data.flatten.sortBy(_.timeStamp).toList
    }
  }
}
