package edu.fiuba.algo3.modelo.exclusividad;

import edu.fiuba.algo3.modelo.Jugador;

public class ExclusividadActivada implements EstadoExclusividad {

    @Override
    public void utilizarExclusividad(Jugador jugador1, int puntaje1, Jugador jugador2, int puntaje2, int modificador) {
        if ((puntaje1 == puntaje2) || ((puntaje2 != 0) && (puntaje1 != 0))) {
            puntaje1 = 0;
            puntaje2 = 0;
        }
        jugador1.sumarPuntos(aplicarModificador(puntaje1, modificador));
        jugador2.sumarPuntos(aplicarModificador(puntaje2, modificador));
    }

    private int aplicarModificador(int puntaje, int modificador) {
        return (puntaje * modificador);
    }

}
