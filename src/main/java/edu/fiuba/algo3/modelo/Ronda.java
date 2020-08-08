package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.MultiplicadorExcepcion;
import edu.fiuba.algo3.modelo.excepciones.RondaSinPreguntaExcepcion;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;

import java.util.List;

public class Ronda {

    private List<Jugador> jugadores;
    private Preguntable pregunta;

    public Ronda(List<Jugador> jugadores, Preguntable pregunta){
        this.jugadores = jugadores;
        this.pregunta = pregunta;
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
        jugador.sumarPuntos(pregunta.establecerPuntuacion(opciones, multiplicador));
    }
}
