package edu.fiuba.algo3.modelo.pregunta.multiplechoice;

import edu.fiuba.algo3.modelo.excepciones.MultiplicadorExcepcion;
import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
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
    public void debeEstablecerPuntuacionCorrectamenteEligiendoTodasRespuestasCorrectasSinIncorrectas() throws ParametrosInvalidosExcepcion, MultiplicadorExcepcion {
        Opcion opcion1Correcta = new Opcion("opcion 1", Boolean.TRUE);
        Opcion opcion2Correcta = new Opcion("opcion 2", Boolean.TRUE);
        Opcion opcion3Correcta = new Opcion("opcion 3", Boolean.TRUE);
        Opcion opcion4Incorrecta = new Opcion("opcion 4", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Correcta, opcion4Incorrecta);
        String preguntaTexto = "pregunta?";
        Preguntable multipleChoiceParcial = new MultipleChoiceParcial(preguntaTexto, opciones);
        List<Opcion> opcionesSeleccionadas = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Correcta);

        int puntuacion = multipleChoiceParcial.establecerPuntuacion(opcionesSeleccionadas);

        Assertions.assertEquals(3, puntuacion);
    }

    @Test
    public void debeEstablecerPuntuacionCorrectamenteEligiendoAlgunasRespuestasCorrectasSinIncorrectas() throws ParametrosInvalidosExcepcion, MultiplicadorExcepcion {
        Opcion opcion1Correcta = new Opcion("opcion 1", Boolean.TRUE);
        Opcion opcion2Correcta = new Opcion("opcion 2", Boolean.TRUE);
        Opcion opcion3Correcta = new Opcion("opcion 3", Boolean.TRUE);
        Opcion opcion4Incorrecta = new Opcion("opcion 4", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Correcta, opcion4Incorrecta);
        String preguntaTexto = "pregunta?";
        Preguntable multipleChoiceParcial = new MultipleChoiceParcial(preguntaTexto, opciones);
        List<Opcion> opcionesSeleccionadas = Arrays.asList(opcion1Correcta, opcion3Correcta);

        int puntuacion = multipleChoiceParcial.establecerPuntuacion(opcionesSeleccionadas);

        Assertions.assertEquals(2, puntuacion);
    }

    @Test
    public void debeEstablecerPuntuacionCorrectamenteEligiendoAlgunasRespuestasCorrectasYUnaIncorrecta() throws ParametrosInvalidosExcepcion, MultiplicadorExcepcion {
        Opcion opcion1Correcta = new Opcion("opcion 1", Boolean.TRUE);
        Opcion opcion2Correcta = new Opcion("opcion 2", Boolean.TRUE);
        Opcion opcion3Correcta = new Opcion("opcion 3", Boolean.TRUE);
        Opcion opcion4Incorrecta = new Opcion("opcion 4", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Correcta, opcion4Incorrecta);
        String preguntaTexto = "pregunta?";
        Preguntable multipleChoiceParcial = new MultipleChoiceParcial(preguntaTexto, opciones);
        List<Opcion> opcionesSeleccionadas = Arrays.asList(opcion1Correcta, opcion3Correcta, opcion4Incorrecta);

        int puntuacion = multipleChoiceParcial.establecerPuntuacion(opcionesSeleccionadas);

        Assertions.assertEquals(0, puntuacion);
    }

    @Test
    public void alAplicarMultiplicadorDebeLanzarUnaExcepcion() throws ParametrosInvalidosExcepcion {
        String preguntaTexto = "La canción Sinnerman corresponde a qué artista";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta1 = new Opcion("Nina Simone", esCorrecta);
        Opcion opcionIncorrecta2 = new Opcion("Tom Ellis", !esCorrecta);
        Opcion opcionIncorrecta3 = new Opcion("Ella Fitzgerald", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta1, opcionIncorrecta2, opcionIncorrecta3);
        List<Opcion> opcionesSeleccionadas = Arrays.asList(opcionCorrecta1, opcionIncorrecta2, opcionIncorrecta3);

        Preguntable multipleChoiceParcial = new MultipleChoiceParcial(preguntaTexto, opciones);

        Assertions.assertThrows(MultiplicadorExcepcion.class, () -> multipleChoiceParcial.establecerPuntuacion(opcionesSeleccionadas, Multiplicador.PorDos));

    }

    @Test
    public void obtenerOpcionesPorNombreTest() throws ParametrosInvalidosExcepcion {
        String preguntaTexto = "Cuáles de los siguientes artistas interpretaron la canción Proud Mary";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta1 = new Opcion("Creedence Clearwater Revival", esCorrecta);
        Opcion opcionCorrecta2 = new Opcion("Tina Turner", esCorrecta);
        Opcion opcionIncorrecta3 = new Opcion("Radiohead", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta1, opcionCorrecta2, opcionIncorrecta3);
        List<String> opcionesPorNombre = Arrays.asList("Creedence Clearwater Revival", "Tina Turner", "Radiohead");
        Preguntable multipleChoiceParcial = new MultipleChoiceParcial(preguntaTexto, opciones);

        List<Opcion> opcionesObtenidas = multipleChoiceParcial.obtenerOpcionesPorNombre(opcionesPorNombre);

        Assertions.assertEquals(opciones, opcionesObtenidas);
    }

}
