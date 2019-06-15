val Http4sVersion = "$http4s_version$"
val CirceVersion = "$circe_version$"
val Specs2Version = "$specs2_version$"
val LogbackVersion = "$logback_version$"
val ZioVersion = "$zio_version$"

lazy val root = (project in file("."))
  .settings(
    organization := "$organization$",
    name := "$name;format="norm"$",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "$scala_version$",
    scalacOptions ++= Seq("-Ypartial-unification"),
    libraryDependencies ++= Seq(
      "org.http4s"      %% "http4s-blaze-server"     % Http4sVersion,
      "org.http4s"      %% "http4s-blaze-client"     % Http4sVersion,
      "org.http4s"      %% "http4s-circe"            % Http4sVersion,
      "org.http4s"      %% "http4s-dsl"              % Http4sVersion,
      "io.circe"        %% "circe-generic"           % CirceVersion,
      "org.specs2"      %% "specs2-core"             % Specs2Version % "test",
      "ch.qos.logback"  %  "logback-classic"         % LogbackVersion,
      "org.scalaz"      %% "scalaz-zio"              % ZioVersion,
      "org.scalaz"      %% "scalaz-zio-interop-cats" % ZioVersion,
    ),
    addCompilerPlugin("org.spire-math" %% "kind-projector"     % "0.9.6"),
    addCompilerPlugin("com.olegpy"     %% "better-monadic-for" % "0.2.4"),
  )

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-language:higherKinds",
  "-language:postfixOps",
  "-feature",
  "-Ypartial-unification",
  "-Xfatal-warnings",
)
