# Running example



## The model

Here we will conduct fully inference for a [logistic regression](https://en.wikipedia.org/wiki/Logistic_regression) model for a binary outcome based on some covariates. The $i$th observation will be 1 with probability $p_i$, and the [logit](https://en.wikipedia.org/wiki/Logit) of $p_i$ will depend linearly on predictors. This leads to a log-likelihood function

$$l(b; y) = -\mathbb{1}'[\log(\mathbb{1} + \exp[-(2y - \mathbb{1})\circ(Xb)])]$$

where $y$ is a binary vector of responses, $X$ is an $n\times p$ matrix of covariates and $b$ is the $p$-vector of parameters of inferential interest.

Some languages and frameworks can auto-diff likelihoods like this, but for comparison purposes, we can also use hard-coded gradients:

$$\nabla l(b) = X'(y-p), \quad \text{where}\quad p = (\mathbb{1} + \exp[-Xb])^{-1}.$$

We will use a very simple gradient ascent algorithm in order to try and maximise the likelihood, $l$, wrt to the covariate parameter weights, $b$.

We will be analysing the "Pima" training dataset, with 200 observations and 7 predictors. Including an intercept as the first covariate gives a parameter vector of length $p=8$.

