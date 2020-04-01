package services

import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.global
trait ExecutionContextTrait {
  implicit val ec: ExecutionContext = global

}
