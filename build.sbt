name := """WareHouse"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "com.google.guava" % "guava" % "22.0",
  "com.adrianhurt" % "play-bootstrap-core_2.11" % "1.1-P25"
)
