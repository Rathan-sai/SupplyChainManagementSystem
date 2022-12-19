package com.example.supplychainmanagementsystem;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplyChain extends Application {

    public static final int width = 700, height = 600, headerbar = 50;

    Pane bodyPane = new Pane();

    private GridPane headerbar(){
        TextField searchText = new TextField();
        Button searchbutton = new Button("search");

        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(bodyPane.getMinWidth(), headerbar-10);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-background-color:#C0C0C0");

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(searchbutton, 1, 0);
        gridPane.add(searchText, 0, 0);

        return gridPane;
    }

    public GridPane logincredintials(){
        Label emaillabel = new Label("email");
        Label passwordlabel = new Label("password");
        Label labelmessage = new Label("I am a message");

        TextField emailText = new TextField();
        PasswordField passwordText = new PasswordField();

        Button loginButton = new Button("login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String mail = emailText.getText();
                String password = passwordText.getText();
                labelmessage.setText(mail+ " $& " +password);
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(), bodyPane.getMinHeight());
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setStyle("-fx-background-color:#C0C0C0");

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(emaillabel, 0, 0);
        gridPane.add(emailText, 1, 0);
        gridPane.add(passwordlabel, 0, 1);
        gridPane.add(passwordText, 1, 1);
        gridPane.add(loginButton, 0,2);
        gridPane.add(labelmessage, 1, 2);

        return gridPane;
    }

    public Pane CreateContent(){
        Pane root = new Pane();

        root.setPrefSize(width, height+headerbar);

        bodyPane.setMinSize(width, height);
        bodyPane.setTranslateY(headerbar);

        bodyPane.getChildren().addAll(logincredintials());

        root.getChildren().addAll(headerbar(), bodyPane);
        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(SupplyChain.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(CreateContent());
        stage.setTitle("login credentials");
        stage.setScene(scene);
        stage.show();
    }

//    public static void main(String[] args) {
//        launch();
//    }
}