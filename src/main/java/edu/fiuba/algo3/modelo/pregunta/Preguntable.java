package edu.fiuba.algo3.modelo.pregunta;

import java.util.List;

public interface Preguntable {

    List<Opcion> obtenerOpciones();

    Opcion obtenerOpcionCorrecta();

   // List<Opcion> obtenerOpcionesCorrectas(); FIXME Implementar para preguntas con mas de una opcion correcta.

    int establecerPuntuacion(List<Opcion> opciones);

    String obtenerPregunta();
}
