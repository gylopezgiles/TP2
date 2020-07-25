package edu.fiuba.algo3.modelo.pregunta;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;

import java.util.List;

public class VerdaderoFalsoPenalidad extends VerdaderoFalso {

    public VerdaderoFalsoPenalidad(String pregunta, List<Opcion> opciones) throws ParametrosInvalidosExcepcion {
        validarOpciones(opciones);
        this.pregunta = pregunta;
        this.opciones = opciones;
    }

    @Override
    public int establecerPuntuacion(List<Opcion> opciones) {
        return 0;
    }
}
