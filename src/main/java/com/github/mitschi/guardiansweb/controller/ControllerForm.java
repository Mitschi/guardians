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

    @GetMapping("/form")
    public String greetingForm(Model model) {
        // HTML Code from the template "form" is displayed on the web

        model.addAttribute("tableEntry", new TableEntry());
        return "form";
    }

    @PostMapping("/form")
    public String greetingSubmit(@ModelAttribute TableEntry tableEntry) {
        // This method is called when the "submit" button is pressed
        // The values from tableEntry are inserted into the database

        String[] values = {tableEntry.getName(), tableEntry.getUrl()};
        H2Manager.Insert(values,"url_sources","INSERT INTO url_sources (name, url) VALUES (?, ?);");

        return "form";
    }

    @RequestMapping("/form/edit")
    @ResponseBody
    public String getId(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("url") String url) throws Exception {
        // Parameters from the URL are read and printed to the console
        // A database record is deleted, the user is redirected to the form page

        System.out.println("Id: " + id + "\tName: " + name + "\tUrl: " + url);
        H2Manager.delete("DELETE FROM url_sources WHERE id = " + id + ";");

        return FileHandler.readFromFile("src/main/resources/HTML/editRowRedirect.html");
    }
}