package com.postat;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    public static int id;
    public static Scene loginScene;
    public void start(Stage primaryStage){
        Login loginScreen = new Login();
        loginScene = new Scene(loginScreen,380,300);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("PostingApp");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        System.out.println("hello java");
        DatabaseWrapper databaseWrapper = new DatabaseWrapper();
        ArrayList<Post> list = databaseWrapper.getPosts();
        for(Post post : list){
            System.out.println(post.firstName+": "+post.content);
        }
        System.out.println("finished main method");
    }
}