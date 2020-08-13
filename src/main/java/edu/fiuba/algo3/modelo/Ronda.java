package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.RondaSinPreguntaExcepcion;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.EstadoRonda;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ronda {

    private List<Jugador> jugadores;
    private Preguntable pregunta;
    private Map<Jugador, Integer> puntajesRonda;
    private EstadoRonda estadoRonda;

    public Ronda(List<Jugador> jugadores, Preguntable pregunta){
        this.jugadores = jugadores;
        this.pregunta = pregunta;
        this.puntajesRonda = new HashMap<>();
        this.estadoRonda = EstadoRonda.INICIA;
    }

    public List<Jugador> obtenerJugadores(){
        return jugadores;
    }

    public Preguntable obtenerPregunta(){
        return pregunta;
    }

    public void responder(Jugador jugador, List<Opcion> opciones) throws RondaSinPreguntaExcepcion {
        responder(jugador, opciones, Multiplicador.PorDefecto);
    }

    public void responder(Jugador jugador, List<Opcion> opciones, MultiplicableStrategy multiplicador) throws RondaSinPreguntaExcepcion {
        if(pregunta == null){
            throw new RondaSinPreguntaExcepcion("No se puede responder sin una pregunta");
        }
        estadoRonda = EstadoRonda.RESPONDIENDO;
        puntajesRonda.put(jugador, pregunta.establecerPuntuacion(opciones, multiplicador));
        actualizarEstadoRonda();
    }

    private void actualizarEstadoRonda() {
        if(estadoRonda == EstadoRonda.INICIA){
            estadoRonda = EstadoRonda.FIN_RONDA;
        }
    }
}
