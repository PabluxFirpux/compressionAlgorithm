package model.tree;

public class Leaf extends Node{
    public Leaf(Character character, Integer frequency) {
        super(character, frequency);
    }
    public boolean isLeaf() {
        return true;
    }
}
