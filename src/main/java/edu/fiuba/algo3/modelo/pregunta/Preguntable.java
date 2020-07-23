package edu.fiuba.algo3.modelo.pregunta;

import java.util.List;

public interface Preguntable {

    List<Opcion> obtenerOpciones();

    int establecerPuntuacion(List<Opcion> opciones);

    String obtenerPregunta();
}
