package edu.fiuba.algo3.modelo.pregunta.cargador;

import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CargadorPreguntasTest {

    //Este archivo contiene 4 preguntas bien armadas y 2 preguntas con fallas (1 parametros invalidos, 1 pregunta no implementada)
    private static final String RUTA_PREGUNTAS_TEST = "src/main/resources/test/preguntas_test.json";

    @Test
    public void debeCargarPreguntasDeArchivo() {
        CargadorPreguntas cargadorPreguntas = CargadorPreguntas.obtenerInstancia(RUTA_PREGUNTAS_TEST);

        List<Preguntable> preguntas = cargadorPreguntas.obtenerPreguntas();

        Assertions.assertEquals(4, preguntas.size());
    }

    @Test
    public void obtenerInstanciaSinArchivoDebeDevolverMismaInstancia() {
        CargadorPreguntas cargadorPreguntas = CargadorPreguntas.obtenerInstancia(RUTA_PREGUNTAS_TEST);
        CargadorPreguntas cargadorPreguntasSinArchivo = CargadorPreguntas.obtenerInstancia();

        Assertions.assertEquals(cargadorPreguntas, cargadorPreguntasSinArchivo);
    }

}
