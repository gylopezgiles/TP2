package edu.fiuba.algo3.modelo.pregunta.cargador;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.excepciones.TipoPreguntaNoImplementadaException;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PreguntaTest {

    @Test
    public void preguntaSeCargaBien() throws JsonProcessingException, TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion {
        String preguntaVerdaderoFalso = "{\"tipoPregunta\": \"VerdaderoFalsoClasico\", " +
                "\"datoPregunta\": { " +
                "\"texto\": \"La canción Feelling Good fue escrita por Muse\", " +
                "\"opciones\":[{\"texto\": \"Verdadero\", \"esCorrecta\": false},{\"texto\": \"Falso\", \"esCorrecta\": true}]}}";
        ObjectMapper objectMapper = new ObjectMapper();

        Pregunta pregunta = objectMapper.readValue(preguntaVerdaderoFalso, Pregunta.class);

        Assertions.assertEquals(TipoPregunta.VerdaderoFalsoClasico, pregunta.getTipoPregunta());
        Assertions.assertNotEquals(null, pregunta.getDatoPregunta());
    }

    @Test
    public void debeParsearVerdaderoFalsoClasico() throws JsonProcessingException, TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion {
        String preguntaVerdaderoFalso = "{\"tipoPregunta\": \"VerdaderoFalsoClasico\", " +
                "\"datoPregunta\": { " +
                "\"texto\": \"La canción Feelling Good fue escrita por Muse\", " +
                "\"opciones\":[{\"texto\": \"Verdadero\", \"esCorrecta\": false},{\"texto\": \"Falso\", \"esCorrecta\": true}]}}";
        ObjectMapper objectMapper = new ObjectMapper();
        Pregunta pregunta = objectMapper.readValue(preguntaVerdaderoFalso, Pregunta.class);

        Preguntable verdaderoFalso = pregunta.parsearPregunta();

        Assertions.assertEquals(TipoPregunta.VerdaderoFalsoClasico, verdaderoFalso.obtenerTipoPregunta());
    }

}
