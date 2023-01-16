# Scala crash course

## Immutability

Scala is not a *pure* functional language, so it is *possible* to use *mutable variables*.

```scala mdoc
var v = 5
v = v + 1
v
```
But use of these is strongly discouraged in favour of *immutable values*.
```scala mdoc:fail
val x = 5
x = x + 1
```

## Immutable collections

We often want to work with collections of values of given type, and these are also immutable.
```scala mdoc
val vi = Vector(2, 4, 6, 3)
// now update value at position 2 to be 7
val viu = vi.updated(2, 7)
// original vector unchanged
vi
viu
```
The new vector is *effectively* an updated copy, but that doesn't mean that all of the data has been copied. We can point to data in the original vector safely, since it is immutable.

## Manipulating collections

Since collections are *functors*, we can *map* them.
```scala mdoc
vi.map(x => x*2)
vi map (x => x*2)
vi map (_*2)
```
We can also reduce them.
```scala mdoc
vi.foldLeft(0)(_+_)
vi.reduce(_+_)
vi.sum
```

