package main;

import model.*;
import model.tree.Tree;

public class app {
    public static void main(String[] args) {
       //createFileOOP();
       // decodeFile();
        createDir();
    }

    public static void createFileOOP() {
        CompFile file = new CompFile("./test.txt");
        file.save();
    }

    public static  void createDir() {
        CompDir dir = new CompDir("./pruebas");
        dir.save();
    }

    public static void decodeFile() {
        String path = "./test.pfc";
        String text;
        try {
            text = fhelper.getFileContents(path);
        } catch (Exception e) {
            System.out.println("File not found");
            return;
        }
        String decodedText = Decoder.decodeFile(text);

        try {
            fhelper.writeToFile("./banana/result.txt", decodedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
