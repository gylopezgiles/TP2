package edu.fiuba.algo3.modelo.pregunta;

import edu.fiuba.algo3.modelo.exclusividad.Exclusividad;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;

import java.util.List;

public interface Preguntable<T> {

    List<Opcion> obtenerOpciones();

    int establecerPuntuacion(T opciones);

    int establecerPuntuacion(T opciones, MultiplicableStrategy multiplicador, Exclusividad exclusividad);

    TipoPregunta obtenerTipoPregunta();

    String obtenerPregunta();

    List<Opcion> obtenerOpcionesPorNombre(List<String> opcionesSeleccionadas);

    //agregar un metodo que sea aplicarExclusividad o algo asi para que cada pregunta implemente segun necesite
}
