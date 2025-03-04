import java.util.Arrays;

public class Asgmnt3Task1 {
    public static void main(String[] args) {
        int[] arr = {7, 3, 8, 1, 5};

        System.out.println(countUpPairs(arr));
        System.out.println(Arrays.toString(arr));
    }

    public static int countUpPairs(int[] A) {
        return divideAndCount(A, 0, A.length - 1);
    }

    public static int divideAndCount(int[] A, int p, int r) {
        if (p >= r)
            return 0;

        int q = p + ((r - p) / 2);
        return divideAndCount(A, p, q) + divideAndCount(A, q + 1, r) + mergeAndCount(A, p, q, r);
    }

    public static int mergeAndCount(int[] A, int p, int q, int r) {
        int nL = q - p + 1;
        int nR = r - q;

        int[] L = new int[nL];
        int[] R = new int[nR];

        //copies from A[] into L and R
        //NOTE TO SELF: its nL/nR - 1 since nL/nR is length so max index is nL/nR - 1
        for (int i = 0; i < nL; i++) {
            L[i] = A[p + i];
        }
        for (int j = 0; j < nR; j++) {
            R[j] = A[q + j + 1]; //NOTE TO SELF: q is the last index of L, so you have to add 1,
        }

        int i = 0, j = 0, k = p, count = 0;

        while (i < nL && j < nR) {
            if (L[i] <= R[j]) {
                A[k] = L[i];
                count++;
                i++;
            }
            else {
                A[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < nL) {
            A[k] = L[i];
            i++;
            k++;
        }
        while (j < nR) {
            A[k] = R[j];
            j++;
            k++;
        }

        return count;
    }
}

