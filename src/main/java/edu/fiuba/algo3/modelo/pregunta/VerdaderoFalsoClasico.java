package edu.fiuba.algo3.modelo.pregunta;

import java.util.List;

public class VerdaderoFalsoClasico implements Preguntable {

    private List<Opcion> opciones;
    private int puntaje;

    VerdaderoFalsoClasico(List<Opcion> opciones){
        this.opciones = opciones;
    }

    @Override
    public List<Opcion> obtenerOpciones() {
        return this.opciones;
    }

    @Override
    public int establecerPuntuacion(List<Opcion> opciones) {
        if(opciones.get(0) == this.opciones.get(0))
            this.puntaje = 1;
        return this.puntaje;
    }
}
