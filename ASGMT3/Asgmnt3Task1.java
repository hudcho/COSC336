import java.util.*;
import java.io.*;

public class Asgmnt3Task1 {
    public static void main(String[] args) throws FileNotFoundException  {
        int[] arr = {7, 3, 8, 1, 5};
        System.out.println("Data Set 1:");
        // countUpPairs sorts the array and returns the number of UP-pairs.
        System.out.println("UP-Pairs count: " + countUpPairs(arr));
        System.out.println("Sorted array: " + Arrays.toString(arr));
        System.out.println();

        // Prompt the user to enter filenames for additional data sets
        Scanner console = new Scanner(System.in);
        System.out.println("Enter filenames (separated by spaces):");
        String inputLine = console.nextLine().trim();
        if (!inputLine.isEmpty()) {
            // Split the input string into individual filenames
            String[] filenames = inputLine.split("\\s+");
            for (String filename : filenames) {
                try {
                    Scanner fileScanner = new Scanner(new File(filename));
                    // The first integer in the file is the number of elements
                    int n = fileScanner.nextInt();
                    int[] fileInputArr = new int[n];
                    // Read the next n integers from the file into the array
                    for (int i = 0; i < n; i++) {
                        fileInputArr[i] = fileScanner.nextInt();
                    }
                    fileScanner.close();
                    // Process the array: sort it and count the UP-pairs using the modified merge sort
                    int count = countUpPairs(fileInputArr);
                    System.out.println("File: " + filename);
                    System.out.println("UP-Pairs count: " + count);
                    System.out.println("Sorted array: " + Arrays.toString(fileInputArr));
                    System.out.println();
                } catch (FileNotFoundException e) {
                    System.err.println("File not found: " + filename);
                }
            }
        }
    }

    /**
     * countUpPairs:
     * Initiates the modified merge sort that counts UP-pairs.
     *
     * @param A the input array of numbers.
     * @return the total number of UP-pairs in the array.
     */
    public static int countUpPairs(int[] A) {
        return divideAndCount(A, 0, A.length - 1);
    }

    /**
     * divideAndCount:
     * Recursively divides the array into halves, counts the UP-pairs in
     * each half, and then counts the UP-pairs that cross the two halves during the merge.
     *
     * @param A the array to process.
     * @param p the starting index.
     * @param r the ending index.
     * @return the number of UP-pairs in the subarray A[p..r].
     */
    public static int divideAndCount(int[] A, int p, int r) {
        if (p >= r)
            return 0;
        int q = p + ((r - p) / 2);
        // Recursively count UP-pairs in the left half, right half, and across the halves.
        return divideAndCount(A, p, q) 
             + divideAndCount(A, q + 1, r) 
             + mergeAndCount(A, p, q, r);
    }

    /**
     * mergeAndCount:
     * Merges two sorted subarrays A[p...q] and A[q+1...r] while counting UP-pairs.
     * If an element in the left subarray (L) is less than an element in the right subarray (R),
     * then all remaining elements in R will form UP-pairs with that element from L.
     *
     * @param A the array containing the two subarrays.
     * @param p the starting index of the first subarray.
     * @param q the ending index of the first subarray.
     * @param r the ending index of the second subarray.
     * @return the number of UP-pairs counted during the merge.
     */
    public static int mergeAndCount(int[] A, int p, int q, int r) {
        int nL = q - p + 1; // Length of left subarray
        int nR = r - q;     // Length of right subarray

        int[] L = new int[nL];
        int[] R = new int[nR];

        // Copy A[p...q] into L and A[q+1...r] into R
        for (int i = 0; i < nL; i++) {
            L[i] = A[p + i];
        }
        for (int j = 0; j < nR; j++) {
            R[j] = A[q + j + 1];
        }
        int i = 0, j = 0, k = p, count = 0;
        // Merge the two sorted arrays back into A while counting UP-pairs.
        // When L[i] < R[j], all elements after R[j] form an UP-pair with L[i].
        while (i < nL && j < nR) {
            if (L[i] < R[j]) {
                count += (nR - j);  // All remaining elements in R are greater than L[i]
                A[k++] = L[i++];
            } else {
                A[k++] = R[j++];
            }
        }
        // Copy any remaining elements of L into A.
        while (i < nL) {
            A[k] = L[i];
            i++;
            k++;
        }
        // Copy any remaining elements of R into A.
        while (j < nR) {
            A[k] = R[j];
            j++;
            k++;
        }
        // Return the count of UP-pairs found during the merge.
        return count;
    }
}
