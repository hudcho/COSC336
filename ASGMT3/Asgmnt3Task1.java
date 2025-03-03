import java.util.Arrays;

public class Asgmnt3Task1 {
    public static void main(String[] args) {
        int[] A = {7, 3, 8, 1, 5};

        System.out.println(countUpPairs(A));
    }

    public static int countUpPairs(int[] A) {
        return splitArray(A, 0, A.length - 1);
    }

    public static int splitArray(int[] A, int p, int r) {
        if (p >= r)
            return 0;

        int count = 0;
        int q = p + ((r - p) / 2);

        count += splitArray(A, p, q);
        count += splitArray(A, q + 1, r);

        count += mergeAndCount(A, p, q, r);
        return count;
}

    public static int mergeAndCount(int[] A, int p, int q, int r) {
        int nL = q - p + 1; //gets length of Left subarray
        int nR = r - q; //gets length of right subarray

        int[] L = new int[nL];
        int[] R = new int[nR];

        for (int i = 0; i < nL; i++) { //copies A[p, q] into L
            L[i] = A[p + i];
        }
        for (int j = 0; j < nR; j++) {
            R[j] = A[q + j + 1];
        }

        int i = 0, j = 0, count = 0;

  
       // System.out.println("L[] is " + Arrays.toString(L));
        // System.out.println("R[] is " + Arrays.toString(R));

        // count all elements in R that form an UP-pair with it
        while (i < nL) {
            // Reset j to 0 for each new element from Ldd
            j = 0;
            while (j < nR && L[i] >= R[j]) {
                j++;
            }

            // Now j points to the first element in R that is > L[i]
            // All elements from j to nR-1 form UP-pairs with L[i]
            if (j < nR) {
                count += nR - j;
            }

            i++;
        }



        return count;
    }

}
