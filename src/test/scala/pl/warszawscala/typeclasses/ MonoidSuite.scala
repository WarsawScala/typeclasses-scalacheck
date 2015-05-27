package pl.warszawscala.typeclasses

import org.junit.runner.RunWith
import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary._
import org.scalacheck.Gen
import org.scalacheck.Prop.forAll
import org.scalacheck.Properties
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.Checkers

@RunWith(classOf[JUnitRunner])
class MonoidSuite extends FunSuite with Checkers {

  /**
   * Universal ScalaCheck testcase that can be used to validate Monoid typeclass 
   * instances.
   * 
   * The context bound MonoidLaws[T: Monoid : Arbitrary] is equivalent to:
   * MonoidLaws[T](implicit ev$1: Monoid[T], ev$2: Arbitrary[T])
   * 
   * Instantiating this class requires that appropriate instances of Monoid[T] and
   * Arbitrary[T] are present in the implicit scope.
   */
  class MonoidLaws[T: Monoid: Arbitrary] extends Properties("Monoid") {

    val m = implicitly[Monoid[T]]
    import m.zero

    property("zero should be a left identity element of plus operation") = forAll { a: T =>
      zero + a == a
    }

    property("zero should be a right identity element of plus operation") = forAll { a: T =>
      a + zero == a
    }

    property("plus operation should be associative") = forAll { (a: T, b: T, c: T) =>
      (a + b) + c == a + (b + c)
    }

  }

  test("String monoid should satisfy requisite laws") {
    check(new MonoidLaws[String])
  }

  test("Int monoid should satisfy requisite laws") {
    check(new MonoidLaws[Int])
  }

  /* 
   * In order to instantiate MonoidLaws[T] we must provide an instance of Arbitrary[T].
   *  
   * To check SeqSize[E] monoid, let's fix the parameter type E to be Char. We can construct 
   * a Gen[SeqSize[Char]] using arbitrary lists generator provided by ScalaCheck.
   */
  val seqSizeGen = for {
    l <- arbitrary[List[Char]]
  } yield SeqSize(l, l.size)
  
  /* Then, we use the generator to construct required Arbitrary[SeqSize[Char]] implicit */
  implicit val arbitrarySeqSize = Arbitrary(seqSizeGen)

  test("SeqSize monoid should satisfy requisite laws") {
    check(new MonoidLaws[SeqSize[Char]])
  }

}