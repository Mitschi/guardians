package com.github.mitschi.guardiansweb;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.File;
import java.util.Scanner;

@RestController
public class SController2 {

    @RequestMapping("/next")
    public String index() throws Exception {
        String path = "src/main/resources/Files/next.html";
        File file = new File(path);


        Scanner scanner = new Scanner(file);
        String st = "";

        if (file.exists()) {
            while (scanner.hasNextLine()) {
                st =String.format("%s %s",st,scanner.nextLine());
            }
        } else {
            throw new Exception("file doesnt exist");
        }

        return st;
    }

}