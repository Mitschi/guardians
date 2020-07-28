package com.github.mitschi.guardiansweb.h2;

import com.github.mitschi.guardiansweb.FileHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class H2Read {

    public static String H2read(String QUERY, String columnLabel1, String columnLabel2) {
        String texttowrite = "";

        try (Connection connection = H2JDBCUtils.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String country = rs.getString(columnLabel1);
                String value = rs.getString(columnLabel2);
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
