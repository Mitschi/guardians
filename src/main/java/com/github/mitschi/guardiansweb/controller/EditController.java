package com.github.mitschi.guardiansweb.controller;

import com.github.mitschi.guardiansweb.FileHandler;
import com.github.mitschi.guardiansweb.TableEntry;
import com.github.mitschi.guardiansweb.h2.H2Manager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.*;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

@Controller
public class EditController {
    public static String ID;
    private static final String INSERT_Url_sources =
            "INSERT INTO url_sources" +
                    "(name, url) VALUES " +
                    "(?, ?);";


    @GetMapping("/formEdit")
    public String greetingEdit(Model model,@RequestParam ("id") String id) {
        model.addAttribute("tableEntry", new TableEntry());
        System.out.println(id);
        ID=String.valueOf(id);
        return "FormEdit";
    }



@RequestMapping("/formEdit")
    public String greetingSubmit(@ModelAttribute TableEntry tableEntry) throws SQLException {
        String[] values = {tableEntry.getName(), tableEntry.getUrl()};

            H2Manager.updateRecord("update url_sources set name = ? where id = ?;",values[0],parseInt(ID));
            H2Manager.updateRecord("update url_sources set url = ? where id = ?;",values[1],parseInt(ID));


        return "FormEdit";
    }
}
