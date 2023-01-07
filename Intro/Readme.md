# Introduction

## Functional programming for scalable statistical computing and ML




### Learning resources

Below are some links for further information about the languages and libraries briefly introduced in this course. See my [logreg repo](https://github.com/darrenjw/logreg) for more example (MCMC) code using these languages and libraries.

#### Scala

* [Scala programming language](https://www.scala-lang.org/) - main web site
    * [Learning resources](https://docs.scala-lang.org/)
* [Functional Programming in Scala](https://www.manning.com/books/functional-programming-in-scala) - unfortunately this book is not free, but remains one of the best introductions to functional programming (in any language)
    * [Chapter notes](https://github.com/fpinscala/fpinscala)
* [Typelevel](https://typelevel.org/) - libraries for functional programming in Scala
    * [Spire](https://typelevel.org/spire/) - numerics library
	* [Cats](https://typelevel.org/cats/) - category theory abstractions for pure functional programming
	* [Monocle](https://www.optics.dev/Monocle/) - lens/optics library
	* [FS2](https://fs2.io/) - pure functional streaming data library
* [Breeze](https://github.com/scalanlp/breeze/) - numerical linear algebra and scientific computing (like numpy/scipy for Scala)
* [Smile](https://haifengl.github.io/) - Statistical machine learning library (like scikit-learn for Scala)
* [Apache Spark](https://spark.apache.org/) - library for parallel and distributed big data processing


#### Haskell

* [Haskell](https://www.haskell.org/) - main web site
    * [Documentation](https://www.haskell.org/documentation/)
* [Cabal](https://www.haskell.org/cabal/) - a build tool
    * [Hackage](https://hackage.haskell.org/) - a package repository
* [Stack](https://docs.haskellstack.org/en/stable/) - a newer build tool
    * [Stackage](https://www.stackage.org/) - alternative repository
    * [Hoogle](https://hoogle.haskell.org/) - Haskell search engine
* [lyahfgg](http://learnyouahaskell.com/) - introductory book
* [Haskell wiki](https://wiki.haskell.org/Haskell)
    * [Typeclassopedia](https://wiki.haskell.org/Typeclassopedia)

The resources below use Haskell to illustrate the ideas, but are of generic interest for any serious functional programmer.

* Category theory for programmers, by [Bartosz Milewski](https://bartoszmilewski.com/)
    * [Blog post series](https://bartoszmilewski.com/2014/10/28/category-theory-for-programmers-the-preface/)
    * [Book](https://github.com/hmemcpy/milewski-ctfp-pdf/)
	    * [Scala edition](https://github.com/hmemcpy/milewski-ctfp-pdf/releases/tag/v1.3.0)
    * [YouTube](https://www.youtube.com/channel/UC8BtBl8PNgd3vWKtm2yJ7aA)
	    * [Series 1](https://www.youtube.com/playlist?list=PLbgaMIhjbmEnaH_LTkxLI7FMa2HsnawM_)
	    * [Series 2](https://www.youtube.com/playlist?list=PLbgaMIhjbmElia1eCEZNvsVscFef9m0dm)
	    * [Series 3](https://www.youtube.com/playlist?list=PLbgaMIhjbmEn64WVX4B08B4h2rOtueWIL)
    * **New book!**: [The Dao of FP](https://github.com/BartoszMilewski/Publications/raw/master/TheDaoOfFP/DaoFP.pdf) - this is now possibly the best introduction to the ideas of category theory in the context of functional programming


#### JAX

* [JAX](https://jax.readthedocs.io/en/latest/)
    * [github](https://github.com/google/jax)
	* [Tutorial: JAX 101](https://jax.readthedocs.io/en/latest/jax-101/)
	    * [Random numbers](https://jax.readthedocs.io/en/latest/jax-101/05-random-numbers.html)
	* [Autodiff cookbook](https://jax.readthedocs.io/en/latest/notebooks/autodiff_cookbook.html)


#### Dex

* [Dex](https://github.com/google-research/dex-lang)
    * [Tutorial](https://google-research.github.io/dex-lang/examples/tutorial.html)
	* [Paper](https://arxiv.org/abs/2104.05372)
	* [Prelude](https://google-research.github.io/dex-lang/prelude.html)
	* [InDex](https://google-research.github.io/dex-lang/index.html)
	    * [Stats library](https://google-research.github.io/dex-lang/lib/stats.html)
* [My Dex reminders](https://github.com/darrenjw/djwhacks/blob/master/dex/Reminders.md)



