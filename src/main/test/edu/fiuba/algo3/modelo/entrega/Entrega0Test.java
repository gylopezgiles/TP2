package edu.fiuba.algo3.modelo.entrega;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Ronda;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.pregunta.CreadorPregunta;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class Entrega0Test {

    @Test
    public void crearVerdaderoFalsoClasicoConOpciones() throws ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException {
        //Given
        Opcion opcionCorrecta = new Opcion("Verdadero", Boolean.TRUE);
        Opcion opcionIncorrecta = new Opcion("Falso", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        String preguntaTexto = "pregunta?";

        //When
        Preguntable verdaderoFalsoClasico = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoClasico, preguntaTexto, opciones);

        //Then
        Assertions.assertEquals(2, verdaderoFalsoClasico.obtenerOpciones().size());
        Assertions.assertEquals(opciones, verdaderoFalsoClasico.obtenerOpciones());
        Assertions.assertEquals(opcionCorrecta, verdaderoFalsoClasico.obtenerOpciones().stream().filter(opcion -> opcion.esCorrecta()).findAny().orElse(null));
    }

    @Test
    public void preguntaVerdaderoFalsoClasicoAsignaPuntajeCorrectamente() throws RondaSinPreguntaExcepcion, ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException {
        //Given
        Opcion opcionCorrecta = new Opcion("Verdadero", Boolean.TRUE);
        Opcion opcionIncorrecta = new Opcion("Falso", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        String preguntaTexto = "pregunta?";
        Preguntable verdaderoFalsoClasico = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoClasico, preguntaTexto, opciones);

        List<Opcion> opcionSeleccionada = Arrays.asList(opcionCorrecta);
        Jugador jugador = new Jugador("jugador");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, verdaderoFalsoClasico);

        //When
        ronda.responder(jugador, opcionSeleccionada);

        //Then
        Assertions.assertEquals(1, jugador.obtenerPuntos());
    }
}
