package Utils;

import java.util.ArrayList;
import java.util.List;

public class PunctuationRemover {
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
