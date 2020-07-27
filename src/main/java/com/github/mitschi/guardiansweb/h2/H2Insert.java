package com.github.mitschi.guardiansweb.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class H2Insert {
    private static final String INSERT_USERS_SQL =
            "INSERT INTO guardians_values" +
            "(country,value) VALUES " +
            "(?, ?);";

    public static void Insert(String value1, int value2) throws SQLException {
        H2Insert createTableExample = new H2Insert();
        createTableExample.insertRecord(value1, value2);
    }

    public void insertRecord(String value1, int value2) {
        System.out.println(INSERT_USERS_SQL);

        try (Connection connection = H2JDBCUtils.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, value1 );
            preparedStatement.setString(2, String.valueOf(value2));

            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }
}