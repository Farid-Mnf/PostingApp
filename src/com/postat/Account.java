package com.postat;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class Account extends SignUp {
    public static Label posts;
    public static TextField numOfPosts;
    Account(){
        posts = new Label("Posts number:");
        numOfPosts = new TextField("4");
        posts.setFont(Font.font(20));
        posts.setTextFill(Color.WHITE);
        posts.setFont(Font.font("Verdana", FontPosture.REGULAR,12));
        submit.setVisible(false);
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
