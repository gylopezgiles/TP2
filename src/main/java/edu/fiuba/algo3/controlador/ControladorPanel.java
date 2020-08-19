package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.interfazGrafica.PantallaPrincipal;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Partida;
import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.cargador.CargadorPreguntas;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class ControladorPanel implements ActionListener {

    private final static int TIEMPO_MAX = 20;
    private static final int FIN_TURNO = -1;

    private int contador = TIEMPO_MAX;
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
                try {
                    jugar();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
                break;
            case "RESPONDER":
                try {
                    responder();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
                break;
            case "COUNTDOWN":
                try {
                    countdown();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
                break;
        }

    }

    private void jugar() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
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

    private void countdown() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (contador == FIN_TURNO) {
            responder();
            reestablecerTemporizador();
        } else {
            pantallaPrincipal.establecerVisualTemporizador(contador);
            contador--;
        }
    }

    private void responder() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
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

        reestablecerTemporizador();
    }

    private void establecerTurno() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Preguntable pregunta = partida.obtenerPreguntaTurno();
        Jugador jugador = partida.obtenerJugadorTurno();
        pantallaPrincipal.establecerTurno(pregunta, jugador);
    }

    public void reestablecerTemporizador(){
        contador = TIEMPO_MAX;
    }

}
