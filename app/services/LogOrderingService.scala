package services

import data.{LogData, LogParsingResult}

import scala.concurrent.Future

/***
  * This is the real implentation of the log ordering service.
  * Given a module name, this service will get the dependency map of the service and then
  * collect the log for that service and chronologically order the logs and return them
  */
trait LogOrderingService extends ModuleDependencyService{

  def getOrderedLog(component: String): Future[List[LogData]]

}
