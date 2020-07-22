package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.VerdaderoFalsoClasico;

import java.util.List;

public class Ronda {

    private List<Jugador> jugadores;
    private Preguntable pregunta;

    public Ronda(List<Jugador> jugadores, Preguntable pregunta){
        this.jugadores = jugadores;
        this.pregunta = pregunta;
    }

    public void responder(Jugador jugador, List<Opcion> opciones){
        jugador.sumarPuntos(pregunta.establecerPuntuacion(opciones));
    }
}
