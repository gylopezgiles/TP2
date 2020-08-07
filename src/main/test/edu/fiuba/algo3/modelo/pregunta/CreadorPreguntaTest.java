package edu.fiuba.algo3.modelo.pregunta;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.excepciones.TipoPreguntaNoImplementadaException;
import edu.fiuba.algo3.modelo.pregunta.multiplechoice.MultipleChoiceClasico;
import edu.fiuba.algo3.modelo.pregunta.multiplechoice.MultipleChoiceConPenalidad;
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

        String preguntaTexto = "¿Los Elefantes son los mamiferos TERRESTRES mas grandes del mundo?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta = new Opcion("Verdadero", esCorrecta);
        Opcion opcionIncorrecta = new Opcion("Falso", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        VerdaderoFalsoClasico preguntaEsperada = new VerdaderoFalsoClasico(preguntaTexto, opciones);

        Preguntable pregunta = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoClasico, preguntaTexto, opciones);

        Assertions.assertEquals(preguntaEsperada.getClass(), pregunta.getClass());

    }

    @Test
    public void debeCrearPreguntaVerdaderoFalsoPenalidad() throws ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException {

        String preguntaTexto = "¿Sydney es la capital de Australia?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta = new Opcion("Falso", esCorrecta);
        Opcion opcionIncorrecta = new Opcion("Verdadero", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        VerdaderoFalsoPenalidad preguntaEsperada = new VerdaderoFalsoPenalidad(preguntaTexto, opciones);

        Preguntable pregunta = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoPenalidad, preguntaTexto, opciones);

        Assertions.assertEquals(preguntaEsperada.getClass(), pregunta.getClass());
    }

    @Test
    public void debeCrearPreguntaMultipleChoiceClasico() throws ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException {

        String preguntaTexto = "¿Donde nacio Simon Bolivar?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta = new Opcion("Venezuela", esCorrecta);
        Opcion opcion2 = new Opcion("España", !esCorrecta);
        Opcion opcion3 = new Opcion("Colombia", !esCorrecta);
        Opcion opcion4 = new Opcion("Bolivia", !esCorrecta);
        Opcion opcion5 = new Opcion("Ecuador", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcion2, opcion3, opcion4, opcion5);
        MultipleChoiceClasico preguntaEsperada = new MultipleChoiceClasico(preguntaTexto, opciones);

        Preguntable pregunta = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceClasico, preguntaTexto, opciones);

        Assertions.assertEquals(preguntaEsperada.getClass(), pregunta.getClass());

    }

    @Test
    public void debeCrearPreguntaMultipleChoiceParcial() throws ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException {

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("Auckland", esCorrecta);
        Opcion opcion2Correcta = new Opcion("Wellington", esCorrecta);
        Opcion opcion3Incorrecta = new Opcion("Canberra", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta);
        MultipleChoiceParcial preguntaEsperada = new MultipleChoiceParcial(preguntaTexto, opciones);

        Preguntable pregunta = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceParcial, preguntaTexto, opciones);

        Assertions.assertEquals(preguntaEsperada.getClass(), pregunta.getClass());

    }

    @Test
    public void debeCrearPreguntaGroupChoice() throws ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException {

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";
        Boolean pertenece = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenece);
        Opcion opcion2 = new Opcion("Wellington", pertenece);
        Opcion opcion3 = new Opcion("Hamilton", pertenece);
        Opcion opcion4 = new Opcion("Canberra", !pertenece);
        Opcion opcion5 = new Opcion("Hawaii", !pertenece);
        Opcion opcion6 = new Opcion("Oslo", !pertenece);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5, opcion6);
        GroupChoice preguntaEsperada = new GroupChoice(preguntaTexto, opciones);

        Preguntable pregunta = CreadorPregunta.crearPregunta(TipoPregunta.GroupChoice, preguntaTexto, opciones);

        Assertions.assertEquals(preguntaEsperada.getClass(), pregunta.getClass());

    }

    @Test
    public void debeCrearPreguntaMultipleChoiceConPenalidad() throws ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException {
        String preguntaTexto = "Cuál es la capital de Uruguay?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta = new Opcion("Montevideo", esCorrecta);
        Opcion opcionIncorrecta = new Opcion("Asunción", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);

        Preguntable pregunta = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceConPenalidad, preguntaTexto, opciones);

        Assertions.assertEquals(MultipleChoiceConPenalidad.class, pregunta.getClass());
    }

    @Test
    public void debeCrearPreguntaOrderedChoice() throws ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException {
        String preguntaTexto = "Orden de las letras vocales";
        boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1 = new Opcion("A", esCorrecta);
        Opcion opcion2 = new Opcion("E",esCorrecta);
        Opcion opcion3 = new Opcion("I", esCorrecta);
        Opcion opcion4 = new Opcion("O", esCorrecta);
        Opcion opcion5 = new Opcion("U", esCorrecta);

        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5);

        Preguntable pregunta = CreadorPregunta.crearPregunta(TipoPregunta.OrderedChoice, preguntaTexto, opciones);

        Assertions.assertEquals(OrderedChoice.class, pregunta.getClass());
    }

    @Test
    public void debeLanzarErrorCrearTipoPreguntaNoImplementada() {

        Assertions.assertThrows(TipoPreguntaNoImplementadaException.class, () -> CreadorPregunta.crearPregunta(TipoPregunta.Otro, "pregunta?", Collections.EMPTY_LIST));

    }

}
