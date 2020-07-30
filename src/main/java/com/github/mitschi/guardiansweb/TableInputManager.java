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

    public static void GetTableInput(String Url, int NR) {
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

        } catch (Exception e) {
            e.printStackTrace();
        }
            if(Url.contains("http://localhost:8080/j")){
                SaveToDB(parsedString);
            }else{
            SaveChartToDB(parsedString);}
    }

    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private static final String INSERT_Date_value =
            "INSERT INTO date_value" +
                    "(date, value) VALUES " +
                    "(?, ?);";
    private static final String INSERT_ChartData =
            "INSERT INTO ChartData" +
                    "(date,failed,successful) VALUES " +
                    "(?, ?, ?);";

    private static String SaveToDB(String HTML) {
        H2Manager.TruncateTable("Date_value");
        String[] Tr = HTML.split("<tr>");
        // idx=1 da idx=0 = th der tablle
        for (int idx = 1; idx <= Tr.length - 1; idx++) {
            String[] Td = Tr[idx].split("\n");
            String tdName = Td[2].replaceAll("\\s+", "");
            String tdUrl = Td[3].replaceAll("\\s+", "");
            tdName = tdName.substring(4, tdName.length() - 5);
            tdUrl = tdUrl.substring(4, tdUrl.length() - 5);
            String[] values = {tdName, tdUrl};
            H2Manager.Insert(values, "Date_value", INSERT_Date_value);
        }
        return "";
    }

    private static String SaveChartToDB(String HTML) {
        H2Manager.TruncateTable("ChartData");
        String[] Tr = HTML.split("<tr>");
        // idx=1 da idx=0 = th der tablle oder html vor der tabelle
        for (int idx = 1; idx <= Tr.length - 1; idx++) {
            String[] Td = Tr[idx].split("\n");

            String tdweek = Td[1].replaceAll("\\s", "");
            String tdfailed = Td[2].replaceAll("\\s+", "");
            String tdsuccessful = Td[3].replaceAll("\\s+", "");
            System.out.println(tdweek);

            tdweek = tdweek.substring(4, tdweek.length() - 5);
            tdsuccessful = tdsuccessful.substring(4, tdsuccessful.length() - 5);
            tdfailed = tdfailed.substring(4, tdfailed.length() - 5);

            System.out.println(tdsuccessful + " " + tdsuccessful + " " + tdweek);
            String[] values = {tdweek, tdsuccessful, tdfailed};
            H2Manager.Insert(values, "ChartData", INSERT_ChartData);
        }
        return "";
    }
}
