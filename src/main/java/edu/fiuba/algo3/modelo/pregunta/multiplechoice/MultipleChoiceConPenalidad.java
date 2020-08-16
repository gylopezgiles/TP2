package edu.fiuba.algo3.modelo.pregunta.multiplechoice;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.exclusividad.Exclusividad;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;

import java.util.List;
import java.util.Optional;

public class MultipleChoiceConPenalidad extends MultipleChoice {

    public  MultipleChoiceConPenalidad(String pregunta, List<Opcion> opciones) throws ParametrosInvalidosExcepcion {
        validarOpciones(opciones);
        this.opciones = opciones;
        this.pregunta = pregunta;
    }

    @Override
    public int establecerPuntuacion(List<String> opcionesSeleccionadas, MultiplicableStrategy multiplicador,  Exclusividad exclusividad) {
        List<Opcion> opciones = obtenerOpcionesPorNombre(opcionesSeleccionadas);
        Optional<Opcion> opcion = opciones.stream()
                .filter(op -> !op.esCorrecta())
                .findAny();
        int puntosObtenidos = opcion.isPresent() ? puntajeOpcionesIncorrectas(opciones) : puntajeOpcionesCorrectas(opciones);
        return multiplicador.aplicarMultiplicador(puntosObtenidos);
    }

    @Override
    public TipoPregunta obtenerTipoPregunta() {
        return TipoPregunta.MultipleChoiceConPenalidad;
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
