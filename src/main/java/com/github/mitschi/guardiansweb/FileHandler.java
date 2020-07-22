package com.github.mitschi.guardiansweb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class FileHandler {

    public static String readFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        String fileContent = "";

        if (file.exists()) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine() + "\n";
                fileContent = fileContent.concat(line);
            }
        } else {
            throw new FileNotFoundException(filePath + "doesn't exist");
        }

        return fileContent;
    }

    public static void writeToFile(String filePath, String textToWrite) throws Exception {
        FileWriter myWriter = new FileWriter(filePath);

        try {
            myWriter.write(textToWrite);
        }
        catch (Exception ex) {
        }
        finally {
            myWriter.close();
        }
    }
}
