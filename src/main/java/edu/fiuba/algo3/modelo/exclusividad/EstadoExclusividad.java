package edu.fiuba.algo3.modelo.exclusividad;

import edu.fiuba.algo3.modelo.Jugador;

public interface EstadoExclusividad {

    void utilizarExclusividad(Jugador jugador1, int puntaje1, Jugador jugador2, int puntaje2, int modificador);

}
