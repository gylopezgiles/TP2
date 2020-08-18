package edu.fiuba.algo3.interfazGrafica.pregunta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Temporizador {

    private final static int UN_SEGUNDO = 1000;
    private final static int SEGUNDOS_ESPERA = 10;

    private int contador = SEGUNDOS_ESPERA;
    private JLabel timerLabel = new JLabel();
    private Timer timer;

    private PanelPregunta panelPregunta;

    public Temporizador(PanelPregunta panelPregunta){
        this.panelPregunta = panelPregunta;
        timer = new Timer(UN_SEGUNDO, new TemporizadorEventListener());
    }

    public class TemporizadorEventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (contador == -1) {
                contador = SEGUNDOS_ESPERA;

            } else {
                timerLabel.setText("Contador: " + Integer.toString(contador));
                contador--;
            }
        }
    }

    public void comenzar() {
        timer.setInitialDelay(0);
        timer.start();
    }

    public Component obtenerVisual(){
        return timerLabel;
    }

    public void reestablecerTemporizador(){
        contador = SEGUNDOS_ESPERA;
    }

        /*
        * JProgressBar  progressBar = new JProgressBar(0, MILISEGUNDOS_ESPERA);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        * */
}
