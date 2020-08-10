package edu.fiuba.algo3.modelo.pregunta;

import edu.fiuba.algo3.modelo.excepciones.MultiplicadorExcepcion;
import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OrderedChoiceTest {
    @Test
    public void debeCrearUnOrderedChoiceConOpciones() throws ParametrosInvalidosExcepcion {
        boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta = new Opcion("A", esCorrecta);
        Opcion opcionIncorrecta = new Opcion("B", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        String preguntaTexto = "pregunta?";

        Preguntable orderedChoice = new OrderedChoice(preguntaTexto, opciones);

        Assertions.assertEquals(OrderedChoice.class, orderedChoice.getClass());
    }

    @Test
    public void debeLanzarExcepcionAlCrearSinOpciones(){
        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new OrderedChoice("pregunta?", Collections.EMPTY_LIST));
    }
    @Test
    public void debeLanzarExcepcionAlCrearConUnaOpcion(){
        boolean esCorrecta = Boolean.TRUE;
        Opcion opcion = new Opcion("opcion", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new OrderedChoice("pregunta?", opciones));

    }
    @Test
    public void debeLanzarExcepcionAlCrearConMasDe5pciones(){
        boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1 = new Opcion("opcion", esCorrecta);
        Opcion opcion2 = new Opcion("opcion", esCorrecta);
        Opcion opcion3 = new Opcion("opcion", esCorrecta);
        Opcion opcion4 = new Opcion("opcion", esCorrecta);
        Opcion opcion5 = new Opcion("opcion", esCorrecta);
        Opcion opcion6 = new Opcion("opcion", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5, opcion6);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class,
                () -> new OrderedChoice("pregunta?", opciones));
    }

    @Test

    public void debeAsignarPuntajeCorrectamenteConOpcionesOrdenadas() throws ParametrosInvalidosExcepcion, MultiplicadorExcepcion {
        boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1 = new Opcion("A", esCorrecta);
        Opcion opcion2 = new Opcion("E",esCorrecta);
        Opcion opcion3 = new Opcion("I", esCorrecta);
        Opcion opcion4 = new Opcion("O", esCorrecta);
        Opcion opcion5 = new Opcion("U", esCorrecta);

        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5);

        OrderedChoice orderedChoice = new OrderedChoice("Orden de las letras vocales", opciones);
        List<Opcion> opcionesSeleccionadas = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5);

        Assertions.assertEquals(1, orderedChoice.establecerPuntuacion(opcionesSeleccionadas));

    }
    @Test
    public void debeAsignarPuntajeCorrectamenteConOpcionesDesrdenadas() throws ParametrosInvalidosExcepcion, MultiplicadorExcepcion {
        boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1 = new Opcion("A", esCorrecta);
        Opcion opcion2 = new Opcion("E",esCorrecta);
        Opcion opcion3 = new Opcion("I", esCorrecta);
        Opcion opcion4 = new Opcion("O", esCorrecta);
        Opcion opcion5 = new Opcion("U", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5);

        OrderedChoice orderedChoice = new OrderedChoice("Orden de las letras vocales", opciones);
        List<Opcion> opcionesSeleccionadas = Arrays.asList(opcion4, opcion3, opcion2, opcion1, opcion5);

        Assertions.assertEquals(0, orderedChoice.establecerPuntuacion(opcionesSeleccionadas));
    }
}
