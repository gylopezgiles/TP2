package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.RondaSinPreguntaExcepcion;
import edu.fiuba.algo3.modelo.exclusividad.Exclusividad;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.EstadoRonda;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
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

    public Ronda(List<Jugador> jugadores, Preguntable pregunta){
        this.jugadores = jugadores;
        this.pregunta = pregunta;
        this.jugadorIterator = jugadores.iterator();
        this.jugadorTurno = jugadorIterator.next();

        aplicanExclusividad = new ArrayList<>();
        puntajesRonda = new HashMap<>();

    }

    public void restablecerRonda(Preguntable pregunta){
        this.pregunta = pregunta;
        this.jugadorIterator = jugadores.iterator();
        this.jugadorTurno = jugadorIterator.next();
        this.puntajesRonda = new HashMap<>();
        this.estadoRonda = EstadoRonda.INICIA;
    }

    public List<Jugador> obtenerJugadores(){
        return jugadores;
    }

    public Preguntable obtenerPregunta(){
        return pregunta;
    }

    public void responder(List<Opcion> opciones) throws RondaSinPreguntaExcepcion {
        responder(opciones, Multiplicador.PorDefecto);
    }

    public void responder(List<Opcion> opciones, Boolean aplicaExclusividad) throws RondaSinPreguntaExcepcion {
        if(aplicaExclusividad){
            aplicanExclusividad.add(jugadorTurno);
        }
        responder(opciones, Multiplicador.PorDefecto);
    }

    public void responder(List<Opcion> opciones, MultiplicableStrategy multiplicador) throws RondaSinPreguntaExcepcion {
        if(pregunta == null){
            throw new RondaSinPreguntaExcepcion("No se puede responder sin una pregunta");
        }
        estadoRonda = EstadoRonda.RESPONDIENDO;
        exclusividad = new Exclusividad();
        puntajesRonda.put(jugadorTurno, pregunta.establecerPuntuacion(opciones, multiplicador, exclusividad));
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

    public Preguntable obtenerPreguntaTurno(){
        return pregunta;
    }

    private void actualizarEstadoRonda() {
        if(jugadorTurno == null){
            estadoRonda = EstadoRonda.FIN_RONDA;
        }
    }

    public void aplicarPuntajes(){
        puntajesRonda = exclusividad.aplicarExclusividad(jugadores, puntajesRonda, aplicanExclusividad);
        jugadores.stream().forEach(jugador -> jugador.sumarPuntos(puntajesRonda.get(jugador)));
    }
}
