package edu.fiuba.algo3.modelo.pregunta.multiplechoice;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.pregunta.Opcion;

import java.util.List;
import java.util.Optional;

public class MultipleChoiceParcial extends MultipleChoice {

    public  MultipleChoiceParcial(String pregunta, List<Opcion> opciones) throws ParametrosInvalidosExcepcion {
        validarOpciones(opciones);
        this.opciones = opciones;
        this.pregunta = pregunta;
    }


    @Override
    public int establecerPuntuacion(List<Opcion> opciones) {
        if(tieneOpcionesIncorrectas(opciones)){
            return 0;
        }

        Optional<Opcion> opcion = opciones.stream()
                .filter(op -> !op.esCorrecta())
                .findAny();
        return opcion.isPresent() ? 0 : puntajeOpcionesCorrectas(opciones);
    }

    private int puntajeOpcionesCorrectas(List<Opcion> opciones) {
        long puntaje = opciones.stream()
                .filter(op -> op.esCorrecta())
                .count();
        return (int)puntaje;
    }
}
