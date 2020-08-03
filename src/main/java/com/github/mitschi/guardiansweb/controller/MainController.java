package com.github.mitschi.guardiansweb.controller;

import com.github.mitschi.guardiansweb.FileHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;

@RestController
public class MainController {

    @RequestMapping("/")
    public String index() throws FileNotFoundException {
        // HTML Code is read from a file

        String filePath = "src/main/resources/HTML/index.html";
        return FileHandler.readFromFile(filePath);
    }
}