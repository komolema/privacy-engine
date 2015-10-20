val projectVersion = "0.3-SNAPSHOT"
val projectOrganisation = "ac.za.cput"
name := "Privacy Engine"

lazy val commonSettings = Seq(
  organization := projectOrganisation,
  version := projectVersion,
  scalaVersion := "2.11.1",
  publishTo := Some(Resolver.file("file",
    new File(Path.userHome.absolutePath + "/.m2/repository"))),
  resolvers ++= Seq(
    "Maven Central Server" at "http://repo1.maven.org/maven2",
    "spray repo" at "http://repo.spray.io/",
    "sonatype releases" at "http://oss.sonatype.org/content/repositories/releases/",
    "sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
    "typesafe repo" at "http://repo.typesafe.com/typesafe/releases/"
  ),
  crossPaths := false
)
lazy val root = project.in(file("."))
  .settings(commonSettings: _*)
  .aggregate(peModel, peApi)

lazy val peModel = project.in(file("pe-model"))
  .enablePlugins(SbtOsgi)
  .settings(Seq(
    name := "pe-model",
    libraryDependencies ++= Seq(
      "org.osgi" % "org.osgi.core" % "5.0.0" % "provided",
      "org.apache.felix" % "org.apache.felix.framework" % "4.2.1" % "runtime",
      "org.squeryl" % "squeryl_2.11" % "0.9.5-7",
      "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
      "cglib" % "cglib-nodep" % "3.1",
      "joda-time" % "joda-time" % "2.8.2"
    ),
    OsgiKeys.bundleActivator := Option("ac.za.cput.pe.model.activator.ModelActivator"),
    OsgiKeys.exportPackage := Seq("ac.za.cput.pe.model.schema", "ac.za.cput.pe.model.domain"),
    OsgiKeys.importPackage := Seq(
      "sun.misc;resolution:=optional",
      "org.postgresql",
      "org.squeryl.*",
      "net.sf.cglib.asm;resolution:=optional",
      "net.sf.cglib.asm.signature;resolution:=optional",
      "net.sf.cglib.beans;resolution:=optional",
      "net.sf.cglib.core;resolution:=optional",
      "net.sf.cglib.proxy;resolution:=optional",
      "net.sf.cglib.reflect;resolution:=optional",
      "net.sf.cglib.transform;resolution:=optional",
      "net.sf.cglib.transform.impl;resolution:=optional",
      "net.sf.cglib.util;resolution:=optional",
      "*"
    )) ++ commonSettings)
  .settings(osgiSettings)

lazy val peApi = project.in(file("pe-api"))
  .enablePlugins(SbtOsgi)
  .settings(commonSettings ++ Seq(
    name := "pe-api",
    libraryDependencies ++= Seq(
      "org.osgi" % "org.osgi.core" % "5.0.0" % "provided",
      "org.apache.felix" % "org.apache.felix.framework" % "4.2.1" % "runtime",
      "javax.annotation" % "javax.annotation-api" % "1.2",
      "log4j" % "log4j" % "1.2.17",
      "org.slf4j" % "slf4j-api" % "1.6.1",
      "org.slf4j" % "slf4j-log4j12" % "1.6.1",
      "org.apache.cxf" % "cxf-rt-rs-client" % "3.1.3" % "provided",
      "org.apache.cxf" % "cxf-rt-transports-http" % "3.1.3" % "provided",
      "org.apache.cxf" % "cxf-rt-transports-http-jetty" % "3.1.3" % "provided",
      "org.apache.cxf" % "cxf-rt-frontend-jaxws" % "3.1.3" % "provided",
      "org.apache.cxf" % "cxf-rt-frontend-jaxrs" % "3.1.3" % "provided",
      "org.apache.cxf" % "cxf-rt-databinding-jaxb" % "3.1.3" % "provided",
      "com.sun.xml.fastinfoset" % "FastInfoset" % "1.2.13",
      "org.jvnet.staxex" % "stax-ex" % "1.7.7"
    ),
    OsgiKeys.bundleActivator := Option("ac.za.cput.pe.api.activator.APIActivator"),
    OsgiKeys.importPackage := Seq(
      "sun.misc;resolution:=optional",
      "javax.ws.*",
      "*"
    )
  ))
.settings(osgiSettings)

