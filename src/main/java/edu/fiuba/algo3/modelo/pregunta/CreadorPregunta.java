package edu.fiuba.algo3.modelo.pregunta;

import java.util.List;

public class CreadorPregunta {

    public static Preguntable crearPregunta(TipoPregunta tipoPregunta, List<Opcion> opciones){

        Preguntable preguntaACrear ;
        switch (tipoPregunta) {
            case VerdaderoFalsoClasico: preguntaACrear = new VerdaderoFalsoClasico(opciones);
            default: preguntaACrear = new VerdaderoFalsoClasico(opciones);
        }


        return preguntaACrear;
    }
}
