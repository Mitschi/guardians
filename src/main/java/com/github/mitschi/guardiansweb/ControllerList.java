package com.github.mitschi.guardiansweb;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@RestController
public class ControllerList {

    @RequestMapping("/list")
    public String index() throws FileNotFoundException {
        String filePath = "src/main/resources/Files/HTML/list.html";
        String targetFilePath = "src/main/resources/Files/draggingPieChartValues.csv";
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        String htmlText = "";

        boolean lineWasWritten = false;
        String testCol1 = "Austria";
        int testCol2 = 50;

        try {
            if (file.exists()) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();

                    if (line.contains("<!--trPlaceholder-->")) {
                        if (!lineWasWritten) {
                            String lineToWrite = String.format("\t\t\t\t\t\t<tr><td>%s</td><td>%s</td></tr>\n<!--trPlaceholder-->\n", testCol1, testCol2);
                            htmlText = htmlText.concat(lineToWrite);
                            lineWasWritten = true;

                            FileHandler.writeToFile(targetFilePath, String.format("%s%s,%s,", FileHandler.readFromFile(targetFilePath), testCol1, testCol2));
                        }
                    } else {
                        htmlText = htmlText.concat(line + "\n");
                    }
                }

                FileHandler.writeToFile(filePath, htmlText);
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