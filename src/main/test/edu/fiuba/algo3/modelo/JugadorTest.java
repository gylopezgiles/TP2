package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JugadorTest {

    @Test
    public void debeCrearJugadorConNombreYPuntaje0(){
        Jugador jugador = new Jugador("Diego");

        Assertions.assertEquals("Diego", jugador.obtenerNombre());
        Assertions.assertEquals(0, jugador.obtenerPuntos());
    }

    @Test
    public void debeSumar1PuntoAJugador(){
        Jugador jugador = new Jugador("Diego");

        Assertions.assertEquals(0, jugador.obtenerPuntos());

        jugador.sumarPuntos(1);

        Assertions.assertEquals(1, jugador.obtenerPuntos());
    }

    @Test
    public void debeSumar1PuntoNegativoAJugador(){
        Jugador jugador = new Jugador("Diego");

        Assertions.assertEquals(0, jugador.obtenerPuntos());

        jugador.sumarPuntos(-1);

        Assertions.assertEquals(-1, jugador.obtenerPuntos());
    }
}
