//Clemens Osbahr // Matrikelnummer: 23453430
import static java.lang.Math.*;

public class Arrays {
    public static void printArray(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i == array.length - 1) {
                break;
            } else {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    ;

    public static int sum(int[] array) {
        int sum = 0;

        for (int j : array) {
            sum += j;
        }

        return sum;
    }

    public static double mean(int[] array) {
        float mean = 0;
        for (int j : array) {
            mean += j;
        }
        if (array.length > 0) {
            mean = mean / array.length;
        }
        return mean;
    }

    public static int[] sumArrays(int[] array1, int[] array2) {
        int length = min(array1.length, array2.length);

        int[] array3 = new int[length];

        for (int i = 0; i < length; i++) {
            array3[i] = array1[i] + array2[i];
        }
        return array3;
    }

    public static int maximum(int[] array) {
        int maximum = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > maximum) {
                maximum = array[i];
            }
        }
        return maximum;
    }


    public static int[] tail(int[] array) {
        if (array.length > 0) {
            int[] tailedArray = new int[array.length - 1];
            for (int i = 0; i < array.length - 1; i++) {
                tailedArray[i] = array[i + 1];

            }
            return tailedArray;
        }
        return array;
    }


    public static boolean checkSorting(int[] array) {
        if (array.length == 0) return false;

        for (int i = 1; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[i]) {
                    return false;
                }
            }
        }
        return true;
    }


    public static boolean[] evenNumbers(int[] array) {
        //Was soll denn fuer arraylaenge 0 zuruckgegeben werden//

        boolean[] trueFalseArray = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            trueFalseArray[i] = array[i] % 2 == 0;
        }
        return trueFalseArray;
    }

    public static void printBooleanArray(boolean[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i == array.length - 1) {
                break;
            } else {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    ;


    public static void main(String[] args) {
        int[] array1 = {0, 5, 12, 17, 21, 42};
        int[] array2 = {23, 18, 3, 16, 41, 7, 9};

        printArray(array1);
        printArray(array2);


        int sumArray1 = sum(array1);
        double meanArray1 = mean(array1);

        int sumArray2 = sum(array2);
        double meanArray2 = mean(array2);


        System.out.println("array1: sum = " + sumArray1 + ", mean = " + meanArray1);
        System.out.println("array2: sum = " + sumArray2 + ", mean = " + meanArray2);


        int[] array3 = sumArrays(array1, array2);
        printArray(array3);

        int maximumArray1 = maximum(array1);
        System.out.println(maximumArray1);


        int[] array1Tailed = tail(array1);
        int[] array2Tailed = tail(array2);

        System.out.print("array1: tail = ");
        printArray(array1Tailed);
        System.out.print("array2: tail = ");
        printArray(array2Tailed);


        boolean checkSorting1 = checkSorting(array1);
        boolean checkSorting2 = checkSorting(array2);

        System.out.println("array1: sorted = " + checkSorting1);
        System.out.println("array2: sorted = " + checkSorting2);

        boolean[] array1EvenUneven = evenNumbers(array1);
        printBooleanArray(array1EvenUneven);


    }
}