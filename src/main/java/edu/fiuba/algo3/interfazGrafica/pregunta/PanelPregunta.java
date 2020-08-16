package edu.fiuba.algo3.interfazGrafica.pregunta;

import edu.fiuba.algo3.controlador.ControladorPanel;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;

import javax.swing.*;
import java.util.List;

import static edu.fiuba.algo3.modelo.pregunta.TipoPregunta.MultipleChoiceConPenalidad;
import static edu.fiuba.algo3.modelo.pregunta.TipoPregunta.VerdaderoFalsoPenalidad;

public class PanelPregunta extends JPanel {

    private JButton responder;
    private JPanelPregunta opciones;
    private JCheckBox exclusividad;

    public PanelPregunta(){
        responder = new JButton("Responder");
        exclusividad = new JCheckBox();
    }

    public void establecerTurno(Preguntable pregunta, Jugador jugador){
        agregarTextoPregunta(jugador.obtenerNombre(), pregunta);
        agregarOpciones(pregunta);
        agregarExclusividad(pregunta);
        agregarBotonResponder();
    }


    public void agregarExclusividad(Preguntable pregunta) {
        if(esPreguntaSinPenalidad(pregunta.obtenerTipoPregunta())) {
            add(exclusividad);
        }
    }

    private boolean esPreguntaSinPenalidad(TipoPregunta tipoPregunta) {
        return (tipoPregunta != VerdaderoFalsoPenalidad) && (tipoPregunta != MultipleChoiceConPenalidad);
    }

    public Boolean obtenerExclusividad(){
        return exclusividad.isSelected();
    }



    private void agregarTextoPregunta(String nombreJugador, Preguntable pregunta) {
        String texto = String.format("%s: %s", nombreJugador, pregunta.obtenerPregunta());
        JLabel textoPregunta = new JLabel(texto);
        add(textoPregunta);
    }

    private void agregarOpciones(Preguntable pregunta) {
        opciones = CreadorPanelOpciones.crearPanelOpcionesPorTipoPregunta(pregunta);

        opciones.agregarA(this);
    }

    private void agregarBotonResponder() {
        add(responder);
    }

    public List<String> obtenerOpcionesSeleccionadas(){
        return opciones.obtenerOpcionesSeleccionadas();
    }

    public void conectaControlador(ControladorPanel controlador){
        responder.addActionListener(controlador);
        responder.setActionCommand("RESPONDER");
    }


}
