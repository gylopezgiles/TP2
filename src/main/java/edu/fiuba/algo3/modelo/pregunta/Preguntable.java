package edu.fiuba.algo3.modelo.pregunta;

import edu.fiuba.algo3.modelo.excepciones.MultiplicadorExcepcion;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;

import java.util.List;

public interface Preguntable {

    List<Opcion> obtenerOpciones();

    int establecerPuntuacion(List<Opcion> opciones);

    String obtenerPregunta();

    int aplicarMultiplicador(int puntos, MultiplicableStrategy multiplicador) throws MultiplicadorExcepcion;
}
