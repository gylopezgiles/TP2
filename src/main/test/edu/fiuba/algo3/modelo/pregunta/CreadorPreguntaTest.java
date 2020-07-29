package edu.fiuba.algo3.modelo.pregunta;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.excepciones.TipoPreguntaNoImplementadaException;
import edu.fiuba.algo3.modelo.pregunta.multiplechoice.MultipleChoiceClasico;
import edu.fiuba.algo3.modelo.pregunta.multiplechoice.MultipleChoiceParcial;
import edu.fiuba.algo3.modelo.pregunta.verdaderofalso.VerdaderoFalsoClasico;
import edu.fiuba.algo3.modelo.pregunta.verdaderofalso.VerdaderoFalsoPenalidad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CreadorPreguntaTest {

    @Test
    public void debeCrearPreguntaVerdaderoFalsoClasico() throws ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException {
        Opcion opcionCorrecta = new Opcion("opcion", Boolean.TRUE);
        Opcion opcionIncorrecta = new Opcion("opcion", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        String preguntaTexto = "pregunta?";
        VerdaderoFalsoClasico preguntaEsperada = new VerdaderoFalsoClasico(preguntaTexto, opciones);

        Preguntable pregunta = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoClasico, preguntaTexto, opciones);

        Assertions.assertEquals(preguntaEsperada.getClass(), pregunta.getClass());
        Assertions.assertEquals(preguntaEsperada.obtenerOpciones(), pregunta.obtenerOpciones());
        Assertions.assertEquals(preguntaEsperada.obtenerPregunta(), pregunta.obtenerPregunta());
    }

    @Test
    public void debeCrearPreguntaVerdaderoFalsoPenalidad() throws ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException {
        Opcion opcionCorrecta = new Opcion("opcion", Boolean.TRUE);
        Opcion opcionIncorrecta = new Opcion("opcion", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        String preguntaTexto = "pregunta?";
        VerdaderoFalsoPenalidad preguntaEsperada = new VerdaderoFalsoPenalidad(preguntaTexto, opciones);

        Preguntable pregunta = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoPenalidad, preguntaTexto, opciones);

        Assertions.assertEquals(preguntaEsperada.getClass(), pregunta.getClass());
        Assertions.assertEquals(preguntaEsperada.obtenerOpciones(), pregunta.obtenerOpciones());
        Assertions.assertEquals(preguntaEsperada.obtenerPregunta(), pregunta.obtenerPregunta());
    }

    @Test
    public void debeCrearPreguntaMultipleChoiceClasico() throws ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException {
        Opcion opcionCorrecta = new Opcion("opcion", Boolean.TRUE);
        Opcion opcionIncorrecta = new Opcion("opcion", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        String preguntaTexto = "pregunta?";
        MultipleChoiceClasico preguntaEsperada = new MultipleChoiceClasico(preguntaTexto, opciones);

        Preguntable pregunta = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceClasico, preguntaTexto, opciones);

        Assertions.assertEquals(preguntaEsperada.getClass(), pregunta.getClass());
        Assertions.assertEquals(preguntaEsperada.obtenerOpciones(), pregunta.obtenerOpciones());
        Assertions.assertEquals(preguntaEsperada.obtenerPregunta(), pregunta.obtenerPregunta());
    }

    @Test
    public void debeCrearPreguntaMultipleChoiceParcial() throws ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException {
        Opcion opcionCorrecta = new Opcion("opcion", Boolean.TRUE);
        Opcion opcionIncorrecta = new Opcion("opcion", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        String preguntaTexto = "pregunta?";
        MultipleChoiceParcial preguntaEsperada = new MultipleChoiceParcial(preguntaTexto, opciones);

        Preguntable pregunta = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceParcial, preguntaTexto, opciones);

        Assertions.assertEquals(preguntaEsperada.getClass(), pregunta.getClass());
        Assertions.assertEquals(preguntaEsperada.obtenerOpciones(), pregunta.obtenerOpciones());
        Assertions.assertEquals(preguntaEsperada.obtenerPregunta(), pregunta.obtenerPregunta());
    }

    @Test
    public void debeLanzarErrorCrearTipoPreguntaNoImplementada() {

        Assertions.assertThrows(TipoPreguntaNoImplementadaException.class, () -> CreadorPregunta.crearPregunta(TipoPregunta.Otro, "pregunta?", Collections.EMPTY_LIST));

    }

}
