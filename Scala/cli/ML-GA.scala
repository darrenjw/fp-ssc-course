//> using scala 3.1.2
//> using dep org.scalanlp::breeze:2.1.0
//> using dep com.github.haifengl:smile-scala_2.13:3.0.2

/*
ML-GA.scala

Simple gradient ascent algorithm for maximum likelihood estimation
of a logistic regression model, applied to the Pima data

*/

import breeze.linalg.*
import breeze.numerics.*
import smile.data.pimpDataFrame
import annotation.tailrec

type DVD = DenseVector[Double]

object GradientAscent:


  @main def run() =
    println("First read and process the data")
    val df = smile.read.csv("../../pima.data", delimiter=" ", header=false)
    print(df)
    val y = DenseVector(df.select("V8").
      map(_(0).asInstanceOf[String]).
      map(s => if (s == "Yes") 1.0 else 0.0).toArray)
    println(y)
    val x = DenseMatrix(df.drop("V8").toMatrix.toArray:_*)
    println(x)
    val ones = DenseVector.ones[Double](x.rows)
    val X = DenseMatrix.horzcat(ones.toDenseMatrix.t, x)
    println(X)
    val p = X.cols
    println(p)

    println("Now define log likelihood and gradient")
    def ll(beta: DVD): Double =
      sum(-log(ones + exp(-1.0*(2.0*y - ones)*:*(X * beta))))
    def gll(beta: DVD): DVD =
      (X.t)*(y - ones/:/(ones + exp(-X*beta)))

    println("Now define functions for gradient ascent")
    def oneStep(learningRate: Double)(b0: DVD): DVD =
      b0 + learningRate*gll(b0)
    def ascend(step: DVD => DVD, init: DVD, maxIts: Int = 10000,
        tol: Double = 1e-8, verb: Boolean = true): DVD =
      @tailrec def go(b0: DVD, ll0: Double, itsLeft: Int): DVD =
        if (verb)
          println(s"$itsLeft : $ll0")
        val b1 = step(b0)
        val ll1 = ll(b1)
        if ((math.abs(ll0 - ll1) < tol)|(itsLeft < 1))
          b1
        else
          go(b1, ll1, itsLeft - 1)
      go(init, ll(init), maxIts)

    println("Now run a simple gradient ascent algorithm")
    // Better choose a reasonable init as gradient ascent is terrible...
    val init = DenseVector(-9.8, 0.1, 0, 0, 0, 0, 1.8, 0)
    val opt = ascend(oneStep(1e-6), init)
    println("Inits: " + init)
    println("Init ll: " + ll(init))
    println("Opt: " + opt)
    println("Opt ll: " + ll(opt))
    println("Goodbye.")

