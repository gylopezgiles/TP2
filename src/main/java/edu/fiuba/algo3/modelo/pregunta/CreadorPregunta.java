package edu.fiuba.algo3.modelo.pregunta;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.excepciones.TipoPreguntaNoImplementadaException;

import java.util.List;

public class CreadorPregunta {

    public static Preguntable crearPregunta(TipoPregunta tipoPregunta, String pregunta, List<Opcion> opciones) throws ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException {
        switch (tipoPregunta) {
            case VerdaderoFalsoClasico:
                return new VerdaderoFalsoClasico(pregunta, opciones);
            default:
                throw new TipoPreguntaNoImplementadaException(String.format("El tipoPregunta %s no esta implementado", tipoPregunta.toString()));
        }
    }
}
