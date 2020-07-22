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
        String filePath = "src/main/resources/Files/list.html";
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        String htmlText = "";

        boolean lineWasWritten = false;
        int testNum1 = 111;
        int testNum2 = 222;
        int testNum3 = 333;

        try {
            if (file.exists()) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();

                    if (line.equals("/*x*/")) {
                        if (!lineWasWritten) {
                            String lineToWrite = String.format("\t\t\t<tr><td>%s</td><td>%s</td><td>%s</td></tr>\n/*x*/\n", testNum1, testNum2, testNum3);
                            htmlText = htmlText.concat(lineToWrite);
                            lineWasWritten = true;
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