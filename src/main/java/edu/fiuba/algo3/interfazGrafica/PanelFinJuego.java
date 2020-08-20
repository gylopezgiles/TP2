package edu.fiuba.algo3.interfazGrafica;

import edu.fiuba.algo3.modelo.Jugador;
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
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class PanelFinJuego extends JPanel {

    final static Logger log = Logger.getLogger(PanelFinJuego.class);
    private Integer puestoPodio;

    public PanelFinJuego(List<Jugador> jugadores){
        puestoPodio = 0;
        empezarMusica();
        mostrarResultados(jugadores);
    }

    private void mostrarResultados(List<Jugador> jugadores) {
        if(hayEmpate(jugadores)){
            mostrarResultadosEmpate(jugadores);
        }
        else{
            mostrarResultadosConGanador(jugadores);
        }
    }

    private boolean hayEmpate(List<Jugador> jugadores) {
        List<Integer> puntajes = new LinkedList<>();
        jugadores.stream().forEach(jugador -> puntajes.add(jugador.obtenerPuntos()));
        return new HashSet<Integer>(puntajes).size() <= 1;
    }

    private void mostrarResultadosConGanador(List<Jugador> jugadores) {
        agregarImagen("doc/imagenes/banner_kahoot.jpg");
        List<Jugador> jugadoresEnOrden = jugadores.stream()
                                        .sorted(Comparator.comparingInt(Jugador::obtenerPuntos).reversed())
                                        .collect(Collectors.toList());
        Box podio = Box.createVerticalBox();

        jugadoresEnOrden.stream().forEach(jugador -> podio.add(textoPodio(jugador)));
        add(podio);
    }

    private Component textoPodio(Jugador jugador) {
        puestoPodio ++;
        Box puesto = Box.createHorizontalBox();
        String pathMedalla = String.format("doc/imagenes/%s_kahoot.jpg", puestoPodio);
        agregarImagenEn(puesto, pathMedalla);
        String jugadorTexto = String.format("El Jugador %s obtuvo el lugar %s con: %s puntos.", jugador.obtenerNombre(), puestoPodio, jugador.obtenerPuntos());
        JLabel jugadorLabel = new JLabel(jugadorTexto);
        puesto.add(jugadorLabel);
        return puesto;
    }

    private void mostrarResultadosEmpate(List<Jugador> jugadores) {
        agregarImagen("doc/imagenes/banner_kahoot.jpg");
        String mensaje = String.format("Hubo un empate de %s puntos, buena suerte la proxima :p", jugadores.get(1).obtenerPuntos());
        JLabel mensajeFinal = new JLabel(mensaje);
        add(mensajeFinal);
    }

    private void empezarMusica(){
        try {
            Clip musicaFinal = AudioSystem.getClip();
            musicaFinal.open(AudioSystem.getAudioInputStream(new File("doc/musica/final_kahoot.wav")));
            musicaFinal.start();
        } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
            log.info(String.format("Error al cargar musica del final"));
        } catch (IOException ioException) {
            log.info(String.format("Error al cargar musica del final"));
        } catch (LineUnavailableException lineUnavailableException) {
            log.info(String.format("Error al cargar musica del final"));
        }
    }

    private void agregarImagen(String path){
        try {
            FileInputStream entrada = new FileInputStream(path);
            Image image = ImageIO.read(entrada);
            ImageIcon imagenIcon = new ImageIcon(image);
            JLabel imagen = new JLabel(imagenIcon);
            add(imagen);
        } catch (FileNotFoundException ignoreError){
        } catch (IOException ignoreError){ }
    }

    private void agregarImagenEn(Box box, String path){
        try {
            FileInputStream entrada = new FileInputStream(path);
            Image image = ImageIO.read(entrada);
            ImageIcon imagenIcon = new ImageIcon(image);
            JLabel imagen = new JLabel(imagenIcon);
            box.add(imagen);
        } catch (FileNotFoundException ignoreError){
        } catch (IOException ignoreError){ }
    }

}
