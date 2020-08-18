package edu.fiuba.algo3.interfazGrafica.pregunta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Temporizador {

    private final static int UN_SEGUNDO = 1000;
    private final static int TIEMPO_MAX = 10;

    private int contador = TIEMPO_MAX;
    private JProgressBar  barraProgreso;
    private Timer timer;

    private PanelPregunta panelPregunta;

    public Temporizador(PanelPregunta panelPregunta){
        this.panelPregunta = panelPregunta;
        barraProgreso = new JProgressBar(0,TIEMPO_MAX);
        timer = new Timer(UN_SEGUNDO, new TemporizadorEventListener());
    }

    public class TemporizadorEventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (contador == -1) {
                contador = TIEMPO_MAX;

            } else {
                barraProgreso.setValue(contador);
                barraProgreso.setStringPainted(true);
                barraProgreso.setString("Tiempo Restante");
                contador--;
            }
        }
    }

    public void comenzar() {
        timer.setInitialDelay(0);
        timer.start();
    }

    public Component obtenerVisual(){
        return barraProgreso;
    }

    public void reestablecerTemporizador(){
        contador = TIEMPO_MAX;
    }

        /*
        * JProgressBar  progressBar = new JProgressBar(0, MILISEGUNDOS_ESPERA);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        * */
}
