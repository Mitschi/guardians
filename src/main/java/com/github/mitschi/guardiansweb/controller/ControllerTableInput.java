package com.github.mitschi.guardiansweb.controller;

import com.github.mitschi.guardiansweb.FileHandler;
import com.github.mitschi.guardiansweb.TableInputManager;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;

@RestController
public class ControllerTableInput {

    @RequestMapping("/tableInput")
    public String index() throws FileNotFoundException {
        String filePath = "src/main/resources/HTML/tableInput.html";

        TableInputManager.GetTableInput("http://localhost:8080/tableInput");

        return FileHandler.readFromFile(filePath);
    }
}