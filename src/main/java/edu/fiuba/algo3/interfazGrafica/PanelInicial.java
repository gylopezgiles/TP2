package edu.fiuba.algo3.interfazGrafica;


import edu.fiuba.algo3.controlador.ControladorPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PanelInicial extends JPanel {

    private JTextField nombreJugador1;
    private JTextField nombreJugador2;
    private JButton jugar;

    public PanelInicial(){
        agregarImagen();
        agregarBienvenida();
        agregarIndicaciones();
        agregarJugadoresInput();
        agregarBotonJugar();
    }

    private void agregarImagen(){
        try {
            FileInputStream entrada = new FileInputStream("doc/imagenes/logo_kahoot.jpg");
            Image image = ImageIO.read(entrada);
            ImageIcon imagenIcon = new ImageIcon(image);
            JLabel imagen = new JLabel(imagenIcon);
            add(imagen);
        } catch (FileNotFoundException ignoreError){
        } catch (IOException ignoreError){ }
    }

    private void agregarBienvenida(){

        String textoBienvenida = "Bienvenido al juego del grupo N1 turno noche!!";

        JLabel bienvenida = new JLabel(textoBienvenida);

        add(bienvenida);
    }

    private void agregarIndicaciones(){
        String textoIndicaciones = "Para empezar a jugar ingrese los nombres de los jugadores y luego presionar el boton 'Jugar'";

        JLabel indicaciones = new JLabel(textoIndicaciones);

        add(indicaciones);
    }

    private void agregarJugadoresInput(){
        JLabel jugador1 = new JLabel("Nombre jugador 1: ");
        nombreJugador1 = new JTextField();
        nombreJugador1.setColumns(10);
        JLabel jugador2 = new JLabel("Nombre jugador 2: ");
        nombreJugador2 = new JTextField();
        nombreJugador2.setColumns(10);

        add(jugador1);
        add(nombreJugador1);
        add(jugador2);
        add(nombreJugador2);
    }

    public List<String> obtenerNombresJugadores(){
        return Arrays.asList(nombreJugador1.getText(), nombreJugador2.getText());
    }

    private void agregarBotonJugar(){
        jugar = new JButton("Jugar");
        add(jugar);
    }

    public void conectaControlador(ControladorPanel c){
        jugar.addActionListener(c);
        jugar.setActionCommand("JUGAR");
    }

}
