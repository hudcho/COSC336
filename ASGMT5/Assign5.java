import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assign5 {

    public static void main(String[] args) throws FileNotFoundException {
        int[] x;
        x = fileInts();
        print(x, 0, x.length-1);


    }

    public static void findMaxSubSeq(int[] y) {
        

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
            System.out.println();
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

    //Done : Array printing, Use index values in call//
    public static void print(int[] y, int start, int end) {
        System.out.print("Array Size: " + (end - start + 1) + "  : ");

        System.out.print("( " + start + ", " + end + " ) : " + "[ ");

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
}
