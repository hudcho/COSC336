import java.io.*;
import java.util.*;

public class Asgmt4Task1 {

   public static void main(String[] args) {
      // Hard-coded array
      int[] arr1 = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
      // Will hold prices from input-4.2.txt
      int[] arr42 = null;
      // Will hold prices from input-4.3.txt
      int[] arr43 = null;
      
      // Read the integers from each file into separate arrays
      try {
         arr42 = readIntegersFromFile("input-4.2.txt");
         arr43 = readIntegersFromFile("input-4.3.txt");
      } catch (FileNotFoundException e) {
         System.err.println("File not found: " + e.getMessage());
      }
      
      // Call printCutRodSolution to process and display solutions for each set
      System.out.println("Data set 1:");
      printCutRodSolution(arr1, arr1.length);
      
      System.out.println("Data set 2:");
      printCutRodSolution(arr42, arr42.length);
      
      System.out.println("Data set 3:");
      printCutRodSolution(arr43, arr43.length);
   } // end main()
   
    /**
       * Calculates the maximum revenue obtainable from a rod of length n and the corresponding first-cut sizes that lead to that revenue
       *
       * @param p An array of prices where p[i-1] is the price for a rod of length i
       * @param n The total length of the rod
       * @return A 2D array where the first row is the revenue array r and the second row is the first cut array s
    */
   
   public static int[][] extendedBottomUpCutSolution(int[] p, int n) {
      // r[j] will hold the maximum revenue that can be obtained from a rod of length j.
      int[] r = new int[n + 1];
      // s[j] will store the length of the first piece to cut off when a rod of length j is optimally cut.
      int[] s = new int[n + 1];
      // Base case: A rod of length 0 has 0 revenue.
      r[0] = 0;
      
      // Calculate the optimal revenue for every rod length j from 1 to n
      for (int j = 1; j <= n; j++){
          // Initialize maxRevenue  to the smallest possible integer so any subsequent revenue is greater
         int maxRevenue  = Integer.MIN_VALUE;
         
         // Consider every possible first cut of length i (from 1 to j)
         // p[i-1] is the revenue for a rod piece of length i.
         // r[j-i] is the optimal revenue for the remaining rod of length j-i.
         for (int i = 1; i <= j; i++){
            if (maxRevenue  < p[i - 1] + r[j - i]) {
               maxRevenue  = p[i - 1] + r[j - i]; // Update maximum revenue if this cut is better.
               s[j] = i;                // Record the best first cut length for rod length j
            }
         }
         // store the best revenue found
         r[j] = maxRevenue;
      }
      return new int[][] { r, s };
   } //end extendedBottomUpCutSolution
   
    /**
       * Reconstructs and prints the optimal cuts for a given rod
       *
       * Calls extendedBottomUpCutSolution() to calculate the maximum revenue for a rod of length n and the optimal first cut for each rod length
       *
       * Reconstructs the sequence of cuts that yields the maximum revenue
       *
       * @param p An array of prices where p[i-1] is the price for a rod of length i
       * @param n The total length of the rod
    */
    
   public static void printCutRodSolution (int[] p, int n){
      // Calls extendedBottomUpCutSolution() to calculate the maximum revenue for a rod of length n and the optimal first cut for each rod length
      int[][] result = extendedBottomUpCutSolution(p, n);
      int[] r = result [0];
      int[] s = result [1];
      // Prints the maximum revenue obtainable for a rod of length n
      System.out.println("Max revenue: " + r[n]);
      // Reconstructs and print the list of cuts that result in optimal revenue
      System.out.print("Cut length(s): ");
      while (n > 0) {
         // s[n] is the length of the first piece to cut off for a rod of length n
         System.out.print(s[n] + " ");
         // Reduce n by the length of the first cut, then repeat until no length remains
         n = n - s[n];
      }
      System.out.println("\n----------------------------------------------");

   
   }// end printCutRodSolution
 
    /**
       * Reads an array of integers from a file.
       *
       * @param fileName The name of the file to read
       * @return An array of integers representing the price table
       * @throws FileNotFoundException if the file is not found
    */
   public static int[] readIntegersFromFile(String fileName) throws FileNotFoundException {
      File file = new File(fileName);
      Scanner scanner = new Scanner(file);
      
      // Read the first integer as the count
      if (!scanner.hasNextInt()) {
         scanner.close();
         return new int[0];
      }
      int len = scanner.nextInt();
      
      // Create an array of the specified size
      int[] prices = new int[len];
      
      // Read exactly 'len' integers from the file
      for (int i = 0; i < len; i++) {
         if (scanner.hasNextInt()) {
            prices[i] = scanner.nextInt();
         } else {
            System.err.println("Expected " + len + " numbers, but found fewer.");
            break;
         }
      }
      scanner.close();
      return prices;
   }// end readIntegersFromFile
   
} // Asgmt4Task1
