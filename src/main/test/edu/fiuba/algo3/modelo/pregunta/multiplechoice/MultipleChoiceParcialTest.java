package edu.fiuba.algo3.modelo.pregunta.multiplechoice;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
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
        String preguntaTexto = "¿Que ciudades pertenecen a Nueva Zelanda?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("Auckland", esCorrecta);
        Opcion opcion2Correcta = new Opcion("Wellington", esCorrecta);
        Opcion opcion3Incorrecta = new Opcion("Canberra", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta);

        Preguntable multipleChoiceParcial = new MultipleChoiceParcial(preguntaTexto, opciones);

        Assertions.assertEquals(opciones, multipleChoiceParcial.obtenerOpciones());
        Assertions.assertEquals(preguntaTexto, multipleChoiceParcial.obtenerPregunta());
        Assertions.assertEquals(TipoPregunta.MultipleChoiceParcial, multipleChoiceParcial.obtenerTipoPregunta());
    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaSinOpciones() {

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new MultipleChoiceParcial("¿pregunta?", Collections.EMPTY_LIST));

    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaConCantidadOpcionesMenorA2() {

        String textoPregunta = "¿Te gusta el Helado?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion = new Opcion("Si", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new MultipleChoiceParcial(textoPregunta, opciones));

    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaConCantidadOpcionesMayorA5() {

        String preguntaTexto = "¿Cuales de estos son numeros Naturales?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1 = new Opcion("1", esCorrecta);
        Opcion opcion2 = new Opcion("-2", !esCorrecta);
        Opcion opcion3 = new Opcion("-3", !esCorrecta);
        Opcion opcion4 = new Opcion("-4", !esCorrecta);
        Opcion opcion5 = new Opcion("-5", !esCorrecta);
        Opcion opcion6 = new Opcion("-6", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5, opcion6);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new MultipleChoiceParcial(preguntaTexto, opciones));

    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaSinOpcionesCorrectas() {
        String preguntaTexto = "¿Cuales de estos son numeros Naturales?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1 = new Opcion("-1", !esCorrecta);
        Opcion opcion2 = new Opcion("-2", !esCorrecta);
        Opcion opcion3 = new Opcion("-3", !esCorrecta);
        Opcion opcion4 = new Opcion("-4", !esCorrecta);
        Opcion opcion5 = new Opcion("-5", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new MultipleChoiceParcial(preguntaTexto, opciones));

    }

    @Test
    public void debeEstablecerPuntuacionCorrectamenteEligiendoTodasRespuestasCorrectasSinIncorrectas() throws ParametrosInvalidosExcepcion {

        String preguntaTexto = "¿Cuales son gases nobles?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("Helio", esCorrecta);
        Opcion opcion2Correcta = new Opcion("Neon", esCorrecta);
        Opcion opcion3Correcta = new Opcion("Kripton", esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("Oxigeno", !esCorrecta);

        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Correcta, opcion4Incorrecta);

        Preguntable multipleChoiceParcial = new MultipleChoiceParcial(preguntaTexto, opciones);
        List<String> opcionesSeleccionadas = Arrays.asList("Helio", "Neon", "Kripton");

        int puntuacion = multipleChoiceParcial.establecerPuntuacion(opcionesSeleccionadas);

        Assertions.assertEquals(3, puntuacion);
    }

    @Test
    public void debeEstablecerPuntuacionCorrectamenteEligiendoAlgunasRespuestasCorrectasSinIncorrectas() throws ParametrosInvalidosExcepcion {

        String preguntaTexto = "¿Cuales son gases nobles?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("Helio", esCorrecta);
        Opcion opcion2Correcta = new Opcion("Neon", esCorrecta);
        Opcion opcion3Correcta = new Opcion("Kripton", esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("Oxigeno", !esCorrecta);

        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Correcta, opcion4Incorrecta);

        Preguntable multipleChoiceParcial = new MultipleChoiceParcial(preguntaTexto, opciones);
        List<String> opcionesSeleccionadas = Arrays.asList("Helio", "Kripton");

        int puntuacion = multipleChoiceParcial.establecerPuntuacion(opcionesSeleccionadas);

        Assertions.assertEquals(2, puntuacion);
    }

    @Test
    public void debeEstablecerPuntuacionCorrectamenteEligiendoAlgunasRespuestasCorrectasYUnaIncorrecta() throws ParametrosInvalidosExcepcion {

        String preguntaTexto = "¿Cuales son gases nobles?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("Helio", esCorrecta);
        Opcion opcion2Correcta = new Opcion("Neon", esCorrecta);
        Opcion opcion3Correcta = new Opcion("Kripton", esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("Oxigeno", !esCorrecta);

        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Correcta, opcion4Incorrecta);

        Preguntable multipleChoiceParcial = new MultipleChoiceParcial(preguntaTexto, opciones);
        List<String> opcionesSeleccionadas = Arrays.asList("Helio", "Kripton", "Oxigeno");

        int puntuacion = multipleChoiceParcial.establecerPuntuacion(opcionesSeleccionadas);

        Assertions.assertEquals(0, puntuacion);
    }

    @Test
    public void alEstablecerPuntuacionConMultiplicadorNoLoDebeAplicar() throws ParametrosInvalidosExcepcion {
        String preguntaTexto = "La canción Sinnerman corresponde a qué artista";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta1 = new Opcion("Nina Simone", esCorrecta);
        Opcion opcionIncorrecta2 = new Opcion("Tom Ellis", !esCorrecta);
        Opcion opcionIncorrecta3 = new Opcion("Ella Fitzgerald", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta1, opcionIncorrecta2, opcionIncorrecta3);
        List<String> opcionesSeleccionadas = Arrays.asList("Nina Simone");

        Preguntable multipleChoiceParcial = new MultipleChoiceParcial(preguntaTexto, opciones);

        Assertions.assertEquals(1, multipleChoiceParcial.establecerPuntuacion(opcionesSeleccionadas, Multiplicador.PorDos));
        Assertions.assertEquals(1, multipleChoiceParcial.establecerPuntuacion(opcionesSeleccionadas, Multiplicador.PorTres));

    }

}
