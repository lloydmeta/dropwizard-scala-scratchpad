name := "Dropwizard Scala scratchpad"

organization := "com.beachape"

version := "0.0.1"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.10" % "2.0" % "test" withSources() withJavadoc(),
  "org.scalacheck" %% "scalacheck" % "1.10.0" % "test" withSources() withJavadoc(),
  "com.massrelevance" %% "dropwizard-scala" % "0.6.2-1"
)

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)

initialCommands := "import com.beachape.dropwizardscalascratchpad._"

assemblySettings
