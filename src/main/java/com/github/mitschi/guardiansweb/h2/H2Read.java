package com.github.mitschi.guardiansweb.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H2Read {

    public static String H2read(String QUERY, String columnLabel1, String columnLabel2) {
        String textToWrite = "";

        try (Connection connection = H2JDBCUtils.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String country = rs.getString(columnLabel1);
                String value = rs.getString(columnLabel2);
                textToWrite = textToWrite.concat(String.format("%s,%s,\n", country, value));
            }

        }
        catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return textToWrite;
    }
}
