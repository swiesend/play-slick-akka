package actors.examples

import akka.actor.Actor
import akka.actor.ActorRef

class EchoActor(nexts: List[ActorRef]) extends Actor {
  //implement the Actor trait
  def receive = {
    case Echo(t) => {
      // fire the text to the "listeners"      
      nexts.foreach(_ ! t)
    }
  }
}

case class Echo(text: String)