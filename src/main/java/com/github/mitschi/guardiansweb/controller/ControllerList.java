package com.github.mitschi.guardiansweb.controller;

import com.github.mitschi.guardiansweb.FileHandler;
import com.github.mitschi.guardiansweb.h2.H2Manager;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;

@RestController
public class ControllerList {

    @RequestMapping("/list")
    public static String index() throws FileNotFoundException {
        String filePath = "src/main/resources/HTML/list.html";
        String[] columnLabels = new String[] {"id", "name", "url"};

        String[] htmlLines = FileHandler.readFromFile(filePath).split("\n");

        String[] allRecords = H2Manager.H2read("SELECT * FROM url_sources", columnLabels).split("\n");

        String[][] separatedValues = new String[allRecords.length][columnLabels.length];

        for (int idx = 0; idx < allRecords.length; idx++) {
             separatedValues[idx] = allRecords[idx].split(",");
        }

        String htmlText = "";

        for (int idx = 0; idx < htmlLines.length; idx++) {
            htmlText = htmlText.concat(htmlLines[idx] + "\n");

            if (htmlLines[idx].contains("<!--tablePlaceholder-->")) {
                htmlText = htmlText.concat("<table>\n<tr>\n");

                for (int headerIdx = 0; headerIdx < columnLabels.length; headerIdx++) {
                    htmlText = htmlText.concat(String.format("<th>%s</th>\n", columnLabels[headerIdx]));
                }

                htmlText = htmlText.concat("<th>Edit Record</th>\n");
                htmlText = htmlText.concat("<th>Delete Record</th>\n</tr>\n");

                for (int outerIdx = 0; outerIdx < separatedValues.length; outerIdx++) {
                    String tempRowId = "row" + String.valueOf(outerIdx + 1);

                    htmlText = htmlText.concat(String.format("<tr id = \"%s\">\n", tempRowId));

                    for (int innerIdx = 0; innerIdx < separatedValues[outerIdx].length; innerIdx++) {
                        htmlText = htmlText.concat(String.format("<td>%s</td>\n", separatedValues[outerIdx][innerIdx]));
                    }

                    htmlText = htmlText.concat(String.format("<td><button class = \"btn btn-primary\" onclick = \"editRow(%s)\">Edit</button></td>\n", outerIdx + 1));
                    htmlText = htmlText.concat(String.format("<td><button class = \"btn btn-danger\" onclick = \"deleteRow(%s)\">Delete</button></td>\n</tr>\n", outerIdx + 1));
                }

                htmlText = htmlText.concat("</table>\n");
            }
        }

        H2Manager.update("url_sources");

        return htmlText;
    }
}