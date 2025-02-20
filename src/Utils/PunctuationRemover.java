package Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс содержит вспомогательный метод для удаления пунктуации из строки.
 * Метод {@link #removePunctuation(String)} принимает строку, удаляет из неё все символы, которые не являются буквами или цифрами
 * и возвращает массив слов.
 */
public class PunctuationRemover {
    /**
     * Удаляет всю пунктуацию и другие лишние символы из входного текста и разбивает его на отдельные слова.
     * Если входная строка null или пуста, метод вернет пустой массив.
     * При обработке строки метод собирает слово посимвольно, и как только встречает что-то
     * кроме буквы или числа, то добавляет накопившееся слово в List.
     * @param inputText текст для обработки
     * @return массив строк, содержащий слова из которых удалена пунктуация.
     */
    public static String[] removePunctuation(String inputText){
        if (inputText == null || inputText.isEmpty()) {
            return new String[0];
        }

        List<String> words = new ArrayList<>(inputText.length() / 5);
        StringBuilder word = new StringBuilder(inputText.length());

        for(int i = 0; i < inputText.length(); i++) {
            char c = inputText.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                word.append(c);
            } else {
                if (word.length() != 0) {
                    words.add(word.toString());
                    word.setLength(0);
                }
            }
        }

        if(word.length() != 0){
            words.add(word.toString());
        }

        return words.toArray(new String[words.size()]);

    }
}
