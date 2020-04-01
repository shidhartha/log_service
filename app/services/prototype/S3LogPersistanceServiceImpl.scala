package services.prototype

import data.LogData
import services.{ExecutionContextTrait, LogPersistanceService}

import scala.concurrent.Future
import scala.util.Random

/***
  * This is demo service mimicking S3 persistance service behavior.
  * Given a module name, the real implementation of this service will get the intended logs for the timestamps given from S3 and
  * send back to the caller
  */

trait S3LogPersistanceServiceImpl extends LogPersistanceService with ExecutionContextTrait{

//  implicit val ec: ExecutionContext = global

  override def getLogFromStorage(moduleName: String, startTimeStamp: Option[Long]= None, endTimeStamp:Option[Long] = None): Future[Seq[LogData]] = Future {
    (startTimeStamp, endTimeStamp) match {
      case (None, None) => {
        val maxLongSeed = System.currentTimeMillis()/1000000
        moduleName match {
          case "apache" =>
            // there are 3 instances of apache, so get  data from three instances
            for {i <-0 to 3} yield {
              LogData(moduleName,System.currentTimeMillis() - Random.nextLong(maxLongSeed), List(s"apache server log"), "info", i)
            }
          case "db" =>
            // there are 3 instances of apache, so get  data from three instances
            for {i <-0 to 2} yield {
              LogData(moduleName,System.currentTimeMillis() - Random.nextLong(maxLongSeed/3), List("db log"), "info", i)
            }
          case "java_ms" =>
            // there are 3 instances of apache, so get  data from three instances
            for {i <-0 to 5} yield {
              LogData(moduleName,System.currentTimeMillis() - Random.nextLong(maxLongSeed/2), List("java microservice log"), "info", i)
            }
          case "php_ms" =>
            // there are 3 instances of apache, so get  data from three instances
            for {i <-0 to 5} yield {
              LogData(moduleName,System.currentTimeMillis() - Random.nextLong(maxLongSeed/2), List("php microservice log"), "info", i)
            }
          case _ =>
            for {i <-0 to 1} yield {
              LogData(moduleName, System.currentTimeMillis() - Random.nextLong(maxLongSeed), List("Unknon service log"), "info", 0)
            }
        }

      }
      case _ =>
        // not implemneted for now :)
        throw new UnsupportedOperationException()
    }
  }

}
