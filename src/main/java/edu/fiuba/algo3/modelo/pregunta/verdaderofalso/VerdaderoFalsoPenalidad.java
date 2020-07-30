package edu.fiuba.algo3.modelo.pregunta.verdaderofalso;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.pregunta.Opcion;

import java.util.List;
import java.util.Optional;

public class VerdaderoFalsoPenalidad extends VerdaderoFalso {

    public VerdaderoFalsoPenalidad(String pregunta, List<Opcion> opciones) throws ParametrosInvalidosExcepcion {
        validarOpciones(opciones);
        this.pregunta = pregunta;
        this.opciones = opciones;
    }

    @Override
    public int establecerPuntuacion(List<Opcion> opciones) {
        Optional<Opcion> opcion = opciones.stream()
                .filter(op -> op.esCorrecta())
                .findAny();
        return opcion.isPresent() ? 1 : -1;
    }
}
