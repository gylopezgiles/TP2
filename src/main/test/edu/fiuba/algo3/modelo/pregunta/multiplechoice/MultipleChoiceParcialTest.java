package edu.fiuba.algo3.modelo.pregunta.multiplechoice;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Ronda;
import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.excepciones.RondaSinPreguntaExcepcion;
import edu.fiuba.algo3.modelo.excepciones.TipoPreguntaNoImplementadaException;
import edu.fiuba.algo3.modelo.pregunta.CreadorPregunta;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MultipleChoiceParcialTest {

    @Test
    public void debeCrearUnaPreguntaMultipleChoiceParcialConOpciones() throws ParametrosInvalidosExcepcion {
        Opcion opcion1Correcta = new Opcion("opcion 1", Boolean.TRUE);
        Opcion opcion2Correcta = new Opcion("opcion 2", Boolean.TRUE);
        Opcion opcion3Incorrecta = new Opcion("opcion 3", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta);
        String preguntaTexto = "pregunta?";

        Preguntable multipleChoiceParcial = new MultipleChoiceParcial(preguntaTexto, opciones);

        Assertions.assertEquals(opciones, multipleChoiceParcial.obtenerOpciones());
        Assertions.assertEquals(preguntaTexto, multipleChoiceParcial.obtenerPregunta());
    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaSinOpciones() {

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new MultipleChoiceParcial("pregunta?", Collections.EMPTY_LIST));

    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaConCantidadOpcionesMenorA2() {

        Opcion opcion = new Opcion("opcion", Boolean.TRUE);
        List<Opcion> opciones = Arrays.asList(opcion);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new MultipleChoiceParcial("pregunta?", opciones));

    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaConCantidadOpcionesMayorA5() {

        Opcion opcion1 = new Opcion("opcion", Boolean.TRUE);
        Opcion opcion2 = new Opcion("opcion", Boolean.FALSE);
        Opcion opcion3 = new Opcion("opcion", Boolean.TRUE);
        Opcion opcion4 = new Opcion("opcion", Boolean.TRUE);
        Opcion opcion5 = new Opcion("opcion", Boolean.FALSE);
        Opcion opcion6 = new Opcion("opcion", Boolean.TRUE);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5, opcion6);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new MultipleChoiceParcial("pregunta?", opciones));

    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaSinOpcionesCorrectas() {
        Opcion opcion1 = new Opcion("opcion", Boolean.FALSE);
        Opcion opcion2 = new Opcion("opcion", Boolean.FALSE);
        Opcion opcion3 = new Opcion("opcion", Boolean.FALSE);
        Opcion opcion4 = new Opcion("opcion", Boolean.FALSE);
        Opcion opcion5 = new Opcion("opcion", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new MultipleChoiceParcial("pregunta?", opciones));

    }

    @Test
    public void preguntaMultipleChoiceParcialAsignaPuntajeCorrectamenteEligiendoTodasCorrectasSinIncorrectas() throws ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion, TipoPreguntaNoImplementadaException {
        Opcion opcion1Correcta = new Opcion("opcion 1", Boolean.TRUE);
        Opcion opcion2Correcta = new Opcion("opcion 2", Boolean.TRUE);
        Opcion opcion3Incorrecta = new Opcion("opcion 3", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta);
        String preguntaTexto = "pregunta?";
        Preguntable multipleChoiceParcial = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceParcial, preguntaTexto, opciones);
        List<Opcion> opcionesSeleccionadas = Arrays.asList(opcion1Correcta, opcion2Correcta);
        Jugador jugador = new Jugador("jugador");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, multipleChoiceParcial);

        ronda.responder(jugador, opcionesSeleccionadas);

        Assertions.assertEquals(2, jugador.obtenerPuntos());
    }

    @Test
    public void preguntaMultipleChoiceParcialAsignaPuntajeCorrectamenteEligiendoAlgunasCorrectasSinIncorrectas() throws ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion, TipoPreguntaNoImplementadaException {
        Opcion opcion1Correcta = new Opcion("opcion 1", Boolean.TRUE);
        Opcion opcion2Correcta = new Opcion("opcion 2", Boolean.TRUE);
        Opcion opcion3Incorrecta = new Opcion("opcion 3", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta);
        String preguntaTexto = "pregunta?";
        Preguntable multipleChoiceParcial = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceParcial, preguntaTexto, opciones);
        List<Opcion> opcionesSeleccionadas = Arrays.asList(opcion1Correcta);
        Jugador jugador = new Jugador("jugador");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, multipleChoiceParcial);

        ronda.responder(jugador, opcionesSeleccionadas);

        Assertions.assertEquals(1, jugador.obtenerPuntos());
    }

    @Test
    public void preguntaMultipleChoiceParcialAsignaPuntajeCorrectamenteEligiendoIncorrecta() throws ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion, TipoPreguntaNoImplementadaException {
        Opcion opcion1Correcta = new Opcion("opcion 1", Boolean.TRUE);
        Opcion opcion2Correcta = new Opcion("opcion 2", Boolean.TRUE);
        Opcion opcion3Incorrecta = new Opcion("opcion 3", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta);
        String preguntaTexto = "pregunta?";
        Preguntable multipleChoiceParcial = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceParcial, preguntaTexto, opciones);
        List<Opcion> opcionesSeleccionadas = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta);
        Jugador jugador = new Jugador("jugador");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, multipleChoiceParcial);

        ronda.responder(jugador, opcionesSeleccionadas);

        Assertions.assertEquals(0, jugador.obtenerPuntos());
    }

}
