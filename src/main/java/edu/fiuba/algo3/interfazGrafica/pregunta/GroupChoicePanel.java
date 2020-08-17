package edu.fiuba.algo3.interfazGrafica.pregunta;

import edu.fiuba.algo3.interfazGrafica.DragAndDropHandler;
import edu.fiuba.algo3.modelo.pregunta.Opcion;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;


public class GroupChoicePanel extends JPanel implements JPanelPregunta<List<List<String>>>{
    private JList<String> grupoDeOpciones;
    private JList<String> grupoIzquierda;
    private JList<String> grupoDerecha;


    public GroupChoicePanel(List<Opcion> opciones){
        grupoDeOpciones = new JList<>(new DefaultListModel<>());
        grupoIzquierda = new JList<>(new DefaultListModel<>());
        grupoDerecha = new JList<>(new DefaultListModel<>());
        configurarDragAndDropEn(grupoDeOpciones);
        configurarDragAndDropEn(grupoIzquierda);
        configurarDragAndDropEn(grupoDerecha);
        int i = 0;
        opciones.stream().forEach(opcion -> agregarOpcion(opcion, i));
        armarLayout();

    }

    private void configurarDragAndDropEn(JList<String> contenedorDeOpciones){
        contenedorDeOpciones.setDragEnabled(true);
        contenedorDeOpciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        contenedorDeOpciones.setDropMode(DropMode.INSERT);
        contenedorDeOpciones.setTransferHandler(new DragAndDropHandler());
    }

    private void armarLayout(){
        Box gruposVacios = Box.createHorizontalBox();
        gruposVacios.add(new JScrollPane(grupoIzquierda));
        gruposVacios.add(new JScrollPane(grupoDerecha));

        Box todosLosGrupos = Box.createVerticalBox();
        todosLosGrupos.add(gruposVacios);
        todosLosGrupos.add(new JScrollPane(grupoDeOpciones));
        add(todosLosGrupos);

    }

    private void agregarOpcion(Opcion opcion, int i) {
        ((DefaultListModel<String>) grupoDeOpciones.getModel()).add(i ,opcion.obtenerTexto());
        i++;

    }

    @Override
    public List<List<String>> obtenerOpcionesSeleccionadas() {
        List respuestaFinal = new LinkedList();
        List opcionesIzquierda = new LinkedList();
        List opcionesDerecha = new LinkedList();
        for (int i = 0; i < grupoIzquierda.getModel().getSize(); i++) {
            opcionesIzquierda.add(grupoIzquierda.getModel().getElementAt(i));
        }
        for (int i = 0; i < grupoDerecha.getModel().getSize(); i++) {
            opcionesDerecha.add(grupoDerecha.getModel().getElementAt(i));
        }
        respuestaFinal.add(opcionesIzquierda);
        respuestaFinal.add(opcionesDerecha);
        return respuestaFinal;
    }

    @Override
    public void agregarA(PanelPregunta panelPregunta) {
        panelPregunta.add(this);
    }
}
