package edu.fiuba.algo3.interfazGrafica.pregunta;

import edu.fiuba.algo3.controlador.ControladorPanel;

import javax.swing.*;
import java.awt.*;

public class Temporizador {

    private final static int UN_SEGUNDO = 1000;


    private JLabel visualTemporizador;
    private Timer timer;


    public Temporizador(){
        visualTemporizador = new JLabel();
    }

    public void establecerVisual(int contador) {
        visualTemporizador.setText("Contador: " + contador);
    }

    public void comenzar() {
        timer.setInitialDelay(0);
        timer.start();
    }

    public Component obtenerVisual(){
        return visualTemporizador;
    }

    public void conectaControlador(ControladorPanel controlador){
        timer = new Timer(UN_SEGUNDO, controlador);
        timer.setActionCommand("COUNTDOWN");
    }

}
