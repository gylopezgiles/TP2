package edu.fiuba.algo3.modelo.pregunta.multiplechoice;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.exclusividad.Exclusividad;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
* Supuesto: Se suman puntos por respuesta correcta seleccionada
* siempre y cuando no se hayan elegido opciones incorrectas
 */

public class MultipleChoiceConPenalidadTest {

    private Exclusividad exclusividad;

    @BeforeEach
    public void setup(){
        exclusividad = new Exclusividad();
    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaSinOpciones() {

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new MultipleChoiceConPenalidad("Qué países se encuentran en Asia?", Collections.EMPTY_LIST));

    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaConCantidadOpcionesMenorA2() {

        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion = new Opcion("Lima", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new MultipleChoiceConPenalidad("Qué países se encuentran en Asia?", opciones));

    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaConCantidadOpcionesMayorA5() {

        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Rusia", esCorrecta);
        Opcion opcion2 = new Opcion("Bolivia", !esCorrecta);
        Opcion opcion3 = new Opcion("Uruguay", !esCorrecta);
        Opcion opcion4 = new Opcion("China", esCorrecta);
        Opcion opcion5 = new Opcion("Japón", esCorrecta);
        Opcion opcion6 = new Opcion("Marruecos", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5, opcion6);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new MultipleChoiceConPenalidad("Qué países se encuentran en Asia?", opciones));

    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaSinOpcionesCorrectas() {

        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Francia", !esCorrecta);
        Opcion opcion2 = new Opcion("Australia", !esCorrecta);
        Opcion opcion3 = new Opcion("Bolivia", !esCorrecta);
        Opcion opcion4 = new Opcion("Canadá", !esCorrecta);
        Opcion opcion5 = new Opcion("Egipto", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new MultipleChoiceConPenalidad("Qué países se encuentran en Asia?", opciones));

    }

    @Test
    public void debeEstablecerPuntuacionCorrectamenteEligiendoTodasRespuestasCorrectasSinIncorrectas() throws ParametrosInvalidosExcepcion {
        String preguntaTexto = "Qué países se encuentran en Asia?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("China", esCorrecta);
        Opcion opcion2Correcta = new Opcion("India", esCorrecta);
        Opcion opcion3Correcta = new Opcion("Japón", esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("España", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Correcta, opcion4Incorrecta);
        Preguntable multipleChoiceConPenalidad = new MultipleChoiceConPenalidad(preguntaTexto, opciones);
        List<String> opcionesSeleccionadas = Arrays.asList("China", "India", "Japón");

        int puntuacion = multipleChoiceConPenalidad.establecerPuntuacion(opcionesSeleccionadas, Multiplicador.PorDefecto, exclusividad);

        Assertions.assertEquals(3, puntuacion);
        Assertions.assertEquals(TipoPregunta.MultipleChoiceConPenalidad, multipleChoiceConPenalidad.obtenerTipoPregunta());
    }

    @Test
    public void debeEstablecerPuntuacionCorrectamenteEligiendoAlgunasRespuestasCorrectasSinIncorrectas() throws ParametrosInvalidosExcepcion {
        String preguntaTexto = "Qué países se encuentran en Europa?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("España", esCorrecta);
        Opcion opcion2Correcta = new Opcion("Francia", esCorrecta);
        Opcion opcion3Correcta = new Opcion("Alemania", esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("Ecuador", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Correcta, opcion4Incorrecta);
        Preguntable multipleChoiceConPenalidad = new MultipleChoiceConPenalidad(preguntaTexto, opciones);
        List<String> opcionesSeleccionadas = Arrays.asList("España", "Alemania");

        int puntuacion = multipleChoiceConPenalidad.establecerPuntuacion(opcionesSeleccionadas, Multiplicador.PorDefecto, exclusividad);

        Assertions.assertEquals(2, puntuacion);
    }

    @Test
    public void debeEstablecerPuntuacionCorrectamenteEligiendoAlgunasRespuestasCorrectasYUnaIncorrecta() throws ParametrosInvalidosExcepcion {
        String preguntaTexto = "Qué países se encuentran en África?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("Egipto", esCorrecta);
        Opcion opcion2Correcta = new Opcion("Kenia", esCorrecta);
        Opcion opcion3Incorrecta = new Opcion("Brasil", !esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("Estonia", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta, opcion4Incorrecta);
        Preguntable multipleChoiceConPenalidad = new MultipleChoiceConPenalidad(preguntaTexto, opciones);
        List<String> opcionesSeleccionadas = Arrays.asList("Egipto", "Kenia", "Brasil");

        int puntuacion = multipleChoiceConPenalidad.establecerPuntuacion(opcionesSeleccionadas, Multiplicador.PorDefecto, exclusividad);

        Assertions.assertEquals(-1, puntuacion);
    }

    @Test
    public void debeEstablecerPuntuacionCorrectamenteEligiendoAlgunasRespuestasCorrectasYAlgunasIncorrecta() throws ParametrosInvalidosExcepcion {
        String preguntaTexto = "Qué países se encuentran en América del Sur?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("Chile", esCorrecta);
        Opcion opcion2Correcta = new Opcion("Paraguay", esCorrecta);
        Opcion opcion3Incorrecta = new Opcion("México", !esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("Panamá", !esCorrecta);
        Opcion opcion5Incorrecta = new Opcion("Estados Unidos", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta, opcion4Incorrecta, opcion5Incorrecta);
        Preguntable multipleChoiceConPenalidad = new MultipleChoiceConPenalidad(preguntaTexto, opciones);
        List<String> opcionesSeleccionadas = Arrays.asList("Chile", "México", "Panamá");

        int puntuacion = multipleChoiceConPenalidad.establecerPuntuacion(opcionesSeleccionadas, Multiplicador.PorDefecto, exclusividad);

        Assertions.assertEquals(-2, puntuacion);
    }

    @Test
    public void debeEstablecerPuntuacionCorrectamenteEligiendoTodasRespuestasIncorrectas() throws ParametrosInvalidosExcepcion {
        String preguntaTexto = "Qué países se encuentran en América Central?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcion1Correcta = new Opcion("Guatemala", esCorrecta);
        Opcion opcion2Correcta = new Opcion("Cuba", esCorrecta);
        Opcion opcion3Incorrecta = new Opcion("Rusia", !esCorrecta);
        Opcion opcion4Incorrecta = new Opcion("Lituania", !esCorrecta);
        Opcion opcion5Incorrecta = new Opcion("Estados Unidos", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcion1Correcta, opcion2Correcta, opcion3Incorrecta, opcion4Incorrecta, opcion5Incorrecta);
        Preguntable multipleChoiceConPenalidad = new MultipleChoiceConPenalidad(preguntaTexto, opciones);
        List<String> opcionesSeleccionadas = Arrays.asList("Rusia", "Lituania", "Estados Unidos");

        int puntuacion = multipleChoiceConPenalidad.establecerPuntuacion(opcionesSeleccionadas, Multiplicador.PorDefecto, exclusividad);

        Assertions.assertEquals(-3, puntuacion);
    }

}
