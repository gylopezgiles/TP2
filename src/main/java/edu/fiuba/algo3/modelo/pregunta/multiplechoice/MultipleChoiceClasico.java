package edu.fiuba.algo3.modelo.pregunta.multiplechoice;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;

import java.util.List;

public class MultipleChoiceClasico extends MultipleChoice {

    public <T>  MultipleChoiceClasico(String pregunta, T opciones) throws ParametrosInvalidosExcepcion {
        validarOpciones((List<Opcion>) opciones);
        this.opciones = (List<Opcion>) opciones;
        this.pregunta = pregunta;
    }

    @Override
    public int establecerPuntuacion(List<Opcion> opciones, MultiplicableStrategy multiplicador) {
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