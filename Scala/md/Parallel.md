# Scala parallel programming crash course

## Parallel collections

This simplest (but by no means the only) way to get started with parallel programming in Scala is using [parallel collections](https://docs.scala-lang.org/overviews/parallel-collections/overview.html).

Let's create some random data
```scala
val rng = scala.util.Random(42)
// rng: Random = scala.util.Random@4129ef67
val v = Vector.fill(10)(rng.nextGaussian)
// v: Vector[Double] = Vector(
//   1.1419053154730547,
//   0.9194079489827879,
//   -0.9498666368908959,
//   -1.1069902863993377,
//   0.2809776380727795,
//   0.6846227956326554,
//   -0.8172214073987268,
//   -1.3966434026780434,
//   -0.19094451307087512,
//   1.4862133923906502
// )
```
and pretend that we don't know the true mean. We can define a simple log likelihood function as
```scala
def ll0(mu: Double)(x: Double): Double = -(x - mu)*(x - mu)/2.0
```
But now to mimic the computation of a very expensive likelihood, we can artificially slow it down.
```scala
def ll(mu: Double)(x: Double): Double =
  Thread.sleep(500)
  ll0(mu)(x)
```
So now the likelihood evaluation associated with every observation will take at least half a second.

We can evaluate the likelihood for our vector of observations (at 0) as follows:
```scala
(v map ll(0.0)) reduce (_+_)
// res0: Double = -4.844171665682075
```
and this will take at least 5 seconds. However, if we convert the vector to a parallel collection
```scala
import scala.collection.parallel.CollectionConverters.*
val vp = v.par // convert v to a ParVector
// vp: ParVector[Double] = ParVector(1.1419053154730547, 0.9194079489827879, -0.9498666368908959, -1.1069902863993377, 0.2809776380727795, 0.6846227956326554, -0.8172214073987268, -1.3966434026780434, -0.19094451307087512, 1.4862133923906502) // convert v to a ParVector
(vp map ll(0.0)) reduce (_+_)
// res1: Double = -4.844171665682075
```
the likelihood evaluation will be much quicker, since the map operation parallelises perfectly, and the reduce operation can be evaluated in parallel with tree reduction.

## Futures




## Effects

Futures are a powerful and flexible way to construct parallel and concurrent applications. However, they aren't a perfect fit to a pure functional approach to programming. The fact that futures "fire" as soon as they are created means that they have a *side-effect* (such as creating a thread), and that is potentially problematic. People have developed more principled functional effects systems for Scala, such as [Cats effect](https://typelevel.org/cats-effect/) with its IO monad. These provide better mechanisms for parallel and concurrent programming in Scala. They are, however, (well) beyond the scope of this course. 

