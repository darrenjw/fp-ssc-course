# Scala parallel programming crash course

## Parallel collections

This simplest (but by no means the only) way to get started with parallel programming in Scala is using [parallel collections](https://docs.scala-lang.org/overviews/parallel-collections/overview.html).

Let's create some random data
```scala mdoc
val rng = scala.util.Random(42)
val v = Vector.fill(10)(rng.nextGaussian)
```
and pretend that we don't know the true mean. 



## Futures




## Effects

Futures are a powerful and flexible way to construct parallel and concurrent applications. However, they aren't a perfect fit to a pure functional approach to programming. The fact that futures "fire" as soon as they are created means that they have a *side-effect* (such as creating a thread), and that is potentially problematic. People have developed more principled functional effects systems for Scala, such as [Cats effect](https://typelevel.org/cats-effect/) with its IO monad. These provide better mechanisms for parallel and concurrent programming in Scala. They are, however, (well) beyond the scope of this course. 

