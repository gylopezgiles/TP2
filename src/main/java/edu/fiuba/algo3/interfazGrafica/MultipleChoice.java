package edu.fiuba.algo3.interfazGrafica;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
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


        TilePane contenedorOpciones = new TilePane();

        RadioButton opcion1 = new RadioButton();
        opcion1.setText("opcion 1");

        RadioButton opcion2 = new RadioButton();
        opcion2.setText("opcion 2");

        RadioButton opcion3 = new RadioButton();
        opcion3.setText("opcion 3");

        RadioButton opcion4 = new RadioButton();
        opcion4.setText("opcion 4");

        RadioButton opcion5 = new RadioButton();
        opcion5.setText("opcion 5");


        contenedorOpciones.getChildren().add(opcion1);
        contenedorOpciones.getChildren().add(opcion2);
        contenedorOpciones.getChildren().add(opcion3);
        contenedorOpciones.getChildren().add(opcion4);
        contenedorOpciones.getChildren().add(opcion5);

        contenedorOpciones.setAlignment(Pos.CENTER);

        Button enviar = new Button();
        enviar.setText("Enviar");


        VBox contenedorPrincipal = new VBox(pregunta, contenedorOpciones, enviar);
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
