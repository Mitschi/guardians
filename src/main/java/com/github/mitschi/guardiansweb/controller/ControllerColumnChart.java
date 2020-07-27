package com.github.mitschi.guardiansweb.controller;

import com.github.mitschi.guardiansweb.ChartDataHandler;
import com.github.mitschi.guardiansweb.FileHandler;
import com.github.mitschi.guardiansweb.h2.H2Read;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@RestController
public class ControllerColumnChart {

    @RequestMapping("/columnChart")
    public String index() throws Exception {
        String filePath = "src/main/resources/HTML/columnChart.html";
        String data= H2Read.H2read();
        String[][] separatedValues= FileHandler.convertStringTo2DArray(data);

        ArrayList<String> columns = new ArrayList<String>();
        columns.add("country");
        columns.add("visits");

        return ChartDataHandler.insertDataIntoHTMLFile(filePath, separatedValues, "", columns);
    }
}