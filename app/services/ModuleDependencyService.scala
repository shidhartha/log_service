package services

import model._

trait ModuleDependencyService {

  val depMap : Map[String, Seq[String]]= Map (
    "apache" -> Seq("apache", "java_ms", "php_ms", "db"),
    "java_ms"  -> Seq("java_ms", "db"),
    "php_ms" -> Seq("php_ms", "db"),
    "db" -> Seq("db")
  )

  def getDependencyList(service: String): Seq[String] = {
    println(service)
    val res = depMap.get(service.trim.toLowerCase())
    println(res)
    res.getOrElse(List.empty)
  }
}
