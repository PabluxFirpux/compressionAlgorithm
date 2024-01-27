package model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        byte[] data = new byte[(int) file.length()];
        try {
            fis.read(data, 0, data.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        contents = new String(data, StandardCharsets.ISO_8859_1);
        return contents;
    }

    public static void writeToFile(String path, String contents, boolean compMode) throws IOException {
        File file = new File(path);
        File dir = new File(getDirectory(path));
        dir.mkdirs();
        if (compMode && file.exists()) {
            while (file.exists()) {
                path = getCopiedFileName(path);
                file = new File(path);
            }
        }
        if(!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file, StandardCharsets.ISO_8859_1);
        if(contents != null) fw.write(contents);
        fw.close();
    }

    private static String getCopiedFileName(String path) {
        String[] parts = path.split("\\.");
        //return parts[parts.length - 2] + "_copy." + parts[parts.length - 1];
        File file = new File(path);
        if (file.isDirectory()) {
            return path + "_copy";
        } else {
            int i = 0;
            int n = 0;
            while (i < path.length()) {
                if (path.charAt(i) == '.') {
                    n = i;
                }
                i++;
            }
            return path.substring(0, n) + "_copy" + path.substring(n);
        }
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
