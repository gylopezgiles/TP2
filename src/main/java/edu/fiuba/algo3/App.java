package edu.fiuba.algo3;

import javax.sound.sampled.LineUnavailableException;

/**
 * JavaFX App
 */
public class App {

    public static void main(String[] args) throws LineUnavailableException {

        Kahoot kahoot = new Kahoot();
        kahoot.iniciar();
    }

}