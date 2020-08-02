package edu.fiuba.algo3.interfazGrafica;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Kahoot");

        StackPane layout = new StackPane();

        Label label = new Label();
        label.setText("Texto de etiqueta");
        layout.getChildren().add(label);

        Button boton = new Button();
        boton.setText("Texto de boton");
        layout.getChildren().add(boton);


        Scene scene = new Scene(layout);
        stage.setScene(scene);

        stage.show();
    }
}