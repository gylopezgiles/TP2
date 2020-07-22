package edu.fiuba.algo3.modelo.pregunta;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Ronda;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class VerdaderoFalsoClasicoTest {

    @Test
    void crearVerdaderoFalsoClasicoConOpciones(){
        //Test para Alan
        Opcion opcionCorrecta = new Opcion("Verdadero", Boolean.TRUE);
        Opcion opcionIncorrecta = new Opcion("Falso", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);

        Preguntable verdaderoFalsoClasico = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoClasico, opciones);

        Assertions.assertEquals(2, verdaderoFalsoClasico.obtenerOpciones().size());
        Assertions.assertEquals(opciones, verdaderoFalsoClasico.obtenerOpciones());
        Assertions.assertEquals(opcionCorrecta, verdaderoFalsoClasico.obtenerOpciones().stream().filter(opcion -> opcion.esCorrecta()).findAny().orElse(null));
    }

    @Test
    void preguntaVerdaderoFalsoClasicoAsignaPuntajeCorrectamente(){
        //Test para Mica
        Opcion opcionCorrecta = new Opcion("Verdadero", Boolean.TRUE);
        Opcion opcionIncorrecta = new Opcion("Falso", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        Preguntable verdaderoFalsoClasico = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoClasico, opciones);
        List<Opcion> opcionSeleccionada = Arrays.asList(opcionCorrecta);
        Jugador jugador = new Jugador("jugador");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, verdaderoFalsoClasico);

        ronda.responder(jugador, opcionSeleccionada);

        Assertions.assertEquals(1, jugador.obtenerPuntos());
    }
}
