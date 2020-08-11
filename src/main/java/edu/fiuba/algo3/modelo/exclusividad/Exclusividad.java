package edu.fiuba.algo3.modelo.exclusividad;

import edu.fiuba.algo3.modelo.Jugador;

/*
 * Supuesto: En el caso MC Parcial se considera que un jugador ha contestado correctamente
 * y el otro no, sólo cuando el otro no ha elegido ninguna respuesta correcta.
 * En este caso no se sumarán puntos a ningún jugador si ambos han elegido
 * al menos una respuesta correcta.
*/

public class Exclusividad {

    private EstadoExclusividad estado = new ExclusividadDesactivada();
    private int modificador = 1;
    private int exclusividadesRestantes;


    public void elegirExclusividad(int cantidadExclusividades) {
        exclusividadesRestantes = cantidadExclusividades;
        modificador = 2 * modificador;
    }

    public void activarExclusividad() {
        if(exclusividadesRestantes > 0) {
            estado = new ExclusividadActivada();
        }
    }

    public void aplicarExclusividad(Jugador jugador1, int puntaje1, Jugador jugador2, int puntaje2) {
        estado.utilizarExclusividad(jugador1, puntaje1, jugador2, puntaje2, modificador);
        modificador = 1;
        estado = new ExclusividadDesactivada();
    }

}
