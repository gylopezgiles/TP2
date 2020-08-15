package edu.fiuba.algo3.modelo.entrega;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Ronda;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.pregunta.CreadorPregunta;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;
import edu.fiuba.algo3.modelo.pregunta.verdaderofalso.VerdaderoFalsoClasico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class Entrega0Test {

    @Test
    public void crearVerdaderoFalsoClasicoConOpciones() throws ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException {
        //Given
        String preguntaTexto = "¿Los Elefantes son los mamiferos TERRESTRES mas grandes del mundo?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta = new Opcion("Verdadero", esCorrecta);
        Opcion opcionIncorrecta = new Opcion("Falso", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);

        //When
        Preguntable verdaderoFalsoClasico = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoClasico, preguntaTexto, opciones);

        //Then

        Assertions.assertEquals(VerdaderoFalsoClasico.class, verdaderoFalsoClasico.getClass());
    }

    @Test
    public void preguntaVerdaderoFalsoClasicoAsignaPuntajeCorrectamente() throws RondaSinPreguntaExcepcion, ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException {
        //Given
        String preguntaTexto = "¿Los Elefantes son los mamiferos mas grandes del mundo?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta = new Opcion("Falso", esCorrecta);
        Opcion opcionIncorrecta = new Opcion("Verdadero", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        Preguntable verdaderoFalsoClasico = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoClasico, preguntaTexto, opciones);

        List<String> opcionSeleccionada = Arrays.asList("Falso");
        Jugador jugador = new Jugador("Diego");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, verdaderoFalsoClasico);

        //When
        ronda.responder(opcionSeleccionada);

        //Then
        Assertions.assertEquals(1, jugador.obtenerPuntos());
    }
}
