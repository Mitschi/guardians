package com.github.mitschi.guardiansweb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Data is converted to JSON format
// and inserted into HTML files
// so that the charts work
public class ChartDataHandler {

    public static String convertToJSONString(String[][] separatedValues, String dummyValues, String[] columns) {
        // Data from a two-dimensional array is converted into a JSON string

        String chartDataString = "";
        chartDataString = chartDataString.concat("var data = [");

        for (int idx = -1; idx < separatedValues.length; idx++) {
            if (idx == -1) {
                if (!dummyValues.equals("")) {
                    chartDataString = chartDataString.concat(String.format("{%s ", dummyValues));

                    if (separatedValues.length > 0) {
                        chartDataString = chartDataString.concat(",");
                    }
                }
            }
            else {
                chartDataString = chartDataString.concat("{");

                for (int innerIdx = 0; innerIdx < separatedValues[idx].length; innerIdx++) {
                    chartDataString = chartDataString.concat(String.format("\"%s\": \"%s\"", columns[innerIdx], separatedValues[idx][innerIdx]));

                    if (innerIdx != columns.length - 1) {
                        chartDataString = chartDataString.concat(",");
                    }

                    chartDataString = chartDataString.concat("\n");
                }

                chartDataString = chartDataString.concat("}");

                if (idx != separatedValues.length - 1) {
                    chartDataString = chartDataString.concat(", ");
                }
            }
        }

        chartDataString = chartDataString.concat("];");
        return chartDataString;
    }

    public static String insertDataIntoHTMLFile(String htmlFilePath, String[][] separatedValues, String dummyValues, String[] columns) throws Exception {
        // Data from a two-dimensional array is inserted at a placeholder comment in an HTML file

        String chartValues = convertToJSONString(separatedValues, dummyValues, columns);

        File file = new File(htmlFilePath);
        Scanner scanner = new Scanner(file);
        String htmlText = "";

        if (file.exists()) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.contains("/*dataPlaceholder*/")) {
                    htmlText = htmlText.concat(chartValues);
                }
                else {
                    htmlText = htmlText.concat(line + "\n");
                }
            }
        }
        else {
            throw new FileNotFoundException(htmlFilePath + "doesn't exist");
        }

        return htmlText;
    }
}