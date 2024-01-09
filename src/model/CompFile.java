package model;

import model.tree.Tree;

public class CompFile {
    private String path;
    private String table;
    private String text;

    public CompFile(String path, String table, String text) {
        this.path = path;
        this.table = table;
        this.text = text;
    }

    public CompFile(String path) {
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
        String contents = table + "\n" + text;
        try {
            fhelper.writeToFile(path, contents);
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }

    public String toString() {
        return path + "\n" + table + "\n" + text;
    }
}
