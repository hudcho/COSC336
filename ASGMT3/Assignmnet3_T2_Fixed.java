import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assignmnet3_T2_Fixed {
    public static int rowF = 0;
    public static int colF = 0;

    public static void main(String[] args) throws FileNotFoundException {
        int[][] temp = new int[][]{ {1, 2, 3}, {4, 8, 1}, {1, 5, 3}};
        rowF = 2;
        colF = 1;
        print(temp, temp.length, temp[0].length);
        System.out.println("\nOptomize path (0, 0) to (" + rowF + ", " + colF + ")\n");
        System.out.println("\nMin cost to cell  (" + rowF + ", " + colF + ") : " + costOpti(temp)+"\n");
        
        
        int[][] x = fileInts();
        print(x, x.length, x[0].length);
        System.out.println("\nOptomize path (0, 0) to (" + rowF + ", " + colF + ")\n");
        System.out.println("\nMin cost to cell  (" + rowF + ", " + colF + ") : " + costOpti(x));

        x = fileInts();
        print(x, x.length, x[0].length);
        System.out.println("\nOptomize path (0, 0) to (" + rowF + ", " + colF + ")\n");
        System.out.println("\nMin cost to cell  (" + rowF + ", " + colF + ") : " + costOpti(x));

    }

    public static int costOpti(int[][] x) {

        int minInc[][] = new int[x.length][x[0].length];

        // Fill edge row col with right or down only movements//
        minInc[0][0] = x[0][0];
        for (int i = 1; i < x[0].length; i++) {// Case only right moves: Top//
            int rightMoves = minInc[0][i - 1] + x[0][i];
            minInc[0][i] = rightMoves;
        }

        // Process array by layer as each element in a row > 0 has group of
        // Moves to choosen: cell0:(2), cell1:(4), cell2:(4), cell3:(4), ..., Last
        // index:(3)

        for (int k = 1; k < x.length; k++) {// all other rows//

            for (int j = 0; j < x[k].length; j++) {// in line//

                if (j == 0) {// First index of row//
                    int downMove = x[k][j] + minInc[k - 1][j];
                    int diag = x[k][j] + minInc[k - 1][j + 1];
                    minInc[k][j] = Math.min(downMove, diag);
                }

                else if (j < x[k].length - 1) {// Not at last index of row//
                    int downMove = x[k][j] + minInc[k - 1][j];
                    int diagUpRight = x[k][j] + minInc[k - 1][j + 1];
                    int diagUpLeft = x[k][j] + minInc[k - 1][j - 1];
                    int leftMoves = x[k][j] + minInc[k][j - 1];

                    minInc[k][j] = Math.min(Math.min(diagUpRight, diagUpLeft), Math.min(downMove, leftMoves));
                }

                else {// Last index of row//
                    int downMove = x[k][j] + minInc[k - 1][j];
                    int diagUpLeft = x[k][j] + minInc[k - 1][j - 1];
                    int leftMoves = x[k][j] + minInc[k][j - 1];

                    minInc[k][j] = Math.min(diagUpLeft, Math.min(downMove, leftMoves));

                }

            }
        }
        // Actually reviews all moves for each square
        // Does full array incase of negative cost areas...

        System.out.println("Min cost path summations processed...");
        print(minInc, minInc.length, minInc[0].length);

        return minInc[rowF][colF];
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

    // Print 2D int array//
    public static void print(int[][] y, int n, int m) {
        System.out.println("Integer Matrix Size : [" + n + "]" + "[" + m + "]");

        int i;
        int j;

        for (i = 0; i < n; i++) {
            System.out.print("[ ");

            for (j = 0; j < m; j++) {

                if (j != 0) {
                    System.out.print(", ");
                }
                System.out.print(y[i][j]);

            }

            System.out.println(" ]");

        }

    }
}