package edu.fiuba.algo3.modelo.pregunta;

import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;

import java.util.List;

public interface Preguntable {

    List<Opcion> obtenerOpciones();

    int establecerPuntuacion(List<Opcion> opciones);

    int establecerPuntuacion(List<Opcion> opciones, MultiplicableStrategy multiplicador);

    String obtenerPregunta();

}
