package edu.fiuba.algo3.modelo.exclusividad;

import edu.fiuba.algo3.modelo.Jugador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ExclusividadTest {

    @Test
    public void debeDuplicarSoloElPuntajeDelJugador1QueRespondeCorrectamenteCuandoSeEligeUnavez() {
        //Given
        Jugador jugador1 = new Jugador("nombre1");
        Jugador jugador2 = new Jugador("nombre2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        List<Jugador> aplicanExclusividad = Arrays.asList(jugador2);
        Map<Jugador, Integer> puntajesRonda = new HashMap<>();
        puntajesRonda.put(jugador1, 2);
        puntajesRonda.put(jugador2, 0);
        Exclusividad exclusividad = new Exclusividad();
        exclusividad.activarExclusividad();

        //When
        puntajesRonda = exclusividad.aplicarExclusividad(jugadores, puntajesRonda, aplicanExclusividad);

        //Then
        Assertions.assertEquals(4, puntajesRonda.get(jugador1));
        Assertions.assertEquals(0, puntajesRonda.get(jugador2));
    }

    @Test
    public void debeDuplicarSoloElPuntajeDelJugador2QueRespondeCorrectamenteCuandoSeEligeUnavez() {
        //Given
        Jugador jugador1 = new Jugador("nombre1");
        Jugador jugador2 = new Jugador("nombre2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        List<Jugador> aplicanExclusividad = Arrays.asList(jugador1);
        Map<Jugador, Integer> puntajesRonda = new HashMap<>();
        puntajesRonda.put(jugador1, 0);
        puntajesRonda.put(jugador2, 1);
        Exclusividad exclusividad = new Exclusividad();
        exclusividad.activarExclusividad();

        //When
        puntajesRonda = exclusividad.aplicarExclusividad(jugadores, puntajesRonda, aplicanExclusividad);

        //Then
        Assertions.assertEquals(0, puntajesRonda.get(jugador1));
        Assertions.assertEquals(2, puntajesRonda.get(jugador2));
    }

    @Test
    public void debeAnularLosPuntajesSiAmbosRespondenCorrectamenteCuandoSeEligeUnavez() {
        //Given
        Jugador jugador1 = new Jugador("nombre1");
        Jugador jugador2 = new Jugador("nombre2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        List<Jugador> aplicanExclusividad = Arrays.asList(jugador2);
        Map<Jugador, Integer> puntajesRonda = new HashMap<>();
        puntajesRonda.put(jugador1, 1);
        puntajesRonda.put(jugador2, 1);
        Exclusividad exclusividad = new Exclusividad();
        exclusividad.activarExclusividad();

        //When
        puntajesRonda = exclusividad.aplicarExclusividad(jugadores, puntajesRonda, aplicanExclusividad);

        //Then
        Assertions.assertEquals(0, puntajesRonda.get(jugador1));
        Assertions.assertEquals(0, puntajesRonda.get(jugador2));
    }

    @Test
    public void noDebeModificarLosPuntajesSiAmbosRespondenIncorrectamenteCuandoSeEligeUnavez() {
        //Given
        Jugador jugador1 = new Jugador("nombre1");
        Jugador jugador2 = new Jugador("nombre2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        List<Jugador> aplicanExclusividad = Arrays.asList(jugador1);
        Map<Jugador, Integer> puntajesRonda = new HashMap<>();
        puntajesRonda.put(jugador1, 0);
        puntajesRonda.put(jugador2, 0);
        Exclusividad exclusividad = new Exclusividad();
        exclusividad.activarExclusividad();

        //When
        puntajesRonda = exclusividad.aplicarExclusividad(jugadores, puntajesRonda, aplicanExclusividad);

        //Then
        Assertions.assertEquals(0, puntajesRonda.get(jugador1));
        Assertions.assertEquals(0, puntajesRonda.get(jugador2));
    }
    @Test
    public void debeCuaduplicarSoloElPuntajeDelJugador1QueRespondeCorrectamenteCuandoSeEligeDosVeces() {
        //Given
        Jugador jugador1 = new Jugador("nombre1");
        Jugador jugador2 = new Jugador("nombre2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        List<Jugador> aplicanExclusividad = Arrays.asList(jugador1, jugador2);
        Map<Jugador, Integer> puntajesRonda = new HashMap<>();
        puntajesRonda.put(jugador1, 1);
        puntajesRonda.put(jugador2, 0);
        Exclusividad exclusividad = new Exclusividad();
        exclusividad.activarExclusividad();

        //When
        puntajesRonda = exclusividad.aplicarExclusividad(jugadores, puntajesRonda, aplicanExclusividad);

        //Then
        Assertions.assertEquals(4, puntajesRonda.get(jugador1));
        Assertions.assertEquals(0, puntajesRonda.get(jugador2));
    }

    @Test
    public void debeCuaduplicarSoloElPuntajeDelJugador2QueRespondeCorrectamenteCuandoSeEligeDosVeces() {
        //Given
        Jugador jugador1 = new Jugador("nombre1");
        Jugador jugador2 = new Jugador("nombre2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        List<Jugador> aplicanExclusividad = Arrays.asList(jugador1, jugador2);
        Map<Jugador, Integer> puntajesRonda = new HashMap<>();
        puntajesRonda.put(jugador1, 0);
        puntajesRonda.put(jugador2, 3);
        Exclusividad exclusividad = new Exclusividad();
        exclusividad.activarExclusividad();

        //When
        puntajesRonda = exclusividad.aplicarExclusividad(jugadores, puntajesRonda, aplicanExclusividad);

        //Then
        Assertions.assertEquals(0, puntajesRonda.get(jugador1));
        Assertions.assertEquals(12, puntajesRonda.get(jugador2));
    }

    @Test
    public void debeAnularLosPuntajesSiAmbosRespondenCorrectamenteCuandoSeEligeDosVeces() {
        //Given
        Jugador jugador1 = new Jugador("nombre1");
        Jugador jugador2 = new Jugador("nombre2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        List<Jugador> aplicanExclusividad = Arrays.asList(jugador1, jugador2);
        Map<Jugador, Integer> puntajesRonda = new HashMap<>();
        puntajesRonda.put(jugador1, 2);
        puntajesRonda.put(jugador2, 2);
        Exclusividad exclusividad = new Exclusividad();
        exclusividad.activarExclusividad();

        //When
        puntajesRonda = exclusividad.aplicarExclusividad(jugadores, puntajesRonda, aplicanExclusividad);

        //Then
        Assertions.assertEquals(0, puntajesRonda.get(jugador1));
        Assertions.assertEquals(0, puntajesRonda.get(jugador2));
    }

    @Test
    public void noDebeModificarLosPuntajesSiAmbosRespondenIncorrectamenteCuandoSeEligeDosVeces() {
        //Given
        Jugador jugador1 = new Jugador("nombre1");
        Jugador jugador2 = new Jugador("nombre2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        List<Jugador> aplicanExclusividad = Arrays.asList(jugador1, jugador2);
        Map<Jugador, Integer> puntajesRonda = new HashMap<>();
        puntajesRonda.put(jugador1, 0);
        puntajesRonda.put(jugador2, 0);
        Exclusividad exclusividad = new Exclusividad();
        exclusividad.activarExclusividad();

        //When
        puntajesRonda = exclusividad.aplicarExclusividad(jugadores, puntajesRonda, aplicanExclusividad);

        //Then
        Assertions.assertEquals(0, puntajesRonda.get(jugador1));
        Assertions.assertEquals(0, puntajesRonda.get(jugador2));
    }

    @Test
    public void noDebeModificarLosPuntajesCuandoLosJugadoresNoEligenUsarla() {
        //Given
        Jugador jugador1 = new Jugador("nombre1");
        Jugador jugador2 = new Jugador("nombre2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        List<Jugador> aplicanExclusividad = new ArrayList<>();
        Map<Jugador, Integer> puntajesRonda = new HashMap<>();
        puntajesRonda.put(jugador1, 2);
        puntajesRonda.put(jugador2, 1);
        Exclusividad exclusividad = new Exclusividad();
        exclusividad.activarExclusividad();

        //When
        puntajesRonda = exclusividad.aplicarExclusividad(jugadores, puntajesRonda, aplicanExclusividad);

        //Then
        Assertions.assertEquals(2, puntajesRonda.get(jugador1));
        Assertions.assertEquals(1, puntajesRonda.get(jugador2));
    }

    @Test
    public void noDebeModificarLosPuntajesCuandoNoSeActiva() {
        //Caso en que se se quiera aplicar a pregunta con penalidad
        //Given
        Jugador jugador1 = new Jugador("nombre1");
        Jugador jugador2 = new Jugador("nombre2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        List<Jugador> aplicanExclusividad = Arrays.asList(jugador1, jugador2);
        Map<Jugador, Integer> puntajesRonda = new HashMap<>();
        puntajesRonda.put(jugador1, 2);
        puntajesRonda.put(jugador2, 2);
        Exclusividad exclusividad = new Exclusividad();

        //When
        puntajesRonda = exclusividad.aplicarExclusividad(jugadores, puntajesRonda, aplicanExclusividad);

        //Then
        Assertions.assertEquals(2, puntajesRonda.get(jugador1));
        Assertions.assertEquals(2, puntajesRonda.get(jugador2));
    }

    @Test
    public void debeAnularLosPuntajesSiAmbosEligenAlgunaRespuestaCorrecta() {
        //Caso MC Parcial cuando contestan diferentes respuestas, pero en ambos casos correctas
        //Given
        Jugador jugador1 = new Jugador("nombre1");
        Jugador jugador2 = new Jugador("nombre2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        List<Jugador> aplicanExclusividad = Arrays.asList(jugador1, jugador2);
        Map<Jugador, Integer> puntajesRonda = new HashMap<>();
        puntajesRonda.put(jugador1, 2);
        puntajesRonda.put(jugador2, 1);
        Exclusividad exclusividad = new Exclusividad();
        exclusividad.activarExclusividad();

        //When
        puntajesRonda = exclusividad.aplicarExclusividad(jugadores, puntajesRonda, aplicanExclusividad);

        //Then
        Assertions.assertEquals(0, puntajesRonda.get(jugador1));
        Assertions.assertEquals(0, puntajesRonda.get(jugador2));
    }

}
