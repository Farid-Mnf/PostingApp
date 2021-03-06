package com.postat;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;


public class SignUp extends GridPane {
    TextField firstNameField,lastNameField,emailField;
    PasswordField passwordField;
    Label firstNameLabel,lastNameLabel,emailLabel,passwordLabel;
    Button deleteButton;
    Button submit;
    SignUp(){
        firstNameField = new TextField();
        lastNameField = new TextField();
        emailField = new TextField();
        passwordField = new PasswordField();
        firstNameLabel = new Label("First Name: ");
        lastNameLabel = new Label("Last Name: ");
        emailLabel = new Label("Email: ");
        passwordLabel = new Label("Password");
        submit = new Button("Submit");
        deleteButton = new Button("Delete Account");

        deleteButton.setVisible(false);  // make it visible at Account class

        firstNameField.setMinSize(300,40);
        lastNameField.setMinSize(300,40);
        emailField.setMinSize(300,40);
        firstNameField.setFont(Font.font(20));
        lastNameField.setFont(Font.font(20));
        emailField.setFont(Font.font(20));
        passwordField.setFont(Font.font(20));
        firstNameLabel.setTextFill(Color.WHITE);
        lastNameLabel.setTextFill(Color.WHITE);
        emailLabel.setTextFill(Color.WHITE);
        passwordLabel.setTextFill(Color.WHITE);

        firstNameLabel.setFont(Font.font("Verdana", FontPosture.REGULAR,12));
        lastNameLabel.setFont(Font.font("Verdana", FontPosture.REGULAR,12));
        emailLabel.setFont(Font.font("Verdana", FontPosture.REGULAR,12));
        passwordLabel.setFont(Font.font("Verdana", FontPosture.REGULAR,12));
        submit.setStyle("-fx-background-color:#9b59b6");
        submit.setTextFill(Color.WHITE);
        submit.setOnMousePressed(event -> submit.setStyle("-fx-background-color:#8e44ad"));
        submit.setOnMouseReleased(event -> submit.setStyle("-fx-background-color:#9b59b6"));
        submit.setOnMouseMoved(event -> submit.setCursor(Cursor.HAND));
        deleteButton.setStyle("-fx-background-color:#9b59b6");
        deleteButton.setTextFill(Color.WHITE);
        deleteButton.setOnMousePressed(event -> submit.setStyle("-fx-background-color:#8e44ad"));
        deleteButton.setOnMouseReleased(event -> submit.setStyle("-fx-background-color:#9b59b6"));
        deleteButton.setOnMouseMoved(event -> submit.setCursor(Cursor.HAND));

        add(firstNameLabel,0,0);
        add(firstNameField,1,0);
        add(lastNameLabel,0,1);
        add(lastNameField,1,1);
        add(emailLabel,0,2);
        add(emailField,1,2);
        add(passwordLabel,0,3);
        add(passwordField,1,3);
        add(submit,1,5);
        setHgap(20);
        setVgap(20);
        setMargin(submit,new Insets(20,0,0,0));
        setMargin(deleteButton,new Insets(20,0,0,0));
        setMargin(firstNameLabel,new Insets(40,0,0,0));
        setMargin(firstNameField,new Insets(40,0,0,0));
        setStyle("-fx-background-color:#34495e");
        setPadding(new Insets(10));

        //Submit new user record to database
        submit.setOnAction(event -> {
            // add data to the database
            String first_name = getFirstNameField().getText().trim();
            String last_name = getLastNameField().getText().trim();
            String email = getEmailField().getText().trim();
            String password = getPasswordField().getText().trim();

            DatabaseWrapper databaseWrapper = new DatabaseWrapper();
            databaseWrapper.SignUp(first_name,last_name,email,password);
            getScene().getWindow().hide();
        });
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public TextField getFirstNameField() {
        return firstNameField;
    }

    public TextField getLastNameField() {
        return lastNameField;
    }

    public TextField getEmailField() {
        return emailField;
    }
}
