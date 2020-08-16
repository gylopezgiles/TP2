package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.excepciones.RondaSinPreguntaExcepcion;
import edu.fiuba.algo3.modelo.excepciones.TipoPreguntaNoImplementadaException;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.CreadorPregunta;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;

import java.util.*;
import java.util.stream.Collectors;

public class Partida {

    private static final Integer MAX_EXCLUSIVIDAD_POR_JUGADOR = 2;
    private List<Jugador> jugadores;
    private List<Preguntable> preguntas;
    private Iterator<Preguntable> preguntasIterator;
    private Map<Jugador, Integer> exclusividadPorJugador;
    private Ronda ronda;

    public Partida(List<String> nombresJugadores){
        this.jugadores = generarJugadores(nombresJugadores);
        cargarPreguntas();
        this.preguntasIterator = preguntas.iterator();
        this.ronda = new Ronda(jugadores, preguntasIterator.next());
        this.exclusividadPorJugador = new HashMap<>();
        establecerExclusividadPorJugador();
    }

    private List<Jugador> generarJugadores(List<String> nombresJugadores){
        List<Jugador> jugadores = nombresJugadores.stream()
                .map(nombre -> new Jugador(nombre))
                .collect(Collectors.toList());
        return jugadores;
    }

    private void establecerExclusividadPorJugador(){
        jugadores.stream().forEach(jugador -> exclusividadPorJugador.put(jugador, 0));
    }

    private void cargarPreguntas(){
        //TODO: Carga de preguntas a traves de un json
        String preguntaTexto = "La canción Hazy Shade of Winter de Simon & Garfunkel tuvo un cover en 2019 para una serie de Netflix";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta = new Opcion("Verdadero", esCorrecta);
        Opcion opcionIncorrecta = new Opcion("Falso", !esCorrecta);
        List<Preguntable> preguntables = new LinkedList<>();
        try {
            Preguntable verdaderoFalsoClasico = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoClasico, preguntaTexto , Arrays.asList(opcionCorrecta, opcionIncorrecta));
            preguntables.add(verdaderoFalsoClasico);
        } catch (ParametrosInvalidosExcepcion parametrosInvalidosExcepcion) {
            parametrosInvalidosExcepcion.printStackTrace();
        } catch (TipoPreguntaNoImplementadaException e) {
            e.printStackTrace();
        }
        this.preguntas = preguntables;
    }

    public Boolean esPartidaFinalizada(){
        return ronda.esRondaFinalizada() && !preguntasIterator.hasNext();
    }

    public Preguntable obtenerPreguntaTurno(){
        return ronda.obtenerPreguntaTurno();
    }

    public void establecerTurno(){
        if(ronda.esRondaFinalizada()){
            ronda.aplicarPuntajes();
            ronda.restablecerRonda(preguntasIterator.next());
        }
    }

    public Jugador obtenerJugadorTurno(){
        return ronda.obtenerJugadorTurno();
    }

    public <T> void responder(T opcionesSeleccionadas, Boolean aplicaExclusividad){
        //TODO: Manejar las excepciones
        Jugador jugadorTurno = obtenerJugadorTurno();
        if (aplicaExclusividad && exclusividadPorJugador.get(jugadorTurno) < MAX_EXCLUSIVIDAD_POR_JUGADOR) {
            exclusividadPorJugador.replace(jugadorTurno, exclusividadPorJugador.get(jugadorTurno)+1);
            try {
                ronda.responder(opcionesSeleccionadas, aplicaExclusividad);
            } catch (RondaSinPreguntaExcepcion rondaSinPreguntaExcepcion) {
                rondaSinPreguntaExcepcion.printStackTrace();
            }
        } else {
            responder(opcionesSeleccionadas, Multiplicador.PorDefecto);
        }
    }

    public <T> void responder(T opcionesSeleccionadas, MultiplicableStrategy multiplicador){
        //TODO: Manejar las excepciones
        try {
            ronda.responder(opcionesSeleccionadas);
        } catch (RondaSinPreguntaExcepcion rondaSinPreguntaExcepcion) {
            rondaSinPreguntaExcepcion.printStackTrace();
        }
    }

    public List<Jugador> obtenerJugadores() {
        return jugadores;
    }
}
