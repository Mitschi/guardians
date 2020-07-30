package com.github.mitschi.guardiansweb.h2;

import java.sql.*;

public class H2Manager {

    private static String jdbcURL = "jdbc:h2:file:~/test";
    private static String jdbcUsername = "sa";
    private static String jdbcPassword = "";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void printSQLException(SQLException ex) {
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
        }
        catch (SQLException e) {
            printSQLException(e);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return textToWrite;
    }

    public static void Insert(String[] values, String DB, String InsertStatement) {
        H2Manager createTableExample = new H2Manager();
        createTableExample.insertRecord(values, DB,InsertStatement);
    }

    public void insertRecord(String[] values, String DB,String InsertStatement) {
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(InsertStatement)) {

            for (int idx = 1; idx <= values.length;idx++) {
                preparedStatement.setString(idx, values[idx-1]);
            }

            preparedStatement.executeUpdate();
            if(values.length>2){
            update(DB);}
        }
        catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static void delete(String QUERY) {
        H2Manager deleteExample = new H2Manager();
        deleteExample.deleteRecord(QUERY);
    }

    public void deleteRecord(String QUERY) {
        try (Connection connection = getConnection();

            Statement statement = connection.createStatement()) {

            statement.execute(QUERY);
        }
        catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static void update(String DB) {
        H2Manager updateExample = new H2Manager();
        updateExample.updateIDs(DB);
    }

    public void updateIDs(String DB) {
        String QUERY = "Alter Table " + DB + "\ndrop column id;\nAlter Table " + DB + "\nAdd id int IDENTITY(1,1) PRIMARY KEY; ";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void TruncateTable(String DB) {
        try (Connection connection = getConnection();

             Statement statement = connection.createStatement()) {

            statement.execute("Truncate table " + DB);
        }
        catch (SQLException e) {
            printSQLException(e);
        }
    }
}
