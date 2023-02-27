package org.example;

/*
Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке,
разделенные пробелом:Фамилия Имя Отчество датарождения номертелефона пол
Форматы данных:фамилия, имя, отчество - строки
дата_рождения - строка формата dd.mm.yyyy
номер_телефона - целое беззнаковое число без форматирования
пол - символ латиницей f или m.
Приложение должно проверить введенные данные по количеству.
Если количество не совпадает с требуемым, вернуть код ошибки, обработать его и показать пользователю
сообщение, что он ввел меньше и больше данных, чем требуется.
Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры.
Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы.
Можно использовать встроенные типы java и создать свои.
Исключение должно быть корректно обработано, пользователю выведено сообщение с информацией, что именно
неверно.
Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну
строку должны записаться полученные данные, вида
<Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>
Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
Не забудьте закрыть соединение с файлом.
При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано,
пользователь должен увидеть стектрейс ошибки.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in, "Cp866");
        try {
            System.out.println("Введите следующие данные в произвольном порядке, разделенные пробелом: Фамилия Имя Отчество датарождения номертелефона пол");
            String data = scn.nextLine();
            if (data.equals("")) {
                throw new RuntimeException("Ввод пустой строки");
            }
            // Парсинг введенной строки
            String[] parse = parseData(data);
            // Запись распарсенной строки в файл
            saveFile(parse[0], parse);
        } catch (Exception e) {
            // вывод сообщений пользователю по результатам обработки исключений
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static String[] parseData(String data) throws RuntimeException {
        String[] words = data.split(" ");
        if (words.length < 6) {
            throw new RuntimeException("Количество введенных дынных меньше 6");
        }
        if (words.length > 6) {
            throw new RuntimeException("Количество введенных дынных больше 6");
        }
        String[] result = new String[6];
        try {
            // парсинг веденной ФИО
            System.arraycopy(getFIO(data), 0, result, 0, 3);
            // парсинг введенной даты рождения
            result[3] = getDate(data)[0];
            // парсинг введенного телефона
            result[4] = getTelefon(data)[0];
            // парсинг введеного пола
            result[5] = getPol(data)[0];
        } catch (RuntimeException e) {
            throw e;
        }
        return result;
    }

    private static String[] getPol(String data) throws RuntimeException {
        String regexPol = "((^|\s)([mf])(\s|$))";
        Matcher m = Pattern.compile(regexPol).matcher(data);
        int count = 0;
        int numPol = 1;
        String[] allPol = new String[numPol];
        try {
            while (m.find()) {
                allPol[count] = m.group().trim();
                count++;
            }
            if (count < numPol) {
                throw new RuntimeException("Информация о поле не введена");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("Информация о поле введена больше одного раза");
        }
        return allPol;
    }

    private static String[] getDate(String data) throws RuntimeException {
        String regexDate = "((0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[012])\\.(19|20)\\d\\d){1}";
        Matcher m = Pattern.compile(regexDate).matcher(data);
        int count = 0;
        int numDate = 1;
        String[] allDate = new String[numDate];
        try {
            while (m.find()) {
                allDate[count] = m.group();
                count++;
            }
            if (count < numDate) {
                throw new RuntimeException("Дата рождения не введена");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("Дата рождения введена больше одного раза");
        }
        return allDate;
    }

    private static String[] getTelefon(String data) throws RuntimeException {
        String regexTelefon = "([0-9]{7,10})";
        Matcher m = Pattern.compile(regexTelefon).matcher(data);
        int count = 0;
        int numTelefon = 1;
        String[] allTelefon = new String[numTelefon];
        try {
            while (m.find()) {
                allTelefon[count] = m.group();
                count++;
            }
            if (count < numTelefon) {
                throw new RuntimeException("Телефон не задан");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("Введено больше одного телефона");
        }
        return allTelefon;
    }
    private static String[] getFIO(String data) throws RuntimeException {
        String regexFIO = "([A-Z][a-z]{0,})";
        Matcher m = Pattern.compile(regexFIO).matcher(data);
        int numFIO = 3;
        int count = 0;
        String[] allFIO = new String[numFIO];
        try {
            while (m.find()) {
                allFIO[count] = m.group();
                count++;
            }
            if (count < numFIO) {
                throw new RuntimeException("ФИО не задано или меньше трёх слов");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("ФИО больше трёх слов");
        }
        return allFIO;
    }

    public static void saveFile(String name, String[] data) throws RuntimeException {
        try (FileWriter writer = new FileWriter(name,true)) {
            StringBuilder save = new StringBuilder();
            for (String str: data) {
                save.append("<"+str+"> ");
            }
            save.append("\n");
            writer.write(save.toString());
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи файла " + name);
        }
    }
}