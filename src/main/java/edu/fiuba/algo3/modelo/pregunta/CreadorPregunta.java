package edu.fiuba.algo3.modelo.pregunta;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.excepciones.TipoPreguntaNoImplementadaException;
import edu.fiuba.algo3.modelo.pregunta.multiplechoice.MultipleChoiceClasico;
import edu.fiuba.algo3.modelo.pregunta.multiplechoice.MultipleChoiceConPenalidad;
import edu.fiuba.algo3.modelo.pregunta.multiplechoice.MultipleChoiceParcial;
import edu.fiuba.algo3.modelo.pregunta.verdaderofalso.VerdaderoFalsoClasico;
import edu.fiuba.algo3.modelo.pregunta.verdaderofalso.VerdaderoFalsoPenalidad;

import java.util.List;

public class CreadorPregunta {

    public static Preguntable crearPregunta(TipoPregunta tipoPregunta, String pregunta, List<Opcion> opciones) throws ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException {
        switch (tipoPregunta) {
            case VerdaderoFalsoClasico:
                return new VerdaderoFalsoClasico(pregunta, opciones);
            case VerdaderoFalsoPenalidad:
                return new VerdaderoFalsoPenalidad(pregunta, opciones);
            case MultipleChoiceClasico:
                return new MultipleChoiceClasico(pregunta, opciones);
            case MultipleChoiceParcial:
                return new MultipleChoiceParcial(pregunta, opciones);
            case MultipleChoiceConPenalidad:
                return new MultipleChoiceConPenalidad(pregunta, opciones);
            case GroupChoice:
                return new GroupChoice(pregunta, opciones);
            default:
                throw new TipoPreguntaNoImplementadaException(String.format("El tipoPregunta %s no esta implementado", tipoPregunta.toString()));
        }
    }
}
