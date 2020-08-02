package edu.fiuba.algo3.interfazGrafica;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MultipleChoice extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Multiple Choice");

        Label pregunta = new Label();
        pregunta.setText("Elija una opcion entre 1 a 5");

        Button opcion1 = new Button();
        opcion1.setText("Opcion 1");

        Button opcion2 = new Button();
        opcion2.setText("Opcion 2");

        Button opcion3 = new Button();
        opcion3.setText("Opcion 3");

        Button opcion4 = new Button();
        opcion4.setText("Opcion 4");

        Button opcion5 = new Button();
        opcion5.setText("Opcion 5");

        HBox fila1 = new HBox(opcion1, opcion2, opcion3);
        fila1.setSpacing(10);

        HBox fila2 = new HBox(opcion4, opcion5);
        fila2.setSpacing(10);

        VBox contenedorPrincipal = new VBox(pregunta ,fila1, fila2);
        contenedorPrincipal.setSpacing(10);
        contenedorPrincipal.setPadding(new Insets(20));

        Scene scene = new Scene(contenedorPrincipal, 300, 300);
        stage.setScene(scene);

        stage.show();
    }


}
