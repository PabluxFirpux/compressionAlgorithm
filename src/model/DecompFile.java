package model;

import java.io.File;
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
        this.path = checkDuplicatePath(parts[0]);
        this.decodedText = Decoder.decodeFile(parts[1]);
    }

    private String checkDuplicatePath(String path) {
        String[] parts = path.split("\\\\");
        File f = new File(path);
        parts[1] = (f.exists()) ? parts[1] + "_copy" : parts[1];
        String newPath = String.join("\\", parts);
        return newPath;
    }

    public void save() {
        try {
            fhelper.writeToFile(path, decodedText, false);
        } catch (Exception e) {
            System.out.println("Failed to decompress file");
        }
    }
}
