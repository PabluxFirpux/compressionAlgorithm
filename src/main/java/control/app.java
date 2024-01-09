package control;

import model.*;

public class app {

    public static void main(String[] args) {
       //createFileOOP();
        //decodeFile();
        //createDir();
        fhelper.getExtension("./mec");
        if (args.length < 2) {
            System.out.println("No arguments given");
            return;
        } else if(args.length > 2) {
            System.out.println("Too many arguments");
            return;
        }
        if (fhelper.getExtension(args[1]).equals("pfc")) {
            scanDir(args[1]);
        } else if (fhelper.getExtension(args[1]).startsWith("/") || fhelper.getExtension(args[1]).startsWith("\\")) {
            createDir(args[1]);
        } else {
            createFileOOP(args[1]);
        }
    }

    public static void scanDir(String path) {
        DecompDir dir = new DecompDir(path);
        dir.save();
    }

    public static void scanFile() {
        DecompFile file = new DecompFile("./test.pfc", DecompFile.Type.FILE);
        file.save();
    }

    public static void createFileOOP(String path) {
        CompFile file = new CompFile(path);
        file.save();
    }

    public static  void createDir(String path) {
        CompDir dir = new CompDir(path);
        dir.save();
    }

    public static void decodeFile() {
        String path = "./test.pfc";
        String text;
        try {
            text = fhelper.getFileContents(path);
        } catch (Exception e) {
            System.out.println("File not found");
            return;
        }
        String decodedText = Decoder.decodeFile(text);

        try {
            fhelper.writeToFile("./banana/result.txt", decodedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
