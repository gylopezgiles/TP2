package edu.fiuba.algo3.modelo.pregunta.multiplechoice;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import java.util.List;

public class MultipleChoiceClasico extends MultipleChoice {

    public <T>  MultipleChoiceClasico(String pregunta, T opciones) throws ParametrosInvalidosExcepcion {
        validarOpciones((List<Opcion>) opciones);
        this.opciones = (List<Opcion>) opciones;
        this.pregunta = pregunta;
    }

    @Override
    public int establecerPuntuacion(List<Opcion> opciones) {
        boolean sonTodasCorrectas = cantidadDeOpcionesCorrectas(opciones) == cantidadDeOpcionesCorrectas(this.opciones);
        return sonTodasCorrectas ? 1 : 0;
    }

    private int cantidadDeOpcionesCorrectas(List<Opcion> opciones){
        return (int)opciones.stream()
                .filter(op -> op.esCorrecta())
                .count();
    }

}