package edu.fiuba.algo3.interfazGrafica;

import edu.fiuba.algo3.controlador.ControladorPanel;
import edu.fiuba.algo3.interfazGrafica.pregunta.PanelPregunta;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;

import javax.swing.*;
import java.util.List;

public class PantallaPrincipal extends JFrame {

    private PanelInicial panelInicial;
    private PanelPregunta panelPregunta;
    private PanelFinJuego panelFinJuego;

    public PantallaPrincipal(){

        setTitle("Kahoot");
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
        panelInicial.setVisible(Boolean.FALSE);
    }

    public void establecerTurno(Preguntable pregunta, Jugador jugador) {
        panelPregunta.establecerTurno(pregunta, jugador);
        add(panelPregunta);
    }

    public List<String> obtenerOpcionesSeleccionadas(){
        return panelPregunta.obtenerOpcionesSeleccionadas();
    }

    public void finalizarPartida(List<Jugador> jugadores) {
        panelPregunta.setVisible(Boolean.FALSE);
        panelFinJuego = new PanelFinJuego(jugadores);
        add(panelFinJuego);
    }
}