package edu.fiuba.algo3.modelo.entrega;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Ronda;
import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.excepciones.RondaSinPreguntaExcepcion;
import edu.fiuba.algo3.modelo.excepciones.TipoPreguntaNoImplementadaException;
import edu.fiuba.algo3.modelo.pregunta.CreadorPregunta;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;
import edu.fiuba.algo3.modelo.pregunta.multiplechoice.MultipleChoiceConPenalidad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class Entrega2Test {

    @Test
    public void crearMultipleChoiceConPenalidadConOpciones() throws ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException {
        //Given
        String preguntaTexto = "Cuál es la capital de Ecuador?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta = new Opcion("Quito", esCorrecta);
        Opcion opcionIncorrecta = new Opcion("Sucre", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);

        //When
        Preguntable pregunta = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceConPenalidad, preguntaTexto, opciones);

        //Then
        Assertions.assertEquals(MultipleChoiceConPenalidad.class, pregunta.getClass());
    }

    @Test
    public void preguntaMultipleChoiceConPenalidadAsignaPuntajeCorrectamenteEligiendoTodasCorrectas() throws ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion, TipoPreguntaNoImplementadaException {
        //Given
        String preguntaTexto = "Qué países se encuentran en Asia?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("China", esCorrecta);
        Opcion opcion2Correcta = new Opcion("India", esCorrecta);
        Opcion opcion3Correcta = new Opcion("Japón", esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("España", !esCorrecta);
        Opcion opcion5Correcta = new Opcion("Tailandia", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Correcta, opcion4Incorrecta, opcion5Correcta);
        Preguntable multipleChoiceConPenalidad = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceConPenalidad, preguntaTexto, opciones);

        List<Opcion> opcionesSeleccionadas = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Correcta, opcion5Correcta);
        Jugador jugador = new Jugador("jugador");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, multipleChoiceConPenalidad);

        //When
        ronda.responder(jugador, opcionesSeleccionadas);

        //Then
        Assertions.assertEquals(4, jugador.obtenerPuntos());
    }

    @Test
    public void preguntaMultipleChoiceConPenalidadAsignaPuntajeCorrectamenteEligiendoAlgunasCorrectas() throws ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion, TipoPreguntaNoImplementadaException {
        //Given
        String preguntaTexto = "Qué países se encuentran en Asia?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("China", esCorrecta);
        Opcion opcion2Correcta = new Opcion("India", esCorrecta);
        Opcion opcion3Correcta = new Opcion("Japón", esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("España", !esCorrecta);
        Opcion opcion5Correcta = new Opcion("Tailandia", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Correcta, opcion4Incorrecta, opcion5Correcta);
        Preguntable multipleChoiceConPenalidad = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceConPenalidad, preguntaTexto, opciones);

        List<Opcion> opcionesSeleccionadas = Arrays.asList(opcion1Correcta, opcion3Correcta);
        Jugador jugador = new Jugador("jugador");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, multipleChoiceConPenalidad);

        //When
        ronda.responder(jugador, opcionesSeleccionadas);

        //Then
        Assertions.assertEquals(2, jugador.obtenerPuntos());
    }

    @Test
    public void preguntaMultipleChoiceConPenalidadAsignaPuntajeCorrectamenteEligiendoAlgunasCorrectasYUnaIncorrecta() throws ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion, TipoPreguntaNoImplementadaException {
        //Given
        String preguntaTexto = "Qué países se encuentran en Asia?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("China", esCorrecta);
        Opcion opcion2Correcta = new Opcion("India", esCorrecta);
        Opcion opcion3Correcta = new Opcion("Japón", esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("España", !esCorrecta);
        Opcion opcion5Correcta = new Opcion("Tailandia", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Correcta, opcion4Incorrecta, opcion5Correcta);
        Preguntable multipleChoiceConPenalidad = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceConPenalidad, preguntaTexto, opciones);

        List<Opcion> opcionesSeleccionadas = Arrays.asList(opcion1Correcta, opcion3Correcta, opcion4Incorrecta);
        Jugador jugador = new Jugador("jugador");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, multipleChoiceConPenalidad);

        //When
        ronda.responder(jugador, opcionesSeleccionadas);

        //Then
        Assertions.assertEquals(-1, jugador.obtenerPuntos());
    }

    @Test
    public void preguntaMultipleChoiceConPenalidadAsignaPuntajeCorrectamenteEligiendoIncorrectas() throws ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion, TipoPreguntaNoImplementadaException {
        //Given
        String preguntaTexto = "Qué países se encuentran en Asia?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("China", esCorrecta);
        Opcion opcion2Incorrecta = new Opcion("Portugal", !esCorrecta);
        Opcion opcion3Correcta = new Opcion("Japón", esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("España", !esCorrecta);
        Opcion opcion5Incorrecta = new Opcion("Francia", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Incorrecta, opcion3Correcta, opcion4Incorrecta, opcion5Incorrecta);
        Preguntable multipleChoiceConPenalidad = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceConPenalidad, preguntaTexto, opciones);

        List<Opcion> opcionesSeleccionadas = Arrays.asList(opcion2Incorrecta, opcion4Incorrecta, opcion5Incorrecta);
        Jugador jugador = new Jugador("jugador");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, multipleChoiceConPenalidad);

        //When
        ronda.responder(jugador, opcionesSeleccionadas);

        //Then
        Assertions.assertEquals(-3, jugador.obtenerPuntos());
    }

}
