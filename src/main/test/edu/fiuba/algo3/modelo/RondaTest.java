package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.pregunta.CreadorPregunta;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class RondaTest {

    @Test
    public void debeCrearUnaRondaConJugadoresParaUnaPregunta() throws ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException {
        Jugador jugador1 = new Jugador("jugador1");
        Jugador jugador2 = new Jugador("jugador2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);

        Opcion opcionCorrecta = new Opcion("opcion", Boolean.TRUE);
        Opcion opcionIncorrecta = new Opcion("opcion", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        Preguntable pregunta = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoClasico, "pregunta" ,opciones);

        Ronda ronda = new Ronda(jugadores, pregunta);

        Assertions.assertEquals(pregunta, ronda.obtenerPregunta());
        Assertions.assertEquals(jugadores, ronda.obtenerJugadores());
    }

    @Test
    public void debeResponderUnaPreguntaParaUnJugador() throws RondaSinPreguntaExcepcion, ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException {
        Jugador jugador1 = new Jugador("jugador1");
        Jugador jugador2 = new Jugador("jugador2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);

        Opcion opcionCorrecta = new Opcion("opcion", Boolean.TRUE);
        Opcion opcionIncorrecta = new Opcion("opcion", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        Preguntable pregunta = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoClasico, "pregunta" ,opciones);

        Ronda ronda = new Ronda(jugadores, pregunta);

        ronda.responder(jugador1, opciones);

        Assertions.assertEquals(1, jugador1.obtenerPuntos());
    }

    @Test
    public void debeLanzarExcepcionResponderConRespuestaNull() {
        Jugador jugador1 = new Jugador("jugador1");
        Jugador jugador2 = new Jugador("jugador2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);

        Opcion opcion = new Opcion("opcion", Boolean.TRUE);
        List<Opcion> opciones = Arrays.asList(opcion);

        Ronda ronda = new Ronda(jugadores, null);

        Assertions.assertThrows(RondaSinPreguntaExcepcion.class, () -> ronda.responder(jugador1, opciones));

    }

}
