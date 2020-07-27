package com.github.mitschi.guardiansweb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
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

    public static String getChartDataFromCSVFile(String filePath, ArrayList<String> columns, String dummyValues) throws FileNotFoundException {
        String chartDataString = "";
        String[] lines = readFromFile(filePath).split("\n");

        String[][] separatedValues = new String[lines.length][];

        for (int idx = 0; idx < lines.length; idx++) {
            separatedValues[idx] = lines[idx].split(",");
        }

        chartDataString = chartDataString.concat("var data = [");

        for (int idx = -1; idx < lines.length; idx++) {
            if (idx == -1) {
                if (dummyValues != "") {
                    chartDataString = chartDataString.concat(String.format("{%s ", dummyValues));

                    if (separatedValues.length > 0) {
                        chartDataString = chartDataString.concat(",");
                    }
                }
            }
            else {
                chartDataString = chartDataString.concat("{");

                for (int innerIdx = 0; innerIdx < columns.size(); innerIdx++) {
                    chartDataString = chartDataString.concat(String.format("\"%s\": \"%s\"", columns.get(innerIdx), separatedValues[idx][innerIdx]));

                    if (innerIdx != columns.size() - 1) {
                        chartDataString = chartDataString.concat(",");
                    }

                    chartDataString = chartDataString.concat("\n");
                }

                chartDataString = chartDataString.concat("}");

                if (idx != lines.length - 1) {
                    chartDataString = chartDataString.concat(", ");
                }
            }
        }

        chartDataString = chartDataString.concat("];");

        return chartDataString;
    }
}
