package edu.fiuba.algo3.modelo.interfazGrafica;

import javafx.application.Application;
import javafx.scene.Scene;
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
        Scene scene = new Scene(layout);
        stage.setScene(scene);

        stage.show();
    }
}
