import java.util.Scanner;

/*
Реализуйте метод, который запрашивает у пользователя ввод дробного числа (типа float),
 и возвращает введенное значение. Ввод текста вместо числа не должно приводить к падению приложения,
  вместо этого, необходимо повторно запросить у пользователя ввод данных.
 */
public class task1 {
    public static void main(String[] args) {
        float infloat;
        try {
            infloat = inputFloat();
        } catch (NumberFormatException ex) {
            System.out.println("Введено не дробное число, попробуйте ещё раз:");
            infloat = inputFloat();
        }
        System.out.println(infloat);

    }

    public static float inputFloat() throws NumberFormatException {
        Scanner scn = new Scanner(System.in, "Cp866");
        System.out.println("Введите дробное число:");
        return Float.parseFloat(scn.next());
    }
}
