package com.postat;

import java.sql.*;

public class Database {
    private String dbName = "postapp";
    private String userName = "admin";
    private String password = "admin";
    private String sqlClassName = "com.mysql.jdbc.Driver";
    private String connectionPath = "jdbc:mysql://localhost/"+dbName+"?autoReconnect=true&useSSL=false";
    private Connection connection;
    private PreparedStatement preparedStatement;
    public Database(){
        try{
            Class.forName(sqlClassName);
            connection = DriverManager.getConnection(connectionPath,userName,password);
        }catch(ClassNotFoundException e) {
            System.out.println("ClassNotFoundException: "+e.getCause());
        }catch (SQLException e){
            System.out.println("SQL Exception: "+e.getCause());
        }
    }

    public void prepare(String sql) throws SQLException{
        preparedStatement = connection.prepareStatement(sql);
    }

    public void setInt(int paramIndex,int val) throws SQLException{
        preparedStatement.setInt(paramIndex,val);
    }

    public void setString(int paramIndex,String val) throws SQLException{
        preparedStatement.setString(paramIndex,val);
    }

    public boolean execute() throws SQLException{
        return preparedStatement.execute();
    }

    public ResultSet executeQuery() throws SQLException{
        return preparedStatement.executeQuery();
    }

    public void close() throws SQLException{
        connection.close();
        System.out.println("Database closed...");
    }
}