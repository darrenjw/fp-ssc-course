# Scala crash course

## Immutability

Scala is not a *pure* functional language, so it is *possible* to use *mutable variables*.

```scala
var v = 5
// v: Int = 5
v = v + 1
v
// res1: Int = 6
```
But use of these is strongly discouraged in favour of *immutable values*.
```scala
val x = 5
x = x + 1
// error:
// Reassignment to val x
// val viu = vi.updated(2, 7)
//        ^
```

## Immutable collections

We often want to work with collections of values of given type, and these are also immutable.
```scala
val vi = Vector(2, 4, 6, 3)
// vi: Vector[Int] = Vector(2, 4, 6, 3)
// now update value at position 2 to be 7
val viu = vi.updated(2, 7)
// viu: Vector[Int] = Vector(2, 4, 7, 3)
// original vector unchanged
vi
// res3: Vector[Int] = Vector(2, 4, 6, 3)
viu
// res4: Vector[Int] = Vector(2, 4, 7, 3)
```
The new vector is *effectively* an updated copy, but that doesn't mean that all of the data has been copied. We can point to data in the original vector safely, since it is immutable.

## Manipulating collections

Since collections are *functors*, we can *map* them.
```scala
vi.map(x => x*2)
// res5: Vector[Int] = Vector(4, 8, 12, 6)
vi map (x => x*2)
// res6: Vector[Int] = Vector(4, 8, 12, 6)
vi map (_*2)
// res7: Vector[Int] = Vector(4, 8, 12, 6)
```
We can also reduce them
```scala
vi.foldLeft(0)(_+_)
// res8: Int = 15
vi.reduce(_+_)
// res9: Int = 15
vi.sum
// res10: Int = 15
```

