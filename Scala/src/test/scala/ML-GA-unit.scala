import cats.*
import cats.implicits.*

import munit.*

// Example unit tests
class MyUnitTests extends FunSuite:

  test("A List should combine") {
    val l = List(1,2) |+| List(3,4)
    assert(l === List(1,2,3,4))
  }


// eof


