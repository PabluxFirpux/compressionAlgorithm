package model;

import model.tree.Tree;

public class Encoder {
    public static String Binencode(String text, Tree tree) {
        StringBuilder encodedText = new StringBuilder();
        for(Character c: text.toCharArray()) {
            encodedText.append(tree.getCode(c));
        }
        return encodedText.toString();
    }

    public static String encode(String text, Tree tree) {
        String binaryText = Binencode(text, tree);
        StringBuilder encodedText = new StringBuilder();
        StringBuilder surplus = new StringBuilder();
        for(int i = 0; i < binaryText.length(); i += 8) {
            if(i + 8 > binaryText.length()) {
                surplus.append(binaryText.substring(i));
                break;
            }
            encodedText.append((char) Integer.parseInt(binaryText.substring(i, i + 8), 2));
        }
        encodedText.append((char) Integer.parseInt(surplus.toString(), 2));
        return encodedText.toString();
    }
}
