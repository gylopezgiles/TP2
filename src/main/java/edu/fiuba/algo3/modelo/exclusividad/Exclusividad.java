package edu.fiuba.algo3.modelo.exclusividad;

import edu.fiuba.algo3.modelo.Jugador;

import java.util.List;
import java.util.Map;

public class Exclusividad  {

    private int modificador;
    private EstadoExclusividad estado = new ExclusividadDesactivada();

    public void activarExclusividad() {
        estado = new ExclusividadActivada();
    }

    public Map<Jugador, Integer> aplicarExclusividad(List<Jugador> jugadores, Map<Jugador, Integer> puntajesRonda, List<Jugador> aplicanExclusividad){
        elegirTipoExclusividad(aplicanExclusividad);
        return (estado.utilizarExclusividad(jugadores, puntajesRonda, modificador));
    }

    private void elegirTipoExclusividad(List<Jugador> aplicanExclusividad) {
        if (aplicanExclusividad.isEmpty()) {
            estado = new ExclusividadDesactivada();
        }
        modificador = 2 * (int)aplicanExclusividad.stream().count();
    }

}
