package com.github.mitschi.guardiansweb.controller;

import com.github.mitschi.guardiansweb.h2.H2Insert;
import com.github.mitschi.guardiansweb.TableEntry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerForm {

    @GetMapping("/form")
    public String greetingForm(Model model) {
        model.addAttribute("tableEntry", new TableEntry());

        return "form";
    }

    @PostMapping("/form")
    public String greetingSubmit(@ModelAttribute TableEntry tableEntry) throws Exception {
        H2Insert.Insert(tableEntry.getCountry(), tableEntry.getValue());

        return "form";
    }
}