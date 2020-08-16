package edu.fiuba.algo3.modelo.pregunta.multiplechoice;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.exclusividad.Exclusividad;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;

import java.util.List;
import java.util.Optional;

public class MultipleChoiceParcial extends MultipleChoice {

    public MultipleChoiceParcial(String pregunta, List<Opcion> opciones) throws ParametrosInvalidosExcepcion {
        validarOpciones(opciones);
        this.opciones = opciones;
        this.pregunta = pregunta;
    }

    @Override
    public int establecerPuntuacion(List<String> nombresOpcionesSeleccionadas, MultiplicableStrategy multiplicador, Exclusividad exclusividad) {
        exclusividad.activarExclusividad();
        List<Opcion> opcionesSeleccionadas = obtenerOpcionesPorNombre(nombresOpcionesSeleccionadas);
        Optional<Opcion> opcion = opcionesSeleccionadas.stream()
                .filter(op -> !op.esCorrecta())
                .findAny();
        return opcion.isPresent() ? 0 : puntajeOpcionesCorrectas(opcionesSeleccionadas);
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
