package com.github.mitschi.guardiansweb;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@RestController
public class ControllerColumnChart {

    @RequestMapping("/columnChart")
    public String index() throws Exception {
        String filePath = "src/main/resources/Files/HTML/columnChart.html";
        String dataFilePath = "src/main/resources/Files/DB.csv";


        ArrayList<String> columns = new ArrayList<String>();
        columns.add("country");
        columns.add("visits");

        return ChartDataHandler.insertDataIntoHTMLFile(filePath, dataFilePath, "", columns);
    }
}