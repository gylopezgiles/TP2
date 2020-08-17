package edu.fiuba.algo3.interfazGrafica.pregunta;

public interface JPanelPregunta<T> {

    <T> T obtenerOpcionesSeleccionadas();

    void agregarA(PanelPregunta panelPregunta);
}
