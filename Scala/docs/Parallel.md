# Scala parallel programming crash course

## Parallel collections

This simplest (but by no means the only) way to get started with parallel programming in Scala is using [parallel collections](https://docs.scala-lang.org/overviews/parallel-collections/overview.html).

Let's create some random data:
```scala mdoc
val rng = scala.util.Random(42)
val v = Vector.fill(10)(rng.nextGaussian)
```
and pretend that we don't know the true mean. We can define a simple log likelihood function as
```scala mdoc
def ll0(mu: Double)(x: Double): Double = -(x - mu)*(x - mu)/2.0
```
But now to mimic the computation of a very expensive likelihood, we can artificially slow it down.
```scala mdoc
def ll(mu: Double)(x: Double): Double =
  Thread.sleep(500)
  ll0(mu)(x)
```
So now the likelihood evaluation associated with every observation will take at least half a second.

We can evaluate the likelihood for our vector of observations (at 0) as follows:
```scala mdoc
(v map ll(0.0)) reduce (_+_)
```
and this will take at least 5 seconds. However, if we convert the vector to a parallel collection
```scala mdoc
import scala.collection.parallel.CollectionConverters.*
val vp = v.par // convert v to a ParVector
(vp map ll(0.0)) reduce (_+_)
```
the likelihood evaluation will be much quicker, since the `map` operation parallelises "perfectly", and the `reduce` operation can be evaluated in parallel with tree reduction. Scala automatically implements these parallelisations on parallel collections. Note that no change is required to the code - you just switch a regular (serial) collection to a parallel collection, and the library takes care of the rest.


## Futures

Many, if not most, parallel computation operations that arise in statistical computing and machine learning can be formulated in terms of operations on parallel collections. However, we sometimes need more control over the way that computations are evaluated and combined in parallel. A standard approach to this problem in many functional programming languages is to use some kind of `Future` monad. Here we will illustrate how to use Scala's built-in `Future` to do the same parallel computation as above, but without relying on parallel collections.

We start with a few imports.
```scala mdoc
import cats.*
import cats.syntax.all.*
import scala.concurrent.*
import scala.util.Success
import ExecutionContext.Implicits.global
import scala.concurrent.duration.*
```




## Effects

Futures are a powerful and flexible way to construct parallel and concurrent applications. However, they aren't a perfect fit to a pure functional approach to programming. The fact that futures "fire" as soon as they are created means that they have a *side-effect* (such as creating a thread), and that is potentially problematic. People have developed more principled functional effects systems for Scala, such as [Cats effect](https://typelevel.org/cats-effect/) with its IO monad. These provide better mechanisms for parallel and concurrent programming in Scala. They are, however, (well) beyond the scope of this course. 

