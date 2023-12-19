package ru.vsu.cs.MikhailKostyrya.sql_connectors;

import java.sql.*;

public class PostgreSQLConn implements SQLConnector {
    protected final String url;
    protected Connection connection;


    private PostgreSQLConn() throws SQLException {
        String db = "marketplace";
        String user = "admin";
        String pass = "admin";
        url = "jdbc:postgresql://localhost:5432/" + db + "?user=" + user + "&password=" + pass;

        connection = DriverManager.getConnection(url);
    }

    private static PostgreSQLConn INSTANCE;

    public static PostgreSQLConn getInstance() throws SQLException {
        if(INSTANCE == null){
            INSTANCE = new PostgreSQLConn();
        }
        return INSTANCE;
    }

    @Override
    public ResultSet makeQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    @Override
    public PreparedStatement makeUpdate(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.execute();
        return statement;
    }

    protected void rebuildConnection() throws SQLException {
        try {
            connection.rollback();
        } catch (SQLException ignored){

        }
        try {
            connection.close();
        } catch (SQLException ignored){

        }
        connection = DriverManager.getConnection(url);
    }

}