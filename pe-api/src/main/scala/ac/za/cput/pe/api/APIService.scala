package ac.za.cput.pe.api

import akka.actor.Actor

trait APIService {
  def canMove(userKey: String, srcOrgKey: String, destOrgKey: String):ResultMessage = {
    new Allow("Let it all through")
  }
}

class APIActor extends Actor with APIService{

  def receive = {
    case CanMove(userKey,srcOrgKey,destOrgKey) =>
      sender ! canMove(userKey,srcOrgKey,destOrgKey)
  }
}
