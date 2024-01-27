package model;

import model.tree.Tree;

public class CompFile {
    private String path;

    private final String ogPath;
    private String table;
    private String text;

    public CompFile(String path, String table, String text) {
        this.ogPath = path;
        this.path = path;
        this.table = table;
        this.text = text;
    }

    private String parsePath(String path, String root) {
        String p = ".\\" + root;
        String[] parts = path.split(root);
        if (parts.length > 1) {
            p += parts[1];
        }
        return p;
    }

    public CompFile(String path, String root) {
        this.ogPath = parsePath(path, root);
        generate(path);
    }

    private void generate(String path) {
        String text;
        this.path = fhelper.getFilePathWithExtension(path);
        try {
            text = fhelper.getFileContents(path);
        } catch (Exception e) {
            System.out.println("File not found");
            return;
        }
        Tree tree = new Tree(text);
        this.text = Encoder.encode(text, tree);
        this.table = Parser.parseTable(tree.getCodes());
    }

    public void save() {
        String contents = ogPath + "\n" + table + text;
        try {
            fhelper.writeToFile(path, contents, true);
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }

    public String toString() {
        return ogPath + "\n" + table + text;
    }
}
