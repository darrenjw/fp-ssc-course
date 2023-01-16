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

## Writing functions

Here's a simple definition of a log-factorial function.
```scala mdoc
def logFact(n: Int): Double =
  (1 to n).map(_.toDouble).map(math.log).sum
  
logFact(3)
logFact(10)
logFact(100000)
```
This requires creating a collection of size `n`, which might not be desirable.

We will use the log-factorial function to explore the use of recursion instead of more imperative looping constructs.


## Recursive functions

```scala mdoc
def logFactR(n: Int): Double =
  if (n <= 1) 0.0 else
  math.log(n) + logFactR(n - 1)

logFactR(3)
logFactR(10)
```
This function is recursive, but not tail-recursive since the result of the recursive call (`logFactR(n - 1)`) is modified before the correct value is returned. So, although it doesn't consume heap space, it consumes stack space, which is worse. That is, this function will stack-overflow if evaluated at a large enough input value.
```scala mdoc:crash
logFactR(100000)
```

## Tail-recursive functions

```scala mdoc
@annotation.tailrec
final def logFactTR(n: Int, acc: Double = 0.0): Double =
  if (n <=1) acc else
  logFactTR(n - 1, math.log(n) + acc)
  
logFactTR(3)
logFactTR(10)
logFactTR(100000)
```
This version consumes neither heap nor stack space. The `tailrec` annotation is optional, but is useful, since it forces the compiler to flag an error if there is some reason why the tail call elimination can not be performed (eg. here, the method needed to be decalared `final` so it could not be over-ridden).
