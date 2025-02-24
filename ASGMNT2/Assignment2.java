
import java.util.LinkedList;
import java.util.Random;

public class Assignment2 {

    public static void main(String[] args) {
        int[] x;

        x = fillInts(300, 0, 100);

        increasingSubCheck(x);

    }

    public static void increasingSubCheck(int[] y) {
        //print(y, 0, y.length-1);

        if (y.length != 0) {

            LinkedList<LinkedList<Integer>> maxInc = new LinkedList<>();

            for (int i = 0; i < y.length; i++) {
                maxInc.add(new LinkedList<>());
            }

            maxInc.get(0).add(y[0]);

            for(int i=1; i<y.length; i++){
                int currCount = 1;

                for(int j=0; j<i; j++){
                    
                    if(y[i]<y[j] && (currCount < maxInc.get(j).size()+1)){
                        maxInc.set(i, new LinkedList<>(maxInc.get(j)));
                        currCount = maxInc.get(j).size()+1;
                    }

                }

                maxInc.get(i).add(y[i]);
            }

            LinkedList<Integer> maxFinal = maxInc.get(0);
            
            for (LinkedList<Integer> i: maxInc) {
                if(i.size() > maxFinal.size()){
                    maxFinal = i;
                } 
            }

            printLinkedList(maxFinal);
        }
    }

    public static int[] fillInts(int sizey, int min, int max) {
        Random randi = new Random();
        int[] y = new int[sizey];

        for (int i = 0; i < y.length; i++) {
            y[i] = randi.nextInt(min, max + 1);
        }

        return y;
    }
    public static void printLinkedList(LinkedList<Integer> x) {
        System.out.print("Sub Array Size: " + (x.size()) + "  ==>  ");
        System.out.print("( 0, " + (x.size()-1) + " ) : " + "[ ");
        
        
        for(int i=0; i < x.size(); i++) {

            System.out.print(x.get(i));

            if (i < x.size()-1) {
                System.out.print(", ");
            } else {
                System.out.print(" ");
            }

        }
        System.err.println("]");
    }

    public static void print(int[] y, int start, int end) {
        System.out.print("Sub Array Size: " + (end - start + 1) + "  ==>  ");

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
