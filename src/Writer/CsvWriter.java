package Writer;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class CsvWriter implements Writer{
    public void write(List<Map.Entry<String, Integer>> frequencyList, int countWords){

        try (FileWriter writer = new FileWriter("output.csv")) {
            writer.write("Word|Frequency|Percentage\n");
            for(Map.Entry<String, Integer> entry : frequencyList){
                String word = entry.getKey();
                int frequency = entry.getValue();

                double percentage = (double) frequency / countWords * 100;

                writer.write(String.format("%s; %d; %.2f%%\n", word, frequency, percentage));
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
}
