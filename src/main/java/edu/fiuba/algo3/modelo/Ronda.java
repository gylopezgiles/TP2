package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.RondaSinPreguntaExcepcion;
import edu.fiuba.algo3.modelo.exclusividad.Exclusividad;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Ronda {

    private List<Jugador> jugadores;
    private Preguntable pregunta;
    private Iterator<Jugador> jugadorIterator;
    private Jugador jugadorTurno;

    public Ronda(List<Jugador> jugadores, Preguntable pregunta){
        this.jugadores = jugadores;
        this.pregunta = pregunta;
        this.jugadorIterator = jugadores.iterator();
        this.jugadorTurno = jugadorIterator.next();
    }

    public void restablecerRonda(Preguntable pregunta){
        this.pregunta = pregunta;
        this.jugadorIterator = jugadores.iterator();
        this.jugadorTurno = jugadorIterator.next();
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

    public void responder(List<Opcion> opciones, MultiplicableStrategy multiplicador) throws RondaSinPreguntaExcepcion {
        if(pregunta == null){
            throw new RondaSinPreguntaExcepcion("No se puede responder sin una pregunta");
        }
        Exclusividad exclusividad = new Exclusividad();
        jugadorTurno.sumarPuntos(pregunta.establecerPuntuacion(opciones, multiplicador, exclusividad));
        cambiarJugadorTurno();
    }

    public void responderConExclusividad(List<Opcion> opciones1, List<Opcion> opciones2, Exclusividad exclusividad) throws RondaSinPreguntaExcepcion {
        if(pregunta == null){
            throw new RondaSinPreguntaExcepcion("No se puede responder sin una pregunta");
        }
        int puntaje1 = pregunta.establecerPuntuacion(opciones1, Multiplicador.PorDefecto, exclusividad);
        int puntaje2 = pregunta.establecerPuntuacion(opciones2, Multiplicador.PorDefecto, exclusividad);

        exclusividad.aplicarExclusividad(jugadores.get(0), puntaje1, jugadores.get(1), puntaje2);
    }

    private void cambiarJugadorTurno() {
        jugadorTurno = jugadorIterator.hasNext() ? jugadorIterator.next() : null;
    }

    public Boolean esRondaFinalizada(){
        return jugadorTurno == null;
    }

    public Jugador obtenerJugadorTurno(){
        return jugadorTurno;
    }

    public Preguntable obtenerPreguntaTurno(){
        return pregunta;
    }
}
