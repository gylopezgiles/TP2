package edu.fiuba.algo3.modelo.entrega;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Ronda;
import edu.fiuba.algo3.modelo.excepciones.MultiplicadorExcepcion;
import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.excepciones.RondaSinPreguntaExcepcion;
import edu.fiuba.algo3.modelo.excepciones.TipoPreguntaNoImplementadaException;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.CreadorPregunta;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Entrega2Test {

    // Tests de Multiplicadores
    @Test
    public void responderConMultiplicadorCorrectamentePorDosAVerdaderoFalsoPenalidadAplicaCorrectamenteElMultiplicador() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, MultiplicadorExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "La canción Dancing with Myself fue escrita por Billy Idol y Tony James";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta = new Opcion("Verdadero", esCorrecta);
        Opcion opcionIncorrecta = new Opcion("Falso", !esCorrecta);
        Preguntable verdaderoFalsoClasico = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoPenalidad, preguntaTexto ,Arrays.asList(opcionCorrecta, opcionIncorrecta));
        Jugador jugador = new Jugador("Jugador1");
        Ronda ronda = new Ronda(Arrays.asList(jugador), verdaderoFalsoClasico);

        //When
        ronda.responder(jugador, Arrays.asList(opcionCorrecta), Multiplicador.PorDos);

        // Then
        Assertions.assertEquals(2, jugador.obtenerPuntos());

    }

    @Test
    public void responderConMultiplicadorCorrectamentePorTresAVerdaderoFalsoPenalidadAplicaCorrectamenteElMultiplicador() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, MultiplicadorExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "Smoke on the Water es una canción de Blue Oyster Cult";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionIncorrecta = new Opcion("Verdadero", !esCorrecta);
        Opcion opcionCorrecta = new Opcion("Falso", esCorrecta);
        Preguntable verdaderoFalsoClasico = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoPenalidad, preguntaTexto ,Arrays.asList(opcionCorrecta, opcionIncorrecta));
        Jugador jugador = new Jugador("Jugador1");
        Ronda ronda = new Ronda(Arrays.asList(jugador), verdaderoFalsoClasico);

        //When
        ronda.responder(jugador, Arrays.asList(opcionCorrecta), Multiplicador.PorTres);

        // Then
        Assertions.assertEquals(3, jugador.obtenerPuntos());

    }

    @Test
    public void responderConMultiplicadorIncorrectamentePorDosAVerdaderoFalsoPenalidadAplicaCorrectamenteElMultiplicador() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, MultiplicadorExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "La canción Wicked Game fue lanzada en 2011";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionIncorrecta = new Opcion("Verdadero", !esCorrecta);
        Opcion opcionCorrecta = new Opcion("Falso", esCorrecta);
        Preguntable verdaderoFalsoClasico = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoPenalidad, preguntaTexto ,Arrays.asList(opcionCorrecta, opcionIncorrecta));
        Jugador jugador = new Jugador("Jugador1");
        Ronda ronda = new Ronda(Arrays.asList(jugador), verdaderoFalsoClasico);

        //When
        ronda.responder(jugador, Arrays.asList(opcionIncorrecta), Multiplicador.PorDos);

        // Then
        Assertions.assertEquals(-2, jugador.obtenerPuntos());

    }

    @Test
    public void responderConMultiplicadorIncorrectamentePorTresAVerdaderoFalsoPenalidadAplicaCorrectamenteElMultiplicador() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, MultiplicadorExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "La canción Hazy Shade of Winter de Simon & Garfunkel tuvo un cover en 2019 para una serie de Netflix";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta = new Opcion("Verdadero", esCorrecta);
        Opcion opcionIncorrecta = new Opcion("Falso", !esCorrecta);
        Preguntable verdaderoFalsoClasico = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoPenalidad, preguntaTexto ,Arrays.asList(opcionCorrecta, opcionIncorrecta));
        Jugador jugador = new Jugador("Jugador1");
        Ronda ronda = new Ronda(Arrays.asList(jugador), verdaderoFalsoClasico);

        //When
        ronda.responder(jugador, Arrays.asList(opcionIncorrecta), Multiplicador.PorTres);

        // Then
        Assertions.assertEquals(-3, jugador.obtenerPuntos());

    }

    @Test
    public void responderConMultiplicadorAMultipleChoiceClasicoLanzaExcepcion() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion {
        //Given
        String preguntaTexto = "¿A qué cantante pertenece la canción Night Moves?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Incorrecta = new Opcion("Jim Croce", !esCorrecta);
        Opcion opcion2Correcta = new Opcion("Bob Seger", esCorrecta);
        Opcion opcion3Incorrecta = new Opcion("Ted Nugent", esCorrecta);
        Preguntable multipleChoiceClasico = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceClasico, preguntaTexto ,Arrays.asList(opcion1Incorrecta, opcion2Correcta, opcion3Incorrecta));
        Jugador jugador = new Jugador("Jugador1");
        Ronda ronda = new Ronda(Arrays.asList(jugador), multipleChoiceClasico);

        //WhenThen
        Assertions.assertThrows(MultiplicadorExcepcion.class, () -> ronda.responder(jugador, Arrays.asList(opcion2Correcta), Multiplicador.PorDos));
        Assertions.assertThrows(MultiplicadorExcepcion.class, () -> ronda.responder(jugador, Arrays.asList(opcion1Incorrecta), Multiplicador.PorTres));

    }

    @Test
    public void responderConMultiplicadorAMultipleChoiceParcialLanzaExcepcion() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion {
        //Given
        String preguntaTexto = "La canción Knockin' on Heaven's Door del cantante Bob Dylan, fue versionada por cuáles de las siguientes bandas/cantantes.";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Incorrecta = new Opcion("Queen", !esCorrecta);
        Opcion opcion2Correcta = new Opcion("Guns N' Roses", esCorrecta);
        Opcion opcion3Correcta = new Opcion("Avril Lavigne", esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("Led Zeppelin", !esCorrecta);
        Preguntable multipleChoiceParcial = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceParcial, preguntaTexto ,Arrays.asList(opcion1Incorrecta, opcion2Correcta, opcion3Correcta, opcion4Incorrecta));
        Jugador jugador = new Jugador("Jugador1");
        Ronda ronda = new Ronda(Arrays.asList(jugador), multipleChoiceParcial);

        //WhenThen
        Assertions.assertThrows(MultiplicadorExcepcion.class, () -> ronda.responder(jugador, Arrays.asList(opcion1Incorrecta, opcion2Correcta), Multiplicador.PorDos));
        Assertions.assertThrows(MultiplicadorExcepcion.class, () -> ronda.responder(jugador, Arrays.asList(opcion2Correcta), Multiplicador.PorTres));

    }
}
