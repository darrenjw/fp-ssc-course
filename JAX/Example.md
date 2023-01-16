# JAX implementation of the running example

## ML for a logistic regression model using gradient ascent

### The JAX application

A JAX implementation of our running example can be found in the file [ml-ga.py](ml-ga.py). Study this, and compare and contrast with the Scala and Haskell implementations.

If you have installed JAX the script should just run, with something like `./ml-ga.py` (or perhaps `python3 ml-ga.py`, or just `python ml-ga.py`). Make sure that you can build and run the application before proceeding.

### Hands-on exercise

Do either of both of these exercises (or go back to previous exercises) as your interests dictate and time permits.

* Try manually tweaking the initial guess, the learning rate, the convergence tolerance and the maximum number of iterations to see how robust (or otherwise) this naive gradient ascent algorithm is to these tuning parameters.
* Improve on the naive descent algorithm somewhow, perhaps by implementing line search for choosing the step size.


