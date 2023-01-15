# Running example

## ML for a logistic regression model using gradient ascent

Some imports and a type declaration.
```scala mdoc
import breeze.linalg.*
import breeze.numerics.*
import smile.data.pimpDataFrame
import annotation.tailrec

type DVD = DenseVector[Double]
```
Next read and process the data.
```scala mdoc
val df = smile.read.csv("../pima.data", delimiter=" ", header=false)
val y = DenseVector(df.select("V8").
  map(_(0).asInstanceOf[String]).
  map(s => if (s == "Yes") 1.0 else 0.0).toArray)
val x = DenseMatrix(df.drop("V8").toMatrix.toArray:_*)
val ones = DenseVector.ones[Double](x.rows)
val X = DenseMatrix.horzcat(ones.toDenseMatrix.t, x)
val p = X.cols
```
Now define the likelihood and some functions for gradient ascent.
```scala mdoc
def ll(beta: DVD): Double =
  sum(-log(ones + exp(-1.0*(2.0*y - ones)*:*(X * beta))))

def gll(beta: DVD): DVD =
  (X.t)*(y - ones/:/(ones + exp(-X*beta)))

println("Now define a functions for gradient ascent")

def oneStep(learningRate: Double)(b0: DVD): DVD =
  b0 + learningRate*gll(b0)

def ascend(step: DVD => DVD, init: DVD, maxIts: Int = 10000,
    tol: Double = 1e-8, verb: Boolean = true): DVD =
  @tailrec def go(b0: DVD, ll0: Double, itsLeft: Int): DVD = {
	if (verb)
	  println(s"$itsLeft : $ll0")
	val b1 = step(b0)
	val ll1 = ll(b1)
	if ((math.abs(ll0 - ll1) < tol)|(itsLeft < 1))
	  b1
	else
	  go(b1, ll1, itsLeft - 1)
	}
  go(init, ll(init), maxIts)
```
Now let's run the gradient ascent algorithm.
```scala mdoc
val init = DenseVector(-9.8, 0.1, 0, 0, 0, 0, 1.8, 0)
val opt = ascend(oneStep(1e-6), init, verb=false)
ll(opt)
```
