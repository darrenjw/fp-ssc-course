## FP for scalable statistical computing

# Setup

**In order to get the most benefit from the course, it is necessary to install some software in advance**

The languages that will be covered in the course are Scala, Haskell, JAX and Dex. Ideally, you will install all of these on your laptop in advance of the course. However, it is *not necessary* to install/use *all* of these languages for the course to be useful. Installing, say, Scala and at least one other (depending on your interests), will be fine.

### Scala

The easiest way to install all necessary Scala tools on your system is by using a tool called Coursier. See the [getting started](https://docs.scala-lang.org/getting-started/) instructions on the [Scala](https://www.scala-lang.org/) website for how to install this. Once you have Coursier installed, doing `cs setup` should install everything else that you need, including [sbt](https://www.scala-sbt.org/), which is the main tool we will be relying on for this course.

Scala editing modes are available for most programmer code editing tools. If you want a full-featured IDE, then [IntelliJ](https://www.jetbrains.com/idea/download/) is typically recommended. The free community edition is fine. Be sure to install the Scala plugin for IntelliJ during the installation process.

### Haskell

The [Haskell](https://www.haskell.org/) website has information on installing various components of the Haskell toolchain. But if you are running Linux, you can easily install everything you need via your package manager. eg. on Ubuntu, just installing the packages `haskell-platform` and `haskell-stack` will provide everything you need for this course. The packages have similar names on other distros.

### JAX (and Python)

[JAX](https://jax.readthedocs.io/en/latest/) is a library for [Python](https://www.python.org/) which can be installed with `pip`. You first need to make sure that you have python and pip installed on your system. Then see the [installation instructions](https://github.com/google/jax#installation) which are a bit system dependent. The CPU-only version of JAX is fine for this course. Note that if you know about Python "virtual environments", it might be better to install JAX in a new environment.

### Dex

[Dex](https://github.com/google-research/dex-lang) is an experimental new language (written in Haskell) which currently needs to be built from source. This is relatively straightforward on Macs and most Linux distros, but is likely to be impractical on Windows.

If you know `git`, clone the Dex repo, and if not, click on the green "Code" button, and download the repo as a zip file, and uncompress on your system. Then follow the [Installing](https://github.com/google-research/dex-lang#Installing) instructions. Note that I have some additional [installation](https://github.com/darrenjw/djwhacks/blob/master/dex/Reminders.md#Installation) instructions for Ubuntu and Fedora which will probably be useful for anyone using those distros.
