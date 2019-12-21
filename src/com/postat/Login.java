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

import java.sql.*;

public class Login extends GridPane {
    Connection con;
    Statement statement;
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
            String emailValue = emailField.getText();
            String passwordValue = passwordField.getText();
            String sqlQuery = "select * from account where email='"+emailValue+"' AND password='"+passwordValue+"'";
            try{
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/test?autoReconnect=true&useSSL=false","fci","fci");
                Statement statement = con.createStatement();

                ResultSet resultSet = statement.executeQuery(sqlQuery);
                if(resultSet.next()){
                    System.out.println("logged in");
                    String userId = resultSet.getString(1);
                    String first_name = resultSet.getString(2);
                    String last_name = resultSet.getString(3);
                    String userEmail = resultSet.getString(4);
                    Stage stage = new Stage();
                    Home home = new Home();
                    home.id = userId;
                    home.first_name = first_name;
                    home.last_name = last_name;
                    home.userEmail = userEmail;


//                    String fname,lname,postContent;
//                    if(resultSet.next()) {
//                        fname = resultSet.getString(1);
//                        lname = resultSet.getString(2);
//                        postContent = resultSet.getString(3);
//                        home.postText.setText(postContent);
//                        home.userName.setText(fname+" "+lname);
//                    }
                    Scene scene = new Scene(home,800,500);
                    stage.setScene(scene);
                    stage.show();
                    stage.setTitle("Postaty");
                    getScene().getWindow().hide();
                }
                else{
                    errorLabel.setText("Wrong email or password!");
                    errorLabel.setTextFill(Color.RED);
                    errorLabel.setFont(Font.font(20));
                }
                closeConnection();
            }
            catch(ClassNotFoundException e){
                System.out.println("class not found");
            }
            catch(SQLException e){
                System.out.println("SQL exception "+e.getMessage()+" | "+e.getStackTrace()+
                        " | "+e.toString()+" | "+e.getCause());
            }catch (Exception e){
                System.out.println(e.getCause());
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
}