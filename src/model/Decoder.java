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

    public static String decodeFile(String text) {
        String[] parts = splitFile(text);
        if(parts == null) return null;
        Tree tree = new Tree(Parser.parseCodes(parts[0]));
        return decode(parts[1], tree);
    }

    private static String[] splitFile(String text) {
        if(text.charAt(0) != '{') return null;
        for(int i = 0; i < text.length(); i++) {
            if(text.charAt(i) == '}' && text.charAt(i-1) != '"') {
                String[] parts = new String[2];
                parts[0] = text.substring(0, i + 1);
                parts[1] = text.substring(i + 2);
                return parts;
            }
        }
        return null;
    }
}
