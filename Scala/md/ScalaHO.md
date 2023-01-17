# Scala hands-on

## Scastie

You can start experimenting with Scala in the browser without installing any software. Go to [Scastie](https://scastie.scala-lang.org/), and start entering code snippets. Make sure that you have "Worksheet" mode selected (green dot next to "Worksheet"). Note that you can configure Scastie to use different versions of Scala, and to add dependencies on third party libraries. Figuring out how to do this is left as an exercise.

Paste the following into the Scastie worksheet:
```scala
def logFactTR(n: Int, acc: Double = 0.0): Double =
  if (n <= 1) acc else
  logFactTR(n - 1, math.log(n) + acc)
  
logFactTR(100000)
```
and then click on "Run".

In the crash course we glossed over the different styles of Scala syntax. Prior to Scala 3, Scala required braces to delimit blocks, like C, Java, R, etc. In Scala 3, braceless syntax (with significant whitespace) is optionally allowed (like Python and Haskell). So we can write functions like:
```scala
def logFactTRB(n: Int, acc: Double = 0.0): Double = {
  if (n <= 1) acc else
  logFactTR(n - 1, math.log(n) + acc)
}
  
logFactTRB(100000)
```
I prefer the new braceless syntax, but a lot of existing tooling works better with the old braced syntax. It's really just a matter of preference, but it's best to stick with one style or the other as far as possible.

## sbt

If you have `sbt` installed, you can use this to create, build and run Scala projects.

There is an `sbt` project in the [Scala](../) directory of this code repository. This is indicated by the presence of a [build.sbt](../build.sbt) file, which contains most of the configuration info related to the project.

From the directory containing the build file, running `sbt console` will give a Scala REPL.

Try pasting one of the above snippets into the REPL and make sure it works.






