package edu.fiuba.algo3.modelo.exclusividad;

import edu.fiuba.algo3.modelo.Jugador;

import java.util.List;
import java.util.Map;

public class ExclusividadDesactivada implements EstadoExclusividad {

    @Override
    public Map<Jugador, Integer> utilizarExclusividad(List<Jugador> jugadores, Map<Jugador, Integer> puntajesRonda, int modificador) {
        return puntajesRonda;
    }

    public Boolean estaActivada(){
        return false;
    }
}
