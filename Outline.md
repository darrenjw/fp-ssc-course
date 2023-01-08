# FP for scalable statistical computing

## Course outline

* Introduction: problems with existing languages and benefits of FP (20 mins)
* Quick introduction to FP with Scala - Scala crash course (10)
* Hands on with Scala - Scastie and sbt (20)
* Running example - gradient-based opt of a logreg likelihood (10)
* Example in Scala then Run example in Scala (15)
* Quick intro to Haskell (10)
* Example in Haskell then Hands-on with Haskell (15)
* (BREAK?)
* JAX for Python (10)
* Example in JAX then JAX hands-on (15)
* Introduction to Dex (10)
* Example in Dex then Dex hands-on (15)
* Functional and parallel random numbers (10)
* Hands-on with splittable random numbers (choice of language) (15)
* Wrap-up and next steps (logreg repo and learning resources) (5)
* (180 minutes, excluding Break)

## Abstract

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
Python+JAX and Dex. Scala and Haskell are general purpose strongly
typed functional programming languages. JAX is a functional language
for differentiable array programming embedded in Python, and Dex is a
new experimental strongly typed functional language for differentiable
array processing.

