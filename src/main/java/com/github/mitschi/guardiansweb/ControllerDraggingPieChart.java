package com.github.mitschi.guardiansweb;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;

@RestController
public class ControllerDraggingPieChart {

    @RequestMapping("/draggingPieChart")
    public String index() throws FileNotFoundException {
        String filePath = "src/main/resources/Files/draggingPieChart.html";

        return FileHandler.readFromFile(filePath);
    }
}