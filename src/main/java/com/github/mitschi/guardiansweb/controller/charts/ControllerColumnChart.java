package com.github.mitschi.guardiansweb.controller.charts;

import com.github.mitschi.guardiansweb.ChartDataHandler;
import com.github.mitschi.guardiansweb.FileHandler;
import com.github.mitschi.guardiansweb.h2.H2Manager;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ControllerColumnChart {

    @RequestMapping("/charts/column")
    public String index() throws Exception {
        // An HTML File is read, data from a database is inserted into the HTML code

        String filePath = "src/main/resources/HTML/charts/columnChart.html";
        String data = H2Manager.H2read("SELECT * FROM guardians_values", new String[] {"country", "value"});
        String[][] separatedValues = FileHandler.convertStringTo2DArray(data);

        String[] columns = new String[] {"country", "visits"};

        return ChartDataHandler.insertDataIntoHTMLFile(filePath, separatedValues, "", columns);
    }
}