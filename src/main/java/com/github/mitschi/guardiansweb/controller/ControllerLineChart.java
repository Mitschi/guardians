package com.github.mitschi.guardiansweb.controller;

import com.github.mitschi.guardiansweb.ChartDataHandler;
import com.github.mitschi.guardiansweb.FileHandler;
import com.github.mitschi.guardiansweb.h2.H2Read;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;

@RestController
public class ControllerLineChart {

    @RequestMapping("/lineChart")
    public String index() throws Exception {
        String filePath = "src/main/resources/HTML/lineChart.html";
        String data= H2Read.H2read("select * from date_value order by date","date","value");
        String[][] separatedValues= FileHandler.convertStringTo2DArray(data);

        String[] columns = new String[] {"date", "value"};

        return ChartDataHandler.insertDataIntoHTMLFile(filePath, separatedValues, "", columns);
    }
}
