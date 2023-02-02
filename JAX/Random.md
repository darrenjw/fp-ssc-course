JAX splittable random numbers hands on
================
Darren Wilkinson

## Basics

Call Python REPL (`python` or `python3`) and start messing around with
splittable random numbers in JAX.

``` python
import numpy as np
import scipy as sp
import scipy.stats

import jax
from jax import grad, jit
import jax.numpy as jnp
import jax.scipy as jsp
import jax.lax as jl
import jax.random as jr

k0 = jr.PRNGKey(42)

[k1, k2] = jr.split(k0)

k0
```

    Array([ 0, 42], dtype=uint32)

``` python
k1
```

    Array([2465931498, 3679230171], dtype=uint32)

``` python
k2
```

    Array([255383827, 267815257], dtype=uint32)

``` python
jr.uniform(k0)
```

    Array(0.42672753, dtype=float32)

``` python
jr.uniform(k0)
```

    Array(0.42672753, dtype=float32)

``` python
jr.uniform(k0)
```

    Array(0.42672753, dtype=float32)

``` python
jr.uniform(k1)
```

    Array(0.5548415, dtype=float32)

``` python
jr.uniform(k2)
```

    Array(0.91457367, dtype=float32)

Note that we don’t just have to split the key into two.

``` python
[k3, k4, k5] = jr.split(k2, 3)

k3
```

    Array([1245536116, 3554031723], dtype=uint32)

``` python
k4
```

    Array([2395711293, 1211085833], dtype=uint32)

``` python
k5
```

    Array([3735526216, 3692299482], dtype=uint32)

``` python
keys = jr.split(k5, 8)

keys
```

    Array([[ 467138136,   99877137],
           [ 899471984, 3583342607],
           [3016824453,    9292390],
           [4251814516, 3897073703],
           [2111337182, 1349560346],
           [ 969359427, 2464285400],
           [1422039866, 4206098692],
           [1762018044, 1626056740]], dtype=uint32)

It can sometimes be convenient to split a key into an *array* of keys
and then *map* or *fold* a random function over the array.

``` python
jnp.sum(jl.map(jr.uniform, keys))
```

    Array(2.272056, dtype=float32)

If we really just want to “advance” the key, we can do that too.

``` python
jr.split(k5, 1)
```

    Array([[4045048393, 3663185921]], dtype=uint32)

## Probability distributions

`jr.uniform` is used to generate a $U(0,1)$, and `jr.normal` generates
from a standard normal, $N(0,1)$.

``` python
jl.map(jr.normal, keys)
```

    Array([ 0.4662092 , -1.665825  , -0.6956026 , -1.4537567 , -0.16085608,
            0.43099454, -1.9535689 , -1.2808337 ], dtype=float32)

There are other commonly encountered random variables available for
sampling.

``` python
jr.exponential(k5)
```

    Array(0.4373902, dtype=float32)

``` python
jr.poisson(k5, 10.0)
```

    Array(13, dtype=int32)

``` python
jr.poisson(k5, 10.0, [10])
```

    Array([ 8,  8, 10,  8, 12, 11, 17, 10, 11,  8], dtype=int32)

## Random functions

Suppose that you want to define your own random function. Here we will
define our own function for sampling exponentials.

``` python
def rexp(rate, key):
  return jnp.log1p(-jr.uniform(key)) / (-rate)
```

Notice how we have made the key the final input parameter. This is
because of currying. We can call the function directly:

``` python
rexp(10.0, k5)
```

    Array(0.04373902, dtype=float32)

But we can also create a particular random variable:

``` python
from functools import partial

my_rexp = partial(rexp, 20.0)
```

and then use this partially applied function with multiple keys.

``` python
jl.map(my_rexp, keys)
```

    Array([0.05688852, 0.0024529 , 0.01394199, 0.00379046, 0.02864421,
           0.05494518, 0.00128518, 0.00527503], dtype=float32)

This is why you might want to make the key the final input parameter.

## Exercise

Write a function to simulate a 1d [random
walk](https://en.wikipedia.org/wiki/Random_walk).
