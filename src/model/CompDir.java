package model;

import java.util.ArrayList;

public class CompDir {
    private String path;
    private ArrayList<CompFile> files;
    private String text;
    public static final String SEPARATOR = "QEOGQOWEGHQOEBGFOQGBQJGBOEBQNbngjgj";

    public CompDir(String path) {
        this.path = fhelper.getDirectoryPath(path) + "pfc";
        this.files = new ArrayList<>();
        this.text = "";
        generateFiles(path);
    }

    public CompDir(String path, ArrayList<CompFile> files) {
        this.path = path;
        this.files = files;
    }

    private void generateFiles(String path) {
        ArrayList<String> paths = fhelper.getFiles(path);
        for(String p: paths) {
            files.add(new CompFile(p));
        }
        for(CompFile f: files) {
            text += f.toString() + SEPARATOR;
        }
    }

    public void save() {
        try {
            fhelper.writeToFile(this.path, text);
        } catch (Exception e) {
            System.out.println("Failed to compress directory");
        }
    }


}
