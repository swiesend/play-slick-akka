import com.typesafe.sbt.packager.Keys._
import com.typesafe.sbt.SbtNativePackager._
import NativePackagerKeys._
import AssemblyKeys._

name := """play-slick-akka"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.2"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

////////////////////////////////////////////////////////////////////
// Build options
// read more:
// https://www.playframework.com/documentation/2.4.x/ProductionDist
// http://www.scala-sbt.org/sbt-native-packager/installation.html
// https://stackoverflow.com/questions/21950299/how-do-i-get-s3-upload-to-depend-on-packageziptarball-in-universal
////////////////////////////////////////////////////////////////////

//javacOptions in Compile ++= Seq("-source", "1.8", "-target", "1.8")

/**
 * Minimal Debian settings
 *
 * Build your package with:
 * activator debian:packageBin
 */
maintainer in Linux := "Firstname Lastname <firstname.lastname@example.com>"

packageSummary in Linux := "Short description"

packageDescription := "Long description"

/**
 * Using the SBT assembly plugin
 *
 * This will produce one jar as an output artifact, and allow you to execute it directly using the java command.
 * activator assembly
 */
assemblySettings

mainClass in assembly := Some("play.core.server.NettyServer")

fullClasspath in assembly += Attributed.blank(PlayKeys.playPackageAssets.value)

////////////////////////////////////////////////////////////////////
// Test options
////////////////////////////////////////////////////////////////////
testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v", "-s", "-a")

parallelExecution in Test := false

logBuffered := false

////////////////////////////////////////////////////////////////////
// Dependencies - Maven Repository
////////////////////////////////////////////////////////////////////
EclipseKeys.withSource := true

//standard
libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws)

//logging
//Simple Logging Facade for Java - http://www.slf4j.org/
//"org.slf4j" % "slf4j-nop" % "1.6.4" withSources() withJavadoc() 
libraryDependencies ++= Seq(
  //Logback Project - http://logback.qos.ch/
  "ch.qos.logback" % "logback-classic" % "0.9.28" % "test" withSources () withJavadoc ())

//testing
//libraryDependencies += "com.dadrox" % "sbt-junit" % "0.3.1"
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.1" % "test" withSources () withJavadoc ())

//slick
libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "0.8.0" withSources () withJavadoc (),
  "com.typesafe.slick" %% "slick" % "2.1.0" withSources () withJavadoc (),
  "com.typesafe.slick" %% "slick-testkit" % "2.1.0" % "test" withSources () withJavadoc (),
  "com.novocode" % "junit-interface" % "0.10" % "test" withSources () withJavadoc (),
  "com.h2database" % "h2" % "1.4.181" withSources () withJavadoc (),
  "mysql" % "mysql-connector-java" % "5.1.32" /*withSources() withJavadoc()*/ ,
  "joda-time" % "joda-time" % "2.4" withSources () withJavadoc (),
  "org.joda" % "joda-convert" % "1.6" withSources () withJavadoc (),
  "com.github.tototoshi" %% "slick-joda-mapper" % "1.2.0" withSources () withJavadoc ())

//akka
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.5" withSources () withJavadoc (),
  "com.typesafe.akka" %% "akka-testkit" % "2.3.5" withSources () withJavadoc ())