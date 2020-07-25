package edu.fiuba.algo3.modelo.pregunta;

import java.util.List;

public class MultipleChoiceParcial implements Preguntable {
    @Override
    public List<Opcion> obtenerOpciones() {
        return null;
    }

    @Override
    public int establecerPuntuacion(List<Opcion> opciones) {
        return 0;
    }

    @Override
    public String obtenerPregunta() {
        return null;
    }
}
