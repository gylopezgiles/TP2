package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.RondaSinPreguntaExcepcion;
import edu.fiuba.algo3.modelo.exclusividad.Exclusividad;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;

import java.util.Arrays;
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
        Exclusividad exclusividad = new Exclusividad();
        jugador.sumarPuntos(pregunta.establecerPuntuacion(opciones, multiplicador, exclusividad));
    }

    public void responderConExclusividad(List<Opcion> opciones1, List<Opcion> opciones2, Exclusividad exclusividad) throws RondaSinPreguntaExcepcion {
        if(pregunta == null){
            throw new RondaSinPreguntaExcepcion("No se puede responder sin una pregunta");
        }
        int puntaje1 = pregunta.establecerPuntuacion(opciones1, Multiplicador.PorDefecto, exclusividad);
        int puntaje2 = pregunta.establecerPuntuacion(opciones2, Multiplicador.PorDefecto, exclusividad);

        exclusividad.aplicarExclusividad(jugadores.get(0), puntaje1, jugadores.get(1), puntaje2);
    }
}
