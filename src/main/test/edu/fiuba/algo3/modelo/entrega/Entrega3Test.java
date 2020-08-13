package edu.fiuba.algo3.modelo.entrega;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Ronda;
import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.excepciones.RondaSinPreguntaExcepcion;
import edu.fiuba.algo3.modelo.excepciones.TipoPreguntaNoImplementadaException;
import edu.fiuba.algo3.modelo.exclusividad.Exclusividad;
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
        Preguntable verdaderoFalsoPenalidad = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoPenalidad, preguntaTexto , Arrays.asList(opcionIncorrecta, opcionCorrecta));
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("Jugador1", exclusividad);
        Jugador jugador2 = new Jugador("Jugador2", exclusividad);
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, verdaderoFalsoPenalidad);
        jugador1.seleccionarExclusividad();

        //When
        ronda.responderConExclusividad(Arrays.asList(opcionCorrecta), Arrays.asList(opcionIncorrecta), exclusividad);

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
        Preguntable multipleChoiceConPenalidad = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceConPenalidad, preguntaTexto , Arrays.asList(opcion1Correcta, opcion2Incorrecta, opcion3Correcta, opcion4Incorrecta, opcion5Incorrecta));
        List<Opcion> opcionesSeleccionadas1 = Arrays.asList(opcion1Correcta, opcion3Correcta);
        List<Opcion> opcionesSeleccionadas2 = Arrays.asList(opcion1Correcta, opcion4Incorrecta);
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("Jugador1", exclusividad);
        Jugador jugador2 = new Jugador("Jugador2", exclusividad);
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, multipleChoiceConPenalidad);
        jugador2.seleccionarExclusividad();

        //When
        ronda.responderConExclusividad(opcionesSeleccionadas1, opcionesSeleccionadas2, exclusividad);

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
        Preguntable multipleChoiceClasico = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceClasico, preguntaTexto , Arrays.asList(opcion1Correcta, opcion2Incorrecta, opcion3Correcta, opcion4Incorrecta, opcion5Incorrecta));
        List<Opcion> opcionesSeleccionadas1 = Arrays.asList(opcion1Correcta, opcion3Correcta);
        List<Opcion> opcionesSeleccionadas2 = Arrays.asList(opcion1Correcta, opcion4Incorrecta);
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("Jugador1", exclusividad);
        Jugador jugador2 = new Jugador("Jugador2", exclusividad);
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, multipleChoiceClasico);
        jugador2.seleccionarExclusividad();

        //When
        ronda.responderConExclusividad(opcionesSeleccionadas1, opcionesSeleccionadas2, exclusividad);

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
        Preguntable multipleChoiceClasico = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceClasico, preguntaTexto , Arrays.asList(opcion1Correcta, opcion2Incorrecta, opcion3Correcta, opcion4Incorrecta, opcion5Incorrecta));
        List<Opcion> opcionesSeleccionadas1 = Arrays.asList(opcion1Correcta, opcion3Correcta);
        List<Opcion> opcionesSeleccionadas2 = Arrays.asList(opcion1Correcta, opcion4Incorrecta);
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("Jugador1", exclusividad);
        Jugador jugador2 = new Jugador("Jugador2", exclusividad);
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, multipleChoiceClasico);
        jugador1.seleccionarExclusividad();
        jugador2.seleccionarExclusividad();

        //When
        ronda.responderConExclusividad(opcionesSeleccionadas1, opcionesSeleccionadas2, exclusividad);

        //Then
        Assertions.assertEquals(4, jugador1.obtenerPuntos());
        Assertions.assertEquals(0, jugador2.obtenerPuntos());
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
        Preguntable multipleChoiceClasico = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceClasico, preguntaTexto , Arrays.asList(opcion1Correcta, opcion2Incorrecta, opcion3Correcta, opcion4Incorrecta, opcion5Incorrecta));
        List<Opcion> opcionesSeleccionadas1 = Arrays.asList(opcion1Correcta, opcion3Correcta);
        List<Opcion> opcionesSeleccionadas2 = Arrays.asList(opcion1Correcta, opcion3Correcta);
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("Jugador1", exclusividad);
        Jugador jugador2 = new Jugador("Jugador2", exclusividad);
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, multipleChoiceClasico);
        jugador1.seleccionarExclusividad();
        jugador2.seleccionarExclusividad();

        //When
        ronda.responderConExclusividad(opcionesSeleccionadas1, opcionesSeleccionadas2, exclusividad);

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
        Preguntable multipleChoiceParcial = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceParcial, preguntaTexto , Arrays.asList(opcion1Correcta, opcion2Incorrecta, opcion3Correcta, opcion4Incorrecta, opcion5Incorrecta));
        List<Opcion> opcionesSeleccionadas1 = Arrays.asList(opcion1Correcta, opcion3Correcta);
        List<Opcion> opcionesSeleccionadas2 = Arrays.asList(opcion1Correcta);
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("Jugador1", exclusividad);
        Jugador jugador2 = new Jugador("Jugador2", exclusividad);
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, multipleChoiceParcial);
        jugador2.seleccionarExclusividad();

        //When
        ronda.responderConExclusividad(opcionesSeleccionadas1, opcionesSeleccionadas2, exclusividad);

        //Then
        Assertions.assertEquals(0, jugador1.obtenerPuntos());
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
        Preguntable multipleChoiceParcial = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceParcial, preguntaTexto , Arrays.asList(opcion1Correcta, opcion2Incorrecta, opcion3Correcta, opcion4Incorrecta, opcion5Incorrecta));
        List<Opcion> opcionesSeleccionadas1 = Arrays.asList(opcion1Correcta, opcion3Correcta);
        List<Opcion> opcionesSeleccionadas2 = Arrays.asList(opcion4Incorrecta);
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("Jugador1", exclusividad);
        Jugador jugador2 = new Jugador("Jugador2", exclusividad);
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, multipleChoiceParcial);
        jugador1.seleccionarExclusividad();
        jugador2.seleccionarExclusividad();

        //When
        ronda.responderConExclusividad(opcionesSeleccionadas1, opcionesSeleccionadas2, exclusividad);

        //Then
        Assertions.assertEquals(8, jugador1.obtenerPuntos());
        Assertions.assertEquals(0, jugador2.obtenerPuntos());
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
        Preguntable multipleChoiceParcial = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceParcial, preguntaTexto , Arrays.asList(opcion1Correcta, opcion2Incorrecta, opcion3Correcta, opcion4Incorrecta, opcion5Incorrecta));
        List<Opcion> opcionesSeleccionadas1 = Arrays.asList(opcion1Correcta, opcion3Correcta);
        List<Opcion> opcionesSeleccionadas2 = Arrays.asList(opcion1Correcta, opcion3Correcta);
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("Jugador1", exclusividad);
        Jugador jugador2 = new Jugador("Jugador2", exclusividad);
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, multipleChoiceParcial);
        jugador1.seleccionarExclusividad();
        jugador2.seleccionarExclusividad();

        //When
        ronda.responderConExclusividad(opcionesSeleccionadas1, opcionesSeleccionadas2, exclusividad);

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
        Preguntable verdaderoFalsoClasico = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoClasico, preguntaTexto , Arrays.asList(opcionIncorrecta, opcionCorrecta));
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("Jugador1", exclusividad);
        Jugador jugador2 = new Jugador("Jugador2", exclusividad);
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, verdaderoFalsoClasico);
        jugador1.seleccionarExclusividad();

        //When
        ronda.responderConExclusividad(Arrays.asList(opcionIncorrecta), Arrays.asList(opcionCorrecta), exclusividad);

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
        Preguntable verdaderoFalsoClasico = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoClasico, preguntaTexto , Arrays.asList(opcionIncorrecta, opcionCorrecta));
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("Jugador1", exclusividad);
        Jugador jugador2 = new Jugador("Jugador2", exclusividad);
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, verdaderoFalsoClasico);
        jugador1.seleccionarExclusividad();
        jugador2.seleccionarExclusividad();

        //When
        ronda.responderConExclusividad(Arrays.asList(opcionCorrecta), Arrays.asList(opcionIncorrecta), exclusividad);

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
        Preguntable verdaderoFalsoClasico = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoClasico, preguntaTexto , Arrays.asList(opcionIncorrecta, opcionCorrecta));
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("Jugador1", exclusividad);
        Jugador jugador2 = new Jugador("Jugador2", exclusividad);
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, verdaderoFalsoClasico);
        jugador1.seleccionarExclusividad();
        jugador2.seleccionarExclusividad();

        //When
        ronda.responderConExclusividad(Arrays.asList(opcionCorrecta), Arrays.asList(opcionCorrecta), exclusividad);

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
        List<Opcion> opcionesSeleccionadas1 = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5);
        List<Opcion> opcionesSeleccionadas2 = Arrays.asList(opcion2, opcion5, opcion3, opcion4, opcion1);
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("Jugador1", exclusividad);
        Jugador jugador2 = new Jugador("Jugador2", exclusividad);
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, orderedChoice);
        jugador1.seleccionarExclusividad();

        //When
        ronda.responderConExclusividad(opcionesSeleccionadas1, opcionesSeleccionadas2, exclusividad);

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
        List<Opcion> opcionesSeleccionadas2 = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5);
        List<Opcion> opcionesSeleccionadas1 = Arrays.asList(opcion2, opcion5, opcion3, opcion4, opcion1);
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("Jugador1", exclusividad);
        Jugador jugador2 = new Jugador("Jugador2", exclusividad);
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, orderedChoice);
        jugador1.seleccionarExclusividad();
        jugador2.seleccionarExclusividad();

        //When
        ronda.responderConExclusividad(opcionesSeleccionadas1, opcionesSeleccionadas2, exclusividad);

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
        List<Opcion> opcionesSeleccionadas1 = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5);
        List<Opcion> opcionesSeleccionadas2 = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5);
        Exclusividad exclusividad = new Exclusividad();
        Jugador jugador1 = new Jugador("Jugador1", exclusividad);
        Jugador jugador2 = new Jugador("Jugador2", exclusividad);
        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2);
        Ronda ronda = new Ronda(jugadores, orderedChoice);
        jugador1.seleccionarExclusividad();
        jugador2.seleccionarExclusividad();

        //When
        ronda.responderConExclusividad(opcionesSeleccionadas1, opcionesSeleccionadas2, exclusividad);

        //Then
        Assertions.assertEquals(0,jugador1.obtenerPuntos());
        Assertions.assertEquals(0,jugador2.obtenerPuntos());
    }

}
