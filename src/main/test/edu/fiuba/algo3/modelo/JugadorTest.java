package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.exclusividad.Exclusividad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JugadorTest {

    @Test
    public void debeCrearJugadorConNombreYPuntaje0(){
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador = new Jugador("Diego", exclusividad);

        Assertions.assertEquals("Diego", jugador.obtenerNombre());
        Assertions.assertEquals(0, jugador.obtenerPuntos());
    }

    @Test
    public void debeSumar1PuntoAJugador(){
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador = new Jugador("Diego", exclusividad);

        Assertions.assertEquals(0, jugador.obtenerPuntos());

        jugador.sumarPuntos(1);

        Assertions.assertEquals(1, jugador.obtenerPuntos());
    }

    @Test
    public void debeSumar1PuntoNegativoAJugador(){
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador = new Jugador("Diego", exclusividad);

        Assertions.assertEquals(0, jugador.obtenerPuntos());

        jugador.sumarPuntos(-1);

        Assertions.assertEquals(-1, jugador.obtenerPuntos());
    }
}
