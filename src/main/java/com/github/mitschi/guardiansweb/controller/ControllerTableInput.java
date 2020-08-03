package com.github.mitschi.guardiansweb.controller;

import com.github.mitschi.guardiansweb.FileHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;

@RestController
public class ControllerTableInput {

    @RequestMapping("/j")
    public String index() throws FileNotFoundException {
        // This is just a website where data is displayed
        // Said data can be read from other methods

        String filePath = "src/main/resources/HTML/tableinput.html";
        return FileHandler.readFromFile(filePath);
    }
}