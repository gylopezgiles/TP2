package edu.fiuba.algo3.interfazGrafica.pregunta;

import edu.fiuba.algo3.modelo.pregunta.Opcion;

import javax.swing.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class VerdaderoFalsoPanel extends JPanel implements JPanelPregunta<List<String>>{

    private ButtonGroup opcionesButtonGroup;

    public VerdaderoFalsoPanel(List<Opcion> opciones){

        opcionesButtonGroup = new ButtonGroup();
        opciones.stream().forEach(opcion -> agregarOpcion(opcion));
    }

    private void agregarOpcion(Opcion opcion) {
        JRadioButton opcionRadioButton = new JRadioButton();
        opcionRadioButton.setText(opcion.obtenerTexto());
        add(opcionRadioButton);
        opcionesButtonGroup.add(opcionRadioButton);
    }

    @Override
    public List<String> obtenerOpcionesSeleccionadas() {
        List<AbstractButton> botonesSeleccionados = obtenerBotonesSeleccionados(Collections.list(opcionesButtonGroup.getElements()));
        return botonesSeleccionados.stream()
                .map(button -> button.getText())
                .collect(Collectors.toList());
    }

    private List<AbstractButton> obtenerBotonesSeleccionados(List<AbstractButton> botones){
        return botones.stream().filter(radioButton -> radioButton.isSelected()).collect(Collectors.toList());
    }

    @Override
    public void agregarA(PanelPregunta panelPregunta) {
        panelPregunta.add(this);
    }
}
