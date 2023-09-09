// build.sbt

name := "ML-GA"

version := "0.1-SNAPSHOT"

scalacOptions ++= Seq(
  "-unchecked", "-deprecation", "-feature", "-language:higherKinds",
  "-language:implicitConversions", "-Ykind-projector:underscores"
)

enablePlugins(MdocPlugin)

mdocOut := file("md/")

libraryDependencies  ++= Seq(
  "org.scalameta" %% "munit" % "0.7.29" % Test,
  "org.scalameta" %% "munit-scalacheck" % "0.7.29" % Test,
  "org.typelevel" %% "discipline-munit" % "1.0.9" % Test,
  "org.scalanlp" %% "breeze" % "2.1.0",
  ("com.github.haifengl" %% "smile-scala" % "3.0.0").cross(CrossVersion.for3Use2_13),
  "org.typelevel" %% "cats-core" % "2.8.0",
  "org.typelevel" %% "cats-free" % "2.8.0",
  "org.typelevel" %% "cats-laws" % "2.8.0",
  "org.typelevel" %% "cats-effect" % "3.2.2",
  "org.typelevel" %% "discipline-core" % "1.1.5"
)

scalaVersion := "3.1.2"


// eof

