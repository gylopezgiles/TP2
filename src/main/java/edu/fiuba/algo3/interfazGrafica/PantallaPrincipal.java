package edu.fiuba.algo3.interfazGrafica;

import edu.fiuba.algo3.controlador.ControladorPanel;
import edu.fiuba.algo3.interfazGrafica.pregunta.PanelPregunta;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class PantallaPrincipal extends JFrame {

    final static Logger log = Logger.getLogger(PantallaPrincipal.class);

    private PanelInicial panelInicial;
    private PanelPregunta panelPregunta;
    private PanelFinJuego panelFinJuego;

    public PantallaPrincipal()  {
        setImagenMiniatura();
        setTitle("Kahoot!");
        panelInicial = new PanelInicial();
        panelPregunta = new PanelPregunta();
        getContentPane().add(panelInicial);
    }

    public void conectaControladorPrincipal(ControladorPanel c){
        panelInicial.conectaControlador(c);
        panelPregunta.conectaControlador(c);
    }

    public List<String> obtenerJugadores(){
        return panelInicial.obtenerNombresJugadores();
    }

    public void iniciarPartida() {
        panelInicial.detenerMusica();
        panelInicial.setVisible(Boolean.FALSE);
        add(panelPregunta);
    }

    public void establecerTurno(Preguntable pregunta, Jugador jugador) {
        panelPregunta.detenerMusica();
        panelPregunta.removeAll();
        panelPregunta.establecerTurno(pregunta, jugador);
        panelPregunta.revalidate();
        panelPregunta.repaint();
    }

    public Object obtenerOpcionesSeleccionadas(){
        return panelPregunta.obtenerOpcionesSeleccionadas();
    }

    public void finalizarPartida(List<Jugador> jugadores) {
        panelPregunta.detenerMusica();
        panelPregunta.terminarMusica();
        panelPregunta.setVisible(Boolean.FALSE);
        panelPregunta.removeAll();
        panelFinJuego = new PanelFinJuego(jugadores);
        add(panelFinJuego);
    }

    public Boolean obtenerExclusividad() {
        return panelPregunta.obtenerExclusividad();
    }
    public Multiplicador obtenerMultiplicador(){ return  panelPregunta.obtenerMultiplicador();}

    public void mostrarMensajeNombresJugadoresInvalidos(String nombresInvalidosExcepcion) {
        panelInicial.mostrarMensajeEnPantalla(nombresInvalidosExcepcion);
        panelInicial.setVisible(Boolean.FALSE);
        panelInicial.repaint();
        panelInicial.setVisible(Boolean.TRUE);
    }

    public void mostrarMensajePreguntasNoCargadas(String noHayPreguntasCargadas) {
        panelInicial.removeAll();
        panelInicial.mostrarMensajeEnPantalla(noHayPreguntasCargadas);
        panelInicial.setVisible(Boolean.FALSE);
        panelInicial.repaint();
        panelInicial.setVisible(Boolean.TRUE);
    }
  
    public void establecerVisualTemporizador(int contador){
        panelPregunta.establecerVisualTemporizador(contador);
    }

    private void setImagenMiniatura(){
        try {
            FileInputStream entrada = new FileInputStream("doc/imagenes/icono_kahoot.jpg");
            Image imagen = ImageIO.read(entrada);
            setIconImage(imagen);
        } catch (FileNotFoundException ignoreError){
            log.info(String.format("No se pudo cargar la miniatura"));
        } catch (IOException ignoreError){
            log.info(String.format("No se pudo cargar la miniatura"));
        }
    }
}
