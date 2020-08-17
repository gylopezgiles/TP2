package edu.fiuba.algo3.interfazGrafica.pregunta;

import edu.fiuba.algo3.modelo.pregunta.GroupChoice;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;

public class CreadorPanelOpciones {

    public static JPanelPregunta crearPanelOpcionesPorTipoPregunta(Preguntable pregunta){
        switch (pregunta.obtenerTipoPregunta()){
            case VerdaderoFalsoClasico:
            case VerdaderoFalsoPenalidad:
                return new VerdaderoFalsoPanel(pregunta.obtenerOpciones());
            case MultipleChoiceClasico:
            case MultipleChoiceParcial:
            case MultipleChoiceConPenalidad:
                return new MultipleChoicePanel(pregunta.obtenerOpciones());
            case OrderedChoice:
                return new OrderedChoicePanel(pregunta.obtenerOpciones());
            case GroupChoice:
                return new GroupChoicePanel(pregunta.obtenerOpciones());
        }
        return null;
    }
}
