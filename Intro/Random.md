# Functional and parallel random number generation



## Random numbers in JAX

* [Pseudo Random Numbers in JAX](https://jax.readthedocs.io/en/latest/jax-101/05-random-numbers.html)
    * [JAX Random numbers](https://jax.readthedocs.io/en/latest/notebooks/Common_Gotchas_in_JAX.html#random-numbers)
    * [JAX PRNG Design](https://github.com/google/jax/blob/main/docs/jep/263-prng.md)
	* [jax.random](https://jax.readthedocs.io/en/latest/jax.random.html)
* JAX uses a threefry counter PRNG (described in Salmon et al, 2011)) with a functional array-oriented splitting model (described in Claessen and Palka, 2013)
    * Salmon et al (2011) [Parallel Random Numbers: As Easy as 1, 2, 3](http://www.thesalmons.org/john/random123/papers/random123sc11.pdf), *SC '11: Proceedings of 2011 International Conference for High Performance Computing, Networking, Storage and Analysis*, 16.
    * Claessen and Palka (2013) [Splittable pseudorandom number generators using cryptographic hashing](https://publications.lib.chalmers.se/records/fulltext/183348/local_183348.pdf), *Haskell '13: Proceedings of the 2013 ACM SIGPLAN symposium on Haskell*, 47-58.




## Splittable random numbers on the JVM

* Steele at al (2014) [Fast splittable pseudorandom number generators](https://dl.acm.org/doi/abs/10.1145/2714064.2660195), *ACM SIGPLAN Notices*, **49**(10):453-472.







