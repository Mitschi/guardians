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

        H2Manager.delete("DELETE FROM url_sources WHERE id = " + id + ";");
        H2Manager.update("url_sources");

        return FileHandler.readFromFile("src/main/resources/HTML/deleteRowRedirect.html");
    }
}