import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Utils.PunctuationRemover;


public class Processor {
    // Поле класса для подсчёта общего количества слов
    private int countWords = 0;

    public List<Map.Entry<String, Integer>> process(String[] inputStrings) {
        // Сбрасываем значение перед началом обработки (если метод может вызываться несколько раз)
        countWords = 0;
        Map<String, Integer> wordFreq = new HashMap<>();

        for (String line : inputStrings) {
            String[] words = PunctuationRemover.removePunctuation(line);
            for (String word : words) {
                countWords++; // увеличиваем общее количество слов
                wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(wordFreq.entrySet());
        sortedList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        return sortedList;
    }

    // Геттер для доступа к количеству обработанных слов
    public int getCountWords() {
        return countWords;
    }
}
