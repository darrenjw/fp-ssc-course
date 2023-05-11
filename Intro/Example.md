# Running example

## Intro

It will be useful to have a running example for the course.

## The model and likelihood

Here we will conduct inference for a [logistic regression](https://en.wikipedia.org/wiki/Logistic_regression) model for a binary outcome based on some covariates. The *i*th observation will be 1 with probability $p_i$, and the [logit](https://en.wikipedia.org/wiki/Logit) of $p_i$ will depend linearly on predictors. This leads to a log-likelihood function

$$\ell(b; y) = -\mathbf{1}'[\log(\mathbf{1} + \exp[-(2y - \mathbf{1})\circ(Xb)])]$$

where $y$ is a binary vector of responses, $X$ is an $n\times p$ matrix of covariates, $b$ is the $p$-vector of parameters of inferential interest, and $\circ$ denotes the [Hadamard product](https://en.wikipedia.org/wiki/Hadamard_product_(matrices)).

Note that I discuss the derivation of this likelihood in detail in a [series of blog posts](https://darrenjw.wordpress.com/2022/08/07/bayesian-inference-for-a-logistic-regression-model-part-1/).

## Gradient of the likelihood

Some languages and frameworks can auto-diff likelihoods like this, but we can also differentiate by hand:

$$\nabla \ell(b) = X'(y-p), \quad \text{where}\quad p = (\mathbf{1} + \exp[-Xb])^{-1}.$$

For our running example we will use a very simple gradient ascent algorithm in order to try and maximise the likelihood, $\ell$, wrt to the covariate parameter weights, $b$.

## The data

We will be analysing the ["Pima" training dataset](../pima.data), with 200 observations and 7 predictors. Including an intercept as the first covariate gives a parameter vector of length $p=8$.

For a small dataset like this, there is no problem using the gradient of the full likelihood in a simple [**steepest ascent**](https://en.wikipedia.org/wiki/Gradient_descent) algorithm, so that's what we'll start with. But if you are interested in optimisation, you can then go on to experiement with adapting the learning rate, accelerated learning algorithms,  using [**stochastic gradient ascent**](https://en.wikipedia.org/wiki/Stochastic_gradient_descent), etc., according to your interests.
