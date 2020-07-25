package edu.fiuba.algo3.modelo.pregunta;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class VerdaderoFalsoConPenalidadTest {

    // Pruebas que deben pasar las preguntas Verdadero & Falso

    @Test
    public void debeCrearUnaPreguntaVerdaderFalsoConOpciones() throws ParametrosInvalidosExcepcion {
       Opcion opcionCorrecta = new Opcion("Verdadero", Boolean.TRUE);
       Opcion opcionIncorrecta = new Opcion("Falso", Boolean.FALSE);
       List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
       String preguntaTexto = "pregunta?";

       Preguntable verdaderoFalsoClasicoConPenalidad = new VerdaderoFalsoClasicoConPenalidad(preguntaTexto, opciones);

       Assertions.assertEquals(opciones, verdaderoFalsoClasicoConPenalidad.obtenerOpciones());
       Assertions.assertEquals(preguntaTexto, verdaderoFalsoClasicoConPenalidad.obtenerPregunta());
    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaSinOpciones() {
        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new VerdaderoFalsoClasicoConPenalidad("pregunta?", Collections.EMPTY_LIST));
    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaConCantidadOpcionesDistintoDe2() {

        Opcion opcion = new Opcion("opcion", Boolean.TRUE);
        List<Opcion> opciones = Arrays.asList(opcion);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new VerdaderoFalsoClasicoConPenalidad("pregunta?", opciones));
    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaSinOpcionesCorrectas() {
        Opcion opcionIncorrecta1 = new Opcion("Verdadero", Boolean.FALSE);
        Opcion opcionIncorrecta2 = new Opcion("Falso", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionIncorrecta1, opcionIncorrecta2);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new VerdaderoFalsoClasicoConPenalidad("pregunta?", opciones));

    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaConMasDeUnaOpcionCorrecta() {
        Opcion opcionCorrecta1 = new Opcion("Verdadero", Boolean.TRUE);
        Opcion opcionCorrecta2 = new Opcion("Falso", Boolean.TRUE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta1, opcionCorrecta2);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new VerdaderoFalsoClasicoConPenalidad("pregunta?", opciones));

    }

    // Nuevas Pruebas especificas con respecto a Verdadero & Falso Con Penalidad

    @Test
    public void alResponderCorrectamenteDebeSumarUnPunto() throws ParametrosInvalidosExcepcion {

        Opcion opcionCorrecta = new Opcion("Verdadero", Boolean.TRUE);
        Opcion opcionIncorrecta = new Opcion("Falso", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        String preguntaTexto = "pregunta?";

        Preguntable verdaderoFalsoClasicoConPenalidad = new VerdaderoFalsoClasicoConPenalidad(preguntaTexto, opciones);

        Assertions.assertEquals(1, verdaderoFalsoClasicoConPenalidad.establecerPuntuacion(Arrays.asList(opcionCorrecta)));
    }


    @Test
    public void alResponderIncorrectamenteDebeRestarUnPunto() throws ParametrosInvalidosExcepcion {
        Opcion opcionCorrecta = new Opcion("Verdadero", Boolean.TRUE);
        Opcion opcionIncorrecta = new Opcion("Falso", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        String preguntaTexto = "pregunta?";

        Preguntable verdaderoFalsoClasicoConPenalidad = new VerdaderoFalsoClasicoConPenalidad(preguntaTexto, opciones);

        Assertions.assertEquals(-1, verdaderoFalsoClasicoConPenalidad.establecerPuntuacion(Arrays.asList(opcionIncorrecta)));
    }

    @Test
    public void alCrearUnaPruebaVerdaderoOFalsoConPenalidadLePideLaRespuestaCorrectaYMeLaDevuelve() throws ParametrosInvalidosExcepcion {

        Opcion opcionCorrecta = new Opcion("Verdadero", Boolean.TRUE);
        Opcion opcionIncorrecta = new Opcion("Falso", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        String preguntaTexto = "pregunta?";

        Preguntable verdaderoFalsoClasicoConPenalidad = new VerdaderoFalsoClasicoConPenalidad(preguntaTexto, opciones);

        Assertions.assertEquals(opcionCorrecta, verdaderoFalsoClasicoConPenalidad.obtenerOpcionCorrecta());
    }
}
