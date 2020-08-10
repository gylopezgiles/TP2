package edu.fiuba.algo3.modelo.pregunta;

import edu.fiuba.algo3.modelo.excepciones.MultiplicadorExcepcion;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;

import java.util.List;

public interface Preguntable {

    int establecerPuntuacion(List<Opcion> opciones, MultiplicableStrategy multiplicador) throws MultiplicadorExcepcion;
    int establecerPuntuacion(List<Opcion> opciones) throws MultiplicadorExcepcion;
    List<Opcion> obtenerOpciones();
    String obtenerPregunta();

}
