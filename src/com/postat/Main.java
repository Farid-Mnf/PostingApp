package com.postat;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Scene loginScene;
    public void start(Stage primaryStage){
        Login loginScreen = new Login();
        loginScene = new Scene(loginScreen,380,300);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Postaty");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}