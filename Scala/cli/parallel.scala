//> using scala 3.3.0
//> using dep org.scala-lang.modules::scala-parallel-collections:1.0.4

/*
parallel.scala

Simple app illustrating the use of parallel collections

*/

import scala.collection.parallel.CollectionConverters._

object ParallelApp:

  val rng = scala.util.Random(42)

  def ll0(mu: Double)(x: Double): Double = -(x - mu)*(x - mu)/2.0

  def ll(mu: Double)(x: Double): Double =
    Thread.sleep(500)
    ll0(mu)(x)

  @main
  def run() =
    val v = Vector.fill(10)(rng.nextGaussian)
    println("Computing ll sequentially")
    println((v map ll(0.0)) reduce (_+_))
    val vp = v.par // convert v to a ParVector
    println("Computing ll in parallel")
    println((vp map ll(0.0)) reduce (_+_))
    println("Done")    
