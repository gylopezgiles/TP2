package edu.fiuba.algo3.modelo.entrega;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Ronda;
import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.excepciones.RondaSinPreguntaExcepcion;
import edu.fiuba.algo3.modelo.excepciones.TipoPreguntaNoImplementadaException;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.CreadorPregunta;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;
import edu.fiuba.algo3.modelo.pregunta.multiplechoice.MultipleChoiceConPenalidad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class Entrega2Test {

    @Test
    public void crearMultipleChoiceConPenalidadConOpciones() throws ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException {
        //Given
        String preguntaTexto = "Cuál es la capital de Ecuador?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta = new Opcion("Quito", esCorrecta);
        Opcion opcionIncorrecta = new Opcion("Sucre", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);

        //When
        Preguntable pregunta = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceConPenalidad, preguntaTexto, opciones);

        //Then
        Assertions.assertEquals(MultipleChoiceConPenalidad.class, pregunta.getClass());
    }

    @Test
    public void preguntaMultipleChoiceConPenalidadAsignaPuntajeCorrectamenteEligiendoTodasCorrectas() throws ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion, TipoPreguntaNoImplementadaException {
        //Given
        String preguntaTexto = "Qué países se encuentran en Asia?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("China", esCorrecta);
        Opcion opcion2Correcta = new Opcion("India", esCorrecta);
        Opcion opcion3Correcta = new Opcion("Japón", esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("España", !esCorrecta);
        Opcion opcion5Correcta = new Opcion("Tailandia", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Correcta, opcion4Incorrecta, opcion5Correcta);
        Preguntable multipleChoiceConPenalidad = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceConPenalidad, preguntaTexto, opciones);

        List<String> opcionesSeleccionadas = Arrays.asList("China", "India", "Japón", "Tailandia");
        Jugador jugador = new Jugador("jugador");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, multipleChoiceConPenalidad);

        //When
        ronda.responder(opcionesSeleccionadas);

        //Then
        Assertions.assertEquals(4, jugador.obtenerPuntos());
    }

    @Test
    public void preguntaMultipleChoiceConPenalidadAsignaPuntajeCorrectamenteEligiendoAlgunasCorrectas() throws ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion, TipoPreguntaNoImplementadaException {
        //Given
        String preguntaTexto = "Qué países se encuentran en Asia?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("China", esCorrecta);
        Opcion opcion2Correcta = new Opcion("India", esCorrecta);
        Opcion opcion3Correcta = new Opcion("Japón", esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("España", !esCorrecta);
        Opcion opcion5Correcta = new Opcion("Tailandia", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Correcta, opcion4Incorrecta, opcion5Correcta);
        Preguntable multipleChoiceConPenalidad = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceConPenalidad, preguntaTexto, opciones);

        List<String> opcionesSeleccionadas = Arrays.asList("China", "Japón");
        Jugador jugador = new Jugador("jugador");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, multipleChoiceConPenalidad);

        //When
        ronda.responder(opcionesSeleccionadas);

        //Then
        Assertions.assertEquals(2, jugador.obtenerPuntos());
    }

    @Test
    public void preguntaMultipleChoiceConPenalidadAsignaPuntajeCorrectamenteEligiendoAlgunasCorrectasYUnaIncorrecta() throws ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion, TipoPreguntaNoImplementadaException {
        //Given
        String preguntaTexto = "Qué países se encuentran en Asia?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("China", esCorrecta);
        Opcion opcion2Correcta = new Opcion("India", esCorrecta);
        Opcion opcion3Correcta = new Opcion("Japón", esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("España", !esCorrecta);
        Opcion opcion5Correcta = new Opcion("Tailandia", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Correcta, opcion4Incorrecta, opcion5Correcta);
        Preguntable multipleChoiceConPenalidad = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceConPenalidad, preguntaTexto, opciones);

        List<String> opcionesSeleccionadas = Arrays.asList("China", "Japón", "España");
        Jugador jugador = new Jugador("jugador");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, multipleChoiceConPenalidad);

        //When
        ronda.responder(opcionesSeleccionadas);

        //Then
        Assertions.assertEquals(-1, jugador.obtenerPuntos());
    }

    @Test
    public void preguntaMultipleChoiceConPenalidadAsignaPuntajeCorrectamenteEligiendoIncorrectas() throws ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion, TipoPreguntaNoImplementadaException {
        //Given
        String preguntaTexto = "Qué países se encuentran en Asia?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("China", esCorrecta);
        Opcion opcion2Incorrecta = new Opcion("Portugal", !esCorrecta);
        Opcion opcion3Correcta = new Opcion("Japón", esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("España", !esCorrecta);
        Opcion opcion5Incorrecta = new Opcion("Francia", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Incorrecta, opcion3Correcta, opcion4Incorrecta, opcion5Incorrecta);
        Preguntable multipleChoiceConPenalidad = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceConPenalidad, preguntaTexto, opciones);

        List<String> opcionesSeleccionadas = Arrays.asList("Portugal", "España", "Francia");
        Jugador jugador = new Jugador("jugador");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, multipleChoiceConPenalidad);

        //When
        ronda.responder(opcionesSeleccionadas);

        //Then
        Assertions.assertEquals(-3, jugador.obtenerPuntos());
    }

    // Tests de Multiplicadores
    @Test
    public void responderConMultiplicadorCorrectamentePorDosAVerdaderoFalsoPenalidadAplicaCorrectamenteElMultiplicador() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "La canción Dancing with Myself fue escrita por Billy Idol y Tony James";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta = new Opcion("Verdadero", esCorrecta);
        Opcion opcionIncorrecta = new Opcion("Falso", !esCorrecta);
        Preguntable verdaderoFalsoClasico = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoPenalidad, preguntaTexto ,Arrays.asList(opcionCorrecta, opcionIncorrecta));
        Jugador jugador = new Jugador("Jugador1");
        Ronda ronda = new Ronda(Arrays.asList(jugador), verdaderoFalsoClasico);

        //When
        ronda.responder(Arrays.asList("Verdadero"), Multiplicador.PorDos);

        // Then
        Assertions.assertEquals(2, jugador.obtenerPuntos());

    }

    @Test
    public void responderConMultiplicadorCorrectamentePorTresAVerdaderoFalsoPenalidadAplicaCorrectamenteElMultiplicador() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "Smoke on the Water es una canción de Blue Oyster Cult";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionIncorrecta = new Opcion("Verdadero", !esCorrecta);
        Opcion opcionCorrecta = new Opcion("Falso", esCorrecta);
        Preguntable verdaderoFalsoClasico = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoPenalidad, preguntaTexto ,Arrays.asList(opcionCorrecta, opcionIncorrecta));
        Jugador jugador = new Jugador("Jugador1");
        Ronda ronda = new Ronda(Arrays.asList(jugador), verdaderoFalsoClasico);

        //When
        ronda.responder(Arrays.asList("Falso"), Multiplicador.PorTres);

        // Then
        Assertions.assertEquals(3, jugador.obtenerPuntos());

    }

    @Test
    public void responderConMultiplicadorIncorrectamentePorDosAVerdaderoFalsoPenalidadAplicaCorrectamenteElMultiplicador() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "La canción Wicked Game fue lanzada en 2011";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionIncorrecta = new Opcion("Verdadero", !esCorrecta);
        Opcion opcionCorrecta = new Opcion("Falso", esCorrecta);
        Preguntable verdaderoFalsoClasico = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoPenalidad, preguntaTexto ,Arrays.asList(opcionCorrecta, opcionIncorrecta));
        Jugador jugador = new Jugador("Jugador1");
        Ronda ronda = new Ronda(Arrays.asList(jugador), verdaderoFalsoClasico);

        //When
        ronda.responder(Arrays.asList("Verdadero"), Multiplicador.PorDos);

        // Then
        Assertions.assertEquals(-2, jugador.obtenerPuntos());

    }

    @Test
    public void responderConMultiplicadorIncorrectamentePorTresAVerdaderoFalsoPenalidadAplicaCorrectamenteElMultiplicador() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "La canción Hazy Shade of Winter de Simon & Garfunkel tuvo un cover en 2019 para una serie de Netflix";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta = new Opcion("Verdadero", esCorrecta);
        Opcion opcionIncorrecta = new Opcion("Falso", !esCorrecta);
        Preguntable verdaderoFalsoClasico = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoPenalidad, preguntaTexto ,Arrays.asList(opcionCorrecta, opcionIncorrecta));
        Jugador jugador = new Jugador("Jugador1");
        Ronda ronda = new Ronda(Arrays.asList(jugador), verdaderoFalsoClasico);

        //When
        ronda.responder(Arrays.asList("Falso"), Multiplicador.PorTres);

        // Then
        Assertions.assertEquals(-3, jugador.obtenerPuntos());

    }

    @Test
    public void responderConMultiplicadorCorrectamentePorDosAMultipleChoicePenalidadAplicaCorrectamenteElMultiplicador() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "¿Cuáles de las siguientes canciones pertenecen a Bad Company?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Incorrecta = new Opcion("Highway Song", !esCorrecta);
        Opcion opcion2Correcta = new Opcion("Ready for Love", esCorrecta);
        Opcion opcion3Correcta = new Opcion("Bad Company", esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("After Dark", !esCorrecta);
        Preguntable multipleChoicePenalidad = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceConPenalidad, preguntaTexto ,Arrays.asList(opcion1Incorrecta, opcion2Correcta, opcion3Correcta, opcion4Incorrecta));
        Jugador jugador = new Jugador("Jugador1");
        Ronda ronda = new Ronda(Arrays.asList(jugador), multipleChoicePenalidad);

        //When
        ronda.responder(Arrays.asList("Ready for Love", "Bad Company"), Multiplicador.PorDos);

        //Then
        Assertions.assertEquals(4, jugador.obtenerPuntos());

    }

    @Test
    public void responderConMultiplicadorCorrectamentePorTresAMultipleChoicePenalidadAplicaCorrectamenteElMultiplicador() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "¿Cuáles de los siguientes artistas pertenecen al grupo ZZ Top?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Incorrecta = new Opcion("Joe Cocker", !esCorrecta);
        Opcion opcion2Correcta = new Opcion("Billy Gibbons", esCorrecta);
        Opcion opcion3Correcta = new Opcion("Frank Beard", esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("Bob Dylan", !esCorrecta);
        Opcion opcion5Correcta = new Opcion("Dusty Hill", esCorrecta);
        Preguntable multipleChoicePenalidad = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceConPenalidad, preguntaTexto ,Arrays.asList(opcion1Incorrecta, opcion2Correcta, opcion3Correcta, opcion4Incorrecta, opcion5Correcta));
        Jugador jugador = new Jugador("Jugador1");
        Ronda ronda = new Ronda(Arrays.asList(jugador), multipleChoicePenalidad);

        //When
        ronda.responder(Arrays.asList("Billy Gibbons", "Frank Beard", "Dusty Hill"), Multiplicador.PorTres);

        //Then
        Assertions.assertEquals(9, jugador.obtenerPuntos());

    }

    @Test
    public void responderConMultiplicadorCorrectamentePorDosAMultipleChoicePenalidadAplicaIncorrectamenteElMultiplicador() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "A cual de las siguientes bandas pertenece la canción (Don't Fear) The Reaper";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Incorrecta = new Opcion("Asia", !esCorrecta);
        Opcion opcion2Correcta = new Opcion("Blue Oyster Club", esCorrecta);
        Opcion opcion3Incorrecta = new Opcion("The Doors", !esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("Eagles", !esCorrecta);
        Preguntable multipleChoicePenalidad = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceConPenalidad, preguntaTexto ,Arrays.asList(opcion1Incorrecta, opcion2Correcta, opcion3Incorrecta, opcion4Incorrecta));
        Jugador jugador = new Jugador("Jugador1");
        Ronda ronda = new Ronda(Arrays.asList(jugador), multipleChoicePenalidad);

        //When
        ronda.responder(Arrays.asList("The Doors"), Multiplicador.PorDos);

        //Then
        Assertions.assertEquals(-2, jugador.obtenerPuntos());

    }

    @Test
    public void responderConMultiplicadorCorrectamentePorTresAMultipleChoicePenalidadAplicaIncorrectamenteElMultiplicador() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "En qué año se lanzó la canción Paradise by the Dashboard Light";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Incorrecta = new Opcion("1978", !esCorrecta);
        Opcion opcion2Correcta = new Opcion("1977", esCorrecta);
        Opcion opcion3Incorrecta = new Opcion("1980", !esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("1972", !esCorrecta);
        Opcion opcion5Incorrecta = new Opcion("1975", !esCorrecta);
        Preguntable multipleChoicePenalidad = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceConPenalidad, preguntaTexto ,Arrays.asList(opcion1Incorrecta, opcion2Correcta, opcion3Incorrecta, opcion4Incorrecta, opcion5Incorrecta));
        Jugador jugador = new Jugador("Jugador1");
        Ronda ronda = new Ronda(Arrays.asList(jugador), multipleChoicePenalidad);

        //When
        ronda.responder(Arrays.asList("1978"), Multiplicador.PorTres);

        //Then
        Assertions.assertEquals(-3, jugador.obtenerPuntos());

    }

    @Test
    public void responderConMultiplicadorPorDosAMultipleChoiceClasicoNoLoDebeAplicar() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "¿A qué cantante pertenece la canción Night Moves?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Incorrecta = new Opcion("Jim Croce", !esCorrecta);
        Opcion opcion2Correcta = new Opcion("Bob Seger", esCorrecta);
        Opcion opcion3Incorrecta = new Opcion("Ted Nugent", !esCorrecta);
        Preguntable multipleChoiceClasico = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceClasico, preguntaTexto ,Arrays.asList(opcion1Incorrecta, opcion2Correcta, opcion3Incorrecta));
        Jugador jugador = new Jugador("Jugador1");
        Ronda ronda = new Ronda(Arrays.asList(jugador), multipleChoiceClasico);

        //When
        ronda.responder(Arrays.asList("Bob Seger"), Multiplicador.PorDos);

        //Then
        Assertions.assertEquals(1, jugador.obtenerPuntos());
    }

    @Test
    public void responderConMultiplicadorPorTresAMultipleChoiceClasicoNoLoDebeAplicar() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "¿A qué cantante pertenece la canción Night Moves?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Incorrecta = new Opcion("Jim Croce", !esCorrecta);
        Opcion opcion2Correcta = new Opcion("Bob Seger", esCorrecta);
        Opcion opcion3Incorrecta = new Opcion("Ted Nugent", !esCorrecta);
        Preguntable multipleChoiceClasico = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceClasico, preguntaTexto ,Arrays.asList(opcion1Incorrecta, opcion2Correcta, opcion3Incorrecta));
        Jugador jugador = new Jugador("Jugador1");
        Ronda ronda = new Ronda(Arrays.asList(jugador), multipleChoiceClasico);

        //When
        ronda.responder(Arrays.asList("Bob Seger"), Multiplicador.PorTres);

        //Then
        Assertions.assertEquals(1, jugador.obtenerPuntos());
    }

    @Test
    public void responderConMultiplicadorPorDosAMultipleChoiceParcialNoLoDebeAplicar() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
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

        //When

        ronda.responder(Arrays.asList("Guns N' Roses"), Multiplicador.PorDos);

        //Then
        Assertions.assertEquals(1, jugador.obtenerPuntos());

    }

    @Test
    public void responderConMultiplicadorPorTresAMultipleChoiceParcialNoLoDebeAplicar() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
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

        //When

        ronda.responder(Arrays.asList("Guns N' Roses"), Multiplicador.PorTres);

        //Then
        Assertions.assertEquals(1, jugador.obtenerPuntos());

    }



    @Test
    public void OrderedChoiceAsignaPuntajeCorrectamenteConOpcionesOrdenadas() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
        String preguntaTexto = "Orden de las letras vocales";
        boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1 = new Opcion("A", esCorrecta);
        Opcion opcion2 = new Opcion("E",esCorrecta);
        Opcion opcion3 = new Opcion("I", esCorrecta);
        Opcion opcion4 = new Opcion("O", esCorrecta);
        Opcion opcion5 = new Opcion("U", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5);
        Preguntable orderedChoice = CreadorPregunta.crearPregunta(TipoPregunta.OrderedChoice,preguntaTexto,opciones);
        List<String> opcionesSeleccionadas = Arrays.asList("A", "E", "I", "O", "U");
        Jugador jugador = new Jugador("Jugador1");
        Ronda ronda = new Ronda(Arrays.asList(jugador), orderedChoice);

        ronda.responder(opcionesSeleccionadas);

        Assertions.assertEquals(1,jugador.obtenerPuntos());
    }

    @Test
    public void OrderedChoiceAsignaPuntajeCorrectamenteConOpcionesDesrdenadas() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
        String preguntaTexto = "Orden de las letras vocales";
        boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1 = new Opcion("A", esCorrecta);
        Opcion opcion2 = new Opcion("E",esCorrecta);
        Opcion opcion3 = new Opcion("I", esCorrecta);
        Opcion opcion4 = new Opcion("O", esCorrecta);
        Opcion opcion5 = new Opcion("U", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5);
        Preguntable orderedChoice = CreadorPregunta.crearPregunta(TipoPregunta.OrderedChoice,preguntaTexto,opciones);
        List<String> opcionesSeleccionadas = Arrays.asList("O", "U", "E", "A", "I");
        Jugador jugador = new Jugador("Jugador1");
        Ronda ronda = new Ronda(Arrays.asList(jugador), orderedChoice);

        ronda.responder(opcionesSeleccionadas);

        Assertions.assertEquals(0,jugador.obtenerPuntos());

    }

}
