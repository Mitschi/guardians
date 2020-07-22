package com.github.mitschi.guardiansweb;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

@RestController
public class SController2 {

    @RequestMapping("/list")
    public String index() throws Exception {
        String path = "src/main/resources/Files/list.html";
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        String st = "";
        String line;
        boolean write = true;
        int i1 = 0;
        int i2 = 1111;

        if (file.exists()) {
            line = scanner.nextLine();
            while (scanner.hasNextLine()) {
                if (line.equals("/*x*/")) {
                    if (write) {
                        i1++;
                        String string = "\t\t\t<tr><td>" + i1 + "</td><td>" + i2 + "</td></tr>\n" + "/*x*/\n" + "/*y*/\n";
                        st = String.format("%s %s", st, string);
                        write = false;
                    }
                } else {
                    st = String.format("%s %s \n", st, line);
                }
                line = scanner.nextLine();
            }
            try {
                FileWriter myWriter = new FileWriter(path);
                myWriter.write(st);
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        } else {
            throw new Exception("file doesnt exist");
        }

        System.out.print(st);

        return st;
    }

}