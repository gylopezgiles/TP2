package edu.fiuba.algo3.interfazGrafica.pregunta;

import edu.fiuba.algo3.modelo.pregunta.Opcion;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MultipleChoicePanel extends JPanel implements JPanelPregunta{

    private ButtonGroup opcionesButtonGroup;

    public MultipleChoicePanel(List<Opcion> opciones){

        opcionesButtonGroup = new ButtonGroup();
        opciones.stream().forEach(opcion -> agregarOpcion(opcion));
    }

    private void agregarOpcion(Opcion opcion) {
        JCheckBox opcionCheckBox = new JCheckBox();
        opcionCheckBox.setText(opcion.obtenerTexto());
        add(opcionCheckBox);
        opcionesButtonGroup.add(opcionCheckBox);

    }

    @Override
    public List<String> obtenerOpcionesSeleccionadas() {
        List<AbstractButton> botonesSeleccionados = obtenerBotonesSeleccionados(Collections.list(opcionesButtonGroup.getElements()));
        return botonesSeleccionados.stream()
                .map(button -> button.getText())
                .collect(Collectors.toList());
    }

    private List<AbstractButton> obtenerBotonesSeleccionados(List<AbstractButton> botones){
        return botones.stream().filter(checkBox -> checkBox.isSelected()).collect(Collectors.toList());
    }

    @Override
    public void agregarA(PanelPregunta panelPregunta) {
        panelPregunta.add(this);
    }
}