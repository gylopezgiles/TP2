package edu.fiuba.algo3.modelo.exclusividad;

import edu.fiuba.algo3.modelo.Jugador;

import java.util.List;
import java.util.Map;

public interface EstadoExclusividad {

    Map<Jugador, Integer> utilizarExclusividad(List<Jugador> jugadores, Map<Jugador, Integer> puntajesRonda, int modificador);

}
