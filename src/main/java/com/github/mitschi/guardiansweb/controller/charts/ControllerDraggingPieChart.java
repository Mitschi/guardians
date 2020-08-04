package com.github.mitschi.guardiansweb.controller.charts;

import com.github.mitschi.guardiansweb.ChartDataHandler;
import com.github.mitschi.guardiansweb.FileHandler;
import com.github.mitschi.guardiansweb.h2.H2Manager;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ControllerDraggingPieChart {

    @RequestMapping("/charts/draggingPie")
    public String index() throws Exception {
        // Data from a database is inserted into HTML code
        // The dummy values are needed for the "Dragging Pie Chart" to work
        // These values create the circle the user can drag slices onto

        String filePath = "src/main/resources/HTML/charts/draggingPieChart.html";
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