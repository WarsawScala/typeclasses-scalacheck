package pl.warszawscala.typeclasses

/**
 * Monoid is an algebraic structure with a single associative binary operation and an identity element.
 */
trait Monoid[T] {

  /**
   * An associative binary operation.
   */
  def plus(a: T, b: T): T

  /** The identity of the operation. */
  def zero: T

}