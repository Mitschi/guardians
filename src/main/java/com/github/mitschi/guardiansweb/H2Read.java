package com.github.mitschi.guardiansweb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class H2Read {
    private static final String QUERY = "select * from guardians_values";

    public static void H2read() {

        // using try-with-resources to avoid closing resources (boiler plate code)

        // Step 1: Establishing a Connection
        try (Connection connection = H2JDBCUtils.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            String texttowrite="";
            String path="src/main/resources/Files/DB.csv";
            System.out.println("read start");
            while (rs.next()) {
                String country = rs.getString("country");
                String value = rs.getString("value");
                texttowrite=texttowrite + country + "," +value+",\n";
                System.out.println( country + "," + value);
            }
            System.out.println("read end");
            FileHandler.writeToFile(path,texttowrite);
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Step 4: try-with-resource statement will auto close the connection.
    }
}
