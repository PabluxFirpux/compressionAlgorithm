package main;

import model.FrequencyIndexer;
import model.tree.Tree;

public class app {
    public static void main(String[] args) {
        Tree tree = new Tree(FrequencyIndexer.index("Hello World!"));
        System.out.println("mec");
    }
}
