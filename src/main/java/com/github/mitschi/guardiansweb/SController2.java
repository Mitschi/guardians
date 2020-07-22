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
        String path2="src/main/resources/Files/l2.html";
        File file = new File(path);
        File file2=new File(path2);


        Scanner scanner = new Scanner(file);
        Scanner scanner2 = new Scanner(file2);
        Scanner sc = new Scanner(System.in);
        String st = "";
        String st2= "";
        String line;
        Boolean write=true;
        int i1 = 12;
        int i2 = 1111;

        if (file.exists()) {
            line = scanner.nextLine();
            while (scanner.hasNextLine()) {
                if (line.equals("/*x*/") && write==true ) {
                    if(write==true) {
                        String string = "<tr><td>" + i1 + "</td><td>" + i2 + "</td></tr>\n" +
                                "/*x*/\n";
                        st = String.format("%s %s", st, string);
                        write=true;
                    }
                } else {
                    st = String.format("%s %s \n", st, line);
                }
                line = scanner.nextLine();
            }
            try {
                FileWriter myWriter = new FileWriter(path2);
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