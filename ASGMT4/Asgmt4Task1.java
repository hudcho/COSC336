import java.io.*;
import java.util.*;

public class Asgmt4Task1 {

   public static void main(String[] args) {
      // Your original array
      int[] s1 = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

      try {
         // Read the integers from each file into separate arrays
         int[] arr42 = readIntegersFromFile("input-4.2.txt");
         int[] arr43 = readIntegersFromFile("input-4.3.txt");

         // Display the arrays for further manipulation
         System.out.println("Hard-coded array: " + Arrays.toString(s1));
         System.out.println("Array from input-4.2.txt: " + Arrays.toString(arr42));
         System.out.println("Array from input-4.3.txt: " + Arrays.toString(arr43));

      } catch (FileNotFoundException e) {
         System.err.println("File not found: " + e.getMessage());
      }
   }
   
      // Helper method to read integers from a file,
   // where the first number is the total count of the following integers.
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
      int[] numbers = new int[len];
      
      // Read exactly 'len' integers from the file
      for (int i = 0; i < len; i++) {
         if (scanner.hasNextInt()) {
            numbers[i] = scanner.nextInt();
         } else {
            System.err.println("Expected " + len + " numbers, but found fewer.");
            break;
         }
      }
      scanner.close();
      return numbers;
   }
}
