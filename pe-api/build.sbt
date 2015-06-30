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
  "javax.annotation" % "javax.annotation-api" % "1.2",
  "log4j" % "log4j" % "1.2.17",
  "org.slf4j" % "slf4j-api" % "1.6.1",
  "org.slf4j" % "slf4j-log4j12" % "1.6.1",
  "org.apache.cxf" % "cxf-rt-rs-client" % "3.0.4",
  "org.apache.cxf" % "cxf-rt-transports-http" % "3.0.4",
  "org.apache.cxf" % "cxf-rt-transports-http-jetty" % "3.0.4",
  "org.apache.cxf" % "cxf-rt-frontend-jaxws" % "3.0.4",
  "org.apache.cxf" % "cxf-rt-frontend-jaxrs" % "3.0.4",
  "org.apache.cxf" % "cxf-rt-databinding-jaxb" % "3.0.4",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.5.4",
  "com.fasterxml.jackson.core" % "jackson-annotations" % "2.5.4",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.5.4"

)

osgiSettings

OsgiKeys.bundleActivator := Option("ac.za.cput.pe.api.activator.APIActivator")

OsgiKeys.importPackage := Seq(
  "sun.misc;resolution:=optional",
  "javax.ws.*",
  "org.apache.cxf.*",
  "*"
)


publishTo := Some(Resolver.file("file",
  new File(Path.userHome.absolutePath + "/.m2/repository")))

crossPaths := false