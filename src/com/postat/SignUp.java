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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class SignUp extends GridPane {
    Connection con;
    Statement statement;
    private String firstName;
    private String lastName;
    private String email;

    TextField firstNameField,lastNameField,emailField;
    PasswordField passwordField;
    Label firstNameLabel,lastNameLabel,emailLabel,passwordLabel;

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
        submit.setOnMouseMoved(event -> {
            submit.setCursor(Cursor.HAND);
        });
        add(firstNameLabel,0,0);
        add(firstNameField,1,0);
        add(lastNameLabel,0,1);
        add(lastNameField,1,1);
        add(emailLabel,0,2);
        add(emailField,1,2);
        add(passwordLabel,0,3);
        add(passwordField,1,3);
        add(submit,1,4);
        setHgap(20);
        setVgap(20);
        setMargin(submit,new Insets(20,0,0,0));
        setMargin(firstNameLabel,new Insets(40,0,0,0));
        setMargin(firstNameField,new Insets(40,0,0,0));
        setStyle("-fx-background-color:#34495e");
        setPadding(new Insets(10));

        //Submit action
        submit.setOnAction(event -> {
            // add data to the database
            String first_name = getFirstNameField().getText().trim();
            String last_name = getLastNameField().getText().trim();
            String email = getEmailField().getText().trim();
            String password = getPasswordField().getText().trim();
            String sqlQuery = "insert into account(first_name,last_name,email,password) values ('"+first_name+"','"+last_name+"','"+email+"','"+password+"')";
            try{
                setDriver();
                setConnection();
                setStatement();
                insertToAccount(sqlQuery);
                closeConnection();
            }catch(ClassNotFoundException e){
                System.out.println("class not found");
            }
            catch(SQLException e){
                System.out.println("SQL exception "+e.getMessage()+" | "+e.getStackTrace()+
                        " | "+e.toString()+" | "+e.getCause());
            }
            getScene().getWindow().hide();
        });
    }
    public static void setDriver() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
    }

    public void setConnection() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost/test?autoReconnect=true&useSSL=false","fci","fci");

    }

    public void setStatement() throws SQLException{
        statement = con.createStatement();
    }

    public void insertToAccount(String newAccount) throws SQLException{
        statement.executeUpdate(newAccount);
    }
    public void closeConnection() throws SQLException {
        con.close();
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TextField getFirstNameField() {
        return firstNameField;
    }

    public void setFirstNameField(TextField firstNameField) {
        this.firstNameField = firstNameField;
    }

    public TextField getLastNameField() {
        return lastNameField;
    }

    public void setLastNameField(TextField lastNameField) {
        this.lastNameField = lastNameField;
    }

    public TextField getEmailField() {
        return emailField;
    }

    public void setEmailField(TextField emailField) {
        this.emailField = emailField;
    }

    public Label getFirstNameLabel() {
        return firstNameLabel;
    }

    public void setFirstNameLabel(Label firstNameLabel) {
        this.firstNameLabel = firstNameLabel;
    }

    public Label getLastNameLabel() {
        return lastNameLabel;
    }

    public void setLastNameLabel(Label lastNameLabel) {
        this.lastNameLabel = lastNameLabel;
    }

    public Label getEmailLabel() {
        return emailLabel;
    }

    public void setEmailLabel(Label emailLabel) {
        this.emailLabel = emailLabel;
    }
}
