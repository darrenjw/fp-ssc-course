# Functional and parallel random number generation

## Introduction

Since current computers are essentially deterministic, random number generators used for stochastic simulation aren't truly random at all, and so are more correctly referred to as *pseudo-random number generators*.

Random number generators typically consist of three components:
* An internal state
* A function for transforming the internal state
* A function for turning the current internal state into a pseudo-random number (say, a realisation of a $U(0,1)$ random variable)





**TODO: GIVE AN INTRODUCTORY OVERVIEW**





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







