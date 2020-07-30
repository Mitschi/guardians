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

    public static void GetTableInput(String Url){
        String parsedString="";
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
         SaveToDB(parsedString);
    }
    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line ="";
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return sb.toString();
    }
    private static String SaveToDB(String HTML){
        H2Manager.TruncateTable("Date_value");
        String[] Tr=HTML.split("<tr>");
        // idx=1 da idx=0 = th der tablle
        for(int idx=1;idx <= Tr.length-1;idx++){
            String[] Td=Tr[idx].split("\n");
            String tdName=Td[2].substring(4,Td[2].length()-5);
            String tdUrl=Td[3].substring(4,Td[3].length()-5);
            H2Manager.Insert(tdName,tdUrl);
        }
        return "";
    }
}
