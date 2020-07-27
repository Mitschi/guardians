package com.github.mitschi.guardiansweb;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.sql.SQLException;

@RestController
public class TestController {

    @RequestMapping("/test")
    public String index() throws FileNotFoundException, SQLException {
        String filePath = "src/main/resources/Files/test.html";

        H2Insert.Insert(12343,"testvalue1");
        System.out.println("Insert complete");
        H2Read.H2read();
        return FileHandler.readFromFile(filePath);
    }
}