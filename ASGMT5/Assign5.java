import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Assign5 {

    public static void main(String[] args) throws FileNotFoundException {
        int[] x;

        for (int k = 0; k < 4; k++) {

            x = fileInts();

            print(x, 0, x.length - 1);

            int max_Sub = findMaxSubSeq(x);

            System.out.print("Max Sum Sequence : " + max_Sub + " : ");

            printLinkedList(maxSum);

            System.out.println();
        }

    }

    public static LinkedList<Integer> maxSum = new LinkedList<>();

    public static int findMaxSubSeq(int[] y) {
        int max = 0;
        int[] tableSum = new int[y.length];

        for (int i = 0; i < y.length; i++) {

            tableSum[i] = y[i];

            for (int j = 0; j < i; j++) {

                if (y[j] <= y[i]) {

                    tableSum[i] = Math.max(tableSum[i], y[i] + tableSum[j]);

                }

            }
            max = Math.max(max, tableSum[i]);

        }

        return max;

    }

    public static void printLinkedList(LinkedList<Integer> x) {

        System.out.print("[ ");

        for (int i = 0; i < x.size(); i++) {

            System.out.print(x.get(i));

            if (i < x.size() - 1) {
                System.out.print(", ");
            } else {
                System.out.print(" ");
            }

        }
        System.err.println("]");
    }

    // Done : builds array from file correctly//
    public static int[] fileInts() throws FileNotFoundException {
        Scanner scnr = new Scanner(System.in);

        System.out.print("Input File Name : ");
        String userVar = scnr.next();

        try {
            while (!new File(userVar).exists()) {
                System.out.print("Input File Name : ");
                userVar = scnr.next();
            }
            Scanner scnrX = new Scanner(new File(userVar));

            // Grab size n//
            int n = scnrX.nextInt();

            int x[] = new int[n];

            for (n = 0; n < x.length; n++) {

                if (scnrX.hasNextInt()) {
                    x[n] = scnrX.nextInt();
                } else {// Out Of Room//
                    n = x.length;
                }

            }

            return x;
        } catch (FileNotFoundException e) {
            System.out.println("Error File Not Found...");
            return null;
        }
    }

    // Done : Array printing, Use index values in call//
    public static void print(int[] y, int start, int end) {
        System.out.print("Array Size: " + (end - start + 1) + " : ");
        // System.out.print("( " + start + ", " + end + " ) : " + "[ ");
        System.out.print(" [ ");

        for (int i = start; i <= (end); i++) {

            System.out.print(y[i]);

            if (i < end) {
                System.out.print(", ");
            } else {
                System.out.print(" ");
            }
        }
        System.err.println("]");
    }

/* 'Assign5' 

Input File Name : input-5.1.txt
Array Size: 6 :  [ 1, 14, 5, 5, 2, 5 ]
Max Sum Sequence : 16 : [ ]

Input File Name : input-5.2.txt
Array Size: 12 :  [ 41, 18467, 6334, 26500, 19169, 15724, 11478, 29358, 26962, 24464, 30000, 30000 ]
Max Sum Sequence : 134366 : [ ]

Input File Name : input-5.3.txt
Array Size: 12 :  [ 50000, 25053, 4601, 4540, 20255, 23073, 17419, 10282, 3621, 32092, 945, 50000 ]
Max Sum Sequence : 130021 : [ ]

PS C:\Users\Rwils\Desktop\Assignment5> 
 */
}