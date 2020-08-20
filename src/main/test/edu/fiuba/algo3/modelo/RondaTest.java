package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.CreadorPregunta;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
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
    public void debeResponderUnaPreguntaParaUnJugador() throws ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException {
        Jugador jugador1 = new Jugador("jugador1");
        List<Jugador> jugadores = Arrays.asList(jugador1);

        Opcion opcionCorrecta = new Opcion("opcion 1", Boolean.TRUE);
        Opcion opcionIncorrecta = new Opcion("opcion 2", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        Preguntable pregunta = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoClasico, "pregunta" ,opciones);
        List<String> opcionSeleccionada = Arrays.asList("opcion 1");

        Ronda ronda = new Ronda(jugadores, pregunta);

        ronda.responder(opcionSeleccionada, Multiplicador.PorDefecto, Boolean.FALSE);

        Assertions.assertEquals(1, jugador1.obtenerPuntos());
    }

    @Test
    public void debeLanzarExcepcionCrearRondaConRespuestaNull() {
        Jugador jugador1 = new Jugador("jugador1");
        Jugador jugador2 = new Jugador("jugador2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new Ronda(jugadores, null));

    }

    @Test
    public void debeLanzarExcepcionCrearRondaConJugadoresVacio() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion {
        Opcion opcionCorrecta = new Opcion("opcion 1", Boolean.TRUE);
        Opcion opcionIncorrecta = new Opcion("opcion 2", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        Preguntable pregunta = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoClasico, "pregunta" ,opciones);


        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new Ronda(Collections.emptyList(), pregunta));

    }

    @Test
    public void debeResponderUnaPreguntaParaUnJugadorConMultiplicadores() throws ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException {
        Jugador jugador1 = new Jugador("jugador1");
        Jugador jugador2 = new Jugador("jugador2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);

        Boolean esCorrecta = Boolean.TRUE;
        String textoPregunta = "La canción Renegade de Styx, fue lanzada en 1978";
        Opcion opcionCorrecta = new Opcion("Verdadero", esCorrecta);
        Opcion opcionIncorrecta = new Opcion("Falso", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        Preguntable pregunta = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoPenalidad, textoPregunta ,opciones);
        List<String> opcionSeleccionada1 = Arrays.asList("Verdadero");
        List<String> opcionSeleccionada2 = Arrays.asList("Verdadero");

        Ronda ronda = new Ronda(jugadores, pregunta);

        ronda.responder(opcionSeleccionada1, Multiplicador.PorDos, Boolean.FALSE);
        Boolean esRondaFinalizada = ronda.esRondaFinalizada();
        Jugador jugadorTurno = ronda.obtenerJugadorTurno();
        ronda.responder(opcionSeleccionada2, Multiplicador.PorDos, Boolean.FALSE);

        Assertions.assertEquals(2, jugador1.obtenerPuntos());
        Assertions.assertEquals(Boolean.FALSE, esRondaFinalizada);
        Assertions.assertEquals(jugador2, jugadorTurno);
    }

    @Test
    public void alRestablecerRondaSeRestablecenSusEstados() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion {
        Jugador jugador1 = new Jugador("jugador1");
        Jugador jugador2 = new Jugador("jugador2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);

        Boolean esCorrecta = Boolean.TRUE;

        String textoPregunta = "La canción Renegade de Styx, fue lanzada en 1978";
        Opcion opcionCorrecta = new Opcion("Verdadero", esCorrecta);
        Opcion opcionIncorrecta = new Opcion("Falso", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        Preguntable preguntaVerdaderoFalso = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoPenalidad, textoPregunta ,opciones);

        Ronda ronda = new Ronda(jugadores, preguntaVerdaderoFalso);

        Assertions.assertEquals(preguntaVerdaderoFalso, ronda.obtenerPregunta());

        String preguntaTexto = "Cuáles de los siguientes artistas interpretaron la canción Proud Mary";
        Opcion opcionCorrecta1 = new Opcion("Creedence Clearwater Revival", esCorrecta);
        Opcion opcionCorrecta2 = new Opcion("Tina Turner", esCorrecta);
        Opcion opcionIncorrecta3 = new Opcion("Radiohead", !esCorrecta);
        List<Opcion> opcionesMultipleChoice = Arrays.asList(opcionCorrecta1, opcionCorrecta2, opcionIncorrecta3);
        Preguntable multipleChoiceClasico = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceClasico, preguntaTexto, opcionesMultipleChoice);

        ronda.restablecerRonda(multipleChoiceClasico);

        Assertions.assertEquals(multipleChoiceClasico, ronda.obtenerPregunta());

    }

    @Test
    public void respondeHastaFinalizarRonda() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion {
        Jugador jugador1 = new Jugador("jugador1");
        Jugador jugador2 = new Jugador("jugador2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);

        Boolean esCorrecta = Boolean.TRUE;

        String textoPregunta = "La canción Renegade de Styx, fue lanzada en 1978";
        Opcion opcionCorrecta = new Opcion("Verdadero", esCorrecta);
        Opcion opcionIncorrecta = new Opcion("Falso", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        Preguntable preguntaVerdaderoFalso = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoPenalidad, textoPregunta ,opciones);

        Ronda ronda = new Ronda(jugadores, preguntaVerdaderoFalso);

        Assertions.assertEquals(Boolean.FALSE, ronda.esRondaFinalizada());

        ronda.responder(Arrays.asList("Verdadero"), Multiplicador.PorDefecto, Boolean.FALSE);

        Assertions.assertEquals(Boolean.FALSE, ronda.esRondaFinalizada());

        ronda.responder(Arrays.asList("Falso"), Multiplicador.PorDefecto, Boolean.FALSE);

        Assertions.assertEquals(Boolean.TRUE, ronda.esRondaFinalizada());

    }

}
