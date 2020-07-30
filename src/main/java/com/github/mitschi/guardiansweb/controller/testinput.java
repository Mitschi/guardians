package com.github.mitschi.guardiansweb.controller;

import com.github.mitschi.guardiansweb.FileHandler;
import com.github.mitschi.guardiansweb.TableInputManager;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
public class testinput {

    @GetMapping("/input")
    public String index() throws FileNotFoundException {

        TableInputManager.GetTableInput("http://localhost:8080/j",1);

        return FileHandler.readFromFile("src/main/resources/HTML/test.html");
    }
}