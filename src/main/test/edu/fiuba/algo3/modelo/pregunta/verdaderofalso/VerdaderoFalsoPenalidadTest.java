package edu.fiuba.algo3.modelo.pregunta.verdaderofalso;

import edu.fiuba.algo3.modelo.excepciones.MultiplicadorExcepcion;
import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class VerdaderoFalsoPenalidadTest {

    @Test
    public void debeCrearUnaPreguntaVerdaderFalsoConOpciones() throws ParametrosInvalidosExcepcion {
        Opcion opcionCorrecta = new Opcion("Verdadero", Boolean.TRUE);
        Opcion opcionIncorrecta = new Opcion("Falso", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        String preguntaTexto = "pregunta?";

        Preguntable verdaderoFalsoPenalidad = new VerdaderoFalsoPenalidad(preguntaTexto, opciones);

        Assertions.assertEquals(opciones, verdaderoFalsoPenalidad.obtenerOpciones());
        Assertions.assertEquals(preguntaTexto, verdaderoFalsoPenalidad.obtenerPregunta());
    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaSinOpciones() {

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new VerdaderoFalsoPenalidad("pregunta?", Collections.EMPTY_LIST));

    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaConCantidadOpcionesDistintoDe2() {

        Opcion opcion = new Opcion("opcion", Boolean.TRUE);
        List<Opcion> opciones = Arrays.asList(opcion);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new VerdaderoFalsoPenalidad("pregunta?", opciones));

    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaSinOpcionesCorrectas() {
        Opcion opcionIncorrecta1 = new Opcion("Verdadero", Boolean.FALSE);
        Opcion opcionIncorrecta2 = new Opcion("Falso", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionIncorrecta1, opcionIncorrecta2);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new VerdaderoFalsoPenalidad("pregunta?", opciones));

    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaConMasDe1OpcionCorrecta() {
        Opcion opcionCorrecta1 = new Opcion("Verdadero", Boolean.TRUE);
        Opcion opcionCorrecta2 = new Opcion("Falso", Boolean.TRUE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta1, opcionCorrecta2);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new VerdaderoFalsoPenalidad("pregunta?", opciones));

    }

    @Test
    public void alResponderCorrectamenteDebeSumarUnPunto() throws ParametrosInvalidosExcepcion, MultiplicadorExcepcion {

        Opcion opcionCorrecta = new Opcion("Verdadero", Boolean.TRUE);
        Opcion opcionIncorrecta = new Opcion("Falso", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        String preguntaTexto = "pregunta?";

        Preguntable verdaderoFalsoConPenalidad = new VerdaderoFalsoPenalidad(preguntaTexto, opciones);

        Assertions.assertEquals(1, verdaderoFalsoConPenalidad.establecerPuntuacion(Arrays.asList(opcionCorrecta)));
    }


    @Test
    public void alResponderIncorrectamenteDebeRestarUnPunto() throws ParametrosInvalidosExcepcion, MultiplicadorExcepcion {
        Opcion opcionCorrecta = new Opcion("Verdadero", Boolean.TRUE);
        Opcion opcionIncorrecta = new Opcion("Falso", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        String preguntaTexto = "pregunta?";

        Preguntable verdaderoFalsoConPenalidad = new VerdaderoFalsoPenalidad(preguntaTexto, opciones);

        Assertions.assertEquals(-1, verdaderoFalsoConPenalidad.establecerPuntuacion(Arrays.asList(opcionIncorrecta)));

    }

    @Test
    public void alAplicarMultiplicadorDebeMultiplicarLosPuntos() throws ParametrosInvalidosExcepcion, MultiplicadorExcepcion {
        String preguntaTexto = "La banda Kiss tiene 6 integrantes";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionIncorrecta = new Opcion("Verdadero", !esCorrecta);
        Opcion opcionCorrecta = new Opcion("Falso", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        List<Opcion> opcionSeleccionada = Arrays.asList(opcionCorrecta);

        Preguntable verdaderoFalsoConPenalidad = new VerdaderoFalsoPenalidad(preguntaTexto, opciones);

        Assertions.assertEquals(2, verdaderoFalsoConPenalidad.establecerPuntuacion(opcionSeleccionada, Multiplicador.PorDos));
    }

    @Test
    public void alAplicarMultiplicadorDebeMultiplicarLosPuntosNegativos() throws ParametrosInvalidosExcepcion, MultiplicadorExcepcion {
        String preguntaTexto = "La banda Kiss tiene 6 integrantes";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionIncorrecta = new Opcion("Verdadero", !esCorrecta);
        Opcion opcionCorrecta = new Opcion("Falso", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        List<Opcion> opcionSeleccionada = Arrays.asList(opcionIncorrecta);

        Preguntable verdaderoFalsoConPenalidad = new VerdaderoFalsoPenalidad(preguntaTexto, opciones);

        Assertions.assertEquals(-2, verdaderoFalsoConPenalidad.establecerPuntuacion(opcionSeleccionada, Multiplicador.PorDos));
    }

}
