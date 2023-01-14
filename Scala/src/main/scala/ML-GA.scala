/*
Stub.scala
Stub for Scala Breeze code
*/

object Stub:

  import breeze.stats.distributions.Rand.VariableSeed.randBasis

  def main(args: Array[String]): Unit =
    println(breeze.stats.distributions.Poisson(10).sample(5))

