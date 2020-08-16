package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class PartidaTest {

    @Test
    public void debeCrearUnaNuevaPartidaEnBaseANombresDeJugadores(){
        List<String> nombresJugadores = Arrays.asList("Cecilia", "Leandro");

        Partida partida = new Partida(nombresJugadores);

        Jugador jugadorTurno = partida.obtenerJugadorTurno();

        Assertions.assertEquals("Cecilia", jugadorTurno.obtenerNombre());
        Assertions.assertEquals(Boolean.FALSE, partida.esPartidaFinalizada());
    }

    @Test
    public void responderHastaFinalDePartida(){
        List<String> nombresJugadores = Arrays.asList("Cecilia", "Leandro");

        Partida partida = new Partida(nombresJugadores);

        Assertions.assertEquals(Boolean.FALSE, partida.esPartidaFinalizada());

        Preguntable pregunta = partida.obtenerPreguntaTurno();
        List<Opcion> opciones = pregunta.obtenerOpciones();

        partida.responder(Arrays.asList(opciones.get(0).obtenerTexto()), Boolean.FALSE);

        Assertions.assertEquals(Boolean.FALSE, partida.esPartidaFinalizada());

        partida.responder(Arrays.asList(opciones.get(0).obtenerTexto()), Boolean.FALSE);

        Assertions.assertEquals(Boolean.TRUE, partida.esPartidaFinalizada());

    }

}
