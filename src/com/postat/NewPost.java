package com.postat;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class NewPost extends GridPane {
    public static String userId;
    Label userName;
    private TextArea content;
    private Button post;
    Connection con;
    Statement statement;
    NewPost(){
        userName = new Label("UserName");
        content = new TextArea();
        post = new Button("Post");

        post.setOnAction(event -> {
            // store post in database then close
            String postContent = content.getText();
            String sqlQuery = "insert into post" +
                    " values ('"+postContent+"','"+userId+"')";
            try{
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/test?autoReconnect=true&useSSL=false","fci","fci");
                Statement statement = con.createStatement();
                statement.executeUpdate(sqlQuery);
                con.close();
                System.out.println("post added");

                getScene().getWindow().hide();
            }
            catch(ClassNotFoundException e){
                System.out.println("class not found");
            }
            catch(SQLException e){
                System.out.println("SQL exception "+e.getMessage()+" | "+e.getStackTrace()+
                        " | "+e.toString()+" | "+e.getCause());
            }
            getScene().getWindow().hide();
        });

        add(userName,0,0);
        add(content,0,1);
        add(post,0,2);

        userName.setTextFill(Color.WHITE);
        post.setTextFill(Color.WHITE);
        content.setFont(Font.font(20));
        post.setFont(Font.font(20));
        post.setStyle("-fx-background-color:#9b59b6");
        userName.setStyle("-fx-border-color:white;-fx-border-width: 0 0 1 0;-fx-font-size:20");
        userName.setPadding(new Insets(10));
        userName.setTextFill(Color.valueOf("#f39c12"));
        userName.setOnMouseMoved(event -> {
            userName.setTextFill(Color.valueOf("#f1c40f"));
            userName.setCursor(Cursor.HAND);
        });
        post.setOnMouseMoved(event -> {
            post.setCursor(Cursor.HAND);
        });
        userName.setOnMouseExited(event -> userName.setTextFill(Color.valueOf("#f39c12")));

        setHgap(10);
        setVgap(10);
        setStyle("-fx-background-color:#34495e");
        setPadding(new Insets(10));

        post.setPrefSize(100,50);
        post.setOnMousePressed(event -> post.setStyle("-fx-background-color:#8e44ad"));
        post.setOnMouseReleased(event -> post.setStyle("-fx-background-color:#9b59b6"));
        userName.setFont(Font.font("Verdana", FontPosture.REGULAR,20));
        content.setFont(Font.font("Verdana", FontPosture.REGULAR,20));
        post.setFont(Font.font("Verdana", FontPosture.REGULAR,20));

    }


}
