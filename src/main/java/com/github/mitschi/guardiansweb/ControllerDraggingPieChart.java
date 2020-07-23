package com.github.mitschi.guardiansweb;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

@RestController
public class ControllerDraggingPieChart {

    @RequestMapping("/draggingPieChart")
    public String index() throws Exception {
        String filePath = "src/main/resources/Files/draggingPieChart.html";
        String dataFilePath = "src/main/resources/Files/tableValues.csv";

        String dummyElement = "\"country\": \"Dummy\",\n" +
                "\"disabled\": true,\n" +
                "\"litres\": 1000,\n" +
                "\"color\": am4core.color(\"#dadada\"),\n" +
                "\"opacity\": 0.3,\n" +
                "\"strokeDasharray\": \"4,4\"}, ";

        ArrayList<String> columns = new ArrayList<String>();
        columns.add("country");
        columns.add("litres");

        String chartValues = FileHandler.getChartDataFromCSVFile(dataFilePath, columns, dummyElement);

        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        String htmlText = "";

        try {
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
                throw new FileNotFoundException(filePath + "doesn't exist");
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return htmlText;
    }
}