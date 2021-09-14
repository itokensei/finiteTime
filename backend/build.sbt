import sbt.Keys.{libraryDependencies, scalacOptions}

name := """FiniteTime"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    scalaVersion := "2.13.6",
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    ),
    libraryDependencies ++= Seq(
      guice,
      "org.apache.commons" % "commons-lang3" % "3.12.0",
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
      "org.tpolecat" %% "doobie-postgres" % "1.0.0-RC1",
      "org.tpolecat" %% "doobie-core" % "1.0.0-RC1",
    )
  )

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
