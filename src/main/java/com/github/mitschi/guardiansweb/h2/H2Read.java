package com.github.mitschi.guardiansweb.h2;

import com.github.mitschi.guardiansweb.FileHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H2Read {

    private static final String QUERY = "select * from guardians_values";

    public static String H2read() {
        String texttowrite = "";

        try (Connection connection = H2JDBCUtils.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String country = rs.getString("country");
                String value = rs.getString("value");
                texttowrite=texttowrite + country + "," +value+",\n";
                System.out.println( country + "," + value);
            }

        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return texttowrite;
    }
}
