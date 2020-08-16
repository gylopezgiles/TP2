package edu.fiuba.algo3.modelo.pregunta.verdaderofalso;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.exclusividad.Exclusividad;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class VerdaderoFalsoPenalidadTest {

    @Test
    public void debeCrearUnaPreguntaVerdaderFalsoConOpciones() throws ParametrosInvalidosExcepcion {
        String preguntaTexto = "¿Los Elefantes son los mamiferos mas grandes del planeta?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta = new Opcion("Falso", esCorrecta);
        Opcion opcionIncorrecta = new Opcion("Verdadero", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);

        Preguntable verdaderoFalsoPenalidad = new VerdaderoFalsoPenalidad(preguntaTexto, opciones);

        Assertions.assertEquals(opciones, verdaderoFalsoPenalidad.obtenerOpciones());
        Assertions.assertEquals(preguntaTexto, verdaderoFalsoPenalidad.obtenerPregunta());
        Assertions.assertEquals(TipoPregunta.VerdaderoFalsoPenalidad, verdaderoFalsoPenalidad.obtenerTipoPregunta());
    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaSinOpciones() {

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new VerdaderoFalsoPenalidad("¿pregunta?", Collections.EMPTY_LIST));

    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaConCantidadOpcionesDistintoDe2() {

        String preguntaTexto = "¿Los Elefantes son los mamiferos mas grandes del planeta?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta = new Opcion("Falso", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new VerdaderoFalsoPenalidad(preguntaTexto, opciones));

    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaSinOpcionesCorrectas() {

        String preguntaTexto = "El gato de Schrödinger esta muerto?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionIncorrecta1 = new Opcion("Verdadero", !esCorrecta);
        Opcion opcionIncorrecta2 = new Opcion("Falso", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionIncorrecta1, opcionIncorrecta2);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new VerdaderoFalsoPenalidad(preguntaTexto, opciones));

    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaConMasDe1OpcionCorrecta() {

        String preguntaTexto = "El gato de Schrödinger esta vivo?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta1 = new Opcion("Verdadero", esCorrecta);
        Opcion opcionCorrecta2 = new Opcion("Falso", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta1, opcionCorrecta2);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new VerdaderoFalsoPenalidad(preguntaTexto, opciones));

    }

    @Test
    public void alResponderCorrectamenteDebeSumarUnPunto() throws ParametrosInvalidosExcepcion {

        String preguntaTexto = "¿Sydney es la capital de Australia?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta = new Opcion("Falso", esCorrecta);
        Opcion opcionIncorrecta = new Opcion("Verdadero", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);

        Preguntable verdaderoFalsoConPenalidad = new VerdaderoFalsoPenalidad(preguntaTexto, opciones);

        Assertions.assertEquals(1, verdaderoFalsoConPenalidad.establecerPuntuacion(Arrays.asList("Falso")));
    }


    @Test
    public void alResponderIncorrectamenteDebeRestarUnPunto() throws ParametrosInvalidosExcepcion {

        String preguntaTexto = "¿Sydney es la capital de Australia?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta = new Opcion("Falso", esCorrecta);
        Opcion opcionIncorrecta = new Opcion("Verdadero", !esCorrecta);

        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);

        Preguntable verdaderoFalsoConPenalidad = new VerdaderoFalsoPenalidad(preguntaTexto, opciones);

        Assertions.assertEquals(-1, verdaderoFalsoConPenalidad.establecerPuntuacion(Arrays.asList("Verdadero")));

    }

    @Test
    public void alAplicarMultiplicadorDebeMultiplicarLosPuntos() throws ParametrosInvalidosExcepcion {
        String preguntaTexto = "La banda Kiss tiene 6 integrantes";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionIncorrecta = new Opcion("Verdadero", !esCorrecta);
        Opcion opcionCorrecta = new Opcion("Falso", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        List<String> opcionSeleccionada = Arrays.asList("Falso");
        Exclusividad exclusividad = new Exclusividad();

        Preguntable verdaderoFalsoConPenalidad = new VerdaderoFalsoPenalidad(preguntaTexto, opciones);

        Assertions.assertEquals(2, verdaderoFalsoConPenalidad.establecerPuntuacion(opcionSeleccionada, Multiplicador.PorDos, exclusividad));
    }

    @Test
    public void alAplicarMultiplicadorDebeMultiplicarLosPuntosNegativos() throws ParametrosInvalidosExcepcion {
        String preguntaTexto = "La banda Kiss tiene 6 integrantes";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionIncorrecta = new Opcion("Verdadero", !esCorrecta);
        Opcion opcionCorrecta = new Opcion("Falso", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        List<String> opcionSeleccionada = Arrays.asList("Verdadero");
        Exclusividad exclusividad = new Exclusividad();

        Preguntable verdaderoFalsoConPenalidad = new VerdaderoFalsoPenalidad(preguntaTexto, opciones);

        Assertions.assertEquals(-2, verdaderoFalsoConPenalidad.establecerPuntuacion(opcionSeleccionada, Multiplicador.PorDos, exclusividad));
    }

}
