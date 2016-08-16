import java.util.*;
import java.io.*;
import java.lang.*;

public class ArraySet<T extends Comparable<? super T>> implements Set<T>
{

   T[] elements;
   int size;

   @SuppressWarnings("unchecked")
   public ArraySet() {
      elements = (T[]) new Comparable[1];
      size = 0;
   }
   @SuppressWarnings("unchecked")
   private ArraySet(T[] a, int arraySize) {
      elements = (T[]) new Comparable[arraySize];
      System.arraycopy(a, 0 , elements, 0, arraySize);
      size = arraySize;
   }

   public int size() {
      return size;
   }

   public boolean isEmpty() {
      return size == 0;
   }
   public String toString() {
      if (isEmpty()) {
         return "[]";
      }
      StringBuilder result = new StringBuilder();
      result.append("[");
      for (T element : this) {
         result.append(element + ", ");
      }
      result.delete(result.length() - 2, result.length());
      result.append("]");
      return result.toString();
   }

   public boolean contains(T element) {
      int i = 0;
      while (this.size > i) {
         if (!elements.equals(element)) {
            i++;
         }
         if (i == size) {
            break;
         }
         if (elements.equals(element)) {
            return true;
         }
      }
      return false;
   }
   private int locate (T element) {
      int low = 0;
      int high = size - 1;
      int mid = 0;
      while (low <= high) {
         mid = (high + low) / 2;
         int compareElements = elements[mid].compareTo(element);
         if (compareElements > 0) {
            high = mid - 1;
         }
         if (compareElements < 0) {
            low = mid + 1;
         }
         if (compareElements == 0) {
            return mid;
         }
      }
      return -1;
   }

   private void resize(int capacity)
   {
      T[] newT=(T[]) (new Comparable[elements.length*2]);
      for(int k=0;k<elements.length;k++)
         newT[k]=elements[k];
      elements=newT;
   }


   public boolean add(T element) {
      if(!(contains(element)))
      {
         if(size() == elements.length)
            resize(elements.length * 2);
         elements[size]=element;
         size++;
         return true;
      }
      return false;
   }


   public boolean remove(T element) {
      int i = locate(element);
      if (i >= size || i == -1) {
         return false;
      }
      for (int j = i; j < size; j++) {
         elements[j] = elements[j + 1];
      }
      size--;
      if (size > 0 && size < (elements.length / 4)) {
         resize(elements.length / 2);
      }
      return true;
   }

   public boolean equals(ArraySet<T> s) {
      if (s.size != this.size) {
         return false;
      }
      for (int i = 0; i < elements.length; i++) {
         if (!s.contains(this.elements[i]) ) {
            return false;
         }
      }
   
      return true;
   }


   public ArraySet<T> union(ArraySet<T> s) {
      T[] list = Arrays.copyOf(elements, size);
      ArraySet<T> as = new ArraySet<T>();
      for (T value : elements) {
         as.add(value);
      }
      if (s.isEmpty()) {
         return as;
      }
      for (T otherValue : s) {
         as.add(otherValue);
      }
      return as;
   }

   public ArraySet<T> intersection(ArraySet<T> s) {
      ArraySet<T> as = new ArraySet<T>();
      for (T value : elements) {
         if (s.contains(value)) {
            as.add(value);
         }
      }
      return as;
   }

   public ArraySet<T> complement(ArraySet<T> s)
   {
      if(s==null||s.isEmpty())
         return null;
      ArraySet<T> as = new ArraySet<T>();
      for (T value : elements) {
         if (!s.contains(value))
         {
            as.add(value);
         }
      }
      return as;
   }
   private class DescendingIterator implements Iterator {
      private T[] items;
      private int count;
      private int current;
      public DescendingIterator(T[] elements, int size) {
         items = elements;
         count = 0;
         current = size - 1;
      }
      @Override public boolean hasNext() {
         return (current >= count) ;
      }
      @Override public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         return items[current--];
      }
      @Override public void remove() {
         throw new UnsupportedOperationException();
      }
   }

   private class MyPowerSetIterator {
      private T[] items;
      private int count;
      private int current;
      private int bit;
      public MyPowerSetIterator(T[] elements, int size) {
         items = elements;
         count = size;
         current = 0;
         bit = 0;
      }
      public boolean hasNext() {
         return (current < (int) Math.pow(2, size));
      }
      public Set next() {
         Set output = new ArraySet();
         int mask = 1;
         for (int i = 0; i < size; i++) {
            if ((bit & mask) != 0) {
               output.add(elements[size]);
            }
         }
         current++;
         bit = 0;
         return output;
      }
      public void remove() {
         throw new UnsupportedOperationExcpetion();
      }
   }
   private class ArrayIterator<T> implements Iterator<T> {
      private T[] items;
      private int count;
      private int current;
      public ArrayIterator(T[] elements, int size) {
         items = elements;
         count = size;
         current = 0;
      }
      @Override public boolean hasNext() {
         return (current < count);
      }
      @Override public T next() {
         if (!hasNext()) { throw new NoSuchElementException();
         }
         T result = items[current];
         current++;
         return result;
      }
      @Override public void remove() {
         throw new UnsupportedOperationException();
      }
   }
/** * Returns an iterator over the elements in this ArraySet.
* No specific order can be assumed. *
* @return an iterator over the elements in this ArraySet */
   public Iterator iterator() {
      return new ArrayIterator(elements, size);
   }
   /**public Iterator<T> iterator()
   {
      return new ArrayIterator<T>(elements,size());
   }*/

   public Iterator<T> descendingIterator()
   {
      return new DescendingIterator(elements,size());
   }

   public Iterator<T> powerSetIterator()
   {
      return new MyPowerSetIterator(elements,size());
   }
}