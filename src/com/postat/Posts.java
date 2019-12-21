package com.postat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Posts {
    ArrayList<Post> postsList;
    public static int post_id = 0;
    Connection con;
    Statement statement;
    public Posts() {
        postsList = new ArrayList<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/test?autoReconnect=true&useSSL=false","fci","fci");
            statement = con.createStatement();
            String getPostsQuery = "select first_name,last_name,content from account ac,post pst where ac.user_id=pst.user_id";
            ResultSet stackSet = statement.executeQuery(getPostsQuery);
            while (stackSet.next()){
                postsList.add(new Post(stackSet.getString(1),
                        stackSet.getString(2),stackSet.getString(3)));
            }
            con.close();
        }catch (Exception e){
            System.out.println(e.getCause());
        }
    }
    public Post getCurrentPost(){
        return postsList.get(post_id);
    }
    public Post getNextPost(){
        if(postsList.size()-1>post_id){
            post_id++;
            return postsList.get(post_id);
        }else{
            return null;
        }
    }
    public Post getPreviousPost() {
        if(post_id>0){
            post_id--;
            return postsList.get(post_id);
        }else
            return null;
    }
}
