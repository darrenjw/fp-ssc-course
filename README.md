# fp-ssc-course

### An introduction to functional programming for scalable statistical computing and machine learning

## A half-day short-course

A brief introduction to ideas of functional programming in the context
of scalable statistical computing, illustrated with hands-on examples
in Scala, Haskell, (Python+)JAX and
Dex. [Scala](https://www.scala-lang.org/) and
[Haskell](https://www.haskell.org/) are general purpose strongly typed
functional programming
languages. [JAX](https://jax.readthedocs.io/en/latest/) is a
functional language for differentiable array programming embedded in
Python, and [Dex](https://github.com/google-research/dex-lang) is a
new experimental strongly typed functional language for differentiable
array processing.

The first iteration of this course will be delivered to [StatML](https://statml.io/) PhD students on 2023-02-16.

*Please note that you need to install some software on your system **in advance** of the course.* See the [Setup](Setup.md) guide for details.

You will also need a copy of this repo. If you know git, clone it ASAP, and then do a pull the day before the course. If you don't know git, click on the green "Code" button and download a zip file, but do (or re-do) this the day before the course to make sure you have an up-to-date version.

It would also be useful to have a copy of my [logreg](https://github.com/darrenjw/logreg) repo, which explores MCMC algorithms in a variety of (functional) languages.

### Abstract

Non-trivial research problems in statistical computing and machine
learning are often complex and computationally intensive, requiring a
custom implementation in some programming language. All of the
languages commonly used for this purpose are very old, dating back to
the dawn of the computing age, and are quite unsuitable for scalable
and efficient statistical computation. There have been huge advances in
computing science in the decades since these languages were created,
and many new, different and better programming languages have been
created. Although functional programming languages are not new, there
has been a large resurgence of interest in functional languages in the
last decade or two, as people have begun to appreciate the advantages
of the functional approach, especially in the context of developing
large, scalable software systems, and the ability to take advantage of
modern computing hardware.

This short course will provide a brief introduction to ideas of
functional programming in the context of scalable statistical
computing, illustrated with hands-on examples in Scala, Haskell,
(Python+)JAX and Dex. Scala and Haskell are general purpose strongly
typed functional programming languages. JAX is a functional language
for differentiable array programming embedded in Python, and Dex is a
new experimental strongly typed functional language for differentiable
array processing.

### Materials

* [Introduction to FP](Intro/Readme.md)
* Scala crash-course
* Scala hands-on
* [Running example](Intro/Example.md)
* [Scala example and hands-on](Scala/md/Example.md)
* Haskell crash-course
* Haskell example and hands-on
* JAX crash-course
* JAX example and hands-on
* Dex crash-course
* Dex example and hands-on
* [Functional and parallel random numbers](Intro/Random.md)
* Splittable random numbers hands-on
* [Wrap-up and next steps](Intro/Resources.md)


#### Directories

* [Introduction](Intro/)
* [Scala](Scala/)
* [Haskell](Haskell/)
* [JAX](JAX/)
* [Dex](Dex/)

