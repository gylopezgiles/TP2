package edu.fiuba.algo3.modelo.pregunta;

import edu.fiuba.algo3.modelo.excepciones.MultiplicadorExcepcion;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;

import java.util.List;

public interface Preguntable {

    List<Opcion> obtenerOpciones();

    int establecerPuntuacion(List<Opcion> opciones);

    String obtenerPregunta();

    int aplicarMultiplicador(int puntos, Multiplicador multiplicador) throws MultiplicadorExcepcion;
}
