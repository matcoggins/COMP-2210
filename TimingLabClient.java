/**
 * TimingLabClient.java. 
 * Provides a simple example client of TimingLab.java.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2016-02-09
 * 
 */
public class TimingLabClient {

   /** drives execution. */
   public static void main(String[] args) {
   
      /** useful contants and variables */
   
      // number of nanoseconds in one second   
      double BILLION = 1000000000d;
   
      // start time of the current run
      double start = 0;
   
      // elapsed time of the current run
      double elapsedTime = 0;
   
      // elapsed time of the previous run
      double prevTime = 0;
   
      // ratio of current run's elapsed time to
      // previous run's elapsed time:
      // (elapsedTime / prevTime)
      double ratio = 1;
   
      // problem size for the current run
      int N = 8;
   
      // group number
      // used to select one of the provided
      // internal (hidden) methods of the
      // RunningTime class
      int key = 56;
   
      /** generate timing data */
   
      // time a single method in this class
      start = System.nanoTime();
      methodToTime();
      elapsedTime = (System.nanoTime() - start) / BILLION;
      System.out.print("This call to method methodToTime() took ");
      System.out.printf("%4.3f", elapsedTime);
      System.out.println(" seconds.");
   
      // measure elapsed time for a single call to timeTrial
      TimingLab tl = new TimingLab(key);
      start = System.nanoTime();
      tl.timeTrial(N);
      elapsedTime = (System.nanoTime() - start) / BILLION;
      System.out.print("This call to method TimingLab.timeTrial("
         + N + ") took ");
      System.out.printf("%4.3f", elapsedTime);
      System.out.println(" seconds.");
   
      // measure elapsed time for multiple calls to timeTrial
      // with doubling N values
      System.out.print("Timing multiple calls to timeTrial(N) ");
      System.out.println("with increasing N values.");
      System.out.println("N\tElapsed Time (sec)");
      for (int i = 0; i < 7; i++) {
         start = System.nanoTime();
         tl.timeTrial(N);
         elapsedTime = (System.nanoTime() - start) / BILLION;
         System.out.print(N + "\t");
         System.out.printf("%4.3f\n", elapsedTime);
         N = N * 2;
      }
   
   }

   /**
    * Provides enough work to be timed. Hopefully will
    * require more than 0.001 sec on most machines.
    */
   private static void methodToTime() {
      for (int i = 0; i < 100000; i++) {
         String s1 = "War";
         String s2 = "Eagle";
         String s3 = s1 + s2;
         s1 = null;
         s2 = null;
         s3 = null;
      }
   }

}
