package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.exclusividad.Exclusividad;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
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
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("jugador1", exclusividad);
        Jugador jugador2 = new Jugador("jugador2", exclusividad);
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
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("jugador1", exclusividad);
        Jugador jugador2 = new Jugador("jugador2", exclusividad);
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);

        Opcion opcionCorrecta = new Opcion("opcion", Boolean.TRUE);
        Opcion opcionIncorrecta = new Opcion("opcion", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        Preguntable pregunta = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoClasico, "pregunta" ,opciones);

        Ronda ronda = new Ronda(jugadores, pregunta);

        ronda.responder(opciones);

        Assertions.assertEquals(1, jugador1.obtenerPuntos());
    }

    @Test
    public void debeLanzarExcepcionResponderConRespuestaNull() {
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("jugador1", exclusividad);
        Jugador jugador2 = new Jugador("jugador2", exclusividad);
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);

        Opcion opcion = new Opcion("opcion", Boolean.TRUE);
        List<Opcion> opciones = Arrays.asList(opcion);

        Ronda ronda = new Ronda(jugadores, null);

        Assertions.assertThrows(RondaSinPreguntaExcepcion.class, () -> ronda.responder(opciones));

    }

    @Test
    public void debeResponderUnaPreguntaParaUnJugadorConMultiplicadores() throws RondaSinPreguntaExcepcion, ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException {
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("jugador1", exclusividad);
        Jugador jugador2 = new Jugador("jugador2", exclusividad);
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);

        Boolean esCorrecta = Boolean.TRUE;
        String textoPregunta = "La canción Renegade de Styx, fue lanzada en 1978";
        Opcion opcionCorrecta = new Opcion("Verdadero", esCorrecta);
        Opcion opcionIncorrecta = new Opcion("Falso", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        Preguntable pregunta = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoPenalidad, textoPregunta ,opciones);

        Ronda ronda = new Ronda(jugadores, pregunta);

        ronda.responder(opciones, Multiplicador.PorDos);

        Assertions.assertEquals(2, jugador1.obtenerPuntos());
        Assertions.assertEquals(Boolean.FALSE, ronda.esRondaFinalizada());
        Assertions.assertEquals(ronda.obtenerJugadorTurno(), jugador2);
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
    public void respondeHastaFinalizarRonda() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
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

        ronda.responder(Arrays.asList(opcionCorrecta));

        Assertions.assertEquals(Boolean.FALSE, ronda.esRondaFinalizada());

        ronda.responder(Arrays.asList(opcionIncorrecta));

        Assertions.assertEquals(Boolean.TRUE, ronda.esRondaFinalizada());

    }

}
