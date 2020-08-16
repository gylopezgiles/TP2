package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.RondaSinPreguntaExcepcion;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;

import java.util.*;
import java.util.stream.Collectors;

public class Partida {

    private static final Integer MAX_EXCLUSIVIDAD_POR_JUGADOR = 2;

    private List<Jugador> jugadores;
    private Iterator<Preguntable> preguntasIterator;
    private Map<Jugador, Integer> exclusividadPorJugador;
    private Ronda ronda;

    public Partida(List<String> nombresJugadores, List<Preguntable> preguntas){
        this.jugadores = generarJugadores(nombresJugadores);
        this.preguntasIterator = preguntas.iterator();
        this.ronda = new Ronda(jugadores, preguntasIterator.next());
        this.exclusividadPorJugador = new HashMap<>();
        establecerExclusividadPorJugador();
    }

    private List<Jugador> generarJugadores(List<String> nombresJugadores){
        List<Jugador> jugadores = nombresJugadores.stream()
                .map(nombre -> new Jugador(nombre))
                .collect(Collectors.toList());
        return jugadores;
    }

    private void establecerExclusividadPorJugador(){
        jugadores.stream().forEach(jugador -> exclusividadPorJugador.put(jugador, 0));
    }

    public Boolean esPartidaFinalizada(){
        return ronda.esRondaFinalizada() && !preguntasIterator.hasNext();
    }

    public Preguntable obtenerPreguntaTurno(){
        return ronda.obtenerPreguntaTurno();
    }

    private void actualizarTurno(){
        if(ronda.esRondaFinalizada()){
            ronda.aplicarPuntajes();
            if(!esPartidaFinalizada()){
                ronda.restablecerRonda(preguntasIterator.next());
            }
        }
    }

    public Jugador obtenerJugadorTurno(){
        return ronda.obtenerJugadorTurno();
    }

    public <T> void responder(T opcionesSeleccionadas, Boolean aplicaExclusividad){
        //TODO: Manejar las excepciones
        Jugador jugadorTurno = obtenerJugadorTurno();
        if (aplicaExclusividad && exclusividadPorJugador.get(jugadorTurno) < MAX_EXCLUSIVIDAD_POR_JUGADOR) {
            exclusividadPorJugador.replace(jugadorTurno, exclusividadPorJugador.get(jugadorTurno)+1);
            try {
                ronda.responder(opcionesSeleccionadas, aplicaExclusividad);
            } catch (RondaSinPreguntaExcepcion rondaSinPreguntaExcepcion) {
                rondaSinPreguntaExcepcion.printStackTrace();
            }
        } else {
            responder(opcionesSeleccionadas, Multiplicador.PorDefecto);
        }
        actualizarTurno();
    }

    public <T> void responder(T opcionesSeleccionadas, MultiplicableStrategy multiplicador){
        //TODO: Manejar las excepciones
        try {
            ronda.responder(opcionesSeleccionadas, multiplicador);
        } catch (RondaSinPreguntaExcepcion rondaSinPreguntaExcepcion) {
            rondaSinPreguntaExcepcion.printStackTrace();
        }
    }

    public List<Jugador> obtenerJugadores() {
        return jugadores;
    }
}
