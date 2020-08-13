package edu.fiuba.algo3.interfazGrafica;

import edu.fiuba.algo3.modelo.Jugador;

import javax.swing.*;
import java.util.List;

public class PanelFinJuego extends JPanel {

    public PanelFinJuego(List<Jugador> jugadores){
        jugadores.stream().forEach(jugador -> cargarPuntajeJugador(jugador));
    }

    private void cargarPuntajeJugador(Jugador jugador) {
        String jugadorTexto = String.format("Jugador %s puntos obtenidos %s", jugador.obtenerNombre(), jugador.obtenerPuntos());
        JLabel jugadorLabel = new JLabel(jugadorTexto);
        add(jugadorLabel);
    }
}
