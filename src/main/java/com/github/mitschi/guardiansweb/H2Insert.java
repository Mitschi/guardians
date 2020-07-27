package com.github.mitschi.guardiansweb;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Insert PrepareStatement JDBC Example
 *
 * @author Ramesh Fadatare
 *
 */
public class H2Insert{
    private static final String INSERT_USERS_SQL =
            "INSERT INTO guardians_values" +
            "  (country,value) VALUES " +
            " (?, ?);";

    public static void Insert(int value2,String value1) throws SQLException {
        H2Insert createTableExample = new H2Insert();
        createTableExample.insertRecord(value2,value1);
    }

    public void insertRecord(int value2,String value1) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = H2JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, value1 );
            preparedStatement.setString(2, String.valueOf(value2));

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            H2JDBCUtils.printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }
}