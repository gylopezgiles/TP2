package edu.fiuba.algo3.modelo.pregunta.multiplechoice;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.excepciones.TipoPreguntaNoImplementadaException;
import edu.fiuba.algo3.modelo.pregunta.CreadorPregunta;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MultipleChoiceClasicoTest {

    @Test
    public void crearMultipleChoiceClasicoConOpciones()throws ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException {
        //Given
        Opcion opcion1 = new Opcion("Esta Si", Boolean.TRUE);
        Opcion opcion2 = new Opcion("Esta NO", Boolean.FALSE);
        Opcion opcion3 = new Opcion("Esta Tampoco", Boolean.FALSE);
        Opcion opcion4 = new Opcion("Esta Menos", Boolean.FALSE);
        Opcion opcion5 = new Opcion("Nope", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5);
        String preguntaTexto = "pregunta?";

        //When
        Preguntable multipleChoiceClasico = new MultipleChoiceClasico(preguntaTexto, opciones);

        //Then
        List<Opcion> opcionesEnPregunta = (List<Opcion>) multipleChoiceClasico.obtenerOpciones();
        Assertions.assertEquals(5, opcionesEnPregunta.size());
        Assertions.assertEquals(opciones, multipleChoiceClasico.obtenerOpciones());

    }

    @Test
    public void multipleChoiceAlmacenaUnaOpcionCorrecta()throws ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException{
        Opcion opcionCorrecta = new Opcion("Esta Si", Boolean.TRUE);
        Opcion opcion2 = new Opcion("Esta NO", Boolean.FALSE);
        Opcion opcion3 = new Opcion("Esta Tampoco", Boolean.FALSE);
        Opcion opcion4 = new Opcion("Esta Menos", Boolean.FALSE);
        Opcion opcion5 = new Opcion("Nope", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcion2, opcion3, opcion4, opcion5);
        String preguntaTexto = "pregunta?";

        //When
        Preguntable multipleChoiceClasico = new MultipleChoiceClasico(preguntaTexto, opciones);

        //Then
        List<Opcion> opcionesEnPregunta = (List<Opcion>) multipleChoiceClasico.obtenerOpciones();
        Assertions.assertEquals(opcionCorrecta, opcionesEnPregunta.stream().filter(opcion -> opcion.esCorrecta()).findAny().orElse(null));

    }

    @Test
    public void multipleChoiceAlmacenaMasDeUnaOpcionCorrecta()throws ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException{
        Opcion opcionCorrecta = new Opcion("Esta Si", Boolean.TRUE);
        Opcion opcion2 = new Opcion("Esta NO", Boolean.FALSE);
        Opcion opcion3 = new Opcion("Esta Tampoco", Boolean.FALSE);
        Opcion opcion4 = new Opcion("Esta Menos", Boolean.FALSE);
        Opcion otraOpcionCorrecta = new Opcion("Si", Boolean.TRUE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcion2, opcion3, opcion4, otraOpcionCorrecta);
        List<Opcion> opcionesCorrectas = Arrays.asList(opcionCorrecta, otraOpcionCorrecta);
        String preguntaTexto = "pregunta?";

        //When
        Preguntable multipleChoiceClasico = new MultipleChoiceClasico(preguntaTexto, opciones);

        //Then
        List<Opcion> opcionesEnPregunta = (List<Opcion>) multipleChoiceClasico.obtenerOpciones();
        Assertions.assertEquals(opcionesCorrectas, opcionesEnPregunta.stream().filter(opcion -> opcion.esCorrecta()).collect(Collectors.toList()));

    }

    @Test
    public void multipleChoicePuedeTenerTodasOpcionesCorrectas()throws ParametrosInvalidosExcepcion, TipoPreguntaNoImplementadaException{
        Opcion opcion1 = new Opcion("Esta Si", Boolean.TRUE);
        Opcion opcion2 = new Opcion("Esta Si", Boolean.TRUE);
        Opcion opcion3 = new Opcion("Esta Tambien", Boolean.TRUE);
        Opcion opcion4 = new Opcion("Esta Mas", Boolean.TRUE);
        Opcion opcion5 = new Opcion("Si", Boolean.TRUE);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5);
        String preguntaTexto = "pregunta?";

        //When
        Preguntable multipleChoiceClasico = new MultipleChoiceClasico(preguntaTexto, opciones);

        //Then
        List<Opcion> opcionesEnPregunta = (List<Opcion>) multipleChoiceClasico.obtenerOpciones();
        Assertions.assertEquals(opciones, opcionesEnPregunta.stream().filter(opcion -> opcion.esCorrecta()).collect(Collectors.toList()));

    }

    @Test
    public void noSePuedeCrearMultipleChoiceSinOpciones(){
        Assertions.assertThrows(ParametrosInvalidosExcepcion.class,
                               () -> new MultipleChoiceClasico("pregunta?", Collections.EMPTY_LIST));
    }

    @Test
    public void multipleChoiceDebeTenerUnaOpcionCorrecta(){
        Opcion opcion1 = new Opcion("opcion", Boolean.FALSE);
        Opcion opcion2 = new Opcion("opcion", Boolean.FALSE);
        Opcion opcion3 = new Opcion("opcion", Boolean.FALSE);
        Opcion opcion4 = new Opcion("opcion", Boolean.FALSE);
        Opcion opcion5 = new Opcion("opcion", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class,
                               () -> new MultipleChoiceClasico("pregunta?", opciones));
    }

    @Test
    public void multipleChoiceDebeTenerMasDeUnaOpcion(){
        Opcion opcion = new Opcion("opcion", Boolean.TRUE);
        List<Opcion> opciones = Arrays.asList(opcion);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class,
                               () -> new MultipleChoiceClasico("pregunta?", opciones));
    }

    @Test
    public void multipleChoiceNoDebeTenerMasDeCincoOpciones(){
        Opcion opcion1 = new Opcion("opcion", Boolean.TRUE);
        Opcion opcion2 = new Opcion("opcion", Boolean.FALSE);
        Opcion opcion3 = new Opcion("opcion", Boolean.FALSE);
        Opcion opcion4 = new Opcion("opcion", Boolean.FALSE);
        Opcion opcion5 = new Opcion("opcion", Boolean.FALSE);
        Opcion opcion6 = new Opcion("opcion", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5, opcion6);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class,
                               () -> new MultipleChoiceClasico("pregunta?", opciones));
    }

    @Test
    public void multipleChoiceClasicoAsignaCorrectamenteElPuntajeAUnJugadorConTodasLasOpcionesCorrectas()throws ParametrosInvalidosExcepcion{
        Opcion opcion1Correcta = new Opcion("opcion 1", Boolean.TRUE);
        Opcion opcion2Correcta = new Opcion("opcion 2", Boolean.TRUE);
        Opcion opcion3Correcta = new Opcion("opcion 3", Boolean.TRUE);
        Opcion opcion4Incorrecta = new Opcion("opcion 4", Boolean.FALSE);
        Opcion opcion5Incorrecta = new Opcion("opcion 5", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Correcta,opcion4Incorrecta,opcion5Incorrecta);
        String preguntaTexto = "pregunta?";

        Preguntable multipleChoiceClasico = new MultipleChoiceClasico(preguntaTexto, opciones);
        List<Opcion> opcionesSeleccionadas = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Correcta);

        Assertions.assertEquals(1,multipleChoiceClasico.establecerPuntuacion(opcionesSeleccionadas));
    }

    @Test
    public void multipleChoiceClasicoAsignaCorrectamenteElPuntajeAUnJugadorConTodasLasOpcionesIncorrectas()throws ParametrosInvalidosExcepcion{
        Opcion opcion1Correcta = new Opcion("opcion 1", Boolean.TRUE);
        Opcion opcion2Correcta = new Opcion("opcion 2", Boolean.TRUE);
        Opcion opcion3Correcta = new Opcion("opcion 3", Boolean.TRUE);
        Opcion opcion4Incorrecta = new Opcion("opcion 4", Boolean.FALSE);
        Opcion opcion5Incorrecta = new Opcion("opcion 5", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Correcta,opcion4Incorrecta,opcion5Incorrecta);
        String preguntaTexto = "pregunta?";

        Preguntable multipleChoiceClasico = new MultipleChoiceClasico(preguntaTexto, opciones);
        List<Opcion> opcionesSeleccionadas = Arrays.asList(opcion4Incorrecta,opcion5Incorrecta);

        Assertions.assertEquals(0,multipleChoiceClasico.establecerPuntuacion(opcionesSeleccionadas));
    }

    @Test
    public void multipleChoiceClasicoAsignaCorrectamenteElPuntajeAUnJugadorConAlgunasOpcionesCorrectas()throws ParametrosInvalidosExcepcion{
        Opcion opcion1Correcta = new Opcion("opcion 1", Boolean.TRUE);
        Opcion opcion2Correcta = new Opcion("opcion 2", Boolean.TRUE);
        Opcion opcion3Correcta = new Opcion("opcion 3", Boolean.TRUE);
        Opcion opcion4Incorrecta = new Opcion("opcion 4", Boolean.FALSE);
        Opcion opcion5Incorrecta = new Opcion("opcion 5", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Correcta,opcion4Incorrecta,opcion5Incorrecta);
        String preguntaTexto = "pregunta?";

        Preguntable multipleChoiceClasico = new MultipleChoiceClasico(preguntaTexto, opciones);
        List<Opcion> opcionesSeleccionadas = Arrays.asList(opcion1Correcta,opcion2Correcta);

        Assertions.assertEquals(0,multipleChoiceClasico.establecerPuntuacion(opcionesSeleccionadas));
    }

    @Test
    public void multipleChoiceClasicoAsignaCorrectamenteElPuntajeAUnJugadorConAlgunasOpcionesCorrectasEIncorrectas()throws ParametrosInvalidosExcepcion{
        Opcion opcion1Correcta = new Opcion("opcion 1", Boolean.TRUE);
        Opcion opcion2Correcta = new Opcion("opcion 2", Boolean.TRUE);
        Opcion opcion3Correcta = new Opcion("opcion 3", Boolean.TRUE);
        Opcion opcion4Incorrecta = new Opcion("opcion 4", Boolean.FALSE);
        Opcion opcion5Incorrecta = new Opcion("opcion 5", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Correcta,opcion4Incorrecta,opcion5Incorrecta);
        String preguntaTexto = "pregunta?";

        Preguntable multipleChoiceClasico = new MultipleChoiceClasico(preguntaTexto, opciones);
        List<Opcion> opcionesSeleccionadas = Arrays.asList(opcion1Correcta,opcion2Correcta,opcion4Incorrecta);

        Assertions.assertEquals(0,multipleChoiceClasico.establecerPuntuacion(opcionesSeleccionadas));
    }

}

