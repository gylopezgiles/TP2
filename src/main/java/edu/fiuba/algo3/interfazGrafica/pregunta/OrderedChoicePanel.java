package edu.fiuba.algo3.interfazGrafica.pregunta;

import edu.fiuba.algo3.interfazGrafica.DragAndDropHandler;
import edu.fiuba.algo3.modelo.pregunta.Opcion;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.TransferHandler;



public class OrderedChoicePanel extends JPanel implements JPanelPregunta{
    private JList<String> opcionesEnPantalla;

    public OrderedChoicePanel(List<Opcion> opciones){
        opcionesEnPantalla = new JList<>(new DefaultListModel<>());
        opcionesEnPantalla.setDragEnabled(true);
        opcionesEnPantalla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        opcionesEnPantalla.setDropMode(DropMode.INSERT);
        opcionesEnPantalla.setTransferHandler(new DragAndDropHandler());
        int i = 0;
        opciones.stream().forEach(opcion -> agregarOpcion(opcion, i));
        add(new JScrollPane(opcionesEnPantalla));

    }

    private void agregarOpcion(Opcion opcion, int i) {
        ((DefaultListModel<String>) opcionesEnPantalla.getModel()).add(i ,opcion.obtenerTexto());
        i++;

    }

    @Override
    public List<String> obtenerOpcionesSeleccionadas() {
        List opcionesOrdenadas = new LinkedList();
        for (int i = 0; i < opcionesEnPantalla.getModel().getSize(); i++) {
            opcionesOrdenadas.add(opcionesEnPantalla.getModel().getElementAt(i));
        }
        return opcionesOrdenadas;
    }

    @Override
    public void agregarA(PanelPregunta panelPregunta) {
        panelPregunta.add(this);
    }
}

