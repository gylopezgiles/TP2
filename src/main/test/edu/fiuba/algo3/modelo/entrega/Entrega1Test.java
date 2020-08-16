package edu.fiuba.algo3.modelo.entrega;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Ronda;
import edu.fiuba.algo3.modelo.excepciones.*;
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
        String preguntaTexto = "¿Sydney es la capital de Australia?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta = new Opcion("Falso", esCorrecta);
        Opcion opcionIncorrecta = new Opcion("Verdadero", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);

        //When
        Preguntable verdaderoFalsoPenalidad = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoPenalidad, preguntaTexto, opciones);

        //Then
        Assertions.assertEquals(VerdaderoFalsoPenalidad.class, verdaderoFalsoPenalidad.getClass());
    }


    @Test
    public void crearMultipleChoiceClasicoConOpciones()throws ExcepcionBase {
        //Given
        String preguntaTexto = "¿Donde nacio Simon Bolivar?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta = new Opcion("Venezuela", esCorrecta);
        Opcion opcion2 = new Opcion("España", !esCorrecta);
        Opcion opcion3 = new Opcion("Colombia", !esCorrecta);
        Opcion opcion4 = new Opcion("Bolivia", !esCorrecta);
        Opcion opcion5 = new Opcion("Ecuador", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcion2, opcion3, opcion4, opcion5);

        //When
        Preguntable multipleChoiceClasico = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceClasico, preguntaTexto, opciones);

        //Then
        Assertions.assertEquals(MultipleChoiceClasico.class, multipleChoiceClasico.getClass());
    }

    @Test
    public void crearMultipleChoiceParcialConOpciones()throws ExcepcionBase {
        //Given
        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("Auckland", esCorrecta);
        Opcion opcion2Correcta = new Opcion("Wellington", esCorrecta);
        Opcion opcion3Incorrecta = new Opcion("Canberra", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta);

        //When
        Preguntable multipleChoiceParcial = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceParcial, preguntaTexto, opciones);

        //Then
        Assertions.assertEquals(MultipleChoiceParcial.class, multipleChoiceParcial.getClass());
    }

    @Test
    public void preguntaVerdaderoFalsoConPenalidadSumaPuntosAJugadoresRespondenCorrectamente() throws RondaSinPreguntaExcepcion, ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException  {
        //Give
        String preguntaTexto = "¿Sydney es la capital de Australia?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta = new Opcion("Falso", esCorrecta);
        Opcion opcionIncorrecta = new Opcion("Verdadero", !esCorrecta);

        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        Preguntable verdaderoFalsoPenalidad = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoPenalidad, preguntaTexto, opciones);

        List<String> opcionSeleccionada = Arrays.asList("Falso");
        Jugador jugador = new Jugador("Diego");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, verdaderoFalsoPenalidad);

        //When
        ronda.responder(opcionSeleccionada);
        ronda.aplicarPuntajes();

        //Then
        Assertions.assertEquals(1, jugador.obtenerPuntos());
    }

    @Test
    public void preguntaVerdaderoFalsoConPenalidadRestaPuntosAJugadoresRespondenIncorrectamente() throws ExcepcionBase {
        //Given
        String preguntaTexto = "¿Sydney es la capital de Australia?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta = new Opcion("Falso", esCorrecta);
        Opcion opcionIncorrecta = new Opcion("Verdadero", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        Preguntable verdaderoFalsoPenalidad = CreadorPregunta.crearPregunta(TipoPregunta.VerdaderoFalsoPenalidad, preguntaTexto, opciones);

        List<String> opcionSeleccionada = Arrays.asList("Verdadero");
        Jugador jugador = new Jugador("Diego");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, verdaderoFalsoPenalidad);

        //When
        ronda.responder(opcionSeleccionada);
        ronda.aplicarPuntajes();

        //Then
        Assertions.assertEquals(-1, jugador.obtenerPuntos());
    }

    @Test
    public void preguntaMultipleChoiceClasicoSumaPuntosAJugadoresRespondenCorrectamente() throws ExcepcionBase {
        //Given
        String preguntaTexto = "¿Frases celebres de el comandante?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("Yo no manejo el rating, yo manejo un Rolls Royce", esCorrecta);
        Opcion opcion2Correcta = new Opcion("MAIAMEEEEEEEEE!!!", esCorrecta);
        Opcion opcion3Incorrecta = new Opcion("El amor es mejor maestro que el deber", !esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("El dinero no comprará la felicidad a quien no sabe qué desea", !esCorrecta);
        Opcion opcion5Incorrecta = new Opcion("Un pueblo ignorante es un instrumento ciego de su propia destrucción", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta, opcion4Incorrecta, opcion5Incorrecta);
        Preguntable multipleChoiceClasico = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceClasico, preguntaTexto, opciones);

        List<String> opcionSeleccionada = Arrays.asList("Yo no manejo el rating, yo manejo un Rolls Royce", "MAIAMEEEEEEEEE!!!");
        Jugador jugador = new Jugador("Diego");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, multipleChoiceClasico);

        //When
        ronda.responder(opcionSeleccionada);
        ronda.aplicarPuntajes();

        //Then
        Assertions.assertEquals(1, jugador.obtenerPuntos());
    }

    @Test
    public void preguntaMultipleChoiceClasicoNoSumaPuntosAJugadoresRespondenIncorrectamente() throws ExcepcionBase {
        //Given
        String preguntaTexto = "¿Frases celebres de el comandante?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("Yo no manejo el rating, yo manejo un Rolls Royce", esCorrecta);
        Opcion opcion2Correcta = new Opcion("MAIAMEEEEEEEEE!!!", esCorrecta);
        Opcion opcion3Incorrecta = new Opcion("El amor es mejor maestro que el deber", !esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("El dinero no comprará la felicidad a quien no sabe qué desea", !esCorrecta);
        Opcion opcion5Incorrecta = new Opcion("Un pueblo ignorante es un instrumento ciego de su propia destrucción", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta, opcion4Incorrecta, opcion5Incorrecta);
        Preguntable multipleChoiceClasico = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceClasico, preguntaTexto, opciones);

        List<String> opcionSeleccionada = Arrays.asList("Yo no manejo el rating, yo manejo un Rolls Royce", "El amor es mejor maestro que el deber");
        Jugador jugador = new Jugador("Diego");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, multipleChoiceClasico);

        //When
        ronda.responder(opcionSeleccionada);
        ronda.aplicarPuntajes();

        //Then
        Assertions.assertEquals(0, jugador.obtenerPuntos());
    }

    @Test
    public void preguntaMultipleChoiceParcialAsignaPuntajeCorrectamenteEligiendoTodasCorrectasSinIncorrectas() throws ExcepcionBase {
        //Given
        String preguntaTexto = "¿Que ciudades pertenecen a Nueva Zelanda?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("Auckland", esCorrecta);
        Opcion opcion2Correcta = new Opcion("Wellington", esCorrecta);
        Opcion opcion3Incorrecta = new Opcion("Canberra", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta);
        Preguntable multipleChoiceParcial = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceParcial, preguntaTexto, opciones);

        List<String> opcionesSeleccionadas = Arrays.asList("Auckland", "Wellington");
        Jugador jugador = new Jugador("Diego");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, multipleChoiceParcial);

        //When
        ronda.responder(opcionesSeleccionadas);
        ronda.aplicarPuntajes();

        //Then
        Assertions.assertEquals(2, jugador.obtenerPuntos());
    }

    @Test
    public void preguntaMultipleChoiceParcialAsignaPuntajeCorrectamenteEligiendoAlgunasCorrectasSinIncorrectas() throws ExcepcionBase {
        //Given
        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("Auckland", esCorrecta);
        Opcion opcion2Correcta = new Opcion("Wellington", esCorrecta);
        Opcion opcion3Incorrecta = new Opcion("Canberra", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta);
        Preguntable multipleChoiceParcial = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceParcial, preguntaTexto, opciones);

        List<String> opcionesSeleccionadas = Arrays.asList("Auckland");
        Jugador jugador = new Jugador("jugador");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, multipleChoiceParcial);

        //When
        ronda.responder(opcionesSeleccionadas);
        ronda.aplicarPuntajes();

        //Then
        Assertions.assertEquals(1, jugador.obtenerPuntos());
    }

    @Test
    public void preguntaMultipleChoiceParcialAsignaPuntajeCorrectamenteEligiendoIncorrecta() throws ExcepcionBase {
        //Given
        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("Auckland", esCorrecta);
        Opcion opcion2Correcta = new Opcion("Wellington", esCorrecta);
        Opcion opcion3Incorrecta = new Opcion("Canberra", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta);
        Preguntable multipleChoiceParcial = CreadorPregunta.crearPregunta(TipoPregunta.MultipleChoiceParcial, preguntaTexto, opciones);

        List<String> opcionesSeleccionadas = Arrays.asList("Auckland", "Wellington", "Canberra");
        Jugador jugador = new Jugador("Diego");
        List<Jugador> jugadores = Arrays.asList(jugador);
        Ronda ronda = new Ronda(jugadores, multipleChoiceParcial);

        //When
        ronda.responder(opcionesSeleccionadas);
        ronda.aplicarPuntajes();

        //Then
        Assertions.assertEquals(0, jugador.obtenerPuntos());
    }

}
