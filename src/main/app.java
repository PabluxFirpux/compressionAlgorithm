package main;

import model.Decoder;
import model.Encoder;
import model.Parser;
import model.fhelper;
import model.tree.Tree;

public class app {
    public static void main(String[] args) {
        createFile();
        decodeFile();
    }

    public static void createFile() {
        String path = "./test.txt";
        String text;
        try {
            text = fhelper.getFileContents(path);
        } catch (Exception e) {
            System.out.println("File not found");
            return;
        }
        Tree tree = new Tree(text);
        String encodedText = Encoder.encode(text, tree);

        String contents = Parser.parseTable(tree.getCodes()) + "\n" + encodedText;
        try {
            fhelper.writeToFile("./mec.pfc", contents);
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }

    public static void decodeFile() {
        String path = "./mec.pfc";
        String text;
        try {
            text = fhelper.getFileContents(path);
        } catch (Exception e) {
            System.out.println("File not found");
            return;
        }
        String decodedText = Decoder.decodeFile(text);

        try {
            fhelper.writeToFile("./result.txt", decodedText);
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }
}
