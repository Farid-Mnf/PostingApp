package com.postat;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

public class Account extends SignUp {
    public static Home home;
    public static int id;
    private String[] data;
    public static Label posts;
    public static TextField numOfPosts;
    Account(){
        deleteButton = submit;
        deleteButton.setText("Delete Account");
        deleteButton.setOnAction(event -> {
            DatabaseWrapper databaseWrapper = new DatabaseWrapper();
            databaseWrapper.deleteUserById(Main.id);
            getScene().getWindow().hide();
            Login login = new Login();
            Scene scene = new Scene(login);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("PostApp");
            stage.show();
            home.getScene().getWindow().hide();
        });
        posts = new Label("Posts number:");
        numOfPosts = new TextField("4");
        DatabaseWrapper databaseWrapper = new DatabaseWrapper();
        data = Home.userData;
        id = Integer.parseInt(data[0]);
        for(String str : data){
            System.out.println("Data: "+str);
        }
        numOfPosts.setText(""+databaseWrapper.getNumOfPosts(id));
        firstNameField.setText(data[1]);
        lastNameField.setText(data[2]);
        emailField.setText(data[3]);
        posts.setFont(Font.font(20));
        posts.setTextFill(Color.WHITE);
        posts.setFont(Font.font("Verdana", FontPosture.REGULAR,12));

        passwordLabel.setVisible(false);
        passwordField.setVisible(false);

        add(posts,0,4);
        add(numOfPosts,1,4);

        setMargin(firstNameLabel,new Insets(80,0,0,20));
        setMargin(firstNameField,new Insets(80,0,0,0));
        setMargin(lastNameLabel,new Insets(0,0,0,20));
        setMargin(emailLabel,new Insets(0,0,0,20));
        setMargin(passwordLabel,new Insets(0,0,0,20));
    }
}
