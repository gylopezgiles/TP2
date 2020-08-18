package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.interfazGrafica.PantallaPrincipal;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Partida;
import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
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
        //TODO: VALIDAR QUE HAYA PREGUNTAS, AGREGAR MENSAJE AL USUARIO SI NO HAY PREGUNTAS O RONDA TIRO UNA EXCEPCION
        try {
            partida = new Partida(nombresJugadores, CargadorPreguntas.obtenerInstancia().obtenerPreguntas());
        } catch (ParametrosInvalidosExcepcion parametrosInvalidosExcepcion) {
            parametrosInvalidosExcepcion.printStackTrace();
        }
        pantallaPrincipal.iniciarPartida();
        establecerTurno();
    }

    private void responder(){
        Object opcionesSeleccionadas = pantallaPrincipal.obtenerOpcionesSeleccionadas();
        Boolean exclusividadSeleccionada = pantallaPrincipal.obtenerExclusividad();

        if(exclusividadSeleccionada){
           partida.responder(opcionesSeleccionadas, exclusividadSeleccionada);
        } else {
           Multiplicador multiplicador = pantallaPrincipal.obtenerMultiplicador();
           partida.responder(opcionesSeleccionadas, multiplicador);
        }
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
