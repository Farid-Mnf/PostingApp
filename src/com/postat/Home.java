package com.postat;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Arrays;


public class Home extends BorderPane {
    public static int id;  // unified id across all stages to communicate
    public static String[] userData;
    TextArea postText;
    Button next,previous;
    HBox controlPane;
    public static Label userName;
    public static String first_name,last_name,email;


    Home(){
        id = Login.id;
        System.out.println("Home id: "+id);
        userData = new DatabaseWrapper().getUserDataById(id);
        first_name = userData[1];
        last_name = userData[2];
        email = userData[3];
        System.out.println("loaded home constructor");

        System.out.println("Posts initialized");
        setStyle("-fx-background-color:#34495e");
        Button addPost = new Button("Add Post");
        Button logOut = new Button("Log Out");
        addPost.setStyle("-fx-background-color:#9b59b6;-fx-text-fill:white");
        logOut.setStyle("-fx-background-color:#9b59b6;-fx-text-fill:white");
        addPost.setOnMouseMoved(event -> {
            addPost.setCursor(Cursor.HAND);
        });
        logOut.setOnMouseMoved(event -> {
            logOut.setCursor(Cursor.HAND);
        });
        logOut.setOnAction(event -> {
            Login login = new Login();
            Scene scene = new Scene(login);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Postaty");
            stage.show();
            getScene().getWindow().hide();
        });
        addPost.setOnMousePressed(event -> addPost.setStyle("-fx-background-color:#8e44ad;-fx-text-fill:white"));
        addPost.setOnMouseReleased(event -> addPost.setStyle("-fx-background-color:#9b59b6;-fx-text-fill:white"));
        logOut.setOnMousePressed(event -> logOut.setStyle("-fx-background-color:#8e44ad;-fx-text-fill:white"));
        logOut.setOnMouseReleased(event -> logOut.setStyle("-fx-background-color:#9b59b6;-fx-text-fill:white"));
        addPost.setOnAction(event -> {
            Stage stage = new Stage();
            NewPost newPost = new NewPost();
            newPost.userId = id;
            newPost.userName.setText(first_name+" "+last_name);
            Scene scene = new Scene(newPost,500,400);
            stage.setScene(scene);
            stage.setTitle(first_name+" is posting");
            stage.show();
        });
        HBox topHeader = new HBox();
        topHeader.getChildren().addAll(addPost,logOut);
        topHeader.setAlignment(Pos.CENTER);
        topHeader.setSpacing(10);
        topHeader.setPadding(new Insets(10));
        setTop(topHeader);

        final ListView sidePanel = new ListView(FXCollections.observableList(Arrays.asList("Posts","Users","Account","Groups","Pages","Messages",
                "Games","Gallery","Entertainment","Watch","Settings")));
        sidePanel.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(sidePanel.getSelectionModel().getSelectedItem().equals("Posts")){
                    Stage stage = new Stage();
                    Scene scene = new Scene(new Home(),800,500);
                    stage.setScene(scene);
                    stage.show();
                    stage.setTitle("Postaty");
                    getScene().getWindow().hide();
                }else if(sidePanel.getSelectionModel().getSelectedItem().equals("Users")){
                    System.out.println("Users selected");
                }else if(sidePanel.getSelectionModel().getSelectedItem().equals("Account")){
                    Account account = new Account();
                    account.id = id;
                    Scene scene = new Scene(account,550,400);
                    Stage stage = new Stage();
                    stage.setTitle(email);
                    stage.setScene(scene);
                    stage.show();
                }
            }
        });
        sidePanel.setMaxWidth(200);
        sidePanel.setFixedCellSize(40);
        sidePanel.setCellFactory(cell -> {
            return new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                        setText(item);
                        setFont(Font.font(20));
                    }
                }
            };
        });
        if(!sidePanel.getItems().isEmpty()){
            sidePanel.getSelectionModel().select(0);
        }

        setLeft(sidePanel);
        GridPane post = new GridPane();
        userName = new Label("UserName");

        Post currentPost = Posts.getCurrentPost();
        postText = new TextArea("");
        postText.setText(currentPost.content);
        userName.setText(currentPost.firstName+" "+currentPost.lastName);

        userName.setStyle("-fx-border-color:white;-fx-border-width: 0 0 1 0;-fx-font-size:20");
        userName.setPadding(new Insets(10));
        userName.setTextFill(Color.valueOf("#f39c12"));
        userName.setOnMouseMoved(event -> userName.setTextFill(Color.valueOf("#f1c40f")));
        userName.setOnMouseExited(event -> userName.setTextFill(Color.valueOf("#f39c12")));
        userName.setOnMouseMoved(event -> {
            userName.setTextFill(Color.valueOf("#f1c40f"));
            userName.setCursor(Cursor.HAND);
        });
        userName.setOnMouseExited(event -> userName.setTextFill(Color.valueOf("#f39c12")));


        postText.setWrapText(true);
        postText.setPrefRowCount(50);
        postText.setFont(Font.font(20));
        post.add(userName,0,0);
        post.add(postText,0,1);
        post.setVgap(20);
        setMargin(post,new Insets(50));
        setCenter(post);
        setMargin(userName,new Insets(10));
        setMargin(postText,new Insets(10));
        previous = new Button("< Previous");
        next = new Button("Next >");
        previous.setStyle("-fx-background-color:#9b59b6;-fx-text-fill:white");
        next.setStyle("-fx-background-color:#9b59b6;-fx-text-fill:white");
        previous.setOnMousePressed(event -> previous.setStyle("-fx-background-color:#8e44ad;-fx-text-fill:white"));
        previous.setOnMouseReleased(event -> previous.setStyle("-fx-background-color:#9b59b6;-fx-text-fill:white"));
        next.setOnMousePressed(event -> next.setStyle("-fx-background-color:#8e44ad;-fx-text-fill:white"));
        next.setOnMouseReleased(event -> next.setStyle("-fx-background-color:#9b59b6;-fx-text-fill:white"));
        previous.setOnMouseMoved(event -> {
            previous.setCursor(Cursor.HAND);
        });
        previous.setOnAction(event -> {
            Post previousPost = Posts.getPreviousPost();
            if(previousPost!=null){
                userName.setText(previousPost.firstName+" "+previousPost.lastName);
                postText.setText(previousPost.content);
            }
        });
        next.setOnMouseMoved(event -> {
            next.setCursor(Cursor.HAND);
        });
        next.setOnAction(event -> {
            Post nextPost = Posts.getNextPost();
            if(nextPost!=null){
                userName.setText(nextPost.firstName+" "+nextPost.lastName);
                postText.setText(nextPost.content);
            }
        });
        next.setMinWidth(90);
        controlPane = new HBox();
        controlPane.setSpacing(10);
        controlPane.getChildren().addAll(previous,next);
        controlPane.setPadding(new Insets(10));
        controlPane.setAlignment(Pos.CENTER);
        setBottom(controlPane);
    }
}