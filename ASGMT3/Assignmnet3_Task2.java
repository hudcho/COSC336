import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Assignmnet3_Task2 {
    public static int rowF = 0;
    public static int colF = 0;

    public static void main(String[] args) throws FileNotFoundException {
        int[][] x = fileInts();

        print(x, x.length, x[0].length);

        System.out.println("\nOptomize path (0, 0) to (" + rowF + ", " + colF + ")");

    }

    // Done : builds array from file correctly//
    public static int[][] fileInts() throws FileNotFoundException {
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

            // Grab size row x col first//
            int n = scnrX.nextInt();
            int m = scnrX.nextInt();
            // Public static goal index//
            rowF = scnrX.nextInt();
            colF = scnrX.nextInt();

            int x[][] = new int[n][m];

            for (n = 0; n < x.length; n++) {
                for (m = 0; m < x[0].length; m++) {
                    if (scnrX.hasNextInt()) {
                        x[n][m] = scnrX.nextInt();
                    } else {// Out Of Room//
                        n = x.length;
                        m = x[0].length;
                    }
                }
            }

            return x;
        } catch (FileNotFoundException e) {
            System.out.println("Error File Not Found...");
            return null;
        }
    }

    // RandomFill for completeness//
    public static int[][] fillInts(int sizeRow, int sizeCol, int min, int max) {
        Random randi = new Random();
        int[][] y = new int[sizeRow][sizeCol];

        int i;
        int j;

        for (i = 0; i < y.length; i++) {
            for (j = 0; j < y[0].length; j++) {
                y[i][j] = randi.nextInt(min, max + 1);
            }
        }

        return y;
    }

    // Print 2D int array//
    public static void print(int[][] y, int n, int m) {
        System.out.println("Integer Matrix Size : [" + n + "]" + "[" + m + "]");

        int i;
        int j;

        for (i = 0; i < y.length; i++) {
            System.out.print("[ ");

            for (j = 0; j < y[0].length; j++) {

                if (j != 0) {
                    System.out.print(", ");
                }
                System.out.print(y[i][j]);

            }

            System.out.println(" ]");

        }

    }
}