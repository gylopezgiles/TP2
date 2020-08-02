package edu.fiuba.algo3.interfazGrafica;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;



public class MultipleChoice extends Application {

    private void centrar_alinear_fila(HBox fila, int alineamiento ){
        fila.setAlignment(Pos.CENTER);
        fila.setSpacing(alineamiento);
    }

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Multiple Choice");

        //Encabezado
        FileInputStream entrada = new FileInputStream("doc/imagenes/logo_kahoot.jpg");
        Image imagen = new Image(entrada);
        ImageView vistaKahoot = new ImageView(imagen);

        Text bienvenida = new Text("Bienvenizo al juego del grupo N1 turno noche");
        bienvenida.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        HBox encabezado = new HBox(vistaKahoot, bienvenida);
        centrar_alinear_fila(encabezado, 20);


        // Cuerpo
        Text pregunta = new Text("¿2 + 2 = 4?");
        pregunta.setFont(Font.font("Arial", FontWeight.NORMAL, 20));

        Button opcion1 = new Button();
        opcion1.setText("opcion 1");

        Button opcion2 = new Button();
        opcion2.setText("opcion 2");

        Button opcion3 = new Button();
        opcion3.setText("opcion 3");

        Button opcion4 = new Button();
        opcion4.setText("opcion 4");

        Button opcion5 = new Button();
        opcion5.setText("opcion 5");

        HBox fila1opciones = new HBox(opcion1, opcion2, opcion3);
        centrar_alinear_fila(fila1opciones, 3);

        HBox fila2opciones = new HBox(opcion4, opcion5);
        centrar_alinear_fila(fila2opciones, 3);

        VBox contenedorOpciones = new VBox(fila1opciones, fila2opciones);
        contenedorOpciones.setSpacing(5);


        VBox contenedorPrincipal = new VBox(pregunta, contenedorOpciones);
        contenedorPrincipal.setSpacing(60);
        contenedorPrincipal.setPadding(new Insets(40));
        contenedorPrincipal.setAlignment(Pos.CENTER);

        //Cuerpo del Programa
        BorderPane cuerpo = new BorderPane();
        cuerpo.setTop(encabezado);
        cuerpo.setCenter(contenedorPrincipal);

        Scene scene = new Scene(cuerpo, 400, 400);
        stage.setScene(scene);
        stage.show();
    }


}
