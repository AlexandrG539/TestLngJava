import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LngJava {
    // Формат входных данных строго задан, удобно использовать регулярные выражения
    private final static Pattern pattern = Pattern.compile("^(\"([^\"]*)?\";)*\"([^\"]*)?\"$");

    public static void main(String[] args) {
        try {
            if (args.length == 0) { // Имя файла не указано в аргументе
                System.out.println("Отсутствует имя файла");
                return;
            }
            StringSorter sorter = new StringSorter(new GroupStorage()); // sorter занимается обработкой строк.
            Scanner sc = new Scanner(Paths.get(args[0])); // Сканер для чтения данных из файла
            while (true) {
                if (sc.hasNextLine()) { // Пока есть следующа строка
                    String line = sc.nextLine(); // Чтение строки
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.matches()) { // Если строка совпадает с указаным шаблоном
                        //TODO: Строку необходимо проверить на соответствие группам,
                        // поместить в нужное хранилище
                    }
                }
                else break; // Строки закончились - выход из цикла обработки.
            }

        } catch (NoSuchFileException e) {
            System.out.println("Указаного файла не существует: " + e);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e);
        }
    }
}
