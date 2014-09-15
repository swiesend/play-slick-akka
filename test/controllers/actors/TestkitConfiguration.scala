package controllers.actors

import org.scalatest.BeforeAndAfterAll
import org.scalatest.FunSpecLike

import akka.actor.ActorSystem
import akka.testkit.ImplicitSender
import akka.testkit.TestKit

/**
 * This class provides any enclosed specs with an ActorSystem and an implicit sender.
 * An ActorSystem can be an expensive thing to set up, so we define a single system
 * that is used for all of the tests.
 */
abstract class TestkitConfiguration
  extends TestKit(ActorSystem())
  with FunSpecLike
  with BeforeAndAfterAll
  with ImplicitSender {

  override def afterAll() {
    // Send a shutdown message to all actors.
    system.shutdown()

    // Block the current thread until all the actors have received and processed
    // shutdown messages.  Using this method makes certain that all threads have been
    // terminated, which is especially important when running large test suites (otherwise
    // you may find yourself running out of threads unexpectedly)
    system.awaitTermination()
  }

}