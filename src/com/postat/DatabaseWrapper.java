package com.postat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseWrapper {
    private Database database;
    private ResultSet resultSet;
    public DatabaseWrapper(){
        database = new Database();
    }

    public void SignUp(String first_name, String last_name, String email, String password) {
        try{
            database.prepare("insert into user(f_name,l_name,email,password)values(?,?,?,?)");
            database.setString(1,first_name);
            database.setString(2,last_name);
            database.setString(3,email);
            database.setString(4,password);
            database.execute();
            database.close();
        }catch(SQLException e){
            System.out.println("SignUp failed, SQLException: "+e.getCause());
        }
    }

    public int login(String email, String password){
        try{
            database.prepare("select * from user where email=? AND password=?");
            database.setString(1,email);
            database.setString(2,password);
            resultSet = database.executeQuery();
            if(resultSet.next()){  // if user record found
                int id = resultSet.getInt(1);
                database.close();  //close database after getting value from resultset(important)
                return id; // return user id
            }
        }catch (SQLException e){
            System.out.println("Sql exception");
        }
        try{
            database.close();
        }catch(SQLException e){
            System.out.println("Sql exception: database didn't close properly!");
        }
        return 0;
    }

    public String[] getUserDataById(int id){
        String[] data = new String[4];
        try{
            database.prepare("select * from user where id=?");

            database.setInt(1,id);
            resultSet = database.executeQuery();
            resultSet.next();
            data[0] = ""+resultSet.getInt(1);
            data[1] = resultSet.getString(2);
            data[2] = resultSet.getString(3);
            data[3] = resultSet.getString(4);
            database.close();
            System.out.println("database closed in getUserById()");
        }catch (SQLException sql){
            System.out.println("SQLException happed while getting user data: "+sql.getCause());
        }
        return data;
    }

    public int getNumOfPosts(int id) {
        int posts_num = 0;
        try {
            database.prepare("select COUNT(*) from post where id=?");
            database.setInt(1, id);
            resultSet = database.executeQuery();
            if (resultSet.next()) {
                posts_num = resultSet.getInt(1);
            }
            database.close();
        } catch (Exception e) {
            System.out.println(e.getCause());
        }

        return posts_num;
    }

    public ArrayList<Post> getPosts(){
        ArrayList<Post> posts = new ArrayList<>();
        try{
            database.prepare("select f_name, l_name, content from user us, post pst where us.id=pst.id");
            resultSet = database.executeQuery();
            while (resultSet.next()){
                posts.add(new Post(resultSet.getString(1),
                        resultSet.getString(2),resultSet.getString(3)));
            }
            database.close();
        }catch(Exception e){
            System.out.println(e.getCause());
        }
        return posts;
    }

    public void addPost(int id,String content){
        try{
            database.prepare("insert into post(id,content) values(?,?)");
            database.setInt(1,id);
            database.setString(2,content);
            database.execute();
            System.out.println("Execute addPost successfully");
            database.close();
            System.out.println("database closed after adding post");
        }catch (Exception e){
            System.out.println(e.getCause());
        }
    }
}