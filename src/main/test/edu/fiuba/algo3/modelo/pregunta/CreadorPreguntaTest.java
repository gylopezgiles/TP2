package edu.fiuba.algo3.modelo.pregunta;

import edu.fiuba.algo3.modelo.excepciones.CantidadOpcionesExcepcion;
import edu.fiuba.algo3.modelo.excepciones.CantidadRespuestasCorrectasExcepcion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class CreadorPreguntaTest {

    @Test
    public void debeCrearPreguntaVerdaderoFalsoClasico() throws CantidadOpcionesExcepcion, CantidadRespuestasCorrectasExcepcion {
        Opcion opcionCorrecta = new Opcion("opcion", Boolean.TRUE);
        Opcion opcionIncorrecta = new Opcion("opcion", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        String preguntaTexto = "pregunta?";
        Preguntable preguntaEsperada = new VerdaderoFalsoClasico(preguntaTexto, opciones);

        Preguntable pregunta = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoClasico, preguntaTexto, opciones);

        Assertions.assertEquals(preguntaEsperada.obtenerOpciones(), pregunta.obtenerOpciones());
        Assertions.assertEquals(preguntaEsperada.obtenerPregunta(), pregunta.obtenerPregunta());
    }

}
