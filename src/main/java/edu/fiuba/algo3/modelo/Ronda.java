package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.exclusividad.Exclusividad;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;

import java.util.*;

public class Ronda {

    private List<Jugador> jugadores;
    private Preguntable pregunta;
    private Iterator<Jugador> jugadorIterator;
    private Jugador jugadorTurno;
    private Map<Jugador, Integer> puntajesRonda;
    private EstadoRonda estadoRonda;
    private List<Jugador> aplicanExclusividad;
    private Exclusividad exclusividad;

    public Ronda(List<Jugador> jugadores, Preguntable pregunta) throws ParametrosInvalidosExcepcion {
        validarRonda(jugadores, pregunta);
        this.jugadores = jugadores;
        this.pregunta = pregunta;
        this.jugadorIterator = jugadores.iterator();
        this.jugadorTurno = jugadorIterator.next();
        this.aplicanExclusividad = new LinkedList<>();
        this.puntajesRonda = new HashMap<>();
        this.exclusividad = new Exclusividad();

    }

    public void restablecerRonda(Preguntable pregunta){
        this.pregunta = pregunta;
        this.jugadorIterator = jugadores.iterator();
        this.jugadorTurno = jugadorIterator.next();
        this.puntajesRonda = new HashMap<>();
        aplicanExclusividad.removeAll(aplicanExclusividad);
        estadoRonda = EstadoRonda.INICIA;
        exclusividad.desactivarExclusividad();
    }

    private void validarRonda(List<Jugador> jugadores, Preguntable pregunta) throws ParametrosInvalidosExcepcion {
        if (jugadores.isEmpty() || pregunta == null){
            throw new ParametrosInvalidosExcepcion("No se puede crear una ronda sin jugadores o pregunta");
        }
    }

    public List<Jugador> obtenerJugadores(){
        return jugadores;
    }

    public Preguntable obtenerPregunta(){
        return pregunta;
    }

    public <T> void responder(T opciones) {
        responder(opciones, Multiplicador.PorDefecto);
    }

    public <T> void responder(T opciones, Boolean aplicaExclusividad) {
        if(aplicaExclusividad){
            aplicanExclusividad.add(jugadorTurno);
        }
        responder(opciones, Multiplicador.PorDefecto);
    }

    public <T> void responder(T nombresOpcionesSeleccionadas, MultiplicableStrategy multiplicador) {
        estadoRonda = EstadoRonda.RESPONDIENDO;
        puntajesRonda.put(jugadorTurno, pregunta.establecerPuntuacion(nombresOpcionesSeleccionadas, multiplicador, exclusividad));
        cambiarJugadorTurno();
        actualizarEstadoRonda();
    }

    private void cambiarJugadorTurno() {
        jugadorTurno = jugadorIterator.hasNext() ? jugadorIterator.next() : null;
    }

    public Boolean esRondaFinalizada(){
        return estadoRonda == EstadoRonda.FIN_RONDA;
    }

    public Jugador obtenerJugadorTurno(){
        return jugadorTurno;
    }

    private void actualizarEstadoRonda() {
        if(jugadorTurno == null){
            estadoRonda = EstadoRonda.FIN_RONDA;
            aplicarPuntajes();
        }
    }

    private void aplicarPuntajes(){
        puntajesRonda = exclusividad.aplicarExclusividad(jugadores, puntajesRonda, aplicanExclusividad);
        jugadores.stream().forEach(jugador -> jugador.sumarPuntos(puntajesRonda.get(jugador)));
    }
}
