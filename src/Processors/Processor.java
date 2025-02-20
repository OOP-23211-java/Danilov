package Processors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Utils.PunctuationRemover;

/**
 * Класс Processor обрабатывает массивы строк, формируя мапу с частотами, затем
 * сортирует данные, хранящиеся в ней по частоте и возвращает отсортированный список пар {Слово, частота}
 */
public class Processor {
    private int countWords = 0;

    /**
     * Обрабатывает массив строк и считает частоту слов
     * Для каждой строки метод класса removePunctuation {@link PunctuationRemover} удаляет из нее всю
     * пунктуацию и возвращает массив чистых слов.
     * @param inputStrings - массив строк.
     * @return List<Map.Entry<String, Integer>> sortedList - список пар, где каждая пара - {слово, частота}
     * сортируется по убыванию частоты
     */
    public List<Map.Entry<String, Integer>> process(String[] inputStrings) {
        countWords = 0;
        Map<String, Integer> wordFreq = new HashMap<>();

        for (String line : inputStrings) {
            String[] words = PunctuationRemover.removePunctuation(line);
            for (String word : words) {
                if (word.length() > 0) {
                    word = word.toLowerCase();
                    countWords++;
                    wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
                }
            }
        }

        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(wordFreq.entrySet());
        sortedList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        return sortedList;
    }

    public int getCountWords() {
        return countWords;
    }
}
