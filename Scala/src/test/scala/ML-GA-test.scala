import org.scalatest.flatspec.AnyFlatSpec

class SetSpec extends AnyFlatSpec:

  import breeze.stats.distributions.Rand.VariableSeed.randBasis

  "A Poisson(10.0)" should "have mean 10.0" in {
    import breeze.stats.distributions.Poisson
    val p = Poisson(10.0)
    val m = p.mean
    assert(math.abs(m - 10.0) < 0.000001)
  }


