package com.github.mitschi.guardiansweb;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

@RestController
public class SController1 {

    @RequestMapping("/previous")
    public String index() throws Exception {
        String path = "src/main/resources/Files/previous.html";
        File file = new File(path);


        Scanner scanner = new Scanner(file);
        String st = "";

        if (file.exists()) {
            while (scanner.hasNextLine()) {
                st =String.format("%s %s\n",st,scanner.nextLine());
            }
        } else {
            throw new Exception("file doesnt exist");
        }

        return st;
    }

}