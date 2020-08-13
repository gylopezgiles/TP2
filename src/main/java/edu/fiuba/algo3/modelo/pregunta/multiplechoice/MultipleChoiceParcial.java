package edu.fiuba.algo3.modelo.pregunta.multiplechoice;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.exclusividad.Exclusividad;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;

import java.util.List;
import java.util.Optional;

public class MultipleChoiceParcial extends MultipleChoice {

    public <T> MultipleChoiceParcial(String pregunta, T opciones) throws ParametrosInvalidosExcepcion {
        validarOpciones((List<Opcion>) opciones);
        this.opciones = (List<Opcion>) opciones;
        this.pregunta = pregunta;
    }

    @Override
    public int establecerPuntuacion(List<Opcion> opciones, MultiplicableStrategy multiplicador, Exclusividad exclusividad) {
        exclusividad.activarExclusividad();
        Optional<Opcion> opcion = opciones.stream()
                .filter(op -> !op.esCorrecta())
                .findAny();
        return opcion.isPresent() ? 0 : puntajeOpcionesCorrectas(opciones);
    }

    @Override
    public TipoPregunta obtenerTipoPregunta() {
        return TipoPregunta.MultipleChoiceParcial;
    }

    private int puntajeOpcionesCorrectas(List<Opcion> opciones) {
        long puntaje = opciones.stream()
                .filter(op -> op.esCorrecta())
                .count();
        return (int)puntaje;
    }
}
