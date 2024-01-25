package model;

import model.tree.Node;
import model.tree.Tree;

import java.util.Dictionary;

public class Decoder {
    public static String decode(String asciitext, Dictionary<String, Character> codes) {
        String text = Bindecode(asciitext, codes);
        StringBuilder decodedText = new StringBuilder();
        StringBuffer buffer = new StringBuffer();
        for(Character c: text.toCharArray()) {
            buffer.append(c);
            if(codes.get(buffer.toString()) != null) {
                decodedText.append(codes.get(buffer.toString()));
                buffer = new StringBuffer();
            }
        }
        return decodedText.toString();
    }

    public static String Bindecode(String text, Dictionary<String, Character> codes) {
        StringBuilder decodedText = new StringBuilder();
        for(Character c: text.toCharArray()) {
            if (decodedText.length() / 8 == text.length() -1) {
                //Extraño bug pero si el ultimo char tiene de codigo "0" no lo decodifica, lo ignora, este if solo se ejecuta en ese caso
                if (Integer.toBinaryString(c).equals("0")) {
                    StringBuilder temp = new StringBuilder();
                    temp.append(Integer.toBinaryString(c));
                    for (int i = 0; i < 8; i++) {
                        temp.append("0");
                        if (codes.get(temp.toString()) != null) {
                            decodedText.append(temp.toString());
                            return decodedText.toString();
                        }
                    }
                }
                decodedText.append(Integer.toBinaryString(c));
                break;
            }
            String binary = Integer.toBinaryString(c);
            while(binary.length() < 8) {
                binary = "0" + binary;
            }
            decodedText.append(binary);

            //decodedText.append(Integer.toBinaryString(c));
        }
        return decodedText.toString();
    }

    public static String decodeFile(String text) {
        String[] parts = splitFile(text);
        if(parts == null) return null;
        Dictionary<String, Character> codes = Parser.getDecodeDictionary(parts[0]);
        return decode(parts[1], codes);
    }

    private static String[] splitFile(String text) {
        if(text.charAt(0) != '{') return null;
        for(int i = 0; i < text.length(); i++) {
            if(text.charAt(i) == '}' && text.charAt(i-1) != '"') {
                String[] parts = new String[2];
                parts[0] = text.substring(0, i + 1);
                parts[1] = text.substring(i + 1);
                return parts;
            }
        }
        return null;
    }
}
