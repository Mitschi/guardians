package com.github.mitschi.guardiansweb.controller;

import com.github.mitschi.guardiansweb.TableEntry;
import com.github.mitschi.guardiansweb.TableInputManager;
import com.github.mitschi.guardiansweb.h2.H2Manager;
import org.h2.util.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.lang.model.util.Elements;
import javax.swing.text.Document;
import javax.swing.text.Element;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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



}