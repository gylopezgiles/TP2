package edu.fiuba.algo3.modelo.exclusividad;

import edu.fiuba.algo3.modelo.Jugador;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExclusividadActivada implements EstadoExclusividad {

    @Override
    public Map<Jugador, Integer> utilizarExclusividad(List<Jugador> jugadores, Map<Jugador, Integer> puntajesRonda, int modificador) {
        if(jugadoresRespondieronIgual(jugadores, puntajesRonda) || todosJugadoresRespondieronBien(jugadores, puntajesRonda)) {
            jugadores.stream().forEach(jugador -> puntajesRonda.put(jugador, 0));
        }
        return (aplicarModificador(jugadores, puntajesRonda, modificador));
    }

    private Map<Jugador, Integer> aplicarModificador(List<Jugador> jugadores, Map<Jugador, Integer> puntajesRonda, int modificador) {
        jugadores.stream().forEach(jugador -> puntajesRonda.put(jugador, modificador * (puntajesRonda.get(jugador))));
        return puntajesRonda;
    }

    private Boolean todosJugadoresRespondieronBien(List<Jugador> jugadores, Map<Jugador, Integer> puntajesRonda){
        Long cantidadJugadoresRespondieronBien = jugadores.stream().filter(jugador -> puntajesRonda.get(jugador) > 0).count();
        return jugadores.size() == cantidadJugadoresRespondieronBien;
    }

    private Boolean jugadoresRespondieronIgual(List<Jugador> jugadores, Map<Jugador, Integer> puntajesRonda){
        List<Integer> puntajes = jugadores.stream().map(jugador -> puntajesRonda.get(jugador)).collect(Collectors.toList());
        return puntajes.stream().distinct().count() == 1;
    }

}
