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
Note that `map` and `reduce` are higher-order functions (HoFs), since they accept a function as an argument.

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
```scala
logFactR(100000)
// java.lang.StackOverflowError
```

## Tail-recursive functions

```scala mdoc
@annotation.tailrec
final def logFactTR(n: Int, acc: Double = 0.0): Double =
  if (n <= 1) acc else
  logFactTR(n - 1, math.log(n) + acc)
  
logFactTR(3)
logFactTR(10)
logFactTR(100000)
```
This version consumes neither heap nor stack space. The `tailrec` annotation is optional, but is useful, since it forces the compiler to flag an error if there is some reason why the tail call elimination can not be performed (eg. here, the method needed to be decalared `final` so it could not be over-ridden).

## Helper functions

The previous example made use of the fact that Scala has optional arguments with default values. Even if this wasn't the case, we could acheive the same thing by embedding the two-argument version as a private function inside the one-argument version.
```scala mdoc
def logFactTRH(n: Int): Double =
  def go(n: Int, acc: Double): Double =
    if (n <= 1) acc else
    go(n - 1, math.log(n) + acc)
  go(n, 0.0)
  
logFactTRH(3)
logFactTRH(10)
logFactTRH(100000)
```

## Curried functions

Sometimes we want to partially apply a function by providing some of the arguments. We can flag this by grouping them.
```scala mdoc
def linFun(m: Double, c: Double)(x: Double): Double =
  m*x + c

val f = linFun(2, 3)

f(0)
f(1)
f(2)
```
Since the output of the partial call is a function, this is another example of a HoF.
