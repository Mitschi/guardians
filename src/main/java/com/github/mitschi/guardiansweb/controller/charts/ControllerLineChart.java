package com.github.mitschi.guardiansweb.controller.charts;

import com.github.mitschi.guardiansweb.ChartDataHandler;
import com.github.mitschi.guardiansweb.FileHandler;
import com.github.mitschi.guardiansweb.h2.H2Manager;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ControllerLineChart {

    @RequestMapping("/charts/line")
    public String index() throws Exception {
        // Data from a database is inserted into HTML code

        String filePath = "src/main/resources/HTML/charts/lineChart.html";
        String data = H2Manager.H2read("SELECT * FROM date_value ORDER BY date", new String[] {"date", "value"});
        String[][] separatedValues = FileHandler.convertStringTo2DArray(data);

        String[] columns = new String[] {"date", "value"};
        return ChartDataHandler.insertDataIntoHTMLFile(filePath, separatedValues,  "", columns);
    }
}
