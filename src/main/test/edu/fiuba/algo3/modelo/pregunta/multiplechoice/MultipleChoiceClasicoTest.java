package edu.fiuba.algo3.modelo.pregunta.multiplechoice;

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

public class MultipleChoiceClasicoTest {

    @Test
    public void crearMultipleChoiceClasicoConOpciones()throws ParametrosInvalidosExcepcion {
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
        Preguntable multipleChoiceClasico = new MultipleChoiceClasico(preguntaTexto, opciones);

        Assertions.assertEquals(TipoPregunta.MultipleChoiceClasico, multipleChoiceClasico.obtenerTipoPregunta());
    }

    @Test
    public void multipleChoiceAlmacenaUnaOpcionCorrecta() throws ParametrosInvalidosExcepcion {
        //Given
        String preguntaTexto = "¿Donde nacio Simon Bolivar?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta = new Opcion("Venezuela", esCorrecta);
        Opcion opcion2 = new Opcion("España", !esCorrecta);
        Opcion opcion3 = new Opcion("Colombia", !esCorrecta);
        Opcion opcion4 = new Opcion("Bolivia", !esCorrecta);
        Opcion opcion5 = new Opcion("Ecuador", !esCorrecta);

        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcion2, opcion3, opcion4, opcion5);
        List<Opcion> opcionesCorrectas = Arrays.asList(opcionCorrecta);

        //When
        Preguntable multipleChoiceClasico = new MultipleChoiceClasico(preguntaTexto, opciones);

        Assertions.assertEquals( 1 , multipleChoiceClasico.establecerPuntuacion(opcionesCorrectas));
    }

    @Test
    public void multipleChoiceAlmacenaMasDeUnaOpcionCorrecta() throws ParametrosInvalidosExcepcion {
        //Given
        String preguntaTexto = "¿Frases celebres de el comandante?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("Yo no manejo el rating, yo manejo un Rolls Royce", esCorrecta);
        Opcion opcion2Correcta = new Opcion("MAIAMEEEEEEEEE!!!", esCorrecta);
        Opcion opcion3Incorrecta = new Opcion("El amor es mejor maestro que el deber", !esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("El dinero no comprará la felicidad a quien no sabe qué desea", !esCorrecta);
        Opcion opcion5Incorrecta = new Opcion("Un pueblo ignorante es un instrumento ciego de su propia destrucción", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta, opcion4Incorrecta, opcion5Incorrecta);
        List<Opcion> opcionesCorrectas = Arrays.asList(opcion1Correcta, opcion2Correcta);

        //When
        Preguntable multipleChoiceClasico = new MultipleChoiceClasico(preguntaTexto, opciones);

        //Then
        Assertions.assertEquals(1, multipleChoiceClasico.establecerPuntuacion(opcionesCorrectas));
    }

    @Test
    public void multipleChoicePuedeTenerTodasOpcionesCorrectas() throws ParametrosInvalidosExcepcion {
        //Given
        String preguntaTexto = "¿Cuales de estos son numeros Naturales?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1 = new Opcion("1", esCorrecta);
        Opcion opcion2 = new Opcion("2", esCorrecta);
        Opcion opcion3 = new Opcion("3", esCorrecta);
        Opcion opcion4 = new Opcion("4", esCorrecta);
        Opcion opcion5 = new Opcion("5", esCorrecta);

        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5);

        //When
        Preguntable multipleChoiceClasico = new MultipleChoiceClasico(preguntaTexto, opciones);

        //Then
        Assertions.assertEquals(1, multipleChoiceClasico.establecerPuntuacion(opciones));
    }

    @Test
    public void noSePuedeCrearMultipleChoiceSinOpciones() {
        Assertions.assertThrows(ParametrosInvalidosExcepcion.class,
                               () -> new MultipleChoiceClasico("¿pregunta?", Collections.EMPTY_LIST));
    }

    @Test
    public void multipleChoiceDebeTenerUnaOpcionCorrecta(){
        String preguntaTexto = "¿Cuales de estos son numeros Naturales?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1 = new Opcion("-1", !esCorrecta);
        Opcion opcion2 = new Opcion("-2", !esCorrecta);
        Opcion opcion3 = new Opcion("-3", !esCorrecta);
        Opcion opcion4 = new Opcion("-4", !esCorrecta);
        Opcion opcion5 = new Opcion("-5", !esCorrecta);

        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class,
                               () -> new MultipleChoiceClasico(preguntaTexto, opciones));
    }

    @Test
    public void multipleChoiceDebeTenerMasDeUnaOpcion(){
        String preguntaTexto = "¿Te gusta el helado?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion = new Opcion("SI", esCorrecta);

        List<Opcion> opciones = Arrays.asList(opcion);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class,
                               () -> new MultipleChoiceClasico(preguntaTexto, opciones));
    }

    @Test
    public void multipleChoiceNoDebeTenerMasDeCincoOpciones(){
        String preguntaTexto = "¿Cuales de estos son numeros Naturales?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1 = new Opcion("1", esCorrecta);
        Opcion opcion2 = new Opcion("-2", !esCorrecta);
        Opcion opcion3 = new Opcion("-3", !esCorrecta);
        Opcion opcion4 = new Opcion("-4", !esCorrecta);
        Opcion opcion5 = new Opcion("-5", !esCorrecta);
        Opcion opcion6 = new Opcion("-6", !esCorrecta);

        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5, opcion6);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class,
                               () -> new MultipleChoiceClasico(preguntaTexto, opciones));
    }

    @Test
    public void multipleChoiceClasicoAsignaCorrectamenteElPuntajeConTodasOpcionesCorrectas() throws ParametrosInvalidosExcepcion {

        String preguntaTexto = "¿Frases celebres de el comandante?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("Yo no manejo el rating, yo manejo un Rolls Royce", esCorrecta);
        Opcion opcion2Correcta = new Opcion("MAIAMEEEEEEEEE!!!", esCorrecta);
        Opcion opcion3Incorrecta = new Opcion("El amor es mejor maestro que el deber", !esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("El dinero no comprará la felicidad a quien no sabe qué desea", !esCorrecta);
        Opcion opcion5Incorrecta = new Opcion("Un pueblo ignorante es un instrumento ciego de su propia destrucción", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta, opcion4Incorrecta, opcion5Incorrecta);

        Preguntable multipleChoiceClasico = new MultipleChoiceClasico(preguntaTexto, opciones);
        List<Opcion> opcionesSeleccionadas = Arrays.asList(opcion1Correcta, opcion2Correcta);

        Assertions.assertEquals(1,multipleChoiceClasico.establecerPuntuacion(opcionesSeleccionadas));
    }

    @Test
    public void multipleChoiceClasicoAsignaCorrectamenteElPuntajeConTodasLasOpcionesIncorrectas() throws ParametrosInvalidosExcepcion{

        String preguntaTexto = "¿Frases celebres de el comandante?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("Yo no manejo el rating, yo manejo un Rolls Royce", esCorrecta);
        Opcion opcion2Correcta = new Opcion("MAIAMEEEEEEEEE!!!", esCorrecta);
        Opcion opcion3Incorrecta = new Opcion("El amor es mejor maestro que el deber", !esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("El dinero no comprará la felicidad a quien no sabe qué desea", !esCorrecta);
        Opcion opcion5Incorrecta = new Opcion("Un pueblo ignorante es un instrumento ciego de su propia destrucción", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta, opcion4Incorrecta, opcion5Incorrecta);

        Preguntable multipleChoiceClasico = new MultipleChoiceClasico(preguntaTexto, opciones);
        List<Opcion> opcionesSeleccionadas = Arrays.asList(opcion4Incorrecta,opcion5Incorrecta);

        Assertions.assertEquals(0,multipleChoiceClasico.establecerPuntuacion(opcionesSeleccionadas));
    }

    @Test
    public void multipleChoiceClasicoAsignaCorrectamenteElPuntajeConAlgunasOpcionesCorrectas() throws ParametrosInvalidosExcepcion {
        String preguntaTexto = "¿Frases celebres de el comandante?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("Yo no manejo el rating, yo manejo un Rolls Royce", esCorrecta);
        Opcion opcion2Correcta = new Opcion("MAIAMEEEEEEEEE!!!", esCorrecta);
        Opcion opcion3Incorrecta = new Opcion("El amor es mejor maestro que el deber", !esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("El dinero no comprará la felicidad a quien no sabe qué desea", !esCorrecta);
        Opcion opcion5Incorrecta = new Opcion("Un pueblo ignorante es un instrumento ciego de su propia destrucción", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta, opcion4Incorrecta, opcion5Incorrecta);

        Preguntable multipleChoiceClasico = new MultipleChoiceClasico(preguntaTexto, opciones);
        List<Opcion> opcionesSeleccionadas = Arrays.asList(opcion1Correcta);

        Assertions.assertEquals(0,multipleChoiceClasico.establecerPuntuacion(opcionesSeleccionadas));
    }

    @Test
    public void multipleChoiceClasicoAsignaCorrectamenteElPuntajeConAlgunasOpcionesCorrectasEIncorrectas() throws ParametrosInvalidosExcepcion {
        String preguntaTexto = "¿Frases celebres de el comandante?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("Yo no manejo el rating, yo manejo un Rolls Royce", esCorrecta);
        Opcion opcion2Correcta = new Opcion("MAIAMEEEEEEEEE!!!", esCorrecta);
        Opcion opcion3Incorrecta = new Opcion("El amor es mejor maestro que el deber", !esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("El dinero no comprará la felicidad a quien no sabe qué desea", !esCorrecta);
        Opcion opcion5Incorrecta = new Opcion("Un pueblo ignorante es un instrumento ciego de su propia destrucción", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta, opcion4Incorrecta, opcion5Incorrecta);

        Preguntable multipleChoiceClasico = new MultipleChoiceClasico(preguntaTexto, opciones);
        List<Opcion> opcionesSeleccionadas = Arrays.asList(opcion1Correcta,opcion2Correcta,opcion4Incorrecta);

        Assertions.assertEquals(0,multipleChoiceClasico.establecerPuntuacion(opcionesSeleccionadas));
    }

    @Test
    public void alResponderConMultiplicadorNoLoDebeAplicar() throws ParametrosInvalidosExcepcion {
        String preguntaTexto = "Cuáles de los siguientes artistas interpretaron la canción Proud Mary";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta1 = new Opcion("Creedence Clearwater Revival", esCorrecta);
        Opcion opcionCorrecta2 = new Opcion("Tina Turner", esCorrecta);
        Opcion opcionIncorrecta3 = new Opcion("Radiohead", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta1, opcionCorrecta2, opcionIncorrecta3);
        List<Opcion> opcionesSeleccionadas = Arrays.asList(opcionCorrecta1, opcionCorrecta2);
        Exclusividad exclusividad = new Exclusividad();

        Preguntable multipleChoiceClasico = new MultipleChoiceClasico(preguntaTexto, opciones);

        Assertions.assertEquals(1, multipleChoiceClasico.establecerPuntuacion(opcionesSeleccionadas, Multiplicador.PorDos, exclusividad));
        Assertions.assertEquals(1, multipleChoiceClasico.establecerPuntuacion(opcionesSeleccionadas, Multiplicador.PorTres, exclusividad));

    }

    @Test
    public void obtenerOpcionesPorNombreTest() throws ParametrosInvalidosExcepcion {
        String preguntaTexto = "Cuáles de los siguientes artistas interpretaron la canción Proud Mary";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta1 = new Opcion("Creedence Clearwater Revival", esCorrecta);
        Opcion opcionCorrecta2 = new Opcion("Tina Turner", esCorrecta);
        Opcion opcionIncorrecta3 = new Opcion("Radiohead", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta1, opcionCorrecta2, opcionIncorrecta3);
        List<String> opcionesPorNombre = Arrays.asList("Creedence Clearwater Revival", "Tina Turner", "Radiohead");
        Preguntable multipleChoiceClasico = new MultipleChoiceClasico(preguntaTexto, opciones);

        List<Opcion> opcionesObtenidas = multipleChoiceClasico.obtenerOpcionesPorNombre(opcionesPorNombre);

        Assertions.assertEquals(opciones, opcionesObtenidas);
    }

}

