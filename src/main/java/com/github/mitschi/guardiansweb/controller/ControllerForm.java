package com.github.mitschi.guardiansweb.controller;

import com.github.mitschi.guardiansweb.FileHandler;
import com.github.mitschi.guardiansweb.TableEntry;
import com.github.mitschi.guardiansweb.h2.H2Manager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.*;

@Controller
public class ControllerForm {
    private static final String INSERT_Url_sources =
            "INSERT INTO url_sources" +
                    "(name, url) VALUES " +
                    "(?, ?);";

    @GetMapping("/form")
    public String greetingForm(Model model) {
        model.addAttribute("tableEntry", new TableEntry());

        return "form";
    }

    @PostMapping("/form")
    public String greetingSubmit(@ModelAttribute TableEntry tableEntry) {
        String[] values = {tableEntry.getName(), tableEntry.getUrl()};

        H2Manager.Insert(values,"url_sources",INSERT_Url_sources);

        return "form";
    }

    @RequestMapping("/list/editRow")
    @ResponseBody
    public String getId(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("url") String url) throws Exception {
        System.out.println("Id: " + id + "\nName: " + name + "\nUrl: " + url);

        String htmlText = FileHandler.readFromFile("src/main/resources/templates/form.html");

        String oldText1 = "                <p>Name: <input type=\"text\" th:field=\"*{name}\"></p>\n";
        String oldText2 = "<p>URL: <input type=\"text\" th:field=\"*{url}\" /></p>";
        String newText1 = "                <p>Name: <input type=\"text\" th:field=\"*{name}\" value=\"" + name + "\"></p>\n";
        String newText2 = "<p>URL: <input type=\"text\" th:field=\"*{url}\" value=\"" + url + "\" /></p>";

        if (htmlText.contains(oldText1)) {
            System.out.println("Everything good at the start1");
        }
        if (htmlText.contains(oldText2)) {
            System.out.println("Everything good at the start2");
        }

        htmlText = htmlText.replace(oldText1, newText1);
        htmlText = htmlText.replace(oldText2, newText2);

        if (htmlText.contains(newText1)) {
            System.out.println("Everything good at the end1");
        }
        if (htmlText.contains(newText2)) {
            System.out.println("Everything good at the end2");
        }

        return htmlText;
    }
}