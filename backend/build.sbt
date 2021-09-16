import sbt.Keys.{libraryDependencies, scalacOptions}

name := """FiniteTime"""
organization := "com.example"

version := "1.0-SNAPSHOT"

ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision

val scala3Commons: Seq[Setting[_]] = Seq(
  scalaVersion := "3.0.2",
  // Scala 3 migration compilation
  // コンパイラはドロップされたScala2系の機能のほとんどを許容し、エラーの代わりに警告を出力します。
  // 各警告は、コンパイラが非推奨のコードをクロスコンパイルの対応するコードに安全に書き換えることさえできることを強く示しています。
  scalacOptions += "-source:3.0-migration",
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % Test
)

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    scalaVersion := "2.13.6",
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Ywarn-unused",
      "-Xsource:3"
    ),
    libraryDependencies ++= Seq(
      guice,
      "org.apache.commons" % "commons-lang3" % "3.12.0",
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
      "org.tpolecat" %% "doobie-postgres" % "1.0.0-RC1",
      "org.tpolecat" %% "doobie-core" % "1.0.0-RC1"
    )
  )
  .dependsOn(application)

lazy val application = project
  .settings(scala3Commons)
  .dependsOn(domain)

lazy val domain = project
  .settings(scala3Commons)
  .dependsOn(util)

lazy val util = project.settings(scala3Commons)
