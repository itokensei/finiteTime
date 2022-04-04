import sbt.Keys.{libraryDependencies, scalacOptions}

name := """FiniteTime"""
organization := "com.example"
version := "1.0-SNAPSHOT"
Global / onChangedBuildSource := ReloadOnSourceChanges

ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision

val scala3Commons: Seq[Setting[_]] = Seq(
  scalaVersion := "3.0.2",
  scalacOptions += "-source:3.0-migration", // Emit warnings in place of errors on most of the dropped Scala 2 features.
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % Test
)

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    scalaVersion := "2.13.6",
    scalacOptions ++= Seq(
      "-feature", // Emit warning and location for usages of features that should be imported explicitly.
      "-deprecation", // Emit warning and location for usages of deprecated APIs.
      "-Ywarn-unused", // Enable specific unused warnings.
      "-Xsource:3" // Enable some Scala 3 syntax and behavior.
    ),
    libraryDependencies ++= Seq(
      guice,
      "org.apache.commons" % "commons-lang3" % "3.12.0",
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
      "org.tpolecat" %% "doobie-postgres" % "1.0.0-RC1",
      "org.tpolecat" %% "doobie-core" % "1.0.0-RC1"
    )
  )
  //.dependsOn(domain)

lazy val domain = project
  .settings(scala3Commons)
