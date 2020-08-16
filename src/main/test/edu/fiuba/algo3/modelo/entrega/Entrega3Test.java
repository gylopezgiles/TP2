package edu.fiuba.algo3.modelo.entrega;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Ronda;
import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.excepciones.RondaSinPreguntaExcepcion;
import edu.fiuba.algo3.modelo.excepciones.TipoPreguntaNoImplementadaException;
import edu.fiuba.algo3.modelo.pregunta.CreadorPregunta;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class Entrega3Test {

    @Test
    public void seleccionarExclusividadConVerdaderoFalsoPenalidadNoModificaPuntaje() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "¿5+9?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionIncorrecta = new Opcion("8", !esCorrecta);
        Opcion opcionCorrecta = new Opcion("14", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        Preguntable verdaderoFalsoPenalidad = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoPenalidad, preguntaTexto , opciones);
        Jugador jugador1 = new Jugador("Jugador1");
        Jugador jugador2 = new Jugador("Jugador2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, verdaderoFalsoPenalidad);
        List<String> opcionSeleccionada1 = Arrays.asList("14");
        List<String> opcionSeleccionada2 = Arrays.asList("8");
        Boolean aplicaExclusividad = Boolean.TRUE;

        //When
        ronda.responder(opcionSeleccionada1, aplicaExclusividad);
        ronda.responder(opcionSeleccionada2, !aplicaExclusividad);
        ronda.aplicarPuntajes();

        //Then
        Assertions.assertEquals(1, jugador1.obtenerPuntos());
        Assertions.assertEquals(-1, jugador2.obtenerPuntos());
    }

    @Test
    public void seleccionarExclusividadConMultipleChoicePenalidadNoModificaPuntaje() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "¿En qué países se habla español?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("España", esCorrecta);
        Opcion opcion2Incorrecta = new Opcion("Italia", !esCorrecta);
        Opcion opcion3Correcta = new Opcion("Argentina", esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("Portugal", !esCorrecta);
        Opcion opcion5Incorrecta = new Opcion("Canadá", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Incorrecta, opcion3Correcta, opcion4Incorrecta, opcion5Incorrecta);
        Preguntable multipleChoiceConPenalidad = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceConPenalidad, preguntaTexto, opciones);
        Jugador jugador1 = new Jugador("Jugador1");
        Jugador jugador2 = new Jugador("Jugador2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, multipleChoiceConPenalidad);
        List<String> opcionesSeleccionadas1 = Arrays.asList("España", "Argentina");
        List<String> opcionesSeleccionadas2 = Arrays.asList("España", "Portugal");
        Boolean aplicaExclusividad = Boolean.TRUE;

        //When
        ronda.responder(opcionesSeleccionadas1, aplicaExclusividad);
        ronda.responder(opcionesSeleccionadas2, !aplicaExclusividad);
        ronda.aplicarPuntajes();

        //Then
        Assertions.assertEquals(2, jugador1.obtenerPuntos());
        Assertions.assertEquals(-1, jugador2.obtenerPuntos());
    }

    @Test
    public void seleccionarUnaVezExclusividadConMultipleChoiceClasicoDuplicaSoloElPuntajeDelJugadorQueRespondeCorrectamente() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "¿En qué países se habla español?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("España", esCorrecta);
        Opcion opcion2Incorrecta = new Opcion("Italia", !esCorrecta);
        Opcion opcion3Correcta = new Opcion("Argentina", esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("Portugal", !esCorrecta);
        Opcion opcion5Incorrecta = new Opcion("Canadá", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Incorrecta, opcion3Correcta, opcion4Incorrecta, opcion5Incorrecta);
        Preguntable multipleChoiceClasico = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceClasico, preguntaTexto , opciones);
        Jugador jugador1 = new Jugador("Jugador1");
        Jugador jugador2 = new Jugador("Jugador2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, multipleChoiceClasico);
        List<String> opcionesSeleccionadas1 = Arrays.asList("España", "Argentina");
        List<String> opcionesSeleccionadas2 = Arrays.asList("España", "Portugal");
        Boolean aplicaExclusividad = Boolean.TRUE;

        //When
        ronda.responder(opcionesSeleccionadas1, aplicaExclusividad);
        ronda.responder(opcionesSeleccionadas2, !aplicaExclusividad);
        ronda.aplicarPuntajes();

        //Then
        Assertions.assertEquals(2, jugador1.obtenerPuntos());
        Assertions.assertEquals(0, jugador2.obtenerPuntos());
    }

    @Test
    public void seleccionarDosVecesExclusividadConMultipleChoiceClasicoCuadruplicaSoloElPuntajeDelJugadorQueRespondeCorrectamente() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "¿En qué países se habla español?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("España", esCorrecta);
        Opcion opcion2Incorrecta = new Opcion("Italia", !esCorrecta);
        Opcion opcion3Correcta = new Opcion("Argentina", esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("Portugal", !esCorrecta);
        Opcion opcion5Incorrecta = new Opcion("Canadá", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Incorrecta, opcion3Correcta, opcion4Incorrecta, opcion5Incorrecta);
        Preguntable multipleChoiceClasico = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceClasico, preguntaTexto , opciones);
        Jugador jugador1 = new Jugador("Jugador1");
        Jugador jugador2 = new Jugador("Jugador2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, multipleChoiceClasico);
        List<String> opcionesSeleccionadas1 = Arrays.asList("España", "Portugal");
        List<String> opcionesSeleccionadas2 = Arrays.asList("España", "Argentina");
        Boolean aplicaExclusividad = Boolean.TRUE;

        //When
        ronda.responder(opcionesSeleccionadas1, aplicaExclusividad);
        ronda.responder(opcionesSeleccionadas2, aplicaExclusividad);
        ronda.aplicarPuntajes();

        //Then
        Assertions.assertEquals(0, jugador1.obtenerPuntos());
        Assertions.assertEquals(4, jugador2.obtenerPuntos());
    }

    @Test
    public void seleccionarExclusividadConMultipleChoiceClasicoNoSumaPuntajesSiAmbosRespondenCorrectamente() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "¿En qué países se habla español?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("España", esCorrecta);
        Opcion opcion2Incorrecta = new Opcion("Italia", !esCorrecta);
        Opcion opcion3Correcta = new Opcion("Argentina", esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("Portugal", !esCorrecta);
        Opcion opcion5Incorrecta = new Opcion("Canadá", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Incorrecta, opcion3Correcta, opcion4Incorrecta, opcion5Incorrecta);
        Preguntable multipleChoiceClasico = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceClasico, preguntaTexto , opciones);
        Jugador jugador1 = new Jugador("Jugador1");
        Jugador jugador2 = new Jugador("Jugador2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, multipleChoiceClasico);
        List<String> opcionesSeleccionadas1 = Arrays.asList("España", "Argentina");
        List<String> opcionesSeleccionadas2 = Arrays.asList("España", "Argentina");
        Boolean aplicaExclusividad = Boolean.TRUE;

        //When
        ronda.responder(opcionesSeleccionadas1, aplicaExclusividad);
        ronda.responder(opcionesSeleccionadas2, aplicaExclusividad);
        ronda.aplicarPuntajes();

        //Then
        Assertions.assertEquals(0, jugador1.obtenerPuntos());
        Assertions.assertEquals(0, jugador2.obtenerPuntos());
    }

    @Test
    public void seleccionarUnaVezExclusividadConMultipleChoiceParcialDuplicaSoloElPuntajeDelJugadorQueRespondeCorrectamente() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "¿En qué países se habla español?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("España", esCorrecta);
        Opcion opcion2Incorrecta = new Opcion("Italia", !esCorrecta);
        Opcion opcion3Correcta = new Opcion("Argentina", esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("Portugal", !esCorrecta);
        Opcion opcion5Incorrecta = new Opcion("Canadá", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Incorrecta, opcion3Correcta, opcion4Incorrecta, opcion5Incorrecta);
        Preguntable multipleChoiceParcial = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceParcial, preguntaTexto, opciones);
        Jugador jugador1 = new Jugador("Jugador1");
        Jugador jugador2 = new Jugador("Jugador2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, multipleChoiceParcial);
        List<String> opcionesSeleccionadas1 = Arrays.asList("España", "Argentina");
        List<String> opcionesSeleccionadas2 = Arrays.asList("Portugal");
        Boolean aplicaExclusividad = Boolean.TRUE;

        //When
        ronda.responder(opcionesSeleccionadas1, !aplicaExclusividad);
        ronda.responder(opcionesSeleccionadas2, aplicaExclusividad);
        ronda.aplicarPuntajes();

        //Then
        Assertions.assertEquals(4, jugador1.obtenerPuntos());
        Assertions.assertEquals(0, jugador2.obtenerPuntos());
    }

    @Test
    public void seleccionarDosVecesExclusividadConMultipleChoiceParcialCuadruplicaSoloElPuntajeDelJugadorQueRespondeCorrectamente() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "¿En qué países se habla español?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("España", esCorrecta);
        Opcion opcion2Incorrecta = new Opcion("Italia", !esCorrecta);
        Opcion opcion3Correcta = new Opcion("Argentina", esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("Portugal", !esCorrecta);
        Opcion opcion5Incorrecta = new Opcion("Canadá", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Incorrecta, opcion3Correcta, opcion4Incorrecta, opcion5Incorrecta);
        Preguntable multipleChoiceParcial = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceParcial, preguntaTexto, opciones);
        Jugador jugador1 = new Jugador("Jugador1");
        Jugador jugador2 = new Jugador("Jugador2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, multipleChoiceParcial);
        List<String> opcionesSeleccionadas1 = Arrays.asList("España", "Portugal");
        List<String> opcionesSeleccionadas2 = Arrays.asList("España");
        Boolean aplicaExclusividad = Boolean.TRUE;

        //When
        ronda.responder(opcionesSeleccionadas1, aplicaExclusividad);
        ronda.responder(opcionesSeleccionadas2, aplicaExclusividad);
        ronda.aplicarPuntajes();

        //Then
        Assertions.assertEquals(0, jugador1.obtenerPuntos());
        Assertions.assertEquals(4, jugador2.obtenerPuntos());
    }

    @Test
    public void seleccionarExclusividadConMultipleChoiceParcialNoSumaPuntajesSiAmbosRespondenCorrectamente() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "¿En qué países se habla español?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("España", esCorrecta);
        Opcion opcion2Incorrecta = new Opcion("Italia", !esCorrecta);
        Opcion opcion3Correcta = new Opcion("Argentina", esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("Portugal", !esCorrecta);
        Opcion opcion5Incorrecta = new Opcion("Canadá", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Incorrecta, opcion3Correcta, opcion4Incorrecta, opcion5Incorrecta);
        Preguntable multipleChoiceParcial = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceParcial, preguntaTexto, opciones);
        Jugador jugador1 = new Jugador("Jugador1");
        Jugador jugador2 = new Jugador("Jugador2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, multipleChoiceParcial);
        List<String> opcionesSeleccionadas1 = Arrays.asList("España", "Argentina");
        List<String> opcionesSeleccionadas2 = Arrays.asList("España");
        Boolean aplicaExclusividad = Boolean.TRUE;

        //When
        ronda.responder(opcionesSeleccionadas1, aplicaExclusividad);
        ronda.responder(opcionesSeleccionadas2, aplicaExclusividad);
        ronda.aplicarPuntajes();

        //Then
        Assertions.assertEquals(0, jugador1.obtenerPuntos());
        Assertions.assertEquals(0, jugador2.obtenerPuntos());
    }

    @Test
    public void seleccionarUnaVezExclusividadConVerdaderoFalsoClasicoDuplicaSoloElPuntajeDelJugadorQueRespondeCorrectamente() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "¿5+9?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionIncorrecta = new Opcion("8", !esCorrecta);
        Opcion opcionCorrecta = new Opcion("14", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionIncorrecta, opcionCorrecta);
        Preguntable verdaderoFalsoClasico = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoClasico, preguntaTexto, opciones);
        Jugador jugador1 = new Jugador("Jugador1");
        Jugador jugador2 = new Jugador("Jugador2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, verdaderoFalsoClasico);
        List<String> opcionesSeleccionadas1 = Arrays.asList("8");
        List<String> opcionesSeleccionadas2 = Arrays.asList("14");
        Boolean aplicaExclusividad = Boolean.TRUE;

        //When
        ronda.responder(opcionesSeleccionadas1, aplicaExclusividad);
        ronda.responder(opcionesSeleccionadas2, !aplicaExclusividad);
        ronda.aplicarPuntajes();

        //Then
        Assertions.assertEquals(0, jugador1.obtenerPuntos());
        Assertions.assertEquals(2, jugador2.obtenerPuntos());
    }

    @Test
    public void seleccionarDosVecesExclusividadConVerdaderoFalsoClasicoCuadruplicaSoloElPuntajeDelJugadorQueRespondeCorrectamente() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "¿5+9?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionIncorrecta = new Opcion("8", !esCorrecta);
        Opcion opcionCorrecta = new Opcion("14", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionIncorrecta, opcionCorrecta);
        Preguntable verdaderoFalsoClasico = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoClasico, preguntaTexto, opciones);
        Jugador jugador1 = new Jugador("Jugador1");
        Jugador jugador2 = new Jugador("Jugador2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, verdaderoFalsoClasico);
        List<String> opcionesSeleccionadas1 = Arrays.asList("14");
        List<String> opcionesSeleccionadas2 = Arrays.asList("8");
        Boolean aplicaExclusividad = Boolean.TRUE;

        //When
        ronda.responder(opcionesSeleccionadas1, aplicaExclusividad);
        ronda.responder(opcionesSeleccionadas2, aplicaExclusividad);
        ronda.aplicarPuntajes();

        //Then
        Assertions.assertEquals(4, jugador1.obtenerPuntos());
        Assertions.assertEquals(0, jugador2.obtenerPuntos());
    }

    @Test
    public void seleccionarExclusividadConVerdaderoFalsoClasicoNoSumaPuntajesSiAmbosRespondenCorrectamente() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "¿5+9?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionIncorrecta = new Opcion("8", !esCorrecta);
        Opcion opcionCorrecta = new Opcion("14", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionIncorrecta, opcionCorrecta);
        Preguntable verdaderoFalsoClasico = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoClasico, preguntaTexto, opciones);
        Jugador jugador1 = new Jugador("Jugador1");
        Jugador jugador2 = new Jugador("Jugador2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, verdaderoFalsoClasico);
        List<String> opcionesSeleccionadas1 = Arrays.asList("14");
        List<String> opcionesSeleccionadas2 = Arrays.asList("14");
        Boolean aplicaExclusividad = Boolean.TRUE;

        //When
        ronda.responder(opcionesSeleccionadas1, aplicaExclusividad);
        ronda.responder(opcionesSeleccionadas2, !aplicaExclusividad);
        ronda.aplicarPuntajes();

        //Then
        Assertions.assertEquals(0, jugador1.obtenerPuntos());
        Assertions.assertEquals(0, jugador2.obtenerPuntos());
    }

    @Test
    public void seleccionarUnaVezExclusividadConOrderedChoiceDuplicaSoloElPuntajeDelJugadorQueRespondeCorrectamente() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "Ordenar las palabras alfabéticamente";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Amigo", esCorrecta);
        Opcion opcion2 = new Opcion("Esposa",esCorrecta);
        Opcion opcion3 = new Opcion("Imán", esCorrecta);
        Opcion opcion4 = new Opcion("Ola", esCorrecta);
        Opcion opcion5 = new Opcion("Urraca", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5);
        Preguntable orderedChoice = CreadorPregunta.crearPregunta(TipoPregunta.OrderedChoice,preguntaTexto,opciones);
        Jugador jugador1 = new Jugador("Jugador1");
        Jugador jugador2 = new Jugador("Jugador2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, orderedChoice);
        List<String> opcionesSeleccionadas1 = Arrays.asList("Amigo", "Esposa", "Imán", "Ola", "Urraca");
        List<String> opcionesSeleccionadas2 = Arrays.asList("Esposa", "Urraca", "Imán", "Ola", "Amigo");
        Boolean aplicaExclusividad = Boolean.TRUE;

        //When
        ronda.responder(opcionesSeleccionadas1, aplicaExclusividad);
        ronda.responder(opcionesSeleccionadas2, !aplicaExclusividad);
        ronda.aplicarPuntajes();

        //Then
        Assertions.assertEquals(2,jugador1.obtenerPuntos());
        Assertions.assertEquals(0,jugador2.obtenerPuntos());
    }

    @Test
    public void seleccionarDosVecesExclusividadConOrderedChoiceCuadruplicaSoloElPuntajeDelJugadorQueRespondeCorrectamente() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "Ordenar las palabras alfabéticamente";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Amigo", esCorrecta);
        Opcion opcion2 = new Opcion("Esposa",esCorrecta);
        Opcion opcion3 = new Opcion("Imán", esCorrecta);
        Opcion opcion4 = new Opcion("Ola", esCorrecta);
        Opcion opcion5 = new Opcion("Urraca", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5);
        Preguntable orderedChoice = CreadorPregunta.crearPregunta(TipoPregunta.OrderedChoice,preguntaTexto,opciones);
        Jugador jugador1 = new Jugador("Jugador1");
        Jugador jugador2 = new Jugador("Jugador2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, orderedChoice);
        List<String> opcionesSeleccionadas1 = Arrays.asList("Esposa", "Urraca", "Imán", "Ola", "Amigo");
        List<String> opcionesSeleccionadas2 = Arrays.asList("Amigo", "Esposa", "Imán", "Ola", "Urraca");
        Boolean aplicaExclusividad = Boolean.TRUE;

        //When
        ronda.responder(opcionesSeleccionadas1, aplicaExclusividad);
        ronda.responder(opcionesSeleccionadas2, aplicaExclusividad);
        ronda.aplicarPuntajes();

        //Then
        Assertions.assertEquals(0,jugador1.obtenerPuntos());
        Assertions.assertEquals(4,jugador2.obtenerPuntos());
    }

    @Test
    public void seleccionarExclusividadConOrderedChoiceNoSumaPuntajesSiAmbosRespondenCorrectamente() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion, RondaSinPreguntaExcepcion {
        //Given
        String preguntaTexto = "Ordenar las palabras alfabéticamente";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Amigo", esCorrecta);
        Opcion opcion2 = new Opcion("Esposa",esCorrecta);
        Opcion opcion3 = new Opcion("Imán", esCorrecta);
        Opcion opcion4 = new Opcion("Ola", esCorrecta);
        Opcion opcion5 = new Opcion("Urraca", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5);
        Preguntable orderedChoice = CreadorPregunta.crearPregunta(TipoPregunta.OrderedChoice,preguntaTexto,opciones);
        Jugador jugador1 = new Jugador("Jugador1");
        Jugador jugador2 = new Jugador("Jugador2");
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, orderedChoice);
        List<String> opcionesSeleccionadas1 = Arrays.asList("Amigo", "Esposa", "Imán", "Ola", "Urraca");
        List<String> opcionesSeleccionadas2 = Arrays.asList("Amigo", "Esposa", "Imán", "Ola", "Urraca");
        Boolean aplicaExclusividad = Boolean.TRUE;

        //When
        ronda.responder(opcionesSeleccionadas1, aplicaExclusividad);
        ronda.responder(opcionesSeleccionadas2, aplicaExclusividad);
        ronda.aplicarPuntajes();

        //Then
        Assertions.assertEquals(0,jugador1.obtenerPuntos());
        Assertions.assertEquals(0,jugador2.obtenerPuntos());
    }

}
