import java.util.Scanner;

/*
Разработайте программу, которая выбросит Exception, когда пользователь вводит пустую строку.
 Пользователю должно показаться сообщение, что пустые строки вводить нельзя.
 */
public class task4 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in, "Cp866");
        System.out.println("Введите не пустую строку:");
        String str = scn.nextLine();
        if (str.equals("")) {
            throw new RuntimeException("Нельзя вводить пустые строки");
        }
    }
}
