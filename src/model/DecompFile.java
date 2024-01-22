package model;

import java.io.FileNotFoundException;

public class DecompFile {
    public enum Type {
        FILE, DIR
    }
    private String path;
    private String decodedText;

    public DecompFile(String pathOrDir, Type type) {
        if ((type == Type.FILE)) {
            generate(pathOrDir);
        } else {
            generateDir(pathOrDir);
        }
    }

    private void generate(String path) {
        String codedText;
        try {
            codedText = fhelper.getFileContents(path);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        String[] parts = codedText.split("[\n](?<![\"][\n])", 2);
        this.path = parts[0];
        this.decodedText = Decoder.decodeFile(parts[1]);
    }

    private void generateDir(String codedText) {
        String[] parts = codedText.split("[\n](?<![\"][\n])", 2);
        this.path = parts[0];
        this.decodedText = Decoder.decodeFile(parts[1]);
    }

    public void save() {
        try {
            fhelper.writeToFile(path, decodedText);
        } catch (Exception e) {
            System.out.println("Failed to decompress file");
        }
    }
}
