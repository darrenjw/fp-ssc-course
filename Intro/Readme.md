# Introduction

## Functional programming for scalable statistical computing and ML

### Background

-   For non-trivial problems, Bayesian computation typically relies on
    computationally intensive methods, such as MCMC, SMC or ABC ---
    similar structure to stochastic simulation models

-   These often require a custom implementation in some programming
    language

-   All of the languages commonly used are very old, dating back to the
    dawn of the computing age, and are quite unsuitable for scalable and
    efficient Bayesian computation

-   Interpreted dynamic languages such as R, Python and Matlab are far
    too slow, among many other things

-   Languages such as C, C++, Java and Fortran are much faster, but are
    also very poorly suited to the development of efficient,
    well-tested, compositional, scalable code, able to take advantage of
    modern computing hardware

### Alternative languages and approaches

-   All of the languages on the previous slide are fundamentally
    [imperative]{.alert} programming languages, mimicking closely the
    way computer processors actually operate

-   There have been huge advances in computing science in the decades
    since these languages were created, and many new, different and
    better programming languages have been created

-   Although [functional]{.alert} programming (FP) languages are not
    new, there has been a large resurgence of interest in functional
    languages in the last decade or two, as people have begun to
    appreciate the advantages of the functional approach, especially in
    the context of developing large, scalable software systems, and the
    ability to take advantage of modern computing hardware

-   There has also been a swing away from [dynamically typed]{.alert}
    programming languages back to [statically typed]{.alert} languages

### Functional programming

-   FP languages emphasise the use of [immutable]{.alert} data, [pure,
    referentially transparent functions]{.alert}, and [higher order
    functions]{.alert}

-   FP languages more naturally support composition of models, data and
    computation than imperative languages, leading to more scalable and
    testable software

-   Statically typed FP languages (such as Haskell and Scala) correspond
    closely to the [simply-typed lambda calculus]{.alert} which is one
    of the canonical examples of a [Cartesian closed
    category]{.alert} (CCC)

-   This connection between typed FP languages and CCCs enables the
    borrowing of ideas from category theory into FP

-   Category theory concepts such as [functors]{.alert},
    [monads]{.alert} and [comonads]{.alert} are useful for simplifying
    code that would otherwise be somewhat cumbersome to express in pure
    FP languages

### Concurrency, parallelism, distribution, state

-   Modern computing platforms feature processors with many cores, and
    possibly many such processors --- parallel programming is required
    to properly exploit these

-   Most of the notorious difficulties associated with parallel
    programming revolve around [shared mutable state]{.alert}

-   In pure FP, state is not mutable, so there is no mutable state,
    shared or otherwise

-   Consequently, most of the difficulties typically associated with
    parallel, distributed, and concurrent programming simply don't exist
    in FP --- parallelism in FP is so easy and natural that it is
    sometimes completely automatic

-   This natural scalability of FP languages is one reason for their
    recent resurgence

### Compositionality

-   Not all issues relating to scalability of models and algorithms
    relate to parallelism

-   A good way to build a large model is to construct it from smaller
    models

-   A good way to develop a complex computation is to construct it from
    simpler computations

-   This (recursive) decomposition-composition approach is at the heart
    of the so-called "divide and conquer" approach to problem solution,
    and is very natural in FP (eg. FFT and BP for PGMs)

-   It also makes code much easier to [test]{.alert} for correct
    behaviour

-   Category theory is in many ways the mathematical study of
    (associative) composition, and this leads to useful insights

### Bayesian computation

-   [map-reduce]{.alert} operations on [functorial]{.alert} data
    collections can trivially parallelise (and distribute):

    -   Likelihood evaluations for big data

    -   ABC algorithms

    -   SMC re-weighting and re-sampling

-   Gibbs sampling algorithms can be implemented as [cobind]{.alert}
    operations on an appropriately coloured (parallel)
    [comonadic]{.alert} conditional independence graph

-   [Probabilistic programming languages]{.alert} (PPLs) can be
    implemented as embedded domain specific languages (DSLs) trivially
    using [for/do]{.alert} syntax for [monadic composition]{.alert} in
    conjunction with [probability monads]{.alert}

-   [Automatic differentiation]{.alert} (AD) is natural and convenient
    in functional languages, facilitating gradient-based algorithms such
    as MALA, HMC and PDMP

### Monoids and parallel "map--reduce"

-   A [monoid]{.alert} is a very important concept in FP

-   For now we will think of a monoid as a [set]{.alert} of elements
    with a [binary relation]{.alert} $\star$ which is [closed]{.alert}
    and [associative]{.alert}, and having an [identity]{.alert} element
    wrt the binary relation

-   You can think of it as a [semi-group]{.alert} with an identity or a
    [group]{.alert} without an inverse

-   \|fold\|s, \|scan\|s and \|reduce\| operations can be computed in
    parallel using [tree reduction]{.alert}, reducing time from $O(n)$
    to $O(\log n)$ (on infinite parallel hardware)

-   "[map--reduce]{.alert}" is just the pattern of processing large
    amounts of data in an immutable collection by first
    [map]{.alert}ping the data (in parallel) into a monoid and then
    tree-[reduc]{.alert}ing the result (in parallel), sometimes called
    \|foldMap\|

### Distributed parallel collections with Apache Spark

-   [Apache Spark]{.alert} ([spark.apache.org](spark.apache.org)) is a
    Scala library for distributed Big Data processing on (large)
    clusters of machines

-   The basic datatype provided by Spark is an [RDD]{.alert} --- a
    resilient distributed dataset

-   An RDD is just a [lazy]{.alert}, [distributed]{.alert}, parallel
    monadic collection, supporting methods such as \|map\|, \|flatMap\|,
    \|reduce\|, etc., which can be used in exactly the same way as any
    other Scala collection

-   Code looks exactly the same whether the RDD is a small dataset on a
    laptop or terabytes in size, distributed over a large Spark cluster

-   It is a powerful framework for the development of scalable
    algorithms for Bayesian computation

### JAX

-   [JAX]{.alert} ([jax.readthedocs.io](jax.readthedocs.io)) is a Python
    library embedding a DSL for automatic differentiation and
    JIT-compiling (array) functions to run very fast on (multiple) CPU
    or GPU

-   It is especially good at speeding up likelihood evaluations and
    (MCMC-based) sampling algorithms for complex models

-   It is not unheard of for MCMC algorithms to run 100 times faster
    than regular Python code, even on a single CPU (multiple cores and
    GPUs will speed things up further)

-   The JAX eDSL is [pure functional array language]{.alert}

-   Despite targeting a completely different kind of scalability to
    Spark, and being embedded in a very different language, the
    fundamental computational model is very similar: [express algorithms
    in terms of lazy transformations of immutable data structures using
    pure functions]{.alert}

### Functional algorithms

-   By expressing algorithms in a functional style (eg. lazy
    transformations of immutable data structures with pure functions),
    we allow many code optimisations to be automatically applied

-   [Pure functional]{.alert} algorithms are relatively easy to
    [analyse]{.alert}, [optimise]{.alert}, [compile]{.alert},
    [parallelise]{.alert}, [distribute]{.alert},
    [differentiate]{.alert}, push to GPU, etc.

-   These optimisations can typically be done [automatically]{.alert} by
    the library, compiler, framework, etc., without significant user
    intervention

-   It is very difficult (often impossible) to analyse and reason about
    imperative code in a similar way

### JAX ecosystem

-   (Unnormalised) log-posteriors expressed in JAX can be sampled using
    a variety of different algorithms (including HMC and NUTS, via
    auto-diff) using [BlackJAX]{.alert}
    ([blackjax-devs.github.io/blackjax/](blackjax-devs.github.io/blackjax/))

-   The original Pyro uses PyTorch as a back-end, but the popular
    [NumPyro]{.alert} (<https://github.com/pyro-ppl/numpyro>) fork uses
    JAX for a back-end, leading to significant performance improvements

-   The PyMC4 project intended to use TensorFlow for a back-end, but
    this project was abandoned, and [PyMC(3)]{.alert}
    ([docs.pymc.io](docs.pymc.io)) is switching from Theano to Aesara
    with JAX as a back-end

### DEX

-   Although the conceptual computational model of JAX has a number of
    good features, the embedding of such a language in a dynamic,
    interpreted, imperative language such as Python has a number of
    limitations and drawbacks

-   A similar issue arises with Spark -- although it is possible to
    develop Spark applications in Python using PySpark, in practice most
    non-trivial applications are developed in Scala, for good reason

-   This motivates the development of a JAX-like array processing DSL in
    a strongly typed functional programming language

-   [DEX]([github.com/google-research/dex-lang](github.com/google-research/dex-lang))
    is a new experimental (Haskell-like) language in this space with a
    number of interesting and desirable features
	
	




