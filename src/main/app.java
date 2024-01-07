package main;

import model.Decoder;
import model.Encoder;
import model.tree.Tree;

public class app {
    public static void main(String[] args) {
        String text = "As you noticed, the if is checked at each loop although you know that it will be invalidated only on the last loop. A good way to avoid this is to use a while loop instead of a for loop.";
        Tree tree = new Tree(text);

        System.out.print("Coded text: ");
        String encodedText = Encoder.encode(text, tree);
        System.out.println(encodedText);

        System.out.println("Decoded text: ");
        String decodedText = Decoder.decode(encodedText, tree);
        System.out.println(decodedText);
    }
}
