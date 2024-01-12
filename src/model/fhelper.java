package model;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

public class fhelper {

    private static String extens = "pfc";
    public static String getExtension(String path) {
        String[] parts = path.split("\\.");
        return parts[parts.length - 1];
    }

    public static boolean isDirectory(String path) {
        File file = new File(path);
        return file.isDirectory();
    }

    public static String getName(String path) {
        String[] parts = path.split("\\.");
        return parts[parts.length - 2];
    }

    public static String getDirectoryPath(String path) {
        String[] parts = path.split("\\.");
        String s = "";
        for(int i = 0; i < parts.length; i++) {
            s += parts[i];
            s += ".";
        }
        return s;
    }

    public static String getDirectory(String path) {
        String[] parts = path.split("");
        int x = 0;
        for(int i = 0; i < parts.length; i++) {
            if(parts[i].equals("\\") || parts[i].equals("/")) {
                x = i;
            }
        }
        return path.substring(0, x);
    }

    public static String getFileName(String path) {
        String[] parts = path.split("\\.");
        return parts[parts.length - 2] + "." + parts[parts.length - 1];
    }

    public static String getFilePathWithExtension(String path) {
        String[] parts = path.split("");
        int x = 0;
        for (int i = 0; i < parts.length; i++) {
            if(parts[i].equals(".")) {
                x = i;
            }
        }
        return path.substring(0, x+1) + extens;
    }

    public static String getFilePath(String path) {
        String[] parts = path.split("\\.");
        return parts[parts.length - 3] + "." + parts[parts.length - 2] + "." + parts[parts.length - 1];
    }

    public static String getFileContents(String path) throws FileNotFoundException {
        String contents = "";
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        BufferedReader dis = new BufferedReader(new InputStreamReader(bis));
        try {
            while (dis.ready()) {
                contents += dis.readLine();
                contents += "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        contents = contents.substring(0, contents.length() - 1);
        return contents;
    }

    public static void writeToFile(String path, String contents) throws IOException {
        File file = new File(path);
        //File dir = new File(getDirectory(path));
        File dir = new File(getDirectory(path));
        dir.mkdirs();
        //file.createNewFile();
        if(!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        if(contents != null) fw.write(contents);
        fw.close();
    }

    public static ArrayList<String> getFiles(String path) {
        ArrayList<String> paths = new ArrayList<>();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for(File file: listOfFiles) {
            if(file.isFile()) {
                paths.add(file.getPath());
            } else if (file.isDirectory()) {
                paths.addAll(getFiles(file.getPath()));
            }
        }
        return paths;
    }
}
