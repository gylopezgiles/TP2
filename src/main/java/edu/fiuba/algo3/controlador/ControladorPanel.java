package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.interfazGrafica.PantallaPrincipal;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Partida;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.cargador.CargadorPreguntas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControladorPanel implements ActionListener {

    private PantallaPrincipal pantallaPrincipal;
    private Partida partida;

    public ControladorPanel(PantallaPrincipal pantallaPrincipal){
        this.pantallaPrincipal = pantallaPrincipal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch(comando) {
            case "JUGAR":
                jugar();
                break;
            case "RESPONDER":
                responder();
                break;
        }

    }

    private void jugar(){
        List<String> nombresJugadores = pantallaPrincipal.obtenerJugadores();
        partida = new Partida(nombresJugadores, CargadorPreguntas.obtenerInstancia().obtenerPreguntas());
        pantallaPrincipal.iniciarPartida();
        establecerTurno();
    }

    private void responder(){
        List<String> opcionesSeleccionadas = pantallaPrincipal.obtenerOpcionesSeleccionadas();
        partida.responder(opcionesSeleccionadas, Boolean.FALSE);
        if(partida.esPartidaFinalizada()){
            pantallaPrincipal.finalizarPartida(partida.obtenerJugadores());
        } else {
            establecerTurno();
        }

    }

    private void establecerTurno(){
        Preguntable pregunta = partida.obtenerPreguntaTurno();
        Jugador jugador = partida.obtenerJugadorTurno();
        pantallaPrincipal.establecerTurno(pregunta, jugador);
    }

}
