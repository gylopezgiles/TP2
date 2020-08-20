package edu.fiuba.algo3.interfazGrafica.pregunta;

import edu.fiuba.algo3.controlador.ControladorPanel;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;
import edu.fiuba.algo3.modelo.pregunta.cargador.CargadorPreguntas;
import org.apache.log4j.Logger;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static edu.fiuba.algo3.modelo.pregunta.TipoPregunta.MultipleChoiceConPenalidad;
import static edu.fiuba.algo3.modelo.pregunta.TipoPregunta.VerdaderoFalsoPenalidad;

public class PanelPregunta extends JPanel {

    final static Logger log = Logger.getLogger(PanelPregunta.class);

    private JButton responder;
    private JPanelPregunta opciones;

    private JCheckBox exclusividad;
    private ButtonGroup multiplicadores;

    private Temporizador temporizador;
    private Clip musica;


    public PanelPregunta(){
        responder = new JButton("Responder");
        exclusividad = new JCheckBox();
        exclusividad.setText("Exclusividad");
        multiplicadores = new ButtonGroup();
        temporizador = new Temporizador();
        try {
            musica = AudioSystem.getClip();
        } catch (LineUnavailableException lineUnavailableException) {
            log.info(String.format("Error al cargar musica de cuenta atras"));
        }
    }

    public void establecerTurno(Preguntable pregunta, Jugador jugador) {
        empezarMusica();
        agregarTextoPregunta(jugador.obtenerNombre(), pregunta);
        agregarOpciones(pregunta);
        agregarExclusividad(pregunta);
        agregarMultiplicadores(pregunta);
        agregarTemporizador(pregunta);
        agregarBotonResponder();
    }

    private void agregarTemporizador(Preguntable pregunta) {
        temporizador.comenzar();
        add(temporizador.obtenerVisual());
    }

    private void empezarMusica(){
        try {
            musica = AudioSystem.getClip();
            musica.open(AudioSystem.getAudioInputStream(new File("doc/musica/countdown_kahoot.wav")));
            musica.start();
        } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
            log.info(String.format("Error al cargar musica de cuenta atras"));
        } catch (IOException ioException) {
            log.info(String.format("Error al cargar musica de cuenta atras"));
        } catch (LineUnavailableException lineUnavailableException) {
            log.info(String.format("Error al cargar musica de cuenta atras"));
        }
    }

    public void detenerMusica(){
        musica.close();
    }

    public void terminarMusica(){
        musica = null;
    }

    public void agregarExclusividad(Preguntable pregunta) {
        exclusividad.setSelected(Boolean.FALSE);
        if(esPreguntaSinPenalidad(pregunta.obtenerTipoPregunta())) {
            add(exclusividad);
        }
    }

    private boolean esPreguntaSinPenalidad(TipoPregunta tipoPregunta) {
        return (tipoPregunta != VerdaderoFalsoPenalidad) && (tipoPregunta != MultipleChoiceConPenalidad);
    }

    public Boolean obtenerExclusividad(){
        return exclusividad.isSelected();
    }

    public void agregarMultiplicadores(Preguntable pregunta){
        multiplicadores.clearSelection();
       if(!esPreguntaSinPenalidad(pregunta.obtenerTipoPregunta())){
           JRadioButton porDos = new JRadioButton("X2");
           JRadioButton porTres = new JRadioButton("X3");
           multiplicadores.add(porDos);
           multiplicadores.add(porTres);
           add(porDos);
           add(porTres);
       }
    }

    public Multiplicador obtenerMultiplicador(){
        String multiplicador = obtenerMultiplicadorSeleccionado(Collections.list(multiplicadores.getElements()));
        switch (multiplicador){
            case "X2": return Multiplicador.PorDos;
            case "X3":return Multiplicador.PorTres;
            default: return Multiplicador.PorDefecto;
        }
    }

    public String obtenerMultiplicadorSeleccionado(List<AbstractButton> multiplicadores){
        return multiplicadores.stream().filter(multiplicador -> multiplicador.isSelected()).map(multiplicador -> multiplicador.getText()).findAny().orElse("defecto");
    }

    private void agregarTextoPregunta(String nombreJugador, Preguntable pregunta) {
        String texto = String.format("%s: %s", nombreJugador, pregunta.obtenerPregunta());
        JLabel textoPregunta = new JLabel(texto);
        add(textoPregunta);
    }

    private void agregarOpciones(Preguntable pregunta) {
        opciones = CreadorPanelOpciones.crearPanelOpcionesPorTipoPregunta(pregunta);

        opciones.agregarA(this);
    }

    private void agregarBotonResponder() {
        add(responder);
    }

    public Object obtenerOpcionesSeleccionadas(){
        return opciones.obtenerOpcionesSeleccionadas();
    }

    public void conectaControlador(ControladorPanel controlador){
        temporizador.conectaControlador(controlador);
        responder.addActionListener(controlador);
        responder.setActionCommand("RESPONDER");
    }

    public void establecerVisualTemporizador(int contador){
        temporizador.establecerVisual(contador);
    }
}
