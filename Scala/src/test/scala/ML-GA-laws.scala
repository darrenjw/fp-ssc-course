import cats.*
import cats.implicits.*

import munit.DisciplineSuite

// Example Cats laws tests

class MyLawTests extends DisciplineSuite:

  import cats.kernel.laws.discipline.SemigroupTests

  checkAll("List[Int].SemigroupLaws", SemigroupTests[List[Int]].semigroup)

  import cats.laws.discipline.FunctorTests

  checkAll("List.FunctorLaws", FunctorTests[List].functor[Int, Int, String])

  import cats.laws.discipline.MonadTests
  // checking monad laws is quite slow
  //checkAll("List.MonadLaws", MonadTests[List].monad[Int, Int, String])



// eof


