package edu.fiuba.algo3.modelo.pregunta;

public interface Preguntable<T> {

    T obtenerOpciones();

    int establecerPuntuacion(T seleccion);

    String obtenerPregunta();
}
