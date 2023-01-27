# Quick hands-on with splittable random numbers in JAX

## Basics
Call Python REPL (`python` or `python3`) and start messing around with splittable random numbers in JAX.

```python
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
k1
k2

jr.uniform(k0)
jr.uniform(k0)
jr.uniform(k0)
jr.uniform(k1)
jr.uniform(k2)
```

Note that we don't just have to split the key into two.

```python
[k3, k4, k5] = jr.split(k2, 3)

k3
k4
k5

keys = jr.split(k5, 8)

keys
```

It can sometimes be convenient to split a key into an *array* of keys and then *map* or *fold* a random function over the array.

```python
jnp.sum(jl.map(jr.uniform, keys))
```

If we really just want to "advance" the key, we can do that too.

```python
jr.split(k5, 1)
```

## Probability distributions
`jr.uniform` is used to generate a $U(0,1)$, and `jr.normal` generates from a standard normal, $N(0,1)$.

```python
jl.map(jr.normal, keys)
```

There are other commonly encountered random variables available for sampling.

```python
jr.exponential(k5)

jr.poisson(k5, 10.0)

jr.poisson(k5, 10.0, [10])
```

## Random functions
Suppose that you want to define your own random function. Here we will define our own function for sampling exponentials.

```python
def rexp(rate, key):
  return jnp.log1p(-jr.uniform(key)) / (-rate)
```

Notice how we have made the key the final input parameter. This is because of currying. We can call the function directly:

```python
rexp(10.0, k5)
```

But we can also create a particular random variable:

```python
from functools import partial

my_rexp = partial(rexp, 20.0)
```

and then use this partially applied function with multiple keys.

```python
jl.map(my_rexp, keys)
```

This is why you might want to make the key the final input parameter.

