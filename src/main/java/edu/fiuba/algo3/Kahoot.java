package edu.fiuba.algo3;


import edu.fiuba.algo3.controlador.ControladorPanel;
import edu.fiuba.algo3.interfazGrafica.PantallaPrincipal;

import javax.swing.*;

public class Kahoot {

    private ControladorPanel controladorPanel;

    public void iniciar(){

        cargarPantallaPrincipal();

    }

    private void cargarPantallaPrincipal(){
        PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();

        pantallaPrincipal.setSize(600, 500);

        pantallaPrincipal.setLocation(8, 0);

        pantallaPrincipal.setVisible(true);

        pantallaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        controladorPanel = new ControladorPanel(pantallaPrincipal);

        pantallaPrincipal.conectaControladorPrincipal(controladorPanel);
    }

}
