package pl.warszawscala

/**
 * Package object for [[pl.warszawscala.typeclasses]] package.
 *
 * This is a convenient place for defining typelcass instances for built-in types,
 * since importing [[pl.warszawscala.typeclasses.Monoid]] will make them available
 * automatically.
 */
package object typeclasses {

  /**
   * Monoid typeclass instance for [[scala.String]]
   */
  implicit val stringMonoid = new Monoid[String] {

    /** String concatenation */
    def plus(s1: String, s2: String) = s1 + s2

    /** Empty string */
    def zero = ""
  }

  /**
   * Monoid typeclass instance for integers with addition.
   *
   * Multiplication with 1 as identity element is a valid alternative, but we cannot
   * define both in the same scope - that would result in "ambiguous implicit values" error.
   */
  implicit val integerAdditionMonoid = new Monoid[Int] {

    /** Integer addition */
    def plus(i1: Int, i2: Int) = i1 + i2

    /** 0, the identity element of addition. */
    def zero = 0
  }

}