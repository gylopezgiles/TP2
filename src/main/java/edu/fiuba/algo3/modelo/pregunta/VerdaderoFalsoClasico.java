package edu.fiuba.algo3.modelo.pregunta;

import java.util.List;
import java.util.Optional;

public class VerdaderoFalsoClasico implements Preguntable {

    private List<Opcion> opciones;

    VerdaderoFalsoClasico(List<Opcion> opciones){
        this.opciones = opciones;
    }

    @Override
    public List<Opcion> obtenerOpciones() {
        return this.opciones;
    }

    @Override
    public int establecerPuntuacion(List<Opcion> opciones) {
        Optional<Opcion> opcion = opciones.stream()
                .filter(op -> op.esCorrecta())
                .findAny();
        return opcion.isPresent() ? 1 : 0;
    }
}
