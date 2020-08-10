package edu.fiuba.algo3.modelo.pregunta.multiplechoice;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.Opcion;

import java.util.List;
import java.util.Optional;

public class MultipleChoiceConPenalidad extends MultipleChoice {

    public  <T> MultipleChoiceConPenalidad(String pregunta, T opciones) throws ParametrosInvalidosExcepcion {
        validarOpciones((List<Opcion>)opciones);
        this.opciones = (List<Opcion>) opciones;
        this.pregunta = pregunta;
    }

    @Override
    public int establecerPuntuacion(List<Opcion> opciones, MultiplicableStrategy multiplicador){
        Optional<Opcion> opcion = opciones.stream()
                .filter(op -> !op.esCorrecta())
                .findAny();
        int puntosObtenidos = opcion.isPresent() ? puntajeOpcionesIncorrectas(opciones) : puntajeOpcionesCorrectas(opciones);
        return multiplicador.aplicarMultiplicador(puntosObtenidos);
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
