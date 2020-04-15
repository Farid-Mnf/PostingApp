package com.postat;

public class DataBase {
    private String dbName = "postapp";
    private String userName = "admin";
    private String password = "admin";
    private String sqlClassName = "com.mysql.jdbc.Driver";
//    private String
    public DataBase(){
        try{
            Class.forName(sqlClassName);

        }catch(ClassNotFoundException e) {
            System.out.println(e.getCause());
        }
    }
}

