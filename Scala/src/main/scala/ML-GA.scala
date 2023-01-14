/*
ML-GA.scala

Simple gradient ascent algorithm for maximum likelihood estimation
of a logistic regression model, applied to the Pima data

*/

import breeze.linalg.*
import breeze.numerics.*
import smile.data.pimpDataFrame

type DVD = DenseVector[Double]

object GradientAscent:


  @main def run() =
    println("First read and process the data")
    val df = smile.read.csv("../pima.data", delimiter=" ", header=false)
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



    println("Goodbye.")

