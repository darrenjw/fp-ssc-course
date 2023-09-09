# Dex implementation of the running example

## ML for a logistic regression model using gradient ascent

### The Dex application

A Dex implementation of our running example can be found in the file [ml-ga.dx](ml-ga.dx) ([HTML rendering](https://darrenjw.github.io/fp-ssc-course/ml-ga.html)). Study this, and compare and contrast with the Scala, Haskell and JAX implementations.

If you have installed Dex the script should run with something like
```bash
dex --lib-path BUILTIN_LIBRARIES:. script ml-ga.dx
```
Setting the library path will allow the inclusion of some utility functions, [djwutils.dx](https://darrenjw.github.io/fp-ssc-course/djwutils.html). Note that there is a [Makefile](Makefile) in the directory that you might want to take a quick look at. Make sure that you can build and run the application before proceeding.

### Hands-on exercise

Do either or both of these exercises (or go back to previous exercises) as your interests dictate and time permits.

* Try manually tweaking the initial guess, the learning rate, the convergence tolerance and the maximum number of iterations to see how robust (or otherwise) this naive gradient ascent algorithm is to these tuning parameters.
* Improve on the naive descent algorithm somewhow, perhaps by implementing line search for choosing the step size.


