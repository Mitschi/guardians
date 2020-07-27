package com.github.mitschi.guardiansweb;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

@RestController
public class ControllerDraggingPieChart {

    @RequestMapping("/draggingPieChart")
    public String index() throws Exception {
        String filePath = "src/main/resources/Files/HTML/draggingPieChart.html";
        String data=H2Read.H2read();
        String[][] separatedValues=FileHandler.convertStringToTable(data);

        String dummyValues = "\"country\": \"Dummy\",\n" +
                "\"disabled\": true,\n" +
                "\"litres\": 1000,\n" +
                "\"color\": am4core.color(\"#dadada\"),\n" +
                "\"opacity\": 0.3,\n" +
                "\"strokeDasharray\": \"4,4\"}, ";

        ArrayList<String> columns = new ArrayList<String>();
        columns.add("country");
        columns.add("litres");

        return ChartDataHandler.DBinsertDataIntoHTMLFile(filePath,separatedValues,dummyValues,columns);
    }
}