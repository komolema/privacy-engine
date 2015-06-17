organization := "ac.za.cput"

version := "0.3-SNAPSHOT"

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  "org.osgi" % "org.osgi.core" % "5.0.0" % "provided",
  "org.apache.felix" % "org.apache.felix.framework" % "4.2.1" % "runtime",
  "org.squeryl" % "squeryl_2.11" % "0.9.5-7"
)

resolvers += "Maven Central Server" at "http://repo1.maven.org/maven2"

osgiSettings

publishTo := Some(Resolver.file("file",
  new File(Path.userHome.absolutePath + "/.m2/repository")))

crossPaths := false