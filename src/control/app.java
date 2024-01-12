package control;

import model.*;

import java.sql.Time;

public class app {

    public static void main(String[] args) {
        Time time = new Time(System.currentTimeMillis());
        if (args.length < 1) {
            System.out.println("No arguments given");
            return;
        } else if(args.length > 1) {
            System.out.println("Too many arguments");
            return;
        }
        if (fhelper.getExtension(args[0]).equals("pfc")) {
            scanDir(args[0]);
        } else if (fhelper.isDirectory(args[0])) {
            createDir(args[0]);
        } else {
            createFileOOP(args[0]);
        }
        Time time2 = new Time(System.currentTimeMillis());
        System.out.println((time2.getTime() - time.getTime())/1000.0 + " seconds");
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
