package model;

import java.util.ArrayList;

public class CompDir {
    private String path;
    private ArrayList<CompFile> files;
    private String text;
    private String root;
    public static final String SEPARATOR = "QEOGQOWEGHQOEBGFOQGBQJGBOEBQNbngjgj";

    public CompDir(String path, String root) {
        this.path = fhelper.getDirectoryPath(path) + "pfc";
        this.files = new ArrayList<>();
        this.text = "";
        this.root = root;
        generateFiles(path);
    }

    public CompDir(String path, ArrayList<CompFile> files) {
        this.path = path;
        this.files = files;
    }

    private void generateFiles(String path) {
        ArrayList<String> paths = fhelper.getFiles(path);
        for(String p: paths) {
            files.add(new CompFile(p, root));
        }
        for(CompFile f: files) {
            text += f.toString() + SEPARATOR;
        }
    }

    public void save() {
        try {
            fhelper.writeToFile(this.path, text, true);
        } catch (Exception e) {
            System.out.println("Failed to compress directory");
        }
    }


}
