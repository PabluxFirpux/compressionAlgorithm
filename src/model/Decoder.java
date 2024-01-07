package model;

import model.tree.Node;
import model.tree.Tree;

public class Decoder {
    public static String decode(String asciitext, Tree tree) {
        String text = Bindecode(asciitext);
        StringBuilder decodedText = new StringBuilder();
        StringBuffer buffer = new StringBuffer();
        for(Character c: text.toCharArray()) {
            buffer.append(c);
            if(tree.isCode(buffer.toString())) {
                decodedText.append(tree.getCharacter(buffer.toString()));
                buffer = new StringBuffer();
            }
        }
        return decodedText.toString();
    }

    public static String Bindecode(String text) {
        StringBuilder decodedText = new StringBuilder();
        for(Character c: text.toCharArray()) {
            if (decodedText.length() / 8 == text.length() - 1) {
                decodedText.append(Integer.toBinaryString(c));
                break;
            }
            String binary = Integer.toBinaryString(c);
            while(binary.length() < 8) {
                binary = "0" + binary;
            }
            decodedText.append(binary);

        }
        return decodedText.toString();
    }
}
