package com.postat;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

public class Login extends GridPane {
    public static int id;
    Login(){
        Label email = new Label("Email: ");
        TextField emailField = new TextField();
        Label password = new Label("Password: ");
        PasswordField passwordField = new PasswordField();
        HBox buttons = new HBox();
        Label errorLabel = new Label("");
        email.setTextFill(Color.WHITE);
        password.setTextFill(Color.WHITE);
        emailField.setFont(Font.font(20));
        passwordField.setFont(Font.font(20));
        email.setFont(Font.font("Verdana", FontPosture.REGULAR,12));
        password.setFont(Font.font("Verdana", FontPosture.REGULAR,12));
        setMargin(email,new Insets(40,0,0,0));
        setMargin(emailField,new Insets(40,0,0,0));
        Button login = new Button("Login");
        login.setStyle("-fx-background-color:#9b59b6");
        login.setTextFill(Color.WHITE);
        login.setOnMousePressed(event -> login.setStyle("-fx-background-color:#8e44ad"));
        login.setOnMouseReleased(event -> login.setStyle("-fx-background-color:#9b59b6"));
        login.setOnAction(event -> {
            System.out.println("button clicked...");
            String emailValue = emailField.getText().trim();
            String passwordValue = passwordField.getText().trim();
            DatabaseWrapper databaseWrapper = new DatabaseWrapper();
            id = databaseWrapper.login(emailValue,passwordValue);
            System.out.println("Login id: "+id);
            if( id != 0 ) {
                System.out.println("id checked...........");
                Main.id = id;
                Home home = new Home();
                Stage stage = new Stage();
                Scene scene = new Scene(home, 800, 500);
                stage.setScene(scene);
                stage.show();
                stage.setTitle("PostingApp");
                getScene().getWindow().hide();
            }else{
                errorLabel.setText("Wrong email or password!");
                errorLabel.setTextFill(Color.RED);
                errorLabel.setFont(Font.font(20));
            }
        });
        login.setOnMouseMoved(event -> {
            login.setCursor(Cursor.HAND);
        });
        setMargin(login,new Insets(20,0,0,0));
        setHgap(10);
        setVgap(10);
        setStyle("-fx-background-color:#34495e");
        setPadding(new Insets(10));
        Button signUp = new Button("Sign Up");
        buttons.getChildren().addAll(login,signUp);
        buttons.setSpacing(10);
        buttons.setPadding(new Insets(10));
        setMargin(buttons,new Insets(20,0,0,0));
        signUp.setStyle("-fx-background-color:#9b59b6");
        signUp.setTextFill(Color.WHITE);
        signUp.setOnMousePressed(event -> signUp.setStyle("-fx-background-color:#8e44ad"));
        signUp.setOnMouseReleased(event -> signUp.setStyle("-fx-background-color:#9b59b6"));
        signUp.setOnAction(event -> {
            Stage stage = new Stage();
            Scene scene = new Scene(new SignUp(),450,400);
            stage.setScene(scene);
            stage.setTitle("Register");
            stage.show();
        });
        signUp.setOnMouseMoved(event -> {
            signUp.setCursor(Cursor.HAND);
        });
        add(email,0,1);
        add(emailField,1,1);
        add(password,0,2);
        add(passwordField,1,2);
        add(buttons,1,3);
        add(errorLabel,1,4);
    }
}