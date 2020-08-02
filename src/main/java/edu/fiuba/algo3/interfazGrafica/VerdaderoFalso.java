package edu.fiuba.algo3.interfazGrafica;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VerdaderoFalso extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Verdadero o Falso");

        Label pregunta = new Label();
        pregunta.setText("¿2 + 2 = 4?");

        Button opcionVerdadera = new Button();
        opcionVerdadera.setText("True");

        Button opcionFalsa = new Button();
        opcionFalsa.setText("False");

        HBox contenedorHorizontal = new HBox(opcionVerdadera, opcionFalsa);
        contenedorHorizontal.setSpacing(10);

        VBox contenedorPrincipal = new VBox(pregunta ,contenedorHorizontal);
        contenedorPrincipal.setSpacing(10);
        contenedorPrincipal.setPadding(new Insets(20));

        Scene scene = new Scene(contenedorPrincipal, 300, 300);
        stage.setScene(scene);

        stage.show();
    }
}
