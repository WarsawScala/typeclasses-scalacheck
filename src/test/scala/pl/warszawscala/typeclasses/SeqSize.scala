package pl.warszawscala.typeclasses

/**
 * Wrapper for sequence of elements with memoized size
 */
case class SeqSize[E](elems: Seq[E], size: Int) {
  /* Check the invariant inside constructor */
  require(elems.size == size)
}

object SeqSize {

  /**
   * Monoid typeclass instance for SeqSize[E]. Since the type is polymorphic, we define
   * an implicit method that takes a type parameter.
   *
   * Implicits defined inside the companion objects are available everywhere the class 
   * itself is used. This is a good practice for defining typeclass instances for your own
   * types, that live in a different package that the typeclass declaration.
   */
  implicit def seqSizeMonoid[E] = new Monoid[SeqSize[E]] {
    
	  def plus(ss1: SeqSize[E], ss2: SeqSize[E]) = SeqSize(ss1.elems ++ ss2.elems, ss1.size + ss2.size)

    def zero = SeqSize(Seq(), 0)
  
  }
}