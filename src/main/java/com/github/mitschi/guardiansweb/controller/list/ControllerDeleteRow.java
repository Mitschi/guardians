package com.github.mitschi.guardiansweb.controller.list;

import com.github.mitschi.guardiansweb.FileHandler;
import com.github.mitschi.guardiansweb.h2.H2Manager;
import org.springframework.web.bind.annotation.*;

@RestController()
public class ControllerDeleteRow {

    @RequestMapping("/list/deleteRow")
    @ResponseBody
    public String getId(@RequestParam String id) throws Exception {
        // Method is called when the user wants to delete a database record
        // An HTML file is returned here which redirects to list.html

        H2Manager.delete("DELETE FROM url_sources WHERE id = " + id + ";");
        H2Manager.update("url_sources");

        return FileHandler.readFromFile("src/main/resources/HTML/list/deleteRowRedirect.html");
    }
}
