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
// def linFun(m: Double, c: Double)(x: Double): Double =
//                                    ^
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
We can also reduce them.
```scala
vi.foldLeft(0)(_+_)
// res8: Int = 15
vi.reduce(_+_)
// res9: Int = 15
vi.sum
// res10: Int = 15
```
Note that `map` and `reduce` are higher-order functions (HoFs), since they accept a function as an argument.

## Writing functions

Here's a simple definition of a log-factorial function.
```scala
def logFact(n: Int): Double =
  (1 to n).map(_.toDouble).map(math.log).sum
  
logFact(3)
// res11: Double = 1.791759469228055
logFact(10)
// res12: Double = 15.104412573075518
logFact(100000)
// res13: Double = 1051299.2218991187
```
This requires creating a collection of size `n`, which might not be desirable.

We will use the log-factorial function to explore the use of recursion instead of more imperative looping constructs.

## Recursive functions

```scala
def logFactR(n: Int): Double =
  if (n <= 1) 0.0 else
  math.log(n) + logFactR(n - 1)

logFactR(3)
// res14: Double = 1.791759469228055
logFactR(10)
// res15: Double = 15.104412573075518
```
This function is recursive, but not tail-recursive since the result of the recursive call (`logFactR(n - 1)`) is modified before the correct value is returned. So, although it doesn't consume heap space, it consumes stack space, which is worse. That is, this function will stack-overflow if evaluated at a large enough input value.
```scala
logFactR(100000)
// java.lang.StackOverflowError
```

## Tail-recursive functions

```scala
@annotation.tailrec
final def logFactTR(n: Int, acc: Double = 0.0): Double =
  if (n <= 1) acc else
  logFactTR(n - 1, math.log(n) + acc)
  
logFactTR(3)
// res16: Double = 1.791759469228055
logFactTR(10)
// res17: Double = 15.104412573075514
logFactTR(100000)
// res18: Double = 1051299.221899134
```
This version consumes neither heap nor stack space. The `tailrec` annotation is optional, but is useful, since it forces the compiler to flag an error if there is some reason why the tail call elimination can not be performed (eg. here, the method needed to be decalared `final` so it could not be over-ridden).

## Helper functions

The previous example made use of the fact that Scala has optional arguments with default values. Even if this wasn't the case, we could acheive the same thing by embedding the two-argument version as a private function inside the one-argument version.
```scala
def logFactTRH(n: Int): Double =
  def go(n: Int, acc: Double): Double =
    if (n <= 1) acc else
    go(n - 1, math.log(n) + acc)
  go(n, 0.0)
  
logFactTRH(3)
// res19: Double = 1.791759469228055
logFactTRH(10)
// res20: Double = 15.104412573075514
logFactTRH(100000)
// res21: Double = 1051299.221899134
```

## Curried functions

Sometimes we want to partially apply a function by providing some of the arguments. We can flag this by grouping them.
```scala
def linFun(m: Double, c: Double)(x: Double): Double =
  m*x + c

val f = linFun(2, 3)
// f: Function1[Double, Double] = repl.MdocSession$MdocApp$$Lambda$8059/0x0000000802036010@781857e7

f(0)
// res22: Double = 3.0
f(1)
// res23: Double = 5.0
f(2)
// res24: Double = 7.0
```
Since the output of the partial call is a function, this is another example of a HoF.
