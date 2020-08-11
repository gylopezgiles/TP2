package edu.fiuba.algo3.modelo.pregunta.verdaderofalso;

import edu.fiuba.algo3.modelo.excepciones.MultiplicadorExcepcion;
import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.exclusividad.Exclusividad;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.Opcion;

import java.util.List;
import java.util.Optional;

public class VerdaderoFalsoClasico extends VerdaderoFalso {

    public VerdaderoFalsoClasico(String pregunta, List<Opcion> opciones) throws ParametrosInvalidosExcepcion {
        validarOpciones(opciones);
        this.pregunta = pregunta;
        this.opciones = opciones;
    }

    @Override
    public int establecerPuntuacion(List<Opcion> opciones, MultiplicableStrategy multiplicador, Exclusividad exclusividad) throws MultiplicadorExcepcion {
        if(!multiplicador.equals(Multiplicador.PorDefecto)){
            throw new MultiplicadorExcepcion("Solo se puede aplicar multiplicadores a preguntas con penalidad");
        }
        exclusividad.activarExclusividad();
        Optional<Opcion> opcion = opciones.stream()
                .filter(op -> op.esCorrecta())
                .findAny();
        return opcion.isPresent() ? 1 : 0;
    }

}
