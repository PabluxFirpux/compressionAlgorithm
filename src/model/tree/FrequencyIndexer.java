package model.tree;

import java.util.Dictionary;
import java.util.Hashtable;

public class FrequencyIndexer {
    public static Dictionary<Character, Integer> index(String text) {
        Dictionary<Character, Integer> frequencyIndex = new Hashtable<>();
        index(text, frequencyIndex);
        return frequencyIndex;
    }

    public static Dictionary<Character, Integer> index(String text, Dictionary<Character, Integer> frequencyIndex) {
        for(Character c: text.toCharArray()) {
            if(frequencyIndex.get(c) == null) {
                frequencyIndex.put(c, 1);
            } else {
                frequencyIndex.put(c, frequencyIndex.get(c) + 1);
            }
        }
        return frequencyIndex;
    }
}
