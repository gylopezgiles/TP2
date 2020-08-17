package edu.fiuba.algo3.interfazGrafica.pregunta;

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
        //opcionesEnPantalla = new JTable();
        opcionesEnPantalla = new JList<>(new DefaultListModel<>());
        opcionesEnPantalla.setDragEnabled(true);
        opcionesEnPantalla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        opcionesEnPantalla.setDropMode(DropMode.INSERT);
        opcionesEnPantalla.setTransferHandler(new ListTransferHandler());
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
        List opcionesOrdenadas = new ArrayList(opcionesEnPantalla.getModel().getSize());
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

class ListTransferHandler extends TransferHandler {
    @Override
    public int getSourceActions(JComponent c) {
        return TransferHandler.COPY_OR_MOVE;
    }
    @Override
    protected Transferable createTransferable(JComponent source) {
        JList<String> sourceList = (JList<String>) source;
        String data = sourceList.getSelectedValue();
        Transferable t = new StringSelection(data);
        return t;
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        @SuppressWarnings("unchecked")
        JList<String> sourceList = (JList<String>) source;
        String movedItem = sourceList.getSelectedValue();
        if (action == TransferHandler.MOVE) {
            DefaultListModel<String> listModel = (DefaultListModel<String>) sourceList
                    .getModel();
            listModel.removeElement(movedItem);
        }
    }
    @Override
    public boolean canImport(TransferHandler.TransferSupport support) {
        if (!support.isDrop()) {
            return false;
        }
        return support.isDataFlavorSupported(DataFlavor.stringFlavor);
    }
    @Override
    public boolean importData(TransferHandler.TransferSupport support) {
        if (!this.canImport(support)) {
            return false;
        }
        Transferable t = support.getTransferable();
        String data = null;
        try {
            data = (String) t.getTransferData(DataFlavor.stringFlavor);
            if (data == null) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        JList.DropLocation dropLocation = (JList.DropLocation) support
                .getDropLocation();
        int dropIndex = dropLocation.getIndex();
        JList<String> targetList = (JList<String>) support.getComponent();
        DefaultListModel<String> listModel = (DefaultListModel<String>) targetList
                .getModel();
        if (dropLocation.isInsert()) {
            listModel.add(dropIndex, data);
        } else {
            listModel.set(dropIndex, data);
        }
        return true;
    }
}