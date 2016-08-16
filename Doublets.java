import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Doublets.java
 * Provides an implementation of the WordLadderGame interface. The lexicon
 * is stored as a TreeSet of Strings.
 *
 * @author Mathew Coggins (mtc0034@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2016-04-07
 */
public class Doublets implements WordLadderGame {

   ////////////////////////////////////////////
   // DON'T CHANGE THE FOLLOWING TWO FIELDS. //
   ////////////////////////////////////////////

   // A word ladder with no words. Used as the return value for the ladder methods
   // below when no ladder exists.
   List<String> EMPTY_LADDER = new ArrayList<>();

   // The word list used to validate words.
   // Must be instantiated and populated in the constructor.
   TreeSet<String> lexicon;


   /**
    * Instantiates a new instance of Doublets with the lexicon populated with
    * the strings in the provided InputStream. The InputStream can be formatted
    * in different ways as long as the first string on each line is a word to be
    * stored in the lexicon.
    */
   public Doublets(InputStream in) {
      try {
         lexicon = new TreeSet<String>();
         Scanner s =
            new Scanner(new BufferedReader(new InputStreamReader(in)));
         while (s.hasNext()) {
            String str = s.next();
            lexicon.add(str.toLowerCase());
            s.nextLine();
         }
         in.close();
      }
      catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }

   ///////////////////////////////////////////////////////////////////////////////
   // Fill in implementations of all the WordLadderGame interface methods here. //
   ///////////////////////////////////////////////////////////////////////////////

/**
    * Returns the Hamming distance between two strings, str1 and str2. The
    * Hamming distance between two strings of equal length is defined as the
    * number of positions at which the corresponding symbols are different. The
    * Hamming distance is undefined if the strings have different length, and
    * this method returns -1 in that case. See the following link for
    * reference: https://en.wikipedia.org/wiki/Hamming_distance
    *
    * @param  str1 the first string
    * @param  str2 the second string
    * @return      the Hamming distance between str1 and str2 if they are the
    *                  same length, -1 otherwise
    */
   public int getHammingDistance(String str1, String str2) {
      int result = 0;
   
      if (str1.length() != str2.length()) {
         return -1; 
      }
   
      for (int i = 0; i < str1.length(); i++) {
         if (str1.charAt(i) != str2.charAt(i)) {
            result++;
         }
      }
      return result;
   }


  /**
   * Returns a word ladder from start to end. If multiple word ladders exist,
   * no guarantee is made regarding which one is returned. If no word ladder exists,
   * this method returns an empty list.
   *
   * Depth-first search with backtracking must be used in all implementing classes.
   *
   * @param  start  the starting word
   * @param  end    the ending word
   * @return        a word ladder from start to end
   */
   public List<String> getLadder(String start, String end) {
      List<String> result = new ArrayList<String>();
        
      if (start.equals(end)) {
         result.add(start);
         return result;
      }
      else if (start.length() != end.length()) {
         return EMPTY_LADDER;
      }
      else if (!isWord(start) || !isWord(end)) {
         return EMPTY_LADDER;
      }
          
      TreeSet<String> one = new TreeSet<>();
      Deque<String> stack = new ArrayDeque<>();
       
      stack.addLast(start);
      one.add(start);
      while (!stack.isEmpty()) {
       
         String current = stack.peekLast();
         if (current.equals(end)) {
            break;
         }
         List<String> neighbors1 = getNeighbors(current);
         List<String> neighbors = new ArrayList<>();
          
         for (String word : neighbors1) {
            if (!one.contains(word)) {
               neighbors.add(word);
            }
         }
         if (!neighbors.isEmpty()) {
            stack.addLast(neighbors.get(0));
            one.add(neighbors.get(0));
         }
         else {
            stack.removeLast();
         }
      }
      result.addAll(stack);
      return result;
   }


  /**
   * Returns a minimum-length word ladder from start to end. If multiple
   * minimum-length word ladders exist, no guarantee is made regarding which
   * one is returned. If no word ladder exists, this method returns an empty
   * list.
   *
   * Breadth-first search must be used in all implementing classes.
   *
   * @param  start  the starting word
   * @param  end    the ending word
   * @return        a minimum length word ladder from start to end
   */
   public List<String> getMinLadder(String start, String end) {
      List<String> ladder = new ArrayList<String>();
      if (start.equals(end)) {
         ladder.add(start);
         return ladder;
      }
      else if (start.length() != end.length()) {
         return EMPTY_LADDER;
      }
      else if (!isWord(start) || !isWord(end)) {
         return EMPTY_LADDER;
      }
    
      Deque<Node> q = new ArrayDeque<>();
      TreeSet<String> one = new TreeSet<>();
    
      one.add(start);
      q.addLast(new Node(start, null));
      while (!q.isEmpty()) {
       
         Node n = q.removeFirst();
         String position = n.position;
          
         for (String neighbor1 : getNeighbors(position)) {
            if (!one.contains(neighbor1)) {
               one.add(neighbor1);
               q.addLast(new Node(neighbor1, n));
            }
            if (neighbor1.equals(end)) {
             
               Node m = q.removeLast();
               
               while (m != null) {
                  ladder.add(0, m.position);
                  m = m.predecessor;
               }
               return ladder;
            }
         }
      }      
      return EMPTY_LADDER;
   }


   /**
    * Returns all the words that have a Hamming distance of one relative to the
    * given word.
    *
    * @param  word the given word
    * @return      the neighbors of the given word
    */
   public List<String> getNeighbors(String word) {
      List<String> neighbor = new ArrayList<String>();
      TreeSet<String> set = new TreeSet<String>();
       
      if (word == null)
         return EMPTY_LADDER;
      
      for (String fill : lexicon) {
         if (getHammingDistance(word, fill) == 1)
            neighbor.add(fill);
      }
      
      return neighbor;
   }


   /**
    * Returns the total number of words in the current lexicon.
    *
    * @return number of words in the lexicon
    */
   public int getWordCount() {
      int count = lexicon.size();
      return count;
   }


   /**
    * Checks to see if the given string is a word.
    *
    * @param  str the string to check
    * @return     true if str is a word, false otherwise
    */
   public boolean isWord(String str) {
      if (lexicon.contains(str))
      {
         return true;
      }
      return false;
   }


   /**
    * Checks to see if the given sequence of strings is a valid word ladder.
    *
    * @param  sequence the given sequence of strings
    * @return          true if the given sequence is a valid word ladder,
    *                       false otherwise
    */
   public boolean isWordLadder(List<String> sequence) {
      int count = 0;
      if ((sequence == null) || (sequence.isEmpty())) {
         return false;
      }
      
      for (int i = 0; i < sequence.size() - 1; i++){
         if (isWord(sequence.get(i)) != true || isWord(sequence.get(i + 1)) != true || (getHammingDistance(sequence.get(i), sequence.get(i + 1)) != 1))
            count++;  
      }
      boolean x = (count == 0);
      return x;
   }
   
   private class Node {
      String position;
      Node predecessor;
   
      public Node(String p, Node pred) {
         position = p;
         predecessor = pred;
      }
   }
}
