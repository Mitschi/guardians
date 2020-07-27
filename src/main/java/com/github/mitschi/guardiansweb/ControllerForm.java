package com.github.mitschi.guardiansweb;

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
        String filePath = "src/main/resources/Files/formInputData.csv";

        FileHandler.writeToFile(filePath, FileHandler.readFromFile(filePath) + tableEntry.toString());
        H2Insert.Insert(tableEntry.getCountry(),tableEntry.getValue());
        return "form";
    }

}