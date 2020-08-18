package edu.fiuba.algo3.interfazGrafica.pregunta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Temporizador {

    private final static int UN_SEGUNDO = 1000;
    private final static int MILISEGUNDOS_ESPERA = 10000;

    private int contador = MILISEGUNDOS_ESPERA;
    private JLabel timerLabel = new JLabel();
    private Timer timer;

    public Temporizador(){
        timer = new Timer(UN_SEGUNDO, new TemporizadorEventListener() );
        timer.start();
        timerLabel.setText(Integer.toString(contador));
    }

    public class TemporizadorEventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (contador == 0) {

            } else {
                timerLabel.setText("Contador: " + Integer.toString(contador));
                contador--;
            }
        }
    }

    public Component obtenerVisual(){
        return timerLabel;
    }


        /*
        * JProgressBar  progressBar = new JProgressBar(0, MILISEGUNDOS_ESPERA);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        * */

}
