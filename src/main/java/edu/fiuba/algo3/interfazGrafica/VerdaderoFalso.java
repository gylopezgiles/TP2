package edu.fiuba.algo3.interfazGrafica;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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

public class VerdaderoFalso extends Application {

    private void centrar_alinear_fila(HBox fila, int alineamiento ){
        fila.setAlignment(Pos.CENTER);
        fila.setSpacing(alineamiento);
    }


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Verdadero o Falso");

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
        ToggleGroup tg = new ToggleGroup();

        RadioButton opcionVerdadera = new RadioButton("True");
        RadioButton opcionFalsa = new RadioButton("False");

        opcionVerdadera.setToggleGroup(tg);
        opcionFalsa.setToggleGroup(tg);

        contenedorOpciones.getChildren().add(opcionVerdadera);
        contenedorOpciones.getChildren().add(opcionFalsa);
        contenedorOpciones.setAlignment(Pos.CENTER);

        Button enviar = new Button();
        enviar.setText("Enviar");

        VBox contenedorPrincipal = new VBox(pregunta, contenedorOpciones, enviar);
        contenedorPrincipal.setSpacing(60);
        contenedorPrincipal.setPadding(new Insets(40));
        contenedorPrincipal.setAlignment(Pos.CENTER);

    //Cuerpo del programa
        BorderPane cuerpo = new BorderPane();
        cuerpo.setTop(encabezado);
        cuerpo.setCenter(contenedorPrincipal);

        Scene scene = new Scene(cuerpo, 400, 400);
        stage.setScene(scene);
        stage.show();
    }
}
