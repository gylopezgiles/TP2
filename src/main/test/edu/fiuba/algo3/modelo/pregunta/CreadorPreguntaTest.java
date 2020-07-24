package edu.fiuba.algo3.modelo.pregunta;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.excepciones.TipoPreguntaNoImplementadaException;
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
        Preguntable preguntaEsperada = new VerdaderoFalsoClasico(preguntaTexto, opciones);

        Preguntable pregunta = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoClasico, preguntaTexto, opciones);

        Assertions.assertEquals(preguntaEsperada.obtenerOpciones(), pregunta.obtenerOpciones());
        Assertions.assertEquals(preguntaEsperada.obtenerPregunta(), pregunta.obtenerPregunta());
    }

    @Test
    public void debeLanzarErrorCrearTipoPreguntaNoImplementada() {

        Assertions.assertThrows(TipoPreguntaNoImplementadaException.class, () -> CreadorPregunta.crearPregunta(TipoPregunta.Otro, "pregunta?", Collections.EMPTY_LIST));

    }

}
