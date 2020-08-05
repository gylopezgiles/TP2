package edu.fiuba.algo3.modelo.pregunta.multiplechoice;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.pregunta.Opcion;

import java.util.List;
import java.util.Optional;

public class MultipleChoiceConPenalidad extends MultipleChoice {

    public MultipleChoiceConPenalidad(String pregunta, List<Opcion> opciones) throws ParametrosInvalidosExcepcion {
        validarOpciones(opciones);
        this.opciones = opciones;
        this.pregunta = pregunta;
    }

    @Override
    public int establecerPuntuacion(List<Opcion> opciones) {
        Optional<Opcion> opcion = opciones.stream()
                .filter(op -> !op.esCorrecta())
                .findAny();
        return opcion.isPresent() ? puntajeOpcionesIncorrectas(opciones) : puntajeOpcionesCorrectas(opciones);
    }

    private int puntajeOpcionesCorrectas(List<Opcion> opciones) {
        long puntaje = opciones.stream()
                .filter(op -> op.esCorrecta())
                .count();
        return (int)puntaje;
    }

    private int puntajeOpcionesIncorrectas(List<Opcion> opciones) {
        long puntaje = opciones.stream()
                .filter(op -> !op.esCorrecta())
                .count();
        return -(int)puntaje;
    }
}
