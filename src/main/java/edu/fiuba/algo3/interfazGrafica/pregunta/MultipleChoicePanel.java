package edu.fiuba.algo3.interfazGrafica.pregunta;

import edu.fiuba.algo3.modelo.pregunta.Opcion;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MultipleChoicePanel extends JPanel implements JPanelPregunta{

    private LinkedList<JCheckBox> opcionesEnPantalla;

    public MultipleChoicePanel(List<Opcion> opciones){
        opcionesEnPantalla = new LinkedList<JCheckBox>();
        opciones.stream().forEach(opcion -> agregarOpcion(opcion));
    }

    private void agregarOpcion(Opcion opcion) {
        JCheckBox opcionCheckBox = new JCheckBox();
        opcionCheckBox.setText(opcion.obtenerTexto());
        add(opcionCheckBox);
        opcionesEnPantalla.add(opcionCheckBox);

    }

    @Override
    public List<String> obtenerOpcionesSeleccionadas() {
        List<JCheckBox> botonesSeleccionados = obtenerBotonesSeleccionados(opcionesEnPantalla);
        return botonesSeleccionados.stream()
                .map(button -> button.getText())
                .collect(Collectors.toList());
    }

    private List<JCheckBox> obtenerBotonesSeleccionados(List<JCheckBox> botones){
        return botones.stream().filter(checkBox -> checkBox.isSelected()).collect(Collectors.toList());
    }

    @Override
    public void agregarA(PanelPregunta panelPregunta) {
        panelPregunta.add(this);
    }
}