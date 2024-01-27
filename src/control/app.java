package control;

import model.*;

import java.io.File;
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
        //Turns out the algorithm used for decompressing directories also works if it's a single file, so this is redundant, but I'm keeping it here just for reference
        DecompFile file = new DecompFile("./test.pfc", DecompFile.Type.FILE);
        file.save();
    }

    public static void createFileOOP(String path) {
        File f = new File(path);
        String root = f.getName();
        CompFile file = new CompFile(path, root);
        file.save();
    }

    public static  void createDir(String path) {
        File f = new File(path);
        String root = f.getName();
        CompDir dir = new CompDir(path, root);
        dir.save();
    }
}
