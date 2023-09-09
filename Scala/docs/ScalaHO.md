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
  logFactTRB(n - 1, math.log(n) + acc)
}
  
logFactTRB(100000)
```
I prefer the new braceless syntax, but a lot of existing tooling works better with the old braced syntax. It's really just a matter of preference, but it's best to stick with one style or the other as far as possible.

## scala-cli

If you have `scala-cli` installed, you can use this to build and run small (typically one file) Scala applications. Required dependencies can be specified in the header of the script.

[logFact.scala](../cli/logFact.scala) is a simple stand-alone application to compute a log-factorial. It can be run with
```bash
scala-cli logFact.scala -- 10000
```

[ML-GA.scala](../cli/ML-GA.scala) is the gradient-ascent example. Note the external dependencies specified at the start of the script. It can be run with
```bash
scala-cli ML-GA.scala
```

Make sure that you are able to run these scripts before proceeding.

`scala-cli` can also be used to build multi-file applications, but for any non-trivial project, it is probably better to use `sbt`.

## sbt

If you have `sbt` installed, you can use this to create, build and run Scala projects.

### Using an existing project

There is an `sbt` project in the [Scala](../) directory of this code repository. This is indicated by the presence of a [build.sbt](../build.sbt) file, which contains most of the configuration info related to the project.

From the directory containing the build file, running `sbt console` will give a Scala REPL.

Try pasting one of the above snippets into the REPL and make sure it works.

Exit the console (Ctrl-D), and type `sbt run` to build and run the application assocaited with the project. We will study this application later.

Note that `sbt` is really designed to be run interactively. So, just typing `sbt` will lead to an `sbt` prompt (which is different from a Scala REPL prompt). Then, typing `console` from the `sbt` prompt will give a Scala REPL, `compile` will compile the project, `run` will compile and run the project, `test` will run any test suites, etc. Also note that `sbt` can "watch" the project directory for file changes. Use a `~` as a prefix to watch for changes. eg. typing `~compile` will watch for file changes and recompile the application (incrementally) whenever a file is saved.

### Creating a new project

`sbt` expects projects to be configured in a particular way, with certain files in certain directories. `sbt` can itself be used to construct project templates with the correct structure. Different templates are suitable for different kinds of projects. eg.
```bash
sbt new darrenjw/breeze.g8
```
will create a new project in a new directory inside the current directory, using the template provided in the `breeze.g8` repo in the GitHub account of `darrenjw`. This is a template for a project with fairly minimal dependencies on the [Breeze](https://github.com/scalanlp/breeze) library for numerical computing. I have some other templates. eg. `fps.g8` contains dependencies on libraries commonly used for pure functional programming in Scala, `scala-glm.g8` has a dependency on my regression modelling library, etc.

Create a new `sbt` project using one of the templates mentioned, and explore it.

Create an `sbt` application that prints to the console the log-factorial of 10000.

