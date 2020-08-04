package com.github.mitschi.guardiansweb.h2;

import java.sql.*;

// All operations which have
// something to do with H2 database access
public class H2Manager {

    private static final String jdbcURL = "jdbc:h2:file:~/test";
    private static final String jdbcUsername = "sa";
    private static final String jdbcPassword = "";

    public static Connection getConnection() {
        // Establishing a connection to the database

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void printSQLException(SQLException ex) {
        // SQL Exceptions are printed here

        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);

                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());

                Throwable t = ex.getCause();

                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public static String H2read(String QUERY, String[] columnLabels) {
        // Data is read from a database
        // and returned, where all values are separated by ","

        String textToWrite = "";

        try (Connection connection = getConnection();

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
        } catch (SQLException e) {
            printSQLException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return textToWrite;
    }

    public static void Insert(String[] values, String DB, String InsertStatement) {
        // Data is inserted into a database

        H2Manager createTableExample = new H2Manager();
        createTableExample.insertRecord(values, DB, InsertStatement);
    }

    public void insertRecord(String[] values, String DB, String InsertStatement) {
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(InsertStatement)) {

            for (int idx = 1; idx <= values.length; idx++) {
                preparedStatement.setString(idx, values[idx - 1]);
            }

            preparedStatement.executeUpdate();
            if (DB.contains("url_sources")) {
                update(DB);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static void delete(String QUERY) {
        // Specific records can be deleted from a database

        H2Manager deleteExample = new H2Manager();
        deleteExample.deleteRecord(QUERY);
    }

    public void deleteRecord(String QUERY) {
        try (Connection connection = getConnection();

             Statement statement = connection.createStatement()) {

            statement.execute(QUERY);
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static void update(String DB) {
        // All id's of the table are deleted
        // and assigned again
        // This is necessary when a record is deleted
        // so that no gaps appear in the id's

        H2Manager updateExample = new H2Manager();
        updateExample.updateIDs(DB);
    }

    public void updateIDs(String DB) {
        String QUERY = "Alter Table " + DB + "\ndrop column id;\nAlter Table " + DB + "\nAdd id int IDENTITY(1,1) PRIMARY KEY; ";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void TruncateTable(String DB) {
        // All records of the table are deleted
        // but the table is not

        try (Connection connection = getConnection();

             Statement statement = connection.createStatement()) {

            statement.execute("Truncate table " + DB);
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static void edit(String UpdateStatement, String value,int id) throws SQLException {
        H2Manager updateStatementExample = new H2Manager();
        updateRecord(UpdateStatement,value,id);
    }

    public static void updateRecord(String UpdateStatement, String value,int id) throws SQLException {

        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(UpdateStatement)) {
            preparedStatement.setString(1, value);
            preparedStatement.setInt(2,id);

            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }
    }
}
