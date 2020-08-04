package com.github.mitschi.guardiansweb;

import com.github.mitschi.guardiansweb.h2.H2Manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class TableInputManager {

    public static void GetTableInput(String Url) {
        // Data is read from another website and then
        // saved to a database

        String parsedString = "";

        try {
            URL url = new URL(Url);
            URLConnection conn = url.openConnection();

            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            InputStream is = httpConn.getInputStream();
            parsedString = convertStreamToString(is);
            System.out.println(parsedString);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if (Url.contains("http://localhost:8080/list/data")) {
            SaveToDB(parsedString);
        }
        else {
            SaveChartToDB(parsedString);
        }
    }

    private static String convertStreamToString(InputStream is) {
        // This method converts an input stream to a string

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    private static void SaveToDB(String HTML) {
        // Data is saved to a database

        H2Manager.TruncateTable("url_sources");
        String[] Tr = HTML.split("<tr>");

        // idx is 1 at the beginning because idx 0 would be the table's head
        for (int idx = 1; idx <= Tr.length - 1; idx++) {
            String[] Td = Tr[idx].split("\n");
            String tdName = Td[2].replaceAll("\\s+", "");
            String tdUrl = Td[3].replaceAll("\\s+", "");

            tdName = tdName.substring(4, tdName.length() - 5);
            tdUrl = tdUrl.substring(4, tdUrl.length() - 5);

            String[] values = {tdName, tdUrl};

            H2Manager.Insert(values, "url_sources", "INSERT INTO url_sources (country, value) VALUES (?, ?);");
        }
    }

    private static void SaveChartToDB(String HTML) {
        // Data is saved to a database

        H2Manager.TruncateTable("ChartData");
        String[] Tr = HTML.split("<tr>");

        // idx is 1 at the beginning because idx 0 would be the table's head
        for (int idx = 1; idx <= Tr.length - 1; idx++) {
            String[] Td = Tr[idx].split("\n");

            String tdWeek = Td[1].replaceAll("\\s", "");
            String tdFailed = Td[2].replaceAll("\\s+", "");
            String tdSuccessful = Td[3].replaceAll("\\s+", "");
            System.out.println(tdWeek);

            tdWeek = tdWeek.substring(4, tdWeek.length() - 5);
            tdSuccessful = tdSuccessful.substring(4, tdSuccessful.length() - 5);
            tdFailed = tdFailed.substring(4, tdFailed.length() - 5);

            System.out.println(tdSuccessful + " " + tdFailed + " " + tdWeek);
            String[] values = {tdWeek, tdSuccessful, tdFailed};
            H2Manager.Insert(values, "ChartData", "INSERT INTO ChartData (date,failed,successful) VALUES (?, ?, ?);");
        }
    }
}