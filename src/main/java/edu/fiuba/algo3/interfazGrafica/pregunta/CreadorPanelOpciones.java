package edu.fiuba.algo3.interfazGrafica.pregunta;

import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.multiplechoice.MultipleChoice;

public class CreadorPanelOpciones {

    public static JPanelPregunta crearPanelOpcionesPorTipoPregunta(Preguntable pregunta){
        switch (pregunta.obtenerTipoPregunta()){
            case VerdaderoFalsoClasico:
            case VerdaderoFalsoPenalidad:
                return new VerdaderoFalsoPanel(pregunta.obtenerOpciones());
            case MultipleChoiceClasico:
                return new MultipleChoicePanel(pregunta.obtenerOpciones());
            case OrderedChoice:
                return new OrderedChoicePanel(pregunta.obtenerOpciones());
        }
        return null;
    }
}
