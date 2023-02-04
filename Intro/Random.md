# Functional and parallel random number generation

## Introduction

### Background

Since current computers are essentially deterministic, random number generators used for stochastic simulation aren't truly random at all, and so are more correctly referred to as *pseudo-random number generators*.

Random number generators typically consist of three main components:

* An internal state, $s\in S$
* A deterministic function for transforming the internal state, $f: S\rightarrow S$
* A deterministic function for turning the current internal state into a pseudo-random number (say, a realisation of a $U(0,1)$ random variable), $g: S\rightarrow [0,1)$

The state space, $S$, must be sufficiently large that applying $f$ a large number of times is unlikely to get you back to a previously visited state. This is essential for large Monte Carlo simulations. There are typically other functions associated with random number generators. eg. there is very often a function that will turn a given numeric "seed" into an internal state, and there are often functions for turning the internal state into pseudo-random numbers other than $U(0,1)$, but these are not essential to our discussion.

Typically, $f$ is a very complex non-linear function, leading to an apparently random sequence of internal states, and $g$ is a very simple function mapping the internal state to $[0,1)$. But note that this is not the only possibility. $f$ could be a very simple function (say, a function that increments the internal state as a "counter"), and then $g$ could be a very complex function that maps the internal state to an apparently random $[0,1)$. This is the idea behind counter-based pseudo-random number generators, but again, it's not essential to the current discussion.

How these functions are used and accessed depends on the language. In imperative languages, many of the details are often hidden. eg. The internal state might be hidden in a *global* *mutable* variable. Then a function, say, `random`, could first update the internal state by calling $f$, and then over-write the old internal state with the new internal state, and it can then compute a value to return to the user by applying $g$ to the global internal state.

In some languages/libraries the internal state is stored in a *mutable* variable, but it is not *global*. Then you use a seeding function to create a pointer/reference to an internal state that you then need to pass in to any random functions. But again, the random function updates the mutable internal state at the given reference and then turns the new state into a random value to be returned to the user.

### Functional random number generation

In pure functional languages we don't have mutable variables, so we must be more explicit in our handling of the state. The standard way to do this is to have a generator function `random` : $S\rightarrow S\times [0,1)$, which could be implemented (in Scala) as
```scala
def random(s0: S): (S, Double) =
  val s1 = f(s0)
  (s1, g(s1))
```
We then need to unpack the result of this function, and be sure to pass the updated state into subsequent calls to the generator. There are ways to make this process more elegant and convenient by using the *state monad*, but this is again tangential to the current discussion.

### Parallel random number generation

A major problem with all of the approaches discussed so far is that they are fundamentally *sequential*, and don't adapt trivially to a *parallel* context. Since the state is necessarily finite, repeated application of $f$ must eventually result in a previously visited state, and then due to the determinism of $f$, the states must then all repeat. So the state can be considered to live on a circular lattice. In a parallel context, you want different processors to have different internal states, corresponding to different points on the lattice. You need a reliable way to start different processors off at different points so that the probability of getting overlapping streams on different processors is very small. There are certain kinds of generators that allow efficient *leap-frogging*, ahead a large number of steps, but these are not so commonly used. *Parallel pseudo-random number generators* (PPRNGs) have states living on a toroidal lattice, so that different parallel processors can each traverse a different circular lattice. Both approaches can work for a very particular kind of parallel program on particular kinds of parallel hardware. If you are interested to learn more about these, I give an overview in a [very old paper](https://darrenjw.github.io/work/docs/pbc.pdf) of mine. However, I now consider them to have been a bit of a dead end. The problem is that they are not very flexible, and not well-suited to dynamic contexts and modern hardware consisting of multi-core CPUs, GPUs, etc.

### Splittable generators

An arguably much better approach to parallel random number generation than old-school PPRNGs is to use *splittable* generators. There are many variations on this approach, but the main idea is to replace the *advancement* function

$f: S \rightarrow S$

with a *split* function

$f: S \rightarrow S\times S$

so that instead of just getting one new state, the *split* function returns two (or more) states. These states are different to each other and the input state, and the split function is carefully chosen so that if either of the returned states are subsequently split, and so on, the *tree* of states generated is unlikely to contain any repeats. For historical reasons, the states of a splittable generator are often referred to as "keys".

In this case, the `random` function is simply the deterministic function $g$, which takes as input a state (or key), and outputs a random number. The programmer is responsible for ensuring that the same key is never passed in to the random function, typically by splitting before use. Splittable generators solve many issues around the use of random number generation in parallel and concurrent contexts, and are also a perfect fit for pure functional programming languages.

To understand why splittable generators solve the concurrency and parallelism problem, consider two functions, `my_random1` and `my_random2` that each use the splittable generator functions $f$ and $g$ internally. Both need to be called, and ideally they would be executed in parallel. Since these are intended to be used in a random context, they must take a state (or key) as input. The user is responsible for ensuring the keys provided to the two functions are different, so the code to call these two functions might look something like (in Dex):
```haskell
k0 = new_key 42 -- initial key from a seed
[k1, k2] = split_key k0
res1 = my_random1 k1
res2 = my_random2 k2
...
```
Since the functions are pure and there is no dependence between them, there is no reason why `my_random` and `my_random2` cannot be executed in parallel. But this argument works at every level of the program. Arbitrary concurrency and parallelism can be employed throughout. From a type safety viewpoint, there is also the added bonus that the intended randomness of any function is clearly indicated in its type signature, since it must take a state/key as input. In languages with support for currying, it is most convenient to always have the random key as the *final* input parameter.

The downside of splittable generators is that the programmer is responsible for ensuring that no key is ever reused. But it is quite easy to accidentally re-use a key, leading to incorrect behaviour, and bugs in Monte Carlo codes are hard to track down. Smart people are thinking about ways to address this drawback, but for now you just have to be careful!

## Further reading on splittable random numbers

### Random numbers in JAX

* [Pseudo Random Numbers in JAX](https://jax.readthedocs.io/en/latest/jax-101/05-random-numbers.html)
    * [JAX Random numbers](https://jax.readthedocs.io/en/latest/notebooks/Common_Gotchas_in_JAX.html#random-numbers)
    * [JAX PRNG Design](https://github.com/google/jax/blob/main/docs/jep/263-prng.md)
	* [jax.random](https://jax.readthedocs.io/en/latest/jax.random.html)
* JAX uses a threefry counter PRNG (described in Salmon et al, 2011)) with a functional array-oriented splitting model (described in Claessen and Palka, 2013)
    * Salmon et al (2011) [Parallel Random Numbers: As Easy as 1, 2, 3](https://doi.org/10.1145/2063384.2063405), *SC '11: Proceedings of 2011 International Conference for High Performance Computing, Networking, Storage and Analysis*, 16. [PDF](http://www.thesalmons.org/john/random123/papers/random123sc11.pdf)
    * Claessen and Palka (2013) [Splittable pseudorandom number generators using cryptographic hashing](https://doi.org/10.1145/2578854.2503784), *Haskell '13: Proceedings of the 2013 ACM SIGPLAN symposium on Haskell*, 47-58. [PDF](https://publications.lib.chalmers.se/records/fulltext/183348/local_183348.pdf)


### Splittable random numbers on the JVM

* Steele at al (2014) [Fast splittable pseudorandom number generators](https://doi.org/10.1145/2660193.2660195), *ACM SIGPLAN Notices*, **49**(10):453-472. [PDF](https://gee.cs.oswego.edu/dl/papers/oopsla14.pdf)







