package com.github.mitschi.guardiansweb;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;

@RestController
public class ControllerCharts {

    @RequestMapping("/charts")
    public String index() throws FileNotFoundException {
        String filePath = "src/main/resources/Files/HTML/charts.html";

        return FileHandler.readFromFile(filePath);
    }
}