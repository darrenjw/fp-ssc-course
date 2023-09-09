# Scala parallel programming crash course

## Parallel collections

This simplest (but by no means the only) way to get started with parallel programming in Scala is using Scala [parallel collections](https://docs.scala-lang.org/overviews/parallel-collections/overview.html).

```scala
val rng = scala.util.Random(42)
// rng: Random = scala.util.Random@6aa6c315
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
v
// res0: Vector[Double] = Vector(
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



## Futures




## Effects

Futures are a powerful and flexible way to construct parallel and concurrent applications. However, they aren't a perfect fit to a pure functional approach to programming. The fact that futures "fire" as soon as they are created means that they have a *side-effect*, and that is potentially problematic. People have developed more principled, functional effects systems for Scala, such as the [Cats effect](https://typelevel.org/cats-effect/) IO monad, and these provide better mechanisms for parallel and concurrent programming in Scala. They are, however, (well) beyond the scope of this course. 

