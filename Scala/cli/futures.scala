//> using scala 3.3.0
//> using dep org.typelevel::cats-core:2.10.0

/*
futures.scala

Simple app illustrating the use of Futures

*/

import cats.*
import cats.syntax.all.*
import scala.concurrent.*
import scala.util.Success
import ExecutionContext.Implicits.global
import scala.concurrent.duration.*

object FuturesApp:

  val rng = scala.util.Random(42)

  def ll0(mu: Double)(x: Double): Double = -(x - mu)*(x - mu)/2.0

  def ll(mu: Double)(x: Double): Double =
    Thread.sleep(500)
    ll0(mu)(x)

  @main
  def run() =
    val v = Vector.fill(10)(rng.nextGaussian)
    println("Evalulating ll using Futures")
    val vf = v map (x => Future(ll(0.0)(x)))
    val lf = vf.sequence map (_ reduce (_+_))
    lf.onComplete {
      case Success(l) => println(l)
      case _ => println("Computation failed")
    }
    Await.result(lf, 2.seconds)
    Thread.sleep(1000)
