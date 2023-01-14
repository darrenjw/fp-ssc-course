/*
ML-GA.scala
Stub for Scala Cats code
*/

import cats.*
import cats.implicits.*
import cats.effect.{IO, IOApp}

object CatsApp extends IOApp.Simple:

  val l = List(1,2) |+| List(3,4)

  def run = IO{ println(l) }

