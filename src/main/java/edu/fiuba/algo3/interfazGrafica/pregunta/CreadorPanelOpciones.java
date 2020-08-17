package edu.fiuba.algo3.interfazGrafica.pregunta;

import edu.fiuba.algo3.modelo.pregunta.Preguntable;

public class CreadorPanelOpciones {

    public static JPanelPregunta crearPanelOpcionesPorTipoPregunta(Preguntable pregunta){
        switch (pregunta.obtenerTipoPregunta()){
            case VerdaderoFalsoClasico:
            case VerdaderoFalsoPenalidad:
                return new VerdaderoFalsoPanel(pregunta.obtenerOpciones());
            case MultipleChoiceClasico:
                return new MultipleChoicePanel(pregunta.obtenerOpciones());
        }
        return null;
    }
}
