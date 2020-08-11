package edu.fiuba.algo3.modelo.exclusividad;

import edu.fiuba.algo3.modelo.Jugador;

public class ExclusividadDesactivada implements EstadoExclusividad {

    @Override
    public void utilizarExclusividad(Jugador jugador1, int puntaje1, Jugador jugador2, int puntaje2, int modificador) {
        jugador1.sumarPuntos(puntaje1);
        jugador2.sumarPuntos(puntaje2);
    }

}
