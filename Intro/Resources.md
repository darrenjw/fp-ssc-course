# Learning resources

Below are some links for further information about the languages and libraries briefly introduced in this course. See my [logreg repo](https://github.com/darrenjw/logreg) for more example (MCMC) code using these languages and libraries.

## Scala

* [Scala programming language](https://www.scala-lang.org/) - main web site
    * [Learning resources](https://docs.scala-lang.org/)
	* [Scastie](https://scastie.scala-lang.org/) - try Scala in the browser
* [Scala exercises](https://www.scala-exercises.org/) - learn Scala in the browswer
* [sbt](https://www.scala-sbt.org/) - the Scala build tool
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
* [Scala for statistical computing and data science](https://github.com/darrenjw/scala-course/blob/master/StartHere.md) - materials for my short course (not yet updated for Scala 3)


## Haskell

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
* [dataHaskell](https://www.datahaskell.org/) - a project for data science with Haskell


## JAX

* [Python](https://www.python.org/) - JAX is embedded in Python
* [JAX](https://jax.readthedocs.io/en/latest/)
    * [github](https://github.com/google/jax)
	* [Quickstart](https://jax.readthedocs.io/en/latest/notebooks/quickstart.html)
	* [Tutorial: JAX 101](https://jax.readthedocs.io/en/latest/jax-101/)
	    * [Random numbers](https://jax.readthedocs.io/en/latest/jax-101/05-random-numbers.html)
	* [Autodiff cookbook](https://jax.readthedocs.io/en/latest/notebooks/autodiff_cookbook.html)


## Dex

* [Dex](https://github.com/google-research/dex-lang)
    * [Tutorial](https://google-research.github.io/dex-lang/examples/tutorial.html)
	* [Paper](https://arxiv.org/abs/2104.05372)
	* [Prelude](https://google-research.github.io/dex-lang/prelude.html)
	* [InDex](https://google-research.github.io/dex-lang/index.html)
	    * [Stats library](https://google-research.github.io/dex-lang/lib/stats.html)
* [My Dex reminders](https://github.com/darrenjw/djwhacks/blob/master/dex/Reminders.md)


## Misc

### Category theory for FP

These resources are of generic interest for any serious functional programmer. Haskell is often used to illustrate the ideas.

* Category theory for programmers, by [Bartosz Milewski](https://bartoszmilewski.com/)
    * [Blog post series](https://bartoszmilewski.com/2014/10/28/category-theory-for-programmers-the-preface/)
    * [Book](https://github.com/hmemcpy/milewski-ctfp-pdf/) - the blog posts were turned into a book
	    * [Scala edition](https://github.com/hmemcpy/milewski-ctfp-pdf/releases/tag/v1.3.0) - a Scala edition of the book was produced
    * [YouTube](https://www.youtube.com/channel/UC8BtBl8PNgd3vWKtm2yJ7aA) - Video lectures accompany the blog posts (and book)
	    * [Series 1](https://www.youtube.com/playlist?list=PLbgaMIhjbmEnaH_LTkxLI7FMa2HsnawM_), [Series 2](https://www.youtube.com/playlist?list=PLbgaMIhjbmElia1eCEZNvsVscFef9m0dm), [Series 3](https://www.youtube.com/playlist?list=PLbgaMIhjbmEn64WVX4B08B4h2rOtueWIL)
    * **New book!**: [The Dao of FP](https://github.com/BartoszMilewski/Publications/raw/master/TheDaoOfFP/DaoFP.pdf) - Bartosz decided to write a proper book - this is probably the best introduction to category theory in the context of functional programming
	
### Data structures
	
* [Purely functional data structures](https://books.google.co.uk/books/about/Purely_Functional_Data_Structures.html?id=SxPzSTcTalAC) - book on data structures, with examples in SML (and Haskell) ([PDF](https://doc.lagout.org/programmation/Functional%20Programming/Chris_Okasaki-Purely_Functional_Data_Structures-Cambridge_University_Press%281998%29.pdf))

### Other interesting languages

* [OCaml](https://ocaml.org/) - nice strongly typed and fairly functional language
* [Flix](https://flix.dev/) - a new strongly typed and fairly functional language
* [Futhark](https://futhark-lang.org/) - a language for GPU programming
* [Rust](https://www.rust-lang.org/) - not really functional, but a good, safe, modern typed language, increasingly popular for systems programming

