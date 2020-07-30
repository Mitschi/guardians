package com.github.mitschi.guardiansweb.controller;

import com.github.mitschi.guardiansweb.FileHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

    @RestController
    public class ControllerStackedInput {

        @RequestMapping("/s")
        public String index() throws FileNotFoundException {
            String filePath = "src/main/resources/HTML/StackedChartInput.html";

            return FileHandler.readFromFile(filePath);
        }

    }
