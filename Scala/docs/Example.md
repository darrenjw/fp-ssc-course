# Running example

## ML for a logistic regression model using gradient ascent

### An interactive session

Here we will present an interactive Scala session for conducting maximum likelihood inference for our simple logistic regression model using a very naive gradient ascent algorithm. We will need to use the [Breeze](https://github.com/scalanlp/breeze/) library for numerical linear algebra, and we will also use [Smile](https://haifengl.github.io/) for a data frame object and CSV parser. The [sbt](https://www.scala-sbt.org/) project in the [Scala](../) directory has these dependencies (and a few others) preconfigured, so running `sbt console` from the Scala directory will give a REPL into which the following commands can be pasted.

We start with a few imports and a shorthand type declaration.
```scala mdoc
import breeze.linalg.*
import breeze.numerics.*
import smile.data.pimpDataFrame
import annotation.tailrec

type DVD = DenseVector[Double]
```

Next we use Smile to read and process the data.
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

Now `y` is our response variable and `X` is our covariate matrix, including an intercept column. Now we define the likelihood and some functions for gradient ascent. Note that the `ascend` function contains a tail-recursive function `go` that avoids the need for mutable variables and a "while loop", but is effectively equivalent.
```scala mdoc
def ll(beta: DVD): Double =
  sum(-log(ones + exp(-1.0*(2.0*y - ones)*:*(X * beta))))

def gll(beta: DVD): DVD =
  (X.t)*(y - ones/:/(ones + exp(-X*beta)))

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

Now let's run the gradient ascent algorithm, starting from a reasonable initial guess, since naive gradient ascent is terrible.
```scala mdoc
val init = DenseVector(-9.8, 0.1, 0, 0, 0, 0, 1.8, 0)
ll(init)
val opt = ascend(oneStep(1e-6), init, verb=false)
ll(opt)
```
Note how much the likelihood has improved relative to our initial guess.


### A standalone application

We can package the code above into a standalone Scala application, and this is available in the file [ML-GA.scala](../src/main/scala/ML-GA.scala). We can compile and run this application by typing `sbt run` from the Scala directory. Note that you must run `sbt` from the directory containing the [build.sbt](../build.sbt) file, not from the subdirectory containing the actual source code files. Make sure that you can run the application before proceding to the exercises.

### Hands-on exercise


