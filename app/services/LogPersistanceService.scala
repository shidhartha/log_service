package services

import data.LogData

import scala.concurrent.Future

/***
  * This is the serice dealing with the log persistance layer
  * Given a module name, the real implementation of this service will get the intended logs for the timestamps given from
  * the persistance layer and send back to the caller
  */
trait LogPersistanceService {

  def getLogFromStorage(moduleName: String, startTimeStamp: Option[Long]= None, endTimeStamp:Option[Long] = None) : Future[Seq[LogData]]

}
