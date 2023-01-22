# Haskell crash course

Haskell is similar to Scala in the sense that it is a strongly typed functional programming language with a sophisticated type system. However, it is much purer than Scala, and unlike Scala, evaluation is *lazy*, rather than *strict* by default. There are pros and cons with this, which we don't have time to explore today. It also uses a different syntax. The syntax is whitespace sensitive, somewhat similar to Python and the braceless syntax of Scala 3.

## Types and assignments

A REPL can be called up by running `ghci` from the command line. GHC is the Glasgow Haskell Compiler (the "standard" Haskell compiler), and the "i" is for "interactive". This crash course can be followed in the REPL. Note that `:t` is a REPL command that will return the type of an object (and note that `:t` also works similarly in the Scala REPL).
```haskell
x = 5 :: Int
x
:t x

y = 6
y
:t y
```
Note that unlike Scala, Haskell will defer inferring a specific numeric type from a numeric literal as long as possible.

## Immutable collections

Linked lists are very (too) fundamental in Haskell. They are denoted with square brackets.
```haskell
l = [1, 3, 4, 5, 8] :: [Int]
l
:t l
```

But there are many other interesting collections in Haskell. For example, immutable arrays.
```haskell
import Data.Array.IArray
import Data.Array.Unboxed
la = listArray (0, 3) l :: UArray Int Int
la
:t la
la ! 1
la ! 2
la2 = la // [(1, 7)]
la2
la
```

## Manipulating collections

In Haskell, the map operation associated with the functor type class is called `fmap`, and has infix notation `<$>`.
```haskell
fmap (\x -> x*2) l
(\x -> x*2) <$> l
```

```haskell
foldl (+) 0 l -- left fold
foldr (+) 0 l -- right fold
```

## Writing functions

We will again use the log-factorial function as our illustrative example. Note that for declaring functions, you probably want to turn on multi-line mode in the GHCi REPL with `:set +m`. A simple example can be written as follows.
```haskell
logFact :: Int -> Double
logFact n = sum (log <$> [1..n])

logFact 5
logFact 100000
```
A recursive version can also be implemented.
```haskell
logFact2 :: Int -> Double
logFact2 n = if (n == 0)
  then 0.0
  else (log (fromIntegral n)) + logFact2 (n-1)

logFact2 5
logFact2 100000
```
Note that due to Haskell's evaluation model, stack overflow on recursion is very often avoided. However, we can write the function in tail-recursive form if we choose.
```haskell
logFact3 :: Int -> Double
logFact3 n = go (n :: Int) (0.0 :: Double) where
  go 0 acc = acc
  go n acc = go (n-1) (acc + log (fromIntegral n))
  
logFact3 5
logFact3 100000
```

## Curried functions

In Haskell, functions are typically written in fully curried form by default. This is often convenient, but can sometimes make type signatures difficult to interpret.

```haskell
linFun :: Double -> Double -> Double -> Double
linFun m c x = m*x + c

f = linFun 2 3
f 0
f 1
f 2
```

