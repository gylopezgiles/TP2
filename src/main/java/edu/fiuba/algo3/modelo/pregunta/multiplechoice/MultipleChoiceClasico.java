package edu.fiuba.algo3.modelo.pregunta.multiplechoice;

import edu.fiuba.algo3.modelo.excepciones.MultiplicadorExcepcion;
import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.exclusividad.Exclusividad;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.Opcion;

import java.util.List;

public class MultipleChoiceClasico extends MultipleChoice {

    public  MultipleChoiceClasico(String pregunta, List<Opcion> opciones) throws ParametrosInvalidosExcepcion {
        validarOpciones(opciones);
        this.opciones = opciones;
        this.pregunta = pregunta;
    }

    @Override
    public int establecerPuntuacion(List<Opcion> opciones, MultiplicableStrategy multiplicador, Exclusividad exclusividad) throws MultiplicadorExcepcion {
        if(!multiplicador.equals(Multiplicador.PorDefecto)){
            throw new MultiplicadorExcepcion("Solo se puede aplicar multiplicadores a preguntas con penalidad");
        }
        if(tieneOpcionesIncorrectas(opciones)){//esto debe estar sino falla test linea 206
            return 0;
        }
        exclusividad.activarExclusividad();
        boolean sonTodasCorrectas = cantidadDeOpcionesCorrectas(opciones) == cantidadDeOpcionesCorrectas(this.opciones);
        return sonTodasCorrectas ? 1 : 0;
    }

    private int cantidadDeOpcionesCorrectas(List<Opcion> opciones){
        return (int)opciones.stream()
                .filter(opcion -> opcion.esCorrecta())
                .count();
    }


}