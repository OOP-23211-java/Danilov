package Writer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Реализует интерфейс {@link Writer} и отвечает за запись
 * статистики частоты слов в CSV файл.
 * Формат записи:
 * Word|Frequency|Percentage
 * слово; частота; процентное соотношение
 * @see Writer
 */
public class CSVWriter implements Writer {
    /**
     * Записывает список пар <слово ; частота> в CSV файл.
     * @param frequencyList список пар, где ключ - слово, значение - частота
     * @param outputFile    путь к файлу куда записываются результаты.
     * @param countWords    общее количество слов во входном файле используемое для вычисления процентного соотношения
     * @throws IOException при ошибке ввода вывода во время записи в файл
     */
    public void write(List<Map.Entry<String, Integer>> frequencyList, String outputFile, int countWords) throws IOException {
        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write("Word|Frequency|Percentage\n");
            for (Map.Entry<String, Integer> entry : frequencyList) {
                String word = entry.getKey();
                int frequency = entry.getValue();
                double percentage = (double) frequency / countWords * 100;

                writer.write(String.format("%s; %d; %.2f%%\n", word, frequency, percentage));
            }
        }
    }
}
