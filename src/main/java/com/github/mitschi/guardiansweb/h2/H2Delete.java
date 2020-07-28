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

        try (Connection connection = H2JDBCUtils.getConnection();

            Statement statement = connection.createStatement();) {

            statement.execute(deleteTableSQL);
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }
}