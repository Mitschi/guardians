package com.github.mitschi.guardiansweb.controller;

import com.github.mitschi.guardiansweb.ChartDataHandler;
import com.github.mitschi.guardiansweb.FileHandler;
import com.github.mitschi.guardiansweb.h2.H2Manager;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ControllerStackedColumnChart {

    @RequestMapping("/StackedcolumnChart")
    public String index() throws Exception {
        // Data from a database is inserted into HTML code

        String filePath = "src/main/resources/HTML/StackedColumnChart.html";
        String data = H2Manager.H2read("SELECT * FROM ChartData", new String[] {"date", "failed","successful"});
        String[][] separatedValues = FileHandler.convertStringTo2DArray(data);

        String[] columns = new String[] {"category", "failed","successful"};

        return ChartDataHandler.insertDataIntoHTMLFile(filePath, separatedValues, "", columns);
    }
}