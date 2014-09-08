name := """play-slick-akka"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.2"

//javacOptions in Compile ++= Seq("-source", "1.8", "-target", "1.8")

//standard
libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws
)

//ScalaTest
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.1" % "test"

//libraryDependencies += "com.dadrox" % "sbt-junit" % "0.3.1"

//play,slick,slick-testkit,db drivers
libraryDependencies ++= Seq(
	"com.typesafe.play" %% "play-slick" % "0.8.0", //play-slick
	"com.typesafe.slick" %% "slick" % "2.1.0", //slick
	"com.typesafe.slick" %% "slick-testkit" % "2.1.0" % "test", //slick-testkit
  "com.novocode" % "junit-interface" % "0.10" % "test", //slick-testkit
  //"org.slf4j" % "slf4j-nop" % "1.6.4", //slick - Simple Logging Facade for Java - http://www.slf4j.org/
  "ch.qos.logback" % "logback-classic" % "0.9.28" % "test", //slick-testkit - Logback Project - http://logback.qos.ch/
	"com.h2database" % "h2" % "1.4.181", //h2 db driver
	"mysql" % "mysql-connector-java" % "5.1.32", //MySQL db driver
	//DateTime Support for Slick
	"joda-time" % "joda-time" % "2.4",
  "org.joda" % "joda-convert" % "1.6",
  "com.github.tototoshi" %% "slick-joda-mapper" % "1.2.0"
)

//Test options
//testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v", "-s", "-a")

//parallelExecution in Test := false

//logBuffered := false

//akka
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.5",  //akka
  "com.typesafe.akka" %% "akka-testkit" % "2.3.5" //akka-testkit
)
