# Haskell crash course

Haskell is similar to Scala in the sense that it is a strongly typed functional programming language with a sophisticated type system. However, it is much purer than Scala, and unlike Scala, evaluation is *lazy*, rather than *strict* by default. There are pros and cons with this, which we don't have time to explore today. It also uses a slightly different syntax. 

A REPL can be called up by running `ghci` from the command line. GHC is the Glasgow Haskell Compiler (the "standard" Haskell compiler), and the "i" is for "interactive". This crash course can be followed in the REPL. Note that `:t` is a REPL command that will return the type of an object.

```haskell
x = 5 :: Int
x
:t x

y = 6
y
:t y
```

Linked lists are very (too) fundamental in Haskell. They are denoted with square brackets.
```haskell
l = [1, 3, 4, 5, 8] :: [Int]
l
:t l
```



