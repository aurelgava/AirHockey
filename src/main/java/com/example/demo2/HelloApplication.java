package com.example.demo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Stage stage;
    public static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.show();
        HelloApplication.stage = stage;
        HelloApplication.scene = scene;
        scene.setCursor(Cursor.NONE);
        //stage.setFullScreen(true);
        ServerThread st = new ServerThread();
        st.start();
        //HelloController.konektujSe();
        st.controller = fxmlLoader.getController();

    }

    public static void main(String[] args) {
        launch();
    }
}