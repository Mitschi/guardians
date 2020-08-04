package com.github.mitschi.guardiansweb.controller.charts;

import com.github.mitschi.guardiansweb.FileHandler;
import com.github.mitschi.guardiansweb.TableInputManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
public class ControllerStackedColumnChartDataSuccess {

    @GetMapping("/charts/stackedColumn/data/success")
    public String index() throws FileNotFoundException {
        // Data is read from another website

        TableInputManager.GetTableInput("http://localhost:8080/charts/stackedColumn/data");
        return FileHandler.readFromFile("src/main/resources/HTML/charts/stackedColumnChartDataRedirect.html");
    }
}
