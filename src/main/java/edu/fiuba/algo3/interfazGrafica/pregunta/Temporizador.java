package edu.fiuba.algo3.interfazGrafica.pregunta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Temporizador {

    private final static int UN_SEGUNDO = 1000;
    private final static int TIEMPO_MAX = 10;

    private int contador = TIEMPO_MAX;

    private JLabel visualTemporizador;
    private Timer timer;


    public Temporizador(){
        visualTemporizador = new JLabel();
        timer = new Timer(UN_SEGUNDO, new TemporizadorEventListener());
    }

    public class TemporizadorEventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (contador == -1) {
                contador = TIEMPO_MAX;

            } else {
                establecerVisual();
                contador--;
            }
        }
    }

    private void establecerVisual() {
        visualTemporizador.setText("Contador: " + Integer.toString(contador));
    }

    public void comenzar() {
        timer.setInitialDelay(0);
        timer.start();
    }

    public Component obtenerVisual(){
        return visualTemporizador;
    }

    public void reestablecerTemporizador(){
        contador = TIEMPO_MAX;
    }

}
