//> using scala 3.3.0

/*

logFact.scala

Simple stand-alone application script with no external dependencies

Run with:
scala-cli logFact.scala -- <n>
where <n> is the number you want the log-factorial of.

*/

object LogFactApp:

  @annotation.tailrec
  def logFact(n: Int, acc: Double = 0.0): Double =
    if (n <= 1) acc else
    logFact(n - 1, math.log(n) + acc)

  @main
  def run(n: Int) = 
    println(logFact(n))

// eof


