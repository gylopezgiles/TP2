package edu.fiuba.algo3.modelo.pregunta;

import edu.fiuba.algo3.modelo.exclusividad.Exclusividad;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;

import java.util.List;

public interface Preguntable<T> {

    List<Opcion> obtenerOpciones();

    String obtenerPregunta();

    TipoPregunta obtenerTipoPregunta();

    int establecerPuntuacion(T nombresOpcionesSeleccionadas, MultiplicableStrategy multiplicador, Exclusividad exclusividad);

}
