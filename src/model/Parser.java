package model;

import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;

public class Parser {
    public static Dictionary<Character, String> parseCodes(String text) {
        Dictionary<Character, String> codes = new Hashtable<>();

        if (text.isEmpty()) return codes;
        if(text.charAt(0) != '{') return codes;
        if(text.charAt(text.length() - 1) != '}') return codes;

        String[] parts = text.substring(1, text.length() - 1).split("[,](?<![\"][,])");
        for(String part: parts) {
            String[] pair = part.split("(?![:][\"])[:]");

            if(pair.length != 2) return codes;
            if(pair[0].length() != 3) return codes;
            if(pair[0].charAt(0) != '"' || pair[0].charAt(0) != '"') return codes;
            Character character = pair[0].charAt(1);
            String code = pair[1];
            codes.put(character, code);
        }
        return codes;
    }

    public static String parseTable(Dictionary<Character, String> codes) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        for(Character c: Collections.list(codes.keys())) {
            buffer.append('"');
            buffer.append(c);
            buffer.append('"');
            buffer.append(":");
            buffer.append(codes.get(c));
            buffer.append(",");
        }
        buffer.deleteCharAt(buffer.length() - 1);
        buffer.append("}");
        return buffer.toString();
    }

    public static String[] parseDir(String text) {
        return text.split(CompDir.SEPARATOR);
    }

    public static Dictionary<String, Character> getDecodeDictionary(String text) {
        Dictionary<String, Character> codes = new Hashtable<>();

        if (text.isEmpty()) return codes;
        if(text.charAt(0) != '{') return codes;
        if(text.charAt(text.length() - 1) != '}') return codes;

        String[] parts = text.substring(1, text.length() - 1).split("[,](?<![\"][,])");
        for(String part: parts) {
            String[] pair = part.split("(?![:][\"])[:]");

            if(pair.length != 2) return codes;
            if(pair[0].length() != 3) return codes;
            if(pair[0].charAt(0) != '"' || pair[0].charAt(2) != '"') return codes;
            Character character = pair[0].charAt(1);
            String code = pair[1];
            codes.put(code, character);
        }
        return codes;
    }
}
