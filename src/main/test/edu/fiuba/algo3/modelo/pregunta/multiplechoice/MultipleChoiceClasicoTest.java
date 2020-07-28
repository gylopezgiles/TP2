package edu.fiuba.algo3.modelo.pregunta.multiplechoice;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
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

public class MultipleChoiceClasicoTest {

    @Test
    public void crearMultipleChoiceClasicoConOpciones()throws ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException {
        //Given
        Opcion opcion1 = new Opcion("Esta Si", Boolean.TRUE);
        Opcion opcion2 = new Opcion("Esta NO", Boolean.FALSE);
        Opcion opcion3 = new Opcion("Esta Tampoco", Boolean.FALSE);
        Opcion opcion4 = new Opcion("Esta Menos", Boolean.FALSE);
        Opcion opcion5 = new Opcion("Nope", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5);
        String preguntaTexto = "pregunta?";

        //When
        Preguntable multipleChoiceClasico = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceClasico, preguntaTexto, opciones);

        //Then
        Assertions.assertEquals(5, multipleChoiceClasico.obtenerOpciones().size());
        Assertions.assertEquals(opciones, multipleChoiceClasico.obtenerOpciones());

    }

    @Test
    public void multipleChoiceDevuelveLaOpcionCorrecta()throws ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException{
        Opcion opcionCorrecta = new Opcion("Esta Si", Boolean.TRUE);
        Opcion opcion2 = new Opcion("Esta NO", Boolean.FALSE);
        Opcion opcion3 = new Opcion("Esta Tampoco", Boolean.FALSE);
        Opcion opcion4 = new Opcion("Esta Menos", Boolean.FALSE);
        Opcion opcion5 = new Opcion("Nope", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcion2, opcion3, opcion4, opcion5);
        String preguntaTexto = "pregunta?";

        //When
        Preguntable multipleChoiceClasico = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceClasico, preguntaTexto, opciones);

        //Then
        Assertions.assertEquals(opcionCorrecta, multipleChoiceClasico.obtenerOpcionCorrecta());

    }

    @Test
    public void noSePuedeCrearMultipleChoiceSinOpciones(){
        Assertions.assertThrows(ParametrosInvalidosExcepcion.class,
                               () -> new MultipleChoiceClasico("pregunta?", Collections.EMPTY_LIST));
    }

    @Test
    public void multipleChoiceDebeTenerUnaOpcionCorrecta(){
        Opcion opcion1 = new Opcion("opcion", Boolean.FALSE);
        Opcion opcion2 = new Opcion("opcion", Boolean.FALSE);
        Opcion opcion3 = new Opcion("opcion", Boolean.FALSE);
        Opcion opcion4 = new Opcion("opcion", Boolean.FALSE);
        Opcion opcion5 = new Opcion("opcion", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class,
                               () -> new MultipleChoiceClasico("pregunta?", opciones));
    }

    @Test
    public void multipleChoiceDebeTenerMasDeUnaOpcion(){
        Opcion opcion = new Opcion("opcion", Boolean.TRUE);
        List<Opcion> opciones = Arrays.asList(opcion);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class,
                               () -> new MultipleChoiceClasico("pregunta?", opciones));
    }

    @Test
    public void multipleChoiceNoDebeTenerMasDeCincoOpciones(){
        Opcion opcion1 = new Opcion("opcion", Boolean.TRUE);
        Opcion opcion2 = new Opcion("opcion", Boolean.FALSE);
        Opcion opcion3 = new Opcion("opcion", Boolean.FALSE);
        Opcion opcion4 = new Opcion("opcion", Boolean.FALSE);
        Opcion opcion5 = new Opcion("opcion", Boolean.FALSE);
        Opcion opcion6 = new Opcion("opcion", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5, opcion6);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class,
                               () -> new MultipleChoiceClasico("pregunta?", opciones));
    }
}

