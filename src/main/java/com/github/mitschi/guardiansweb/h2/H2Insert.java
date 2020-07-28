package com.github.mitschi.guardiansweb.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class H2Insert {
    private static final String INSERT_USERS_SQL =
            "INSERT INTO url_sources" +
            "(id, name, url) VALUES " +
            "(?, ?, ?);";

    public static void Insert(String value1, String value2) {
        H2Insert createTableExample = new H2Insert();
        createTableExample.insertRecord(value1, value2);
    }

    public void insertRecord(String value1, String value2) {
        try (Connection connection = H2JDBCUtils.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {

            String[] lastRecord = H2Read.H2read("SELECT * FROM url_sources ORDER BY ID DESC LIMIT 1", new String[] {"id", "name", "url"}).split(",");

            int lastId;

            if (lastRecord[0].equals("")) {
                lastId = 0;
            }
            else {
                lastId = Integer.parseInt(lastRecord[0]);
            }

            lastId++;

            preparedStatement.setString(1, String.valueOf(lastId));
            preparedStatement.setString(2, value1);
            preparedStatement.setString(3, value2);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }
}