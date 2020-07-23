package edu.fiuba.algo3.modelo.pregunta;

import edu.fiuba.algo3.modelo.excepciones.CantidadOpcionesExcepcion;
import edu.fiuba.algo3.modelo.excepciones.CantidadRespuestasCorrectasExcepcion;

import java.util.List;

public class CreadorPregunta {

    public static Preguntable crearPregunta(TipoPregunta tipoPregunta, String pregunta, List<Opcion> opciones) throws CantidadRespuestasCorrectasExcepcion, CantidadOpcionesExcepcion {
        switch (tipoPregunta) {
            case VerdaderoFalsoClasico:
                return new VerdaderoFalsoClasico(pregunta, opciones);
            default:
                return null;
        }
    }
}
