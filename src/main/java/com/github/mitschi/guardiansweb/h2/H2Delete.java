package com.github.mitschi.guardiansweb.h2;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class H2Delete {

    public static void delete(String deleteTableSQL) throws SQLException {
        H2Delete deleteExample = new H2Delete();
        deleteExample.deleteRecord(deleteTableSQL);
    }

    public void deleteRecord(String deleteTableSQL) throws SQLException {

        System.out.println(deleteTableSQL);
        // Step 1: Establishing a Connection
        try (Connection connection = H2JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             Statement statement = connection.createStatement();) {

            // Step 3: Execute the query or update query
            statement.execute(deleteTableSQL);

        } catch (SQLException e) {
            // print SQL exception information
            H2JDBCUtils.printSQLException(e);
        }
    }
}