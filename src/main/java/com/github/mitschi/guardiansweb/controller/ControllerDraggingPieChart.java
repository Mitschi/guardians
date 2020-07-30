package com.github.mitschi.guardiansweb.controller;

import com.github.mitschi.guardiansweb.ChartDataHandler;
import com.github.mitschi.guardiansweb.FileHandler;
import com.github.mitschi.guardiansweb.h2.H2Manager;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ControllerDraggingPieChart {
    // InsertStatement für DraggingPieChart
    //private static final String INSERT_Date_value =
    //        "INSERT INTO guardians_values" +
    //                "(country, value) VALUES " +
    //                "(?, ?);";

    @RequestMapping("/draggingPieChart")
    public String index() throws Exception {
        String filePath = "src/main/resources/HTML/draggingPieChart.html";
        String data = H2Manager.H2read("SELECT * FROM guardians_values", new String[] {"country", "value"});
        String[][] separatedValues = FileHandler.convertStringTo2DArray(data);

        String dummyValues = "\"country\": \"Dummy\",\n" +
                "\"disabled\": true,\n" +
                "\"litres\": 1000,\n" +
                "\"color\": am4core.color(\"#dadada\"),\n" +
                "\"opacity\": 0.3,\n" +
                "\"strokeDasharray\": \"4,4\"}, ";

        String[] columns = new String[] {"country", "litres"};

        return ChartDataHandler.insertDataIntoHTMLFile(filePath, separatedValues, dummyValues, columns);
    }
}