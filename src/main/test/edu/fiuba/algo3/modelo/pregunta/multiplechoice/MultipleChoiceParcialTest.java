package edu.fiuba.algo3.modelo.pregunta.multiplechoice;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
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

}
