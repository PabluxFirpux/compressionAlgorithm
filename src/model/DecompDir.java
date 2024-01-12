package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class DecompDir {
    private String path;
    private ArrayList<DecompFile> files;
    public DecompDir(String path) {
        this.files = new ArrayList<>();
        generateFiles(path);
    }

    private void generateFiles(String path) {
        String codedDir = "";
        try {
            codedDir = fhelper.getFileContents(path);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        String[] texts = Parser.parseDir(codedDir);
        for (String text: texts) {
            files.add(new DecompFile(text, DecompFile.Type.DIR));
        }
    }

    public void save() {
        for (DecompFile f: files) {
            f.save();
        }
    }
}
