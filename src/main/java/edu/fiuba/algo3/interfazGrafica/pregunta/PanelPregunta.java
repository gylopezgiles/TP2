package edu.fiuba.algo3.interfazGrafica.pregunta;

import edu.fiuba.algo3.controlador.ControladorPanel;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;

import javax.swing.*;
import java.util.List;

public class PanelPregunta extends JPanel {

    private JButton responder;
    private JPanelPregunta opciones;

    public PanelPregunta(){
        responder = new JButton("Responder");
    }

    public void establecerTurno(Preguntable pregunta, Jugador jugador){
        agregarTextoPregunta(jugador.obtenerNombre(), pregunta);
        agregarOpciones(pregunta);
        agregarBotonResponder();
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
