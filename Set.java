import java.util.Iterator;

/**
 * A collection that implements set behavior.
 *
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2016-03-01
 *
 */

public interface Set<T> extends Iterable<T> {

   /**
    * Ensures the collection contains the specified element.
    * No specific order can be assumed. Neither duplicate nor null
    * values are allowed.
    *
    * @param  element  The element whose presence is to be ensured.
    * @return true if collection is changed, false otherwise.
    */
   boolean add(T element);


   /**
    * Ensures the collection does not contain the specified element.
    * If the specified element is present, this method removes it
    * from the collection.
    *
    * @param   element  The element to be removed.
    * @return  true if collection is changed, false otherwise.
    */
   boolean remove(T element);


   /**
    * Searches for specified element in this collection.
    *
    * @param   element  The element whose presence in this collection is to be tested.
    * @return  true if this collection contains the specified element, false otherwise.
    */
   boolean contains(T element);


   /**
    * Returns the current size of this collection.
    *
    * @return  the number of elements in this collection.
    */
   int size();


   /**
    * Tests to see if this collection is empty.
    *
    * @return  true if this collection contains no elements, false otherwise.
    */
   boolean isEmpty();


   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements as
    *               the parameter set, false otherwise
    */
   boolean equals(Set<T> s);


   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and the parameter set
    */
   Set<T> union(Set<T> s);


   /**
    * Returns a set that is the intersection of this set and the parameter set.
    *
    * @return  a set that contains elements that are in both this set and the parameter set
    */
   Set<T> intersection(Set<T> s);


   /**
    * Returns a set that is the complement of this set and the parameter set.
    *
    * @return  a set that contains elements that are in this set but not the parameter set
    */
   Set<T> complement(Set<T> s);


   /**
    * Returns an iterator over the elements in this collection.
    * No specific order can be assumed.
    *
    * @return  an iterator over the elements in this collection
    */
   Iterator<T> iterator();

}
