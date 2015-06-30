organization := "ac.za.cput"

version := "0.3-SNAPSHOT"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "org.osgi" % "org.osgi.core" % "5.0.0" % "provided",
  "org.apache.felix" % "org.apache.felix.framework" % "4.2.1" % "runtime",
  "org.squeryl" % "squeryl_2.11" % "0.9.5-7",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
)

resolvers += "Maven Central Server" at "http://repo1.maven.org/maven2"

osgiSettings

OsgiKeys.bundleActivator := Option("ac.za.cput.pe.model.activator.ModelActivator")

OsgiKeys.exportPackage := Seq("ac.za.cput.pe.model.schema", "ac.za.cput.pe.model.domain")


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
)

publishTo := Some(Resolver.file("file",
  new File(Path.userHome.absolutePath + "/.m2/repository")))

crossPaths := false



