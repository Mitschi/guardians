package com.github.mitschi.guardiansweb;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;

@RestController
public class ControllerPrevious {

    @RequestMapping("/previous")
    public String index() throws FileNotFoundException {
        String filePath = "src/main/resources/Files/previous.html";

        return FileHandler.readFromFile(filePath);
    }

}