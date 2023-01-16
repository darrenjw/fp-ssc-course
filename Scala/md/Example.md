# Running example

## ML for a logistic regression model using gradient ascent

### An interactive session

Here we will present an interactive Scala session for conducting maximum likelihood inference for our simple logistic regression model using a very naive gradient ascent algorithm. We will need to use the [Breeze](https://github.com/scalanlp/breeze/) library for numerical linear algebra, and we will also use [Smile](https://haifengl.github.io/) for a data frame object and CSV parser. The [sbt](https://www.scala-sbt.org/) project in the [Scala](../) directory has these dependencies (and a few others) preconfigured, so running `sbt console` from the Scala directory will give a REPL into which the following commands can be pasted.

We start with a few imports and a shorthand type declaration.
```scala
import breeze.linalg.*
import breeze.numerics.*
import smile.data.pimpDataFrame
import annotation.tailrec

type DVD = DenseVector[Double]
```

Next we use Smile to read and process the data.
```scala
val df = smile.read.csv("../pima.data", delimiter=" ", header=false)
// df: DataFrame = [V1: int, V2: int, V3: int, V4: int, V5: double, V6: double, V7: int, V8: String]
// +---+---+---+---+----+-----+---+---+
// | V1| V2| V3| V4|  V5|   V6| V7| V8|
// +---+---+---+---+----+-----+---+---+
// |  5| 86| 68| 28|30.2|0.364| 24| No|
// |  7|195| 70| 33|25.1|0.163| 55|Yes|
// |  5| 77| 82| 41|35.8|0.156| 35| No|
// |  0|165| 76| 43|47.9|0.259| 26| No|
// |  0|107| 60| 25|26.4|0.133| 23| No|
// |  5| 97| 76| 27|35.6|0.378| 52|Yes|
// |  3| 83| 58| 31|34.3|0.336| 25| No|
// |  1|193| 50| 16|25.9|0.655| 24| No|
// |  3|142| 80| 15|32.4|  0.2| 63| No|
// |  2|128| 78| 37|43.3|1.224| 31|Yes|
// +---+---+---+---+----+-----+---+---+
// 190 more rows...
// 
val y = DenseVector(df.select("V8").
  map(_(0).asInstanceOf[String]).
  map(s => if (s == "Yes") 1.0 else 0.0).toArray)
// y: DenseVector[Double] = DenseVector(0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0)
val x = DenseMatrix(df.drop("V8").toMatrix.toArray:_*)
// x: DenseMatrix[Double] = 5.0   86.0   68.0   28.0  30.2  0.364  24.0  
// 7.0   195.0  70.0   33.0  25.1  0.163  55.0  
// 5.0   77.0   82.0   41.0  35.8  0.156  35.0  
// 0.0   165.0  76.0   43.0  47.9  0.259  26.0  
// 0.0   107.0  60.0   25.0  26.4  0.133  23.0  
// 5.0   97.0   76.0   27.0  35.6  0.378  52.0  
// 3.0   83.0   58.0   31.0  34.3  0.336  25.0  
// 1.0   193.0  50.0   16.0  25.9  0.655  24.0  
// 3.0   142.0  80.0   15.0  32.4  0.2    63.0  
// 2.0   128.0  78.0   37.0  43.3  1.224  31.0  
// 0.0   137.0  40.0   35.0  43.1  2.288  33.0  
// 9.0   154.0  78.0   30.0  30.9  0.164  45.0  
// 1.0   189.0  60.0   23.0  30.1  0.398  59.0  
// 12.0  92.0   62.0   7.0   27.6  0.926  44.0  
// 1.0   86.0   66.0   52.0  41.3  0.917  29.0  
// 4.0   99.0   76.0   15.0  23.2  0.223  21.0  
// 1.0   109.0  60.0   8.0   25.4  0.947  21.0  
// 11.0  143.0  94.0   33.0  36.6  0.254  51.0  
// 1.0   149.0  68.0   29.0  29.3  0.349  42.0  
// 0.0   139.0  62.0   17.0  22.1  0.207  21.0  
// 2.0   99.0   70.0   16.0  20.4  0.235  27.0  
// 1.0   100.0  66.0   29.0  32.0  0.444  42.0  
// 4.0   83.0   86.0   19.0  29.3  0.317  34.0  
// 0.0   101.0  64.0   17.0  21.0  0.252  21.0  
// 1.0   87.0   68.0   34.0  37.6  0.401  24.0  
// 9.0   164.0  84.0   21.0  30.8  0.831  32.0  
// 1.0   99.0   58.0   10.0  25.4  0.551  21.0  
// 0.0   140.0  65.0   26.0  42.6  0.431  24.0  
// 5.0   108.0  72.0   43.0  36.1  0.263  33.0  
// 2.0   110.0  74.0   29.0  32.4  0.698  27.0  
// 1.0   79.0   60.0   42.0  43.5  0.678  23.0  
// 3.0   148.0  66.0   25.0  32.5  0.256  22.0  
// 0.0   121.0  66.0   30.0  34.3  0.203  33.0  
// 3.0   158.0  64.0   13.0  31.2  0.295  24.0  
// 2.0   105.0  80.0   45.0  33.7  0.711  29.0  
// 13.0  145.0  82.0   19.0  22.2  0.245  57.0  
// 1.0   79.0   80.0   25.0  25.4  0.583  22.0  
// 1.0   71.0   48.0   18.0  20.4  0.323  22.0  
// 0.0   102.0  86.0   17.0  29.3  0.695  27.0  
// 0.0   119.0  66.0   27.0  38.8  0.259  22.0  
// 8.0   176.0  90.0   34.0  33.7  0.467  58.0  
// 1.0   97.0   68.0   21.0  27.2  1.095  22.0  
// 4.0   129.0  60.0   12.0  27.5  0.527  31.0  
// 1.0   97.0   64.0   19.0  18.2  0.299  21.0  
// 0.0   86.0   68.0   32.0  35.8  0.238  25.0  
// 2.0   125.0  60.0   20.0  33.8  0.088  31.0  
// 5.0   123.0  74.0   40.0  34.1  0.269  28.0  
// 2.0   92.0   76.0   20.0  24.2  1.698  28.0  
// 3.0   171.0  72.0   33.0  33.3  0.199  24.0  
// ...
val ones = DenseVector.ones[Double](x.rows)
// ones: DenseVector[Double] = DenseVector(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0)
val X = DenseMatrix.horzcat(ones.toDenseMatrix.t, x)
// X: DenseMatrix[Double] = 1.0  5.0   86.0   68.0   28.0  30.2  0.364  24.0  
// 1.0  7.0   195.0  70.0   33.0  25.1  0.163  55.0  
// 1.0  5.0   77.0   82.0   41.0  35.8  0.156  35.0  
// 1.0  0.0   165.0  76.0   43.0  47.9  0.259  26.0  
// 1.0  0.0   107.0  60.0   25.0  26.4  0.133  23.0  
// 1.0  5.0   97.0   76.0   27.0  35.6  0.378  52.0  
// 1.0  3.0   83.0   58.0   31.0  34.3  0.336  25.0  
// 1.0  1.0   193.0  50.0   16.0  25.9  0.655  24.0  
// 1.0  3.0   142.0  80.0   15.0  32.4  0.2    63.0  
// 1.0  2.0   128.0  78.0   37.0  43.3  1.224  31.0  
// 1.0  0.0   137.0  40.0   35.0  43.1  2.288  33.0  
// 1.0  9.0   154.0  78.0   30.0  30.9  0.164  45.0  
// 1.0  1.0   189.0  60.0   23.0  30.1  0.398  59.0  
// 1.0  12.0  92.0   62.0   7.0   27.6  0.926  44.0  
// 1.0  1.0   86.0   66.0   52.0  41.3  0.917  29.0  
// 1.0  4.0   99.0   76.0   15.0  23.2  0.223  21.0  
// 1.0  1.0   109.0  60.0   8.0   25.4  0.947  21.0  
// 1.0  11.0  143.0  94.0   33.0  36.6  0.254  51.0  
// 1.0  1.0   149.0  68.0   29.0  29.3  0.349  42.0  
// 1.0  0.0   139.0  62.0   17.0  22.1  0.207  21.0  
// 1.0  2.0   99.0   70.0   16.0  20.4  0.235  27.0  
// 1.0  1.0   100.0  66.0   29.0  32.0  0.444  42.0  
// 1.0  4.0   83.0   86.0   19.0  29.3  0.317  34.0  
// 1.0  0.0   101.0  64.0   17.0  21.0  0.252  21.0  
// 1.0  1.0   87.0   68.0   34.0  37.6  0.401  24.0  
// 1.0  9.0   164.0  84.0   21.0  30.8  0.831  32.0  
// 1.0  1.0   99.0   58.0   10.0  25.4  0.551  21.0  
// 1.0  0.0   140.0  65.0   26.0  42.6  0.431  24.0  
// 1.0  5.0   108.0  72.0   43.0  36.1  0.263  33.0  
// 1.0  2.0   110.0  74.0   29.0  32.4  0.698  27.0  
// 1.0  1.0   79.0   60.0   42.0  43.5  0.678  23.0  
// 1.0  3.0   148.0  66.0   25.0  32.5  0.256  22.0  
// 1.0  0.0   121.0  66.0   30.0  34.3  0.203  33.0  
// 1.0  3.0   158.0  64.0   13.0  31.2  0.295  24.0  
// 1.0  2.0   105.0  80.0   45.0  33.7  0.711  29.0  
// 1.0  13.0  145.0  82.0   19.0  22.2  0.245  57.0  
// 1.0  1.0   79.0   80.0   25.0  25.4  0.583  22.0  
// 1.0  1.0   71.0   48.0   18.0  20.4  0.323  22.0  
// 1.0  0.0   102.0  86.0   17.0  29.3  0.695  27.0  
// 1.0  0.0   119.0  66.0   27.0  38.8  0.259  22.0  
// 1.0  8.0   176.0  90.0   34.0  33.7  0.467  58.0  
// 1.0  1.0   97.0   68.0   21.0  27.2  1.095  22.0  
// 1.0  4.0   129.0  60.0   12.0  27.5  0.527  31.0  
// 1.0  1.0   97.0   64.0   19.0  18.2  0.299  21.0  
// 1.0  0.0   86.0   68.0   32.0  35.8  0.238  25.0  
// 1.0  2.0   125.0  60.0   20.0  33.8  0.088  31.0  
// 1.0  5.0   123.0  74.0   40.0  34.1  0.269  28.0  
// 1.0  2.0   92.0   76.0   20.0  24.2  1.698  28.0  
// 1.0  3.0   171.0  72.0   33.0  33.3  0.199  24.0  
// ...
val p = X.cols
// p: Int = 8
```

Now `y` is our response variable and `X` is our covariate matrix, including an intercept column. Now we define the likelihood and some functions for gradient ascent. Note that the `ascend` function contains a tail-recursive function `go` that avoids the need for mutable variables and a "while loop", but is effectively equivalent.
```scala
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
```scala
val init = DenseVector(-9.8, 0.1, 0, 0, 0, 0, 1.8, 0)
// init: DenseVector[Double] = DenseVector(-9.8, 0.1, 0.0, 0.0, 0.0, 0.0, 1.8, 0.0)
ll(init)
// res0: Double = -566.3903911564223
val opt = ascend(oneStep(1e-6), init, verb=false)
// opt: DenseVector[Double] = DenseVector(-9.798616371360632, 0.10314432881260363, 0.032145673085756866, -0.00452855938919666, -0.001984121863541414, 0.08411858929117885, 1.801384805815113, 0.04114190402348266)
ll(opt)
// res1: Double = -89.19598966159712
```
Note how much the likelihood has improved relative to our initial guess.


### A standalone application

We can package the code above into a standalone Scala application, and this is available in the file [ML-GA.scala](../src/main/scala/ML-GA.scala). We can compile and run this application by typing `sbt run` from the Scala directory. Note that you must run `sbt` from the directory containing the [build.sbt](../build.sbt) file, not from the subdirectory containing the actual source code files. Make sure that you can run the application before proceding to the exercises.

### Hands-on exercise

Do some or all these exercises (or go back to previous exercises) as your interests dictate and time permits.

* Try manually tweaking the initial guess, the learning rate, the convergence tolerance and the maximum number of iterations to see how robust (or otherwise) this naive gradient ascent algorithm is to these tuning parameters.
* Improve on the naive ascent algorithm somewhow, perhaps by implementing [line search](https://en.wikipedia.org/wiki/Line_search) for choosing the step size.
* Note that Breeze has a bunch of utilities for optimisation, in the [breeze.optimise](https://github.com/scalanlp/breeze/wiki/Quickstart#breezeoptimize) package. See if you can figure out how to use them by messing around in the REPL. Then see if you can adapt the running example to use one of the methods. The [ScalaDoc](http://www.scalanlp.org/api/breeze/#breeze.optimize.package) may be useful.

