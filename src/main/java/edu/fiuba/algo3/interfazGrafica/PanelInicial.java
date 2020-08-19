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
        Box layoutInicial = Box.createVerticalBox();
        agregarImagen(layoutInicial);
        agregarBienvenida(layoutInicial);
        agregarIndicaciones(layoutInicial);
        agregarJugadoresInput(layoutInicial);
        agregarBotonJugar(layoutInicial);
        add(layoutInicial);
    }

    private void agregarImagen(Box layoutInicial){
        try {
            FileInputStream entrada = new FileInputStream("doc/imagenes/banner_kahoot.jpg");
            Image image = ImageIO.read(entrada);
            ImageIcon imagenIcon = new ImageIcon(image);
            JLabel imagen = new JLabel(imagenIcon);
            layoutInicial.add(imagen);
        } catch (FileNotFoundException ignoreError){
        } catch (IOException ignoreError){ }
    }

    private void agregarBienvenida(Box layoutInicial){

        String textoBienvenida = "Bienvenido al juego del grupo N1 turno noche!!";

        JLabel bienvenida = new JLabel(textoBienvenida);

        layoutInicial.add(bienvenida);
    }

    private void agregarIndicaciones(Box layoutInicial){
        String textoIndicaciones = "Para empezar a jugar ingrese los nombres de los jugadores y luego presionar el boton 'Jugar'";

        JLabel indicaciones = new JLabel(textoIndicaciones);

       layoutInicial.add(indicaciones);
    }

    private void agregarJugadoresInput(Box layoutInicial){
        JLabel jugador1 = new JLabel("Jugador 1: ");
        nombreJugador1 = new JTextField();
        nombreJugador1.setColumns(5);
        JLabel jugador2 = new JLabel("Jugador 2: ");
        nombreJugador2 = new JTextField();
        nombreJugador2.setColumns(5);

        Box layoutJugador1 = Box.createHorizontalBox();
        Box layoutJugador2 = Box.createHorizontalBox();

        layoutJugador1.add(jugador1);
        layoutJugador1.add(nombreJugador1);
        layoutJugador2.add(jugador2);
        layoutJugador2.add(nombreJugador2);

        layoutInicial.add(layoutJugador1);
        layoutInicial.add(layoutJugador2);
    }

    public List<String> obtenerNombresJugadores(){
        return Arrays.asList(nombreJugador1.getText(), nombreJugador2.getText());
    }

    private void agregarBotonJugar(Box layoutInicial){
        jugar = new JButton("Jugar");
        layoutInicial.add(jugar);
    }

    public void conectaControlador(ControladorPanel c){
        jugar.addActionListener(c);
        jugar.setActionCommand("JUGAR");
    }

}
