package services.prototype

import data.LogData
import org.scalatest.FunSuite
import services.ModuleDependencyService

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

class LogOrderingServiceImplTest extends FunSuite with LogOrderingServiceImpl with ModuleDependencyService{
  val atMost: Duration = 1 second

  test("testGetOrderedLog should get the log ordered by timestamp") {

    val logsFut = getOrderedLog("apache")

    val log: List[LogData] = Await.result(logsFut, atMost)
    val timeStamps = log.map(_.timeStamp)
    assert (timeStamps equals(timeStamps.sorted))

  }

  test ("testGetOrderedLog should get all the dependent service logs for apache") {
    val moduleName = "apache"
    val logsFut = getOrderedLog(moduleName)
    val depModules = depMap.get(moduleName).getOrElse(Seq.empty)

    val log: List[LogData] = Await.result(logsFut, atMost)
    val logModules = log.map(_.moduleName)

    depModules.foreach{ module =>
      println(s"Verifying if $module exists in the result log")
      assert(log.map(_.moduleName).toSet.contains(module))
    }
  }

  test ("testGetOrderedLog should get all the dependent service logs for db") {
    val moduleName = "db"
    val logsFut = getOrderedLog(moduleName)
    val depModules = depMap.get(moduleName).getOrElse(Seq.empty)

    val log: List[LogData] = Await.result(logsFut, atMost)
    val logModules = log.map(_.moduleName)

    depModules.foreach{ module =>
      println(s"Verifying if $module exists in the result log")
      assert(log.map(_.moduleName).toSet.contains(module))
    }
  }

  test ("testGetOrderedLog should get all the dependent service logs for java_ms") {
    val moduleName = "java_ms"
    val logsFut = getOrderedLog(moduleName)
    val depModules = depMap.get(moduleName).getOrElse(Seq.empty)

    val log: List[LogData] = Await.result(logsFut, atMost)
    val logModules = log.map(_.moduleName)

    depModules.foreach{ module =>
      println(s"Verifying if $module exists in the result log")
      assert(log.map(_.moduleName).toSet.contains(module))
    }
  }

  test ("testGetOrderedLog should get all the dependent service logs for php_ms") {
    val moduleName = "php_ms"
    val logsFut = getOrderedLog(moduleName)
    val depModules = depMap.get(moduleName).getOrElse(Seq.empty)

    val log: List[LogData] = Await.result(logsFut, atMost)
    val logModules = log.map(_.moduleName)

    depModules.foreach{ module =>
      println(s"Verifying if $module exists in the result log")
      assert(log.map(_.moduleName).toSet.contains(module))
    }
  }

}
