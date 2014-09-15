package controllers.actors.examples

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

import org.specs2.time.NoTimeConversions

import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.testkit.TestProbe
import controllers.actors.TestkitConfiguration
import play.api.test.WithApplication


class EchoActorSpec extends TestkitConfiguration with NoTimeConversions {

  describe("A EchoActor") {
    it("should notify watchers") {
      new WithApplication {
        //we are using the ActorSystem from the TestkitConfiguration
        val probe = new TestProbe(system)
        // creating an EchoAcotor with Properties (Props) - there are also other ways to create an Actor
        val echoActor = system.actorOf(Props(new EchoActor(List(probe.ref))))

        val testStr = "test"
        // send message object
        echoActor ! Echo(testStr)

        val result = probe.receiveOne(3 seconds)
        val expected = testStr

        result equals expected
      }
    }
  }

}
