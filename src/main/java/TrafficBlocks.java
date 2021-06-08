import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TrafficBlocks {
    public static void main(String[] args) {

        String str = args[1];
        DateTimeFormatter dtfArgs = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate searchDate = LocalDate.parse(str, dtfArgs);

        try (FileReader fileReader = new FileReader(args[0]);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            List<String[]> list = new ArrayList<>();
            while (bufferedReader.ready()) {
                list.add(bufferedReader.readLine().split("(,)(?=(?:[^\"]|\"[^\"]*\")*$)"));
            }

            int indexOfColumn = 0;
            for (int x = 0; x < list.get(0).length; x++)
                if (list.get(0)[x].equals("Дата начала ограничения")) {
                    indexOfColumn = x;
                }

            int countOfDays = 0;
            for (int y = 1; y < list.size(); y++) {
                DateTimeFormatter dtfColumn = DateTimeFormatter.ofPattern("yyyyMMdd");
                LocalDate columnDate = LocalDate.parse(list.get(y)[indexOfColumn], dtfColumn);
                if (columnDate.isEqual(searchDate)){
                    countOfDays++;
                }
            }

            System.out.println("Количество действовавших ограничений движения транспорта: " + countOfDays);

        } catch (IOException e) {
            System.out.println("Пожалуйста, введите правильное имя файла или директории");
            e.printStackTrace();
        }

    }
}
