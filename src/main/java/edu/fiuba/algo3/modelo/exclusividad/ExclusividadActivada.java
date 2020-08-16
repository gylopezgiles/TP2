package edu.fiuba.algo3.modelo.exclusividad;

import edu.fiuba.algo3.modelo.Jugador;

import java.util.List;
import java.util.Map;

public class ExclusividadActivada implements EstadoExclusividad {

    @Override
    public Map<Jugador, Integer> utilizarExclusividad(List<Jugador> jugadores, Map<Jugador, Integer> puntajesRonda, int modificador) {
        int puntaje1 = puntajesRonda.get(jugadores.get(0));
        int puntaje2 = puntajesRonda.get(jugadores.get(1));
        if((puntaje1 == puntaje2) || ((puntaje1 != 0) && (puntaje2 != 0))) {
            puntajesRonda.put(jugadores.get(0), 0);
            puntajesRonda.put(jugadores.get(1), 0);
        }
        return (aplicarModificador(jugadores, puntajesRonda, modificador));
    }

    private Map<Jugador, Integer> aplicarModificador(List<Jugador> jugadores, Map<Jugador, Integer> puntajesRonda, int modificador) {
        puntajesRonda.put(jugadores.get(0), modificador * (puntajesRonda.get(jugadores.get(0))));
        puntajesRonda.put(jugadores.get(1), modificador * (puntajesRonda.get(jugadores.get(1))));
        return puntajesRonda;
    }
}
