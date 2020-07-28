package com.github.mitschi.guardiansweb.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H2Read {

    public static String H2read(String QUERY, String[] columnLabels) {
        String textToWrite = "";

        try (Connection connection = H2JDBCUtils.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                for (int idx = 0; idx < columnLabels.length; idx++) {
                    String cellValue = rs.getString(columnLabels[idx]);

                    textToWrite = textToWrite.concat(String.format("%s,", cellValue));

                    if (idx == columnLabels.length - 1) {
                        textToWrite = textToWrite.concat("\n");
                    }
                }
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
