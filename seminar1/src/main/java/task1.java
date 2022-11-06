/*
1. Реализуйте метод, принимающий в качестве аргументов два целочисленных массива,
 и возвращающий новый массив, каждый элемент которого равен разности элементов
 двух входящих массивов в той же ячейке. Если длины массивов не равны,
 необходимо как-то оповестить пользователя.
*/

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class task1 {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in, "Cp866");
        try {
            System.out.println("Введите размер первого массива");
            int lenght1 = scn.nextInt();
            int[] arr1 = fillRandomIntArray(lenght1);
            System.out.println("Массив 1");
            System.out.println(Arrays.toString(arr1));
            System.out.println("Введите размер воторого массива");
            int lenght2 = scn.nextInt();
            int[] arr2 = fillRandomIntArray(lenght2);
            System.out.println("Массив 2");
            System.out.println(Arrays.toString(arr2));
            System.out.printf("Разность массивов 1 и 2: %n" + Arrays.toString(differenceArray(arr1, arr2)));
        } catch (NumberFormatException e) {
            System.out.println("Введена не цифра");
        } catch (RuntimeException e) {
            System.out.println("Длины массивов не равны");
        }
    }

    /**
     * difference element arrays
     * @param arr1
     * @param arr2
     * @return array of difference element of arr1 and arr2
     */
    public static int[] differenceArray(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            throw new RuntimeException("Длины массивов не равны");
        }
        int[] arr = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            arr[i] = arr1[i] - arr2[i];
        }
        return arr;
    }

    /**
     * Fill arrays random int bound 100
     * @param lenght
     * @return array int size = lenght
     */
    public static int [] fillRandomIntArray(int lenght) {
        Random rn = new Random();
        int[] arr = new int[lenght];
        for (int i = 0; i < lenght; i++) {
            arr[i] = rn.nextInt(100);
        }
        return arr;
    }
}
