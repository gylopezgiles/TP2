package edu.fiuba.algo3.modelo.pregunta.multiplechoice;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;

import java.util.List;

public class MultipleChoiceClasico extends MultipleChoice {

    public MultipleChoiceClasico(String pregunta, List<Opcion> opciones) throws ParametrosInvalidosExcepcion {
        validarOpciones(opciones);
        this.opciones = opciones;
        this.pregunta = pregunta;
    }

    @Override
    public int establecerPuntuacion(List<String> opcionesSeleccionadas, MultiplicableStrategy multiplicador) {
        List<Opcion> opciones = obtenerOpcionesPorNombre(opcionesSeleccionadas);
        boolean sonTodasCorrectas = cantidadDeOpcionesCorrectas(opciones) == cantidadDeOpcionesCorrectas(this.opciones);
        return !tieneOpcionesIncorrectas(opciones) && sonTodasCorrectas ? 1 : 0;
    }

    @Override
    public TipoPregunta obtenerTipoPregunta() {
        return TipoPregunta.MultipleChoiceClasico;
    }

    private int cantidadDeOpcionesCorrectas(List<Opcion> opciones){
        return (int)opciones.stream()
                .filter(opcion -> opcion.esCorrecta())
                .count();
    }

}