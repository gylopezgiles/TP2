package edu.fiuba.algo3.modelo.exclusividad;

import edu.fiuba.algo3.modelo.Jugador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExclusividadTest {

    @Test
    public void debeDuplicarSoloElPuntajeDelJugador1QueRespondeCorrectamenteCuandoSeEligeUnavez() {
        //Given
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("nombre1", exclusividad);
        Jugador jugador2 = new Jugador("nombre2", exclusividad);
        jugador1.seleccionarExclusividad();
        exclusividad.activarExclusividad();
        int puntaje1 = 2;
        int puntaje2 = 0;

        //When
        exclusividad.aplicarExclusividad(jugador1, puntaje1, jugador2, puntaje2);

        //Then
        Assertions.assertEquals(4, jugador1.obtenerPuntos());
        Assertions.assertEquals(0, jugador2.obtenerPuntos());
    }

    @Test
    public void debeDuplicarSoloElPuntajeDelJugador2QueRespondeCorrectamenteCuandoSeEligeUnavez() {
        //Given
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("nombre1", exclusividad);
        Jugador jugador2 = new Jugador("nombre2", exclusividad);
        jugador2.seleccionarExclusividad();
        exclusividad.activarExclusividad();
        int puntaje1 = 0;
        int puntaje2 = 1;

        //When
        exclusividad.aplicarExclusividad(jugador1, puntaje1, jugador2, puntaje2);

        //Then
        Assertions.assertEquals(0, jugador1.obtenerPuntos());
        Assertions.assertEquals(2, jugador2.obtenerPuntos());
    }

    @Test
    public void debeAnularLosPuntajesSiAmbosRespondenCorrectamenteCuandoSeEligeUnavez() {
        //Given
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("nombre1", exclusividad);
        Jugador jugador2 = new Jugador("nombre2", exclusividad);
        jugador1.seleccionarExclusividad();
        exclusividad.activarExclusividad();
        int puntaje1 = 2;
        int puntaje2 = 2;

        //When
        exclusividad.aplicarExclusividad(jugador1, puntaje1, jugador2, puntaje2);

        //Then
        Assertions.assertEquals(0, jugador1.obtenerPuntos());
        Assertions.assertEquals(0, jugador2.obtenerPuntos());
    }

    @Test
    public void noDebeModificarLosPuntajesSiAmbosRespondenIncorrectamenteCuandoSeEligeUnavez() {
        //Given
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("nombre1", exclusividad);
        Jugador jugador2 = new Jugador("nombre2", exclusividad);
        jugador1.seleccionarExclusividad();
        exclusividad.activarExclusividad();
        int puntaje1 = 0;
        int puntaje2 = 0;

        //When
        exclusividad.aplicarExclusividad(jugador1, puntaje1, jugador2, puntaje2);

        //Then
        Assertions.assertEquals(0, jugador1.obtenerPuntos());
        Assertions.assertEquals(0, jugador2.obtenerPuntos());
    }

    @Test
    public void debeCuaduplicarSoloElPuntajeDelJugador1QueRespondeCorrectamenteCuandoSeEligeDosVeces() {
        //Given
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("nombre1", exclusividad);
        Jugador jugador2 = new Jugador("nombre2", exclusividad);
        jugador1.seleccionarExclusividad();
        jugador2.seleccionarExclusividad();
        exclusividad.activarExclusividad();
        int puntaje1 = 1;
        int puntaje2 = 0;

        //When
        exclusividad.aplicarExclusividad(jugador1, puntaje1, jugador2, puntaje2);

        //Then
        Assertions.assertEquals(4, jugador1.obtenerPuntos());
        Assertions.assertEquals(0, jugador2.obtenerPuntos());
    }

    @Test
    public void debeCuaduplicarSoloElPuntajeDelJugador2QueRespondeCorrectamenteCuandoSeEligeDosVeces() {
        //Given
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("nombre1", exclusividad);
        Jugador jugador2 = new Jugador("nombre2", exclusividad);
        jugador1.seleccionarExclusividad();
        jugador2.seleccionarExclusividad();
        exclusividad.activarExclusividad();
        int puntaje1 = 0;
        int puntaje2 = 2;

        //When
        exclusividad.aplicarExclusividad(jugador1, puntaje1, jugador2, puntaje2);

        //Then
        Assertions.assertEquals(0, jugador1.obtenerPuntos());
        Assertions.assertEquals(8, jugador2.obtenerPuntos());
    }

    @Test
    public void debeAnularLosPuntajesSiAmbosRespondenCorrectamenteCuandoSeEligeDosVeces() {
        //Given
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("nombre1", exclusividad);
        Jugador jugador2 = new Jugador("nombre2", exclusividad);
        jugador1.seleccionarExclusividad();
        jugador2.seleccionarExclusividad();
        exclusividad.activarExclusividad();
        int puntaje1 = 1;
        int puntaje2 = 1;

        //When
        exclusividad.aplicarExclusividad(jugador1, puntaje1, jugador2, puntaje2);

        //Then
        Assertions.assertEquals(0, jugador1.obtenerPuntos());
        Assertions.assertEquals(0, jugador2.obtenerPuntos());
    }

    @Test
    public void noDebeModificarLosPuntajesSiAmbosRespondenIncorrectamenteCuandoSeEligeDosVeces() {
        //Given
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("nombre1", exclusividad);
        Jugador jugador2 = new Jugador("nombre2", exclusividad);
        jugador1.seleccionarExclusividad();
        jugador2.seleccionarExclusividad();
        exclusividad.activarExclusividad();
        int puntaje1 = 0;
        int puntaje2 = 0;

        //When
        exclusividad.aplicarExclusividad(jugador1, puntaje1, jugador2, puntaje2);

        //Then
        Assertions.assertEquals(0, jugador1.obtenerPuntos());
        Assertions.assertEquals(0, jugador2.obtenerPuntos());
    }

    @Test
    public void noDebeModificarLosPuntajesCuandoNoSeElige() {
        //Caso en que el jugador no elige usarla
        //Given
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("nombre1", exclusividad);
        Jugador jugador2 = new Jugador("nombre2", exclusividad);
        exclusividad.activarExclusividad();
        int puntaje1 = 2;
        int puntaje2 = 0;

        //When
        exclusividad.aplicarExclusividad(jugador1, puntaje1, jugador2, puntaje2);

        //Then
        Assertions.assertEquals(2, jugador1.obtenerPuntos());
        Assertions.assertEquals(0, jugador2.obtenerPuntos());
    }

    @Test
    public void noDebeModificarLosPuntajesCuandoNoSeActiva() {
        //Caso en que se se quiera aplicar a pregunta con penalidad
        //Given
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("nombre1", exclusividad);
        Jugador jugador2 = new Jugador("nombre2", exclusividad);
        jugador1.seleccionarExclusividad();
        int puntaje1 = 2;
        int puntaje2 = 2;

        //When
        exclusividad.aplicarExclusividad(jugador1, puntaje1, jugador2, puntaje2);

        //Then
        Assertions.assertEquals(2, jugador1.obtenerPuntos());
        Assertions.assertEquals(2, jugador2.obtenerPuntos());
    }

    @Test
    public void debeAnularLosPuntajesSiAmbosEligenAlgunaRespuestaCorrecta() {
        //Caso MC Parcial cuando contestan diferentes respuestas, pero en ambos casos correctas
        //Given
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("nombre1", exclusividad);
        Jugador jugador2 = new Jugador("nombre2", exclusividad);
        jugador1.seleccionarExclusividad();
        exclusividad.activarExclusividad();
        int puntaje1 = 3;
        int puntaje2 = 2;

        //When
        exclusividad.aplicarExclusividad(jugador1, puntaje1, jugador2, puntaje2);

        //Then
        Assertions.assertEquals(0, jugador1.obtenerPuntos());
        Assertions.assertEquals(0, jugador2.obtenerPuntos());
    }

    @Test
    public void noDebePoderUsarlaMasDeDosVecesElMismoJugador() {
        //Given
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("nombre1", exclusividad);
        Jugador jugador2 = new Jugador("nombre2", exclusividad);
        jugador1.seleccionarExclusividad();
        exclusividad.activarExclusividad();
        int puntaje1a = 3;
        int puntaje2a = 0;
        exclusividad.aplicarExclusividad(jugador1, puntaje1a, jugador2, puntaje2a);

        jugador1.seleccionarExclusividad();
        exclusividad.activarExclusividad();
        int puntaje1b = 0;
        int puntaje2b = 0;
        exclusividad.aplicarExclusividad(jugador1, puntaje1b, jugador2, puntaje2b);

        jugador1.seleccionarExclusividad();
        exclusividad.activarExclusividad();
        int puntaje1c = 1;
        int puntaje2c = 2;

        //When
        exclusividad.aplicarExclusividad(jugador1, puntaje1c, jugador2, puntaje2c);

        //Then
        Assertions.assertEquals(7, jugador1.obtenerPuntos());
        Assertions.assertEquals(2, jugador2.obtenerPuntos());
    }

    @Test
    public void noDebePoderUsarlaMasDeDosVecesCadaJugador() {
        //Given
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("nombre1", exclusividad);
        Jugador jugador2 = new Jugador("nombre2", exclusividad);
        jugador1.seleccionarExclusividad();
        jugador2.seleccionarExclusividad();
        exclusividad.activarExclusividad();
        int puntaje1a = 2;
        int puntaje2a = 1;
        exclusividad.aplicarExclusividad(jugador1, puntaje1a, jugador2, puntaje2a);

        jugador1.seleccionarExclusividad();
        jugador2.seleccionarExclusividad();
        exclusividad.activarExclusividad();
        int puntaje1b = 0;
        int puntaje2b = 3;
        exclusividad.aplicarExclusividad(jugador1, puntaje1b, jugador2, puntaje2b);

        jugador1.seleccionarExclusividad();
        jugador2.seleccionarExclusividad();
        exclusividad.activarExclusividad();
        int puntaje1c = 3;
        int puntaje2c = 1;

        //When
        exclusividad.aplicarExclusividad(jugador1, puntaje1c, jugador2, puntaje2c);

        //Then
        Assertions.assertEquals(3, jugador1.obtenerPuntos());
        Assertions.assertEquals(13, jugador2.obtenerPuntos());
    }

}
