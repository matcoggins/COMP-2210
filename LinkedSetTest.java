import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;


public class LinkedSetTest {

   LinkedSet<Integer> a = new LinkedSet<Integer>();
   LinkedSet<Integer> s = new LinkedSet<Integer>();

   @Test public void addTest() {
      a.add(4);
      a.add(1);
      a.add(5);
      a.add(3);
      a.add(1);
      a.add(2);
      Assert.assertFalse(a.add(1));
      Assert.assertEquals("Size",a.size(),5);
   }
   
   @Test public void removeTest() {
      a.add(4);
      a.add(1);
      a.add(5);
      a.add(3);
      a.add(2);
      a.remove(4);
      a.remove(1);
      a.remove(5);
      a.remove(2);
      Assert.assertEquals("Size",a.size(),1);
      a.remove(3);
      Assert.assertTrue(a.front == null);
      Assert.assertTrue(a.rear == null);
   }

   @Test public void sizeTest() {
      a.add(4);
      a.add(1);
      a.remove(5);
      a.add(3);
      a.add(2);
      Assert.assertEquals("Size Test",a.size(),4);
   }

   @Test public void containsTest() {
      Assert.assertFalse(a.contains(4));
      a.add(4); 
      Assert.assertTrue(a.contains(4));
      a.add(1); 
      a.add(5); 
      a.add(3); 
      a.add(1); 
      a.add(2); 
      Assert.assertTrue(a.contains(3));
      a.remove(4); 
      Assert.assertFalse(a.contains(4));
      a.remove(1); 
      a.remove(5); 
      Assert.assertFalse(a.contains(5));
      Assert.assertEquals("Size",a.size(),2);
      a.remove(3);
      a.remove(2);
      Assert.assertTrue(a.isEmpty());
   }
   
   @Test public void intersectionTest() {
      Set<Integer> expected = new LinkedSet<Integer>();
      a.add(1);
      a.add(2);
      a.add(3);
      s.add(1);
      s.add(2);
      s.add(3);
      expected = a.intersection(s);
      Iterator itr = expected.iterator();
      Assert.assertTrue(!expected.isEmpty());
      Assert.assertEquals(itr.next(),1);
      Assert.assertEquals(itr.next(),2);
      Assert.assertEquals(itr.next(),3);
   }
   
   @Test public void complementTest() {
      Set<Integer> expected = new LinkedSet<Integer>();
      a.add(1);
      a.add(2);
      a.add(3);
      s.add(1);
      s.add(2);
      s.add(3);
      a.add(4);
      expected = a.complement(s);
      Iterator itr = expected.iterator();
      Assert.assertEquals(itr.next(),4);
   }

   @Test public void itrTest() {
      Set<Integer> s = new LinkedSet<Integer>();
      s.add(1);
      s.add(2);
      s.add(3);
      s.add(5);
      Iterator itr = s.iterator();
      Assert.assertEquals(itr.next(),1);
      Assert.assertEquals(itr.next(),2);
      Assert.assertEquals(itr.next(),3);
      Assert.assertEquals(itr.next(),5);
      Assert.assertFalse(itr.hasNext());
   }

   @Test public void itr2Test() {
      s.add(1);
      s.add(2);
      s.add(3);
      Iterator itr2 = s.descendingIterator();
      Assert.assertTrue(itr2.hasNext());
      Assert.assertEquals(3,itr2.next());
      Assert.assertTrue(itr2.hasNext());
      Assert.assertEquals(2,itr2.next());
      Assert.assertTrue(itr2.hasNext());
      Assert.assertEquals(1,itr2.next());
      Assert.assertFalse(itr2.hasNext());
   
   }
   
   // cant get to work  
   @Test public void itr3Test() {
   }
   
   @Test public void unionSetTest() {
      LinkedSet setU1 = new LinkedSet();
   
      Set setU2 = new LinkedSet();
      setU2.add(1);
      setU2.add(2);
      setU2.add(3);
      
      Set<Integer> unionSet = setU1.union(setU2);
      
      Assert.assertEquals(unionSet.size(), 3);
   }
   
   @Test public void unionLinkedSetTest() {
      LinkedSet setUL = new LinkedSet();
   
      LinkedSet setUL2 = new LinkedSet();
      setUL2.add(1);
      setUL2.add(2);
      setUL2.add(3);
      
      Set<Integer> unionLinkedSet = setUL.union(setUL2);
      
      Assert.assertEquals(unionLinkedSet.size(), 3);
   }
   
   @Test
   public void unionWebcatTest() {
      a.add(1);
      a.add(2);
      a.add(3);
      s.add(1);
      s.add(2);
      s.add(3);
      Set<Integer> union = new LinkedSet<Integer>();
      union = a.union(s);
      Iterator itr = union.iterator();
      Assert.assertFalse(union.isEmpty());
      Assert.assertEquals(itr.next(), 1);
      Assert.assertEquals(itr.next(), 2);
      Assert.assertEquals(itr.next(), 3);
   }
   
   @Test
   public void unionWebcatTest2() {
      Set<Integer> b = new LinkedSet<Integer>();
      Set<Integer> c = new LinkedSet<Integer>();
      Set<Integer> d = new LinkedSet<Integer>();
   
      a.add(1);
      a.add(2);
      a.add(3);
      b.add(6);
      b.add(5);
      b.add(4);
      Set<Integer> union = new LinkedSet<Integer>();
      union = a.union(b);
      Iterator itr = union.iterator();
      Assert.assertFalse(union.isEmpty());
      Assert.assertEquals(itr.next(), 1);
      Assert.assertEquals(itr.next(), 2);
      Assert.assertEquals(itr.next(), 3);
      Assert.assertEquals(itr.next(), 4);
      Assert.assertEquals(itr.next(), 5);
      Assert.assertEquals(itr.next(), 6);
   }

   @Test
   public void complementWebcatTest() {
      a.add(1);
      a.add(2);
      a.add(3);
      s.add(1);
      s.add(2);
      s.add(3);
      s.add(4);
      Set<Integer> complement = new LinkedSet<Integer>();
      complement = a.complement(s);
      Assert.assertTrue(complement.isEmpty());
      Assert.assertTrue(complement != null);
   }
   
   @Test public void complementSetTest() {
      LinkedSet setC = new LinkedSet();
      
      setC.add(1);
      setC.add(2);
      setC.add(3);
      
      Set setC2 = new LinkedSet();
      setC2.add(2);
      setC2.add(3);
      setC2.add(1);
      
      Assert.assertEquals("", setC.complement(setC2).size(), 0);
   }
   
   @Test public void complementLinkedSetTest() {
      LinkedSet setLinkedC = new LinkedSet();
      
      setLinkedC.add(1);
      setLinkedC.add(2);
      setLinkedC.add(3);
      
      Set setLinkedC2 = new LinkedSet();
      setLinkedC2.add(1);
      setLinkedC2.add(2);
      setLinkedC2.add(3);
      
      Assert.assertEquals("", setLinkedC.complement(setLinkedC2).size(), 0);
   }
   
   @Test public void remove2Test() {
      LinkedSet setR = new LinkedSet();
      setR.add(1);
      setR.remove(2);
      setR.remove(1);
   
      Assert.assertEquals("", setR.size(), 0);
   }
}
