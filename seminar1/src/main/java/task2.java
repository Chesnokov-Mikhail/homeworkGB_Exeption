/*
2. Реализуйте метод, принимающий в качестве аргументов два целочисленных массива,
и возвращающий новый массив, каждый элемент которого равен частному элементов
двух входящих массивов в той же ячейке. Если длины массивов не равны,
необходимо как-то оповестить пользователя.
Важно: При выполнении метода единственное исключение,
которое пользователь может увидеть - RuntimeException, т.е. ваше.
 */

import java.util.Arrays;
import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in, "Cp866");
        try {
            System.out.println("Введите размер первого массива");
            int lenght1 = scn.nextInt();
            int[] arr1 = task1.fillRandomIntArray(lenght1);
            System.out.println("Массив 1");
            System.out.println(Arrays.toString(arr1));
            System.out.println("Введите размер воторого массива");
            int lenght2 = scn.nextInt();
            int[] arr2 = task1.fillRandomIntArray(lenght2);
            System.out.println("Массив 2");
            System.out.println(Arrays.toString(arr2));
            System.out.printf("Частное элементов массивов 1 и 2: %n" + Arrays.toString(divisionArray(arr1, arr2)));
        } catch (NumberFormatException e) {
            System.out.println("Введена не цифра");
        }
    }
    /**
     * division element arrays
     * @param arr1
     * @param arr2
     * @return array of division element of arr1 and arr2
     */
    public static double[] divisionArray(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            throw new RuntimeException("Длины массивов не равны");
        }
        try {
            double[] arr = new double[arr1.length];
            for (int i = 0; i < arr1.length; i++) {
                arr[i] = (double) arr1[i] / arr2[i];
            }
            return arr;
        } catch (Exception e) {
            return null;
        }
    }
}
