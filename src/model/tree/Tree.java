package model.tree;

import java.util.*;

public class Tree {
    //TODO: Improve tree creation with recursion
    private Node root;

    private Dictionary<Character, String> codes;

    public Tree(Node root) {
        this.root = root;
        codes = new Hashtable<>();
    }

    public Tree(String text) {
        Dictionary<Character, Integer> FrequencyIndex = FrequencyIndexer.index(text);
        this.root = build(FrequencyIndex);
        codes = new Hashtable<>();
        generateCodes();
    }

    private Node build(Dictionary<Character, Integer> FrequencyIndex) {
        ArrayList<Node> nodes = getNodes(FrequencyIndex);
        while(nodes.size() > 1) {
            nodes.sort(Comparator.comparingInt(Node::getFrequency));
            Node left = nodes.remove(0);
            Node right = nodes.remove(0);
            nodes.add(new Node(left.getFrequency() + right.getFrequency(), left, right));
        }
        return nodes.get(0);
    }

    public void generateCodes() {
        generateCodes(root, "");
    }

    public void generateCodes(Node node, String code) {
        if(node.isLeaf()) {
            codes.put(node.getCharacter(), code);
        } else {
            generateCodes(node.getLeft(), code + "0");
            generateCodes(node.getRight(), code + "1");
        }
    }

    private ArrayList<Node> getNodes(Dictionary<Character, Integer> FrequencyIndex) {
        ArrayList<Node> nodes = new ArrayList<>();
        ArrayList<Character> keys = Collections.list(FrequencyIndex.keys());
        for(Character c: keys) {
            nodes.add(new Leaf(c, FrequencyIndex.get(c)));
        }
        return nodes;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return root.isLeaf();
    }

    public String getCode(Character character) {
        return codes.get(character);
    }

    public Dictionary<Character, String> getCodes() {
        return codes;
    }

    public Character getCharacter(String code) {
        for(Character c: Collections.list(codes.keys())) {
            if(codes.get(c).equals(code)) {
                return c;
            }
        }
        return null;
    }

    public boolean isCode(String code) {
        return getCharacter(code) != null;
    }
}
