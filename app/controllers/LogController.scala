package controllers

import javax.inject.{Inject, Singleton}
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import services.ExecutionContextTrait
import services.prototype.LogOrderingServiceImpl

import scala.concurrent.ExecutionContext.global
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class LogController @Inject()(val controllerComponents: ControllerComponents) extends BaseController
  with LogOrderingServiceImpl with ExecutionContextTrait {
  def getLog(component: String ): Action[AnyContent] = Action.async {
    getOrderedLog(component).map { data =>
      Ok(Json.toJson(data))
    }
//    Future.successful(Ok(Json.toJson(getOrderedLog(component))))
  }
}