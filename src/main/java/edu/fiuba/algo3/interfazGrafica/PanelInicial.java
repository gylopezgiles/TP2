package edu.fiuba.algo3.interfazGrafica;


import edu.fiuba.algo3.controlador.ControladorPanel;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PanelInicial extends JPanel {

    final static Logger log = Logger.getLogger(PanelInicial.class);

    private JTextField nombreJugador1;
    private JTextField nombreJugador2;
    private JButton jugar;
    private Clip musicaLobby;

    public PanelInicial(){
        empezarMusica();
        agregarImagen();
        agregarBienvenida();
        agregarIndicaciones();
        agregarJugadoresInput();
        agregarBotonJugar();
    }

    private void empezarMusica(){
        try {
            musicaLobby = AudioSystem.getClip();
            musicaLobby.open(AudioSystem.getAudioInputStream(new File("doc/musica/lobby_kahoot.wav")));
            musicaLobby.loop(10);
        } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
            log.info(String.format("Error al cargar musica del Lobby"));
        } catch (IOException ioException) {
            log.info(String.format("Error al cargar musica del Lobby"));
        } catch (LineUnavailableException lineUnavailableException) {
            log.info(String.format("Error al cargar musica del Lobby"));
        }
    }

    public void detenerMusica(){
        musicaLobby.close();
    }

    private void agregarImagen( ){
        try {
            FileInputStream entrada = new FileInputStream("doc/imagenes/banner_kahoot.jpg");
            Image image = ImageIO.read(entrada);
            ImageIcon imagenIcon = new ImageIcon(image);
            JLabel imagen = new JLabel(imagenIcon);
            add(imagen);
        } catch (FileNotFoundException ignoreError){
        } catch (IOException ignoreError){ }
    }

    private void agregarBienvenida( ){

        String textoBienvenida = "¡Bienvenido al juego del grupo N1 turno noche!";

        JLabel bienvenida = new JLabel(textoBienvenida);

        add(bienvenida);
    }

    private void agregarIndicaciones( ){
        String textoIndicaciones = "Para iniciar ingrese los nombres de los jugadores y presione el boton 'Jugar'";

        JLabel indicaciones = new JLabel(textoIndicaciones);

       add(indicaciones);
    }

    private void agregarJugadoresInput( ){
        JLabel jugador1 = new JLabel("Jugador 1: ");
        nombreJugador1 = new JTextField();
        nombreJugador1.setColumns(7);
        JLabel jugador2 = new JLabel("Jugador 2: ");
        nombreJugador2 = new JTextField();
        nombreJugador2.setColumns(7);

        Box layoutJugador1 = Box.createHorizontalBox();
        Box layoutJugador2 = Box.createHorizontalBox();

        layoutJugador1.add(jugador1);
        layoutJugador1.add(nombreJugador1);
        layoutJugador2.add(jugador2);
        layoutJugador2.add(nombreJugador2);

        add(layoutJugador1);
        add(layoutJugador2);
    }

    public List<String> obtenerNombresJugadores(){
        return Arrays.asList(nombreJugador1.getText(), nombreJugador2.getText());
    }

    private void agregarBotonJugar( ){
        jugar = new JButton("Jugar");
        add(jugar);
    }

    public void conectaControlador(ControladorPanel c){
        jugar.addActionListener(c);
        jugar.setActionCommand("JUGAR");
    }

    public void mostrarMensajeEnPantalla(String mensaje) {
        JLabel mensajeAMostrar = new JLabel(mensaje);
        mensajeAMostrar.setForeground(Color.red);
        add(mensajeAMostrar);
    }

}
