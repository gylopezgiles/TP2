package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.interfazGrafica.PantallaPrincipal;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Partida;
import edu.fiuba.algo3.modelo.excepciones.NombresInvalidosExcepcion;
import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.cargador.CargadorPreguntas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.util.List;

public class ControladorPanel implements ActionListener {

    private final static int TIEMPO_MAX = 20;
    private static final int FIN_TURNO = -1;

    private int contador;
    private PantallaPrincipal pantallaPrincipal;
    private Partida partida;


    public ControladorPanel(PantallaPrincipal pantallaPrincipal){
        this.pantallaPrincipal = pantallaPrincipal;
        this.contador = TIEMPO_MAX;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch(comando) {
            case "JUGAR":
                try {
                    jugar();
                } catch (NombresInvalidosExcepcion nombresInvalidosExcepcion) {
                    pantallaPrincipal.mostrarMensajeNombresJugadoresInvalidos(nombresInvalidosExcepcion.obtenerDescripcion());
                } catch (ParametrosInvalidosExcepcion noHayPreguntasCargadas) {
                    pantallaPrincipal.mostrarMensajePreguntasNoCargadas(noHayPreguntasCargadas.obtenerDescripcion());
                }
                break;
            case "RESPONDER":
                responder();
                break;
            case "COUNTDOWN":
                countdown();
                break;
        }

    }

    private void jugar() throws NombresInvalidosExcepcion, ParametrosInvalidosExcepcion {
        List<String> nombresJugadores = pantallaPrincipal.obtenerJugadores();
        if(nombresJugadores.stream().anyMatch(jugador -> jugador.isEmpty())) {
            throw new NombresInvalidosExcepcion("Deben ingresarse los nombres de ambos jugadores");
        }
        partida = new Partida(nombresJugadores, CargadorPreguntas.obtenerInstancia().obtenerPreguntas());
        pantallaPrincipal.iniciarPartida();
        establecerTurno();
    }

    private void countdown(){
        if (contador == FIN_TURNO) {
            responder();
            reestablecerTemporizador();
        } else {
            pantallaPrincipal.establecerVisualTemporizador(contador);
            contador--;
        }
    }

    private void responder(){
        Object opcionesSeleccionadas = pantallaPrincipal.obtenerOpcionesSeleccionadas();
        Boolean exclusividadSeleccionada = pantallaPrincipal.obtenerExclusividad();
        Multiplicador multiplicador = pantallaPrincipal.obtenerMultiplicador();
        partida.responder(opcionesSeleccionadas, multiplicador, exclusividadSeleccionada);
        if(partida.esPartidaFinalizada()){
            pantallaPrincipal.finalizarPartida(partida.obtenerJugadores());
        } else {
            establecerTurno();
        }

        reestablecerTemporizador();
    }

    private void establecerTurno(){
        Preguntable pregunta = partida.obtenerPreguntaTurno();
        Jugador jugador = partida.obtenerJugadorTurno();
        pantallaPrincipal.establecerTurno(pregunta, jugador);
    }

    public void reestablecerTemporizador(){
        contador = TIEMPO_MAX;
    }

}
