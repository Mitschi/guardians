package com.github.mitschi.guardiansweb.controller;

import com.github.mitschi.guardiansweb.FileHandler;
import com.github.mitschi.guardiansweb.TableInputManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
public class ControllerStackedIputSuccessful {

    @GetMapping("/StackedSuccessful")
    public String index() throws FileNotFoundException {
        // Data is read from another website

        TableInputManager.GetTableInput("http://localhost:8080/s");
        return FileHandler.readFromFile("src/main/resources/HTML/StackedSuccessful.html");
    }
}
