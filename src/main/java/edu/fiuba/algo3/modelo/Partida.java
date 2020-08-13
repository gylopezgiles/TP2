package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.excepciones.RondaSinPreguntaExcepcion;
import edu.fiuba.algo3.modelo.excepciones.TipoPreguntaNoImplementadaException;
import edu.fiuba.algo3.modelo.pregunta.CreadorPregunta;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;

import java.util.*;
import java.util.stream.Collectors;

public class Partida {

    private List<Jugador> jugadores;
    private List<Preguntable> preguntas;
    private Iterator<Preguntable> preguntasIterator;
    private Ronda ronda;

    public Partida(List<String> nombresJugadores){
        this.jugadores = generarJugadores(nombresJugadores);
        cargarPreguntas();
        this.preguntasIterator = preguntas.iterator();
        this.ronda = new Ronda(jugadores, preguntasIterator.next());
    }

    private List<Jugador> generarJugadores(List<String> nombresJugadores){
        List<Jugador> jugadores = nombresJugadores.stream()
                .map(nombre -> new Jugador(nombre))
                .collect(Collectors.toList());
        return jugadores;
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
            ronda.restablecerRonda(preguntasIterator.next());
        }
    }

    public Jugador obtenerJugadorTurno(){
        return ronda.obtenerJugadorTurno();
    }

    public void responder(List<String> opcionesSeleccionadas){
        //TODO: Manejar las excepciones
        Preguntable pregunta = ronda.obtenerPregunta();
        List<Opcion> opciones = pregunta.obtenerOpcionesPorNombre(opcionesSeleccionadas);
        try {
            ronda.responder(opciones);
        } catch (RondaSinPreguntaExcepcion rondaSinPreguntaExcepcion) {
            rondaSinPreguntaExcepcion.printStackTrace();
        }
    }

    public List<Jugador> obtenerJugadores() {
        return jugadores;
    }
}
