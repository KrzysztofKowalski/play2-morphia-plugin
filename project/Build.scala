import sbt._
import Keys._

object Play2MorphiaPluginBuild extends Build {

  import Resolvers._
  import Dependencies._
  import BuildSettings._

  lazy val Play2MorphiaPlugin = Project(
    "play2-morphia-plugin",
    file("."),
    settings = buildSettings ++ Seq(
      libraryDependencies := runtime ++ test,
      publishMavenStyle := true,
      publishTo := Some(githubRepository),
      scalacOptions ++= Seq("-Xlint", "-deprecation", "-unchecked", "-encoding", "utf8"),
      javacOptions ++= Seq("-source", "1.7", "-encoding", "utf8"),
      resolvers ++= Seq(DefaultMavenRepository, Resolvers.typesafeRepository),
      checksums := Nil // To prevent proxyToys downloding fails https://github.com/leodagdag/play2-morphia-plugin/issues/11
    )
  ).settings()

  object Resolvers {
    val githubRepository = Resolver.file("GitHub Repository", Path.userHome / "Development" / "vannik-software" / "vanniksoftware.github.com" / "repository" asFile)(Resolver.ivyStylePatterns)
    val dropboxReleaseRepository = Resolver.file("Dropbox Repository", Path.userHome / "Dropbox" / "Public" / "repository" / "releases" asFile)
    val dropboxSnapshotRepository = Resolver.file("Dropbox Repository", Path.userHome / "Dropbox" / "Public" / "repository" / "snapshots" asFile)
    val typesafeRepository = "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
  }

  object Dependencies {
    val runtime = Seq(
      "org.mongodb.morphia" % "morphia" % "0.105",
      "org.mongodb.morphia" % "morphia-logging-slf4j" % "0.105",
      "org.mongodb.morphia" % "morphia-validation" % "0.105",
      "org.mongodb" % "mongo-java-driver" % "2.11.0",
      "com.typesafe.play" %% "play-java" % "2.2.1" % "provided"
    )
    val test = Seq(
      "com.typesafe.play" %% "play-test" % "2.2.1" % "provided"
    )
  }

  object BuildSettings {
    val buildOrganization = "k7k"
    val buildVersion = "0.0.18"
    val buildScalaVersion = "2.10.2"
    val buildSbtVersion = "0.13.0"
    val buildSettings = Defaults.defaultSettings ++ Seq(
      organization := buildOrganization,
      version := buildVersion,
      scalaVersion := buildScalaVersion
    )
  }
}
