package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.excepciones.TipoPreguntaNoImplementadaException;
import edu.fiuba.algo3.modelo.pregunta.CreadorPregunta;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PartidaTest {
    private List<Preguntable> preguntas;
    Preguntable verdaderoFalsoClasico;

    @BeforeEach
    public void setUp() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion {
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionIncorrecta = new Opcion("Verdadero",!esCorrecta);
        Opcion opcionCorrecta = new Opcion("Falso", esCorrecta);
        verdaderoFalsoClasico = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoClasico, "La canción Feelling Good fue escrita por Muse", Arrays.asList(opcionIncorrecta, opcionCorrecta));
        preguntas = new LinkedList<>();
        preguntas.add(verdaderoFalsoClasico);
    }

    @Test
    public void debeCrearUnaNuevaPartidaEnBaseANombresDeJugadores(){
        List<String> nombresJugadores = Arrays.asList("Cecilia", "Leandro");

        Partida partida = new Partida(nombresJugadores, preguntas);

        Jugador jugadorTurno = partida.obtenerJugadorTurno();

        Assertions.assertEquals("Cecilia", jugadorTurno.obtenerNombre());
        Assertions.assertEquals(Boolean.FALSE, partida.esPartidaFinalizada());
        Assertions.assertEquals(verdaderoFalsoClasico, partida.obtenerPreguntaTurno());
        Assertions.assertEquals(2, partida.obtenerJugadores().size());
    }

    @Test
    public void responderHastaFinalDePartida(){
        List<String> nombresJugadores = Arrays.asList("Cecilia", "Leandro");

        Partida partida = new Partida(nombresJugadores, preguntas);

        Assertions.assertEquals(Boolean.FALSE, partida.esPartidaFinalizada());

        partida.responder(Arrays.asList("Verdadero"));

        Assertions.assertEquals(Boolean.FALSE, partida.esPartidaFinalizada());

        partida.responder(Arrays.asList("Falso"));

        Assertions.assertEquals(Boolean.TRUE, partida.esPartidaFinalizada());

    }

    @Test
    public void debeAvanzarASiguienteRonda() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion {
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionIncorrecta = new Opcion("Verdadero", !esCorrecta);
        Opcion opcionCorrecta = new Opcion("Falso", esCorrecta);
        Preguntable siguientePregunta = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoPenalidad, "La banda Kiss tiene 6 integrantes", Arrays.asList(opcionIncorrecta, opcionCorrecta));
        preguntas.add(siguientePregunta);
        List<String> nombresJugadores = Arrays.asList("Cecilia", "Leandro");

        Partida partida = new Partida(nombresJugadores, preguntas);

        Assertions.assertEquals(Boolean.FALSE, partida.esPartidaFinalizada());

        partida.responder(Arrays.asList("Verdadero"));

        Assertions.assertEquals(Boolean.FALSE, partida.esPartidaFinalizada());

        partida.responder(Arrays.asList("Falso"));

        Assertions.assertEquals(Boolean.FALSE, partida.esPartidaFinalizada());
        Assertions.assertEquals(siguientePregunta, partida.obtenerPreguntaTurno());
    }

}
