package edu.fiuba.algo3.modelo.pregunta;
import edu.fiuba.algo3.modelo.excepciones.MultiplicadorExcepcion;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;

import java.util.List;

public interface Preguntable<T> {

    List<Opcion> obtenerOpciones();

    int establecerPuntuacion(T seleccion) throws MultiplicadorExcepcion;

    int establecerPuntuacion(T opciones, MultiplicableStrategy multiplicador) throws MultiplicadorExcepcion;

    String obtenerPregunta();

}
