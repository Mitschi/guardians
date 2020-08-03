package com.github.mitschi.guardiansweb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {

    public static String readFromFile(String filePath) throws FileNotFoundException {
        // A file is read and returned as a string

        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        String fileContent = "";

        if (file.exists()) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine() + "\n";
                fileContent = fileContent.concat(line);
            }
        }
        else {
            throw new FileNotFoundException(filePath + "doesn't exist");
        }

        return fileContent;
    }

    public static String[][] convertStringTo2DArray(String stringToConvert) {
        // A string with values which are separated by ","
        // is converted into a two-dimensional array

        String[] lines = stringToConvert.split("\n");
        String[][] separatedValues = new String[lines.length][];

        try {
            for (int idx = 0; idx < lines.length; idx++) {
                separatedValues[idx] = lines[idx].split(",");
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }

        return separatedValues;
    }
}
