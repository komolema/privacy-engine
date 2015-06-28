organization := "ac.za.cput"

version := "0.3-SNAPSHOT"

scalaVersion := "2.11.6"

resolvers ++= Seq(
  "Maven Central Server" at "http://repo1.maven.org/maven2",
  "spray repo" at "http://repo.spray.io/",
  "sonatype releases" at "http://oss.sonatype.org/content/repositories/releases/",
  "sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
  "typesafe repo" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
  "org.osgi" % "org.osgi.core" % "5.0.0" % "provided",
  "org.apache.felix" % "org.apache.felix.framework" % "4.2.1" % "runtime",
  "org.squeryl" % "squeryl_2.11" % "0.9.5-7",
  "io.spray" % "spray-can_2.11" % "1.3.3",
  "io.spray" % "spray-routing_2.11" % "1.3.3",
  "io.spray" % "spray-json_2.11" % "1.3.2",
  "org.json4s" % "json4s-native_2.11" % "3.3.0.RC2",
  "org.json4s" % "json4s-core_2.11" % "3.3.0.RC2",
  "org.json4s" % "json4s-ast_2.11" % "3.3.0.RC2",
  "com.thoughtworks.paranamer" % "paranamer" % "2.7",
  "com.typesafe.akka" % "akka-actor_2.11" % "2.3.11",
  "com.typesafe.akka" % "akka-osgi_2.11" % "2.3.11"
)

osgiSettings

OsgiKeys.bundleActivator := Option("ac.za.cput.pe.api.activator.APIActivator")

OsgiKeys.importPackage := Seq(
  "sun.misc;resolution:=optional",
  "spray.*",
  "akka.*",
  "org.json4s.*",
  "*"
)


publishTo := Some(Resolver.file("file",
  new File(Path.userHome.absolutePath + "/.m2/repository")))

crossPaths := false