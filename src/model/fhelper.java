package model;

import java.io.*;

public class fhelper {
    public static String getExtension(String path) {
        String[] parts = path.split("\\.");
        return parts[parts.length - 1];
    }

    public static String getName(String path) {
        String[] parts = path.split("\\.");
        return parts[parts.length - 2];
    }

    public static String getDirectory(String path) {
        String[] parts = path.split("\\.");
        return parts[parts.length - 3];
    }

    public static String getFileName(String path) {
        String[] parts = path.split("\\.");
        return parts[parts.length - 2] + "." + parts[parts.length - 1];
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
        if(!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        fw.write(contents);
        fw.close();
    }
}
