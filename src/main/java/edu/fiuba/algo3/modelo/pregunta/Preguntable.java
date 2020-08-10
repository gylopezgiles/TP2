package edu.fiuba.algo3.modelo.pregunta;
import java.util.List;

public interface Preguntable<T> {

    List<Opcion> obtenerOpciones();

    int establecerPuntuacion(T seleccion);

    String obtenerPregunta();
}
