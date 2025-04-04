import java.util.*;
import java.io.*;

public class Asgmt5Task {
    public static void main(String[] args) {
        // For reading from file 
    	try (Scanner sc = new Scanner(new File("input-5.4.txt"))) {
    		// Read n
            int n = sc.nextInt();
            
            // Read the array
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            
            // s[i] stores max sum ending at index i
            int[] s = new int[n];
            // p[i] stores previous index in the optimal subsequence
            int[] p = new int[n];
            
            // Initialize first element
            s[0] = a[0];
            p[0] = -1;
            
            // Fill s[] and p[] arrays
            for (int i = 1; i < n; i++) {
                s[i] = a[i];  // s[i] = max sum of an increasing subsequence with last element a[i]
                p[i] = -1;    // index of the element preceding a[i] in an increasing subsequence with max sum and last element a[i]
                
                // Check all previous elements
                for (int j = 0; j < i; j++) {
                    if (a[j] <= a[i] && s[j] + a[i] > s[i]) {
                        s[i] = s[j] + a[i];
                        p[i] = j;
                    }
                }
            }
            
            // Find maximum sum and its ending index
            int maxSum = s[0];
            int endIndex = 0;
            for (int i = 1; i < n; i++) {
                if (s[i] > maxSum) {
                    maxSum = s[i];
                    endIndex = i;
                }
            }
            
            // Reconstruct the subsequence
            List<Integer> subsequence = new ArrayList<>();
            int current = endIndex;
            while (current != -1) {
                subsequence.add(a[current]);
                current = p[current];
            }
            
            // Print results
            System.out.println("Maximum sum: " + maxSum);
            System.out.print("Increasing subsequence: ");
            // Print in correct order (reverse the list)
            for (int i = subsequence.size() - 1; i >= 0; i--) {
                System.out.print(subsequence.get(i));
                if (i > 0) System.out.print(" ");
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found");
            e.printStackTrace();
        } // end try catch
    	           
    } // end main method
} // end class