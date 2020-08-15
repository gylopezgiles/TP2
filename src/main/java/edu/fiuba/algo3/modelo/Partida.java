package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.RondaSinPreguntaExcepcion;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;

import java.util.*;
import java.util.stream.Collectors;

public class Partida {

    private List<Jugador> jugadores;
    private Iterator<Preguntable> preguntasIterator;
    private Ronda ronda;

    public Partida(List<String> nombresJugadores, List<Preguntable> preguntas){
        this.jugadores = generarJugadores(nombresJugadores);
        this.preguntasIterator = preguntas.iterator();
        this.ronda = new Ronda(jugadores, preguntasIterator.next());
    }

    private List<Jugador> generarJugadores(List<String> nombresJugadores){
        List<Jugador> jugadores = nombresJugadores.stream()
                .map(nombre -> new Jugador(nombre))
                .collect(Collectors.toList());
        return jugadores;
    }

    public Boolean esPartidaFinalizada(){
        return ronda.esRondaFinalizada() && !preguntasIterator.hasNext();
    }

    public Preguntable obtenerPreguntaTurno(){
        return ronda.obtenerPreguntaTurno();
    }

    private void actualizarTurno(){
        if(ronda.esRondaFinalizada() && !esPartidaFinalizada()){
            ronda.restablecerRonda(preguntasIterator.next());
        }
    }

    public Jugador obtenerJugadorTurno(){
        return ronda.obtenerJugadorTurno();
    }

    public void responder(List<String> opcionesSeleccionadas){
        //TODO: Manejar las excepciones
        Preguntable pregunta = ronda.obtenerPregunta();
        List<Opcion> opciones = pregunta.obtenerOpcionesPorNombre(opcionesSeleccionadas);
        try {
            ronda.responder(opciones);
        } catch (RondaSinPreguntaExcepcion rondaSinPreguntaExcepcion) {
            rondaSinPreguntaExcepcion.printStackTrace();
        }
        actualizarTurno();
    }

    public List<Jugador> obtenerJugadores() {
        return jugadores;
    }
}
