package model.tree;

public class Node {
    private Character character;
    private Integer frequency;
    private Node left;
    private Node right;

    protected Node(Character character, Integer frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public Node(Integer frequency, Node left, Node right) {
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public Character getCharacter() {
        return character;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public boolean isLeaf() {
        return false;
    }

    public boolean isFull() {
        return left != null && right != null;
    }

    public String toString() {
        return character + ": " + frequency;
    }
}
