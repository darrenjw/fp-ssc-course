# Haskell implementation of the running example

## ML for a logistic regression model using gradient ascent

### The Haskell application

A Haskell implementation of our running example can be found in the file [Main.hs](app/Main.hs). Study this, and compare and contrast with the Scala implementation.

The application is a [stack](https://docs.haskellstack.org/en/stable/) project. So, from the [Haskell directory](./) you can build the application with `stack build` and run it with `stack exec ml-ga-exe`.

Make sure that you can build and run the application before proceeding.

### Hands-on exercise

Do either of both of these exercises (or go back to previous exercises) as your interests dictate and time permits.

* Try manually tweaking the initial guess, the learning rate, the convergence tolerance and the maximum number of iterations to see how robust (or otherwise) this naive gradient ascent algorithm is to these tuning parameters.
* Improve on the naive descent algorithm somewhow, perhaps by implementing line search for choosing the step size.


