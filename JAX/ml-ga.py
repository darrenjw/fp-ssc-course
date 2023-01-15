#!/usr/bin/env python3
# ml-ga.py
# Maximum likelihood via gradient ascent

import os
import pandas as pd
import numpy as np
import scipy as sp

import jax
from jax import grad, jit
import jax.numpy as jnp
import jax.scipy as jsp

print("Maximum likelihood by gradient ascent using JAX")

print("First read and process the data (using regular Python)")
df = pd.read_csv(os.path.join("..", "pima.data"), sep=" ", header=None)
print(df)
n, p = df.shape
print(n, p)

y = pd.get_dummies(df[7])["Yes"].to_numpy(dtype='float32')
X = df.drop(columns=7).to_numpy()
X = np.hstack((np.ones((n,1)), X))
print(X)
print(y)

print("Now gradient ascent using JAX")
X = X.astype(jnp.float32)
y = y.astype(jnp.float32)

@jit
def ll(beta):
    return jnp.sum(-jnp.log(1 + jnp.exp(-(2*y - 1)*jnp.dot(X, beta))))

gll = jit(grad(ll)) # use auto-diff for the gradient

@jit
def one_step(b0, learning_rate=1e-6):
    return b0 + learning_rate*gll(b0)

def ascend(step, init, max_its=10000, tol=1e-8):
    def term(state):
        x1, x0, its = state
        return ((its > 0) & jnp.logical_not(jnp.allclose(x1, x0, tol)))
    def step_state(state):
        x1, x0, its = state
        x2 = step(x1)
        return [x2, x1, its - 1]
    b_opt, _, its_remaining = jax.lax.while_loop(term, step_state, [init, -init, max_its])
    return(b_opt)

init = jnp.array([-9.8, 0.1, 0, 0, 0, 0, 1.8, 0]).astype(jnp.float32)
print(init)
print(ll(init))
opt = ascend(one_step, init)
print(opt)
print(ll(opt))
print("Goodbye.")

# eof

