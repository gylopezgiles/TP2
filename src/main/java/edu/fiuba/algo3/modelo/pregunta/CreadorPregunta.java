package edu.fiuba.algo3.modelo.pregunta;

import java.util.List;

public class CreadorPregunta {

    public static Preguntable crearPregunta(TipoPregunta tipoPregunta, List<Opcion> opciones){
        switch (tipoPregunta){
            case VerdaderoFalsoClasico:
                return new VerdaderoFalsoClasico(opciones);
            default:
                return null;
        }
    }
}
