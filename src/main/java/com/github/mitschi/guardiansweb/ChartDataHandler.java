package com.github.mitschi.guardiansweb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ChartDataHandler {
    public static String insertDataIntoHTMLFile(String htmlFilePath, String dataFilePath, String dummyElement, ArrayList<String> columns) throws Exception {
        String chartValues = FileHandler.getChartDataFromCSVFile(dataFilePath, columns, dummyElement);

        File file = new File(htmlFilePath);
        Scanner scanner = new Scanner(file);
        String htmlText = "";

        if (file.exists()) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.contains("/*dataPlaceholder*/")) {
                    htmlText = htmlText.concat(chartValues);
                } else {
                    htmlText = htmlText.concat(line + "\n");
                }
            }
        } else {
            throw new FileNotFoundException(htmlFilePath + "doesn't exist");
        }

        return htmlText;
    }
}
