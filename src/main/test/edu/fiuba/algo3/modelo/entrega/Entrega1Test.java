package edu.fiuba.algo3.modelo.entrega;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Ronda;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.CreadorPregunta;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;
import edu.fiuba.algo3.modelo.pregunta.multiplechoice.MultipleChoiceClasico;
import edu.fiuba.algo3.modelo.pregunta.multiplechoice.MultipleChoiceParcial;
import edu.fiuba.algo3.modelo.pregunta.verdaderofalso.VerdaderoFalsoPenalidad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class Entrega1Test {

    @Test
    public void crearVerdaderoFalsoClasicoConPenalidadConOpciones() throws ExcepcionBase {
        //Given
        Opcion opcionCorrecta = new Opcion("Verdadero", Boolean.TRUE);
        Opcion opcionIncorrecta = new Opcion("Falso", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        String preguntaTexto = "pregunta?";

        //When
        Preguntable verdaderoFalsoPenalidad = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoPenalidad, preguntaTexto, opciones);

        //Then
        List<Opcion> opcionesEnPregunta = (List<Opcion>) verdaderoFalsoPenalidad.obtenerOpciones();
        Assertions.assertEquals(VerdaderoFalsoPenalidad.class, verdaderoFalsoPenalidad.getClass());
        Assertions.assertEquals(2, opcionesEnPregunta.size());
        Assertions.assertEquals(opciones, verdaderoFalsoPenalidad.obtenerOpciones());
        Assertions.assertEquals(opcionCorrecta, opcionesEnPregunta.stream().filter(opcion -> opcion.esCorrecta()).findAny().orElse(null));
    }


    @Test
    public void crearMultipleChoiceClasicoConOpciones()throws ExcepcionBase {
        //Given
        Opcion opcionCorrecta = new Opcion("Esta Si", Boolean.TRUE);
        Opcion opcion2 = new Opcion("Esta NO", Boolean.FALSE);
        Opcion opcion3 = new Opcion("Esta Tampoco", Boolean.FALSE);
        Opcion opcion4 = new Opcion("Esta Menos", Boolean.FALSE);
        Opcion opcion5 = new Opcion("Nope", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcion2, opcion3, opcion4, opcion5);
        String preguntaTexto = "pregunta?";

        //When
        Preguntable multipleChoiceClasico = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceClasico, preguntaTexto, opciones);

        //Then
        List<Opcion> opcionesEnPregunta = (List<Opcion>) multipleChoiceClasico.obtenerOpciones();
        Assertions.assertEquals(MultipleChoiceClasico.class, multipleChoiceClasico.getClass());
        Assertions.assertEquals(5, opcionesEnPregunta.size());
        Assertions.assertEquals(opciones, multipleChoiceClasico.obtenerOpciones());

    }

    @Test
    public void crearMultipleChoiceParcialConOpciones()throws ExcepcionBase {
        //Given
        Opcion opcion1Correcta = new Opcion("opcion 1", Boolean.TRUE);
        Opcion opcion2Correcta = new Opcion("opcion 2", Boolean.TRUE);
        Opcion opcion3Incorrecta = new Opcion("opcion 3", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta);
        String preguntaTexto = "pregunta?";

        //When
        Preguntable multipleChoiceParcial = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceParcial, preguntaTexto, opciones);

        //Then
        List<Opcion> opcionesEnPregunta = (List<Opcion>) multipleChoiceParcial.obtenerOpciones();
        Assertions.assertEquals(MultipleChoiceParcial.class, multipleChoiceParcial.getClass());
        Assertions.assertEquals(3, opcionesEnPregunta.size());
        Assertions.assertEquals(opciones, multipleChoiceParcial.obtenerOpciones());

    }

    @Test
    public void preguntaVerdaderoFalsoConPenalidadSumaPuntosAJugadoresRespondenCorrectamente() throws ExcepcionBase {
        //Given
        Opcion opcionCorrecta = new Opcion("Verdadero", Boolean.TRUE);
        Opcion opcionIncorrecta = new Opcion("Falso", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        String preguntaTexto = "pregunta?";
        Preguntable verdaderoFalsoPenalidad = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoPenalidad, preguntaTexto, opciones);

        List<Opcion> opcionSeleccionada = Arrays.asList(opcionCorrecta);
        Jugador jugador = new Jugador("jugador");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, verdaderoFalsoPenalidad);

        //When
        ronda.responder(jugador, opcionSeleccionada);

        //Then
        Assertions.assertEquals(1, jugador.obtenerPuntos());
    }

    @Test
    public void preguntaVerdaderoFalsoConPenalidadRestaPuntosAJugadoresRespondenIncorrectamente() throws ExcepcionBase {
        //Given
        Opcion opcionCorrecta = new Opcion("Verdadero", Boolean.TRUE);
        Opcion opcionIncorrecta = new Opcion("Falso", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        String preguntaTexto = "pregunta?";
        Preguntable verdaderoFalsoPenalidad = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoPenalidad, preguntaTexto, opciones);

        List<Opcion> opcionSeleccionada = Arrays.asList(opcionIncorrecta);
        Jugador jugador = new Jugador("jugador");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, verdaderoFalsoPenalidad);

        //When
        ronda.responder(jugador, opcionSeleccionada);

        //Then
        Assertions.assertEquals(-1, jugador.obtenerPuntos());
    }

    @Test
    public void preguntaMultipleChoiceClasicoSumaPuntosAJugadoresRespondenCorrectamente() throws ExcepcionBase {
        //Given
        Opcion opcion1Correcta = new Opcion("Opcion 1", Boolean.TRUE);
        Opcion opcion2Correcta = new Opcion("Opcion 2", Boolean.TRUE);
        Opcion opcion3Incorrecta = new Opcion("Opcion 3", Boolean.FALSE);
        Opcion opcion4Incorrecta = new Opcion("Opcion 4", Boolean.FALSE);
        Opcion opcion5Incorrecta = new Opcion("Opcion 5", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta, opcion4Incorrecta, opcion5Incorrecta);
        String preguntaTexto = "pregunta?";
        Preguntable multipleChoiceClasico = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceClasico, preguntaTexto, opciones);

        List<Opcion> opcionSeleccionada = Arrays.asList(opcion1Correcta, opcion2Correcta);
        Jugador jugador = new Jugador("jugador");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, multipleChoiceClasico);

        //When
        ronda.responder(jugador, opcionSeleccionada);

        //Then
        Assertions.assertEquals(1, jugador.obtenerPuntos());
    }

    @Test
    public void preguntaMultipleChoiceClasicoNoSumaPuntosAJugadoresRespondenIncorrectamente() throws ExcepcionBase {
        //Given
        Opcion opcion1Correcta = new Opcion("Opcion 1", Boolean.TRUE);
        Opcion opcion2Correcta = new Opcion("Opcion 2", Boolean.TRUE);
        Opcion opcion3Incorrecta = new Opcion("Opcion 3", Boolean.FALSE);
        Opcion opcion4Incorrecta = new Opcion("Opcion 4", Boolean.FALSE);
        Opcion opcion5Incorrecta = new Opcion("Opcion 5", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta, opcion4Incorrecta, opcion5Incorrecta);
        String preguntaTexto = "pregunta?";
        Preguntable multipleChoiceClasico = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceClasico, preguntaTexto, opciones);

        List<Opcion> opcionSeleccionada = Arrays.asList(opcion1Correcta, opcion3Incorrecta);
        Jugador jugador = new Jugador("jugador");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, multipleChoiceClasico);

        //When
        ronda.responder(jugador, opcionSeleccionada);

        //Then
        Assertions.assertEquals(0, jugador.obtenerPuntos());
    }

    @Test
    public void preguntaMultipleChoiceParcialAsignaPuntajeCorrectamenteEligiendoTodasCorrectasSinIncorrectas() throws ExcepcionBase {
        //Given
        Opcion opcion1Correcta = new Opcion("opcion 1", Boolean.TRUE);
        Opcion opcion2Correcta = new Opcion("opcion 2", Boolean.TRUE);
        Opcion opcion3Incorrecta = new Opcion("opcion 3", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta);
        String preguntaTexto = "pregunta?";
        Preguntable multipleChoiceParcial = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceParcial, preguntaTexto, opciones);

        List<Opcion> opcionesSeleccionadas = Arrays.asList(opcion1Correcta, opcion2Correcta);
        Jugador jugador = new Jugador("jugador");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, multipleChoiceParcial);

        //When
        ronda.responder(jugador, opcionesSeleccionadas);

        //Then
        Assertions.assertEquals(2, jugador.obtenerPuntos());
    }

    @Test
    public void preguntaMultipleChoiceParcialAsignaPuntajeCorrectamenteEligiendoAlgunasCorrectasSinIncorrectas() throws ExcepcionBase {
        //Given
        Opcion opcion1Correcta = new Opcion("opcion 1", Boolean.TRUE);
        Opcion opcion2Correcta = new Opcion("opcion 2", Boolean.TRUE);
        Opcion opcion3Incorrecta = new Opcion("opcion 3", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta);
        String preguntaTexto = "pregunta?";
        Preguntable multipleChoiceParcial = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceParcial, preguntaTexto, opciones);

        List<Opcion> opcionesSeleccionadas = Arrays.asList(opcion1Correcta);
        Jugador jugador = new Jugador("jugador");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, multipleChoiceParcial);

        //When
        ronda.responder(jugador, opcionesSeleccionadas);

        //Then
        Assertions.assertEquals(1, jugador.obtenerPuntos());
    }

    @Test
    public void preguntaMultipleChoiceParcialAsignaPuntajeCorrectamenteEligiendoIncorrecta() throws ExcepcionBase {
        //Given
        Opcion opcion1Correcta = new Opcion("opcion 1", Boolean.TRUE);
        Opcion opcion2Correcta = new Opcion("opcion 2", Boolean.TRUE);
        Opcion opcion3Incorrecta = new Opcion("opcion 3", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta);
        String preguntaTexto = "pregunta?";
        Preguntable multipleChoiceParcial = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceParcial, preguntaTexto, opciones);

        List<Opcion> opcionesSeleccionadas = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta);
        Jugador jugador = new Jugador("jugador");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, multipleChoiceParcial);

        //When
        ronda.responder(jugador, opcionesSeleccionadas, Multiplicador.PorDefecto);

        //Then
        Assertions.assertEquals(0, jugador.obtenerPuntos());
    }




}
