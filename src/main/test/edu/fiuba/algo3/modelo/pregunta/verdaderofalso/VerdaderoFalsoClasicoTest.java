package edu.fiuba.algo3.modelo.pregunta.verdaderofalso;

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

public class VerdaderoFalsoClasicoTest {

    private Exclusividad exclusividad;

    @BeforeEach
    public void setup(){
        exclusividad = new Exclusividad();
    }

    @Test
    public void debeCrearUnaPreguntaVerdaderFalsoConOpciones() throws ParametrosInvalidosExcepcion {

        String preguntaTexto = "¿Los Elefantes son los mamiferos TERRESTRES mas grandes del mundo?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta = new Opcion("Verdadero", esCorrecta);
        Opcion opcionIncorrecta = new Opcion("Falso", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);

        Preguntable verdaderoFalsoClasico = new VerdaderoFalsoClasico(preguntaTexto, opciones);

        Assertions.assertEquals(opciones, verdaderoFalsoClasico.obtenerOpciones());
        Assertions.assertEquals(preguntaTexto, verdaderoFalsoClasico.obtenerPregunta());
        Assertions.assertEquals(TipoPregunta.VerdaderoFalsoClasico, verdaderoFalsoClasico.obtenerTipoPregunta());
    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaSinOpciones() {

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new VerdaderoFalsoClasico("¿pregunta?", Collections.EMPTY_LIST));

    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaConCantidadOpcionesDistintoDe2() {

        String preguntaTexto = "¿Los Elefantes son los mamiferos TERRESTRES mas grandes del mundo?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta = new Opcion("Verdadero", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new VerdaderoFalsoClasico(preguntaTexto, opciones));

    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaSinOpcionesCorrectas() {

        String preguntaTexto = "El gato de Schrödinger esta muerto?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionIncorrecta1 = new Opcion("Verdadero", !esCorrecta);
        Opcion opcionIncorrecta2 = new Opcion("Falso", !esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionIncorrecta1, opcionIncorrecta2);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new VerdaderoFalsoClasico(preguntaTexto, opciones));

    }

    @Test
    public void debeLanzarExcepcionCrearPreguntaConMasDe1OpcionCorrecta() {

        String preguntaTexto = "El gato de Schrödinger esta vivo?";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionCorrecta1 = new Opcion("Verdadero", esCorrecta);
        Opcion opcionCorrecta2 = new Opcion("Falso", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta1, opcionCorrecta2);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class, () -> new VerdaderoFalsoClasico(preguntaTexto, opciones));

    }

    @Test
    public void establecerPuntuacionRespondiendoCorrectamente() throws ParametrosInvalidosExcepcion {
        String preguntaTexto = "La canción Feelling Good fue escrita por Muse";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionIncorrecta = new Opcion("Verdadero", !esCorrecta);
        Opcion opcionCorrecta = new Opcion("Falso", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        List<String> opcionSeleccionada = Arrays.asList("Falso");

        Preguntable verdaderoFalsoClasico = new VerdaderoFalsoClasico(preguntaTexto, opciones);

        Assertions.assertEquals(1, verdaderoFalsoClasico.establecerPuntuacion(opcionSeleccionada, Multiplicador.PorDefecto, exclusividad));
    }

    @Test
    public void establecerPuntuacionRespondiendoIncorrectamente() throws ParametrosInvalidosExcepcion {
        String preguntaTexto = "La canción Feelling Good fue escrita por Muse";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionIncorrecta = new Opcion("Verdadero", !esCorrecta);
        Opcion opcionCorrecta = new Opcion("Falso", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        List<String> opcionSeleccionada = Arrays.asList("Verdadero");

        Preguntable verdaderoFalsoClasico = new VerdaderoFalsoClasico(preguntaTexto, opciones);

        Assertions.assertEquals(0, verdaderoFalsoClasico.establecerPuntuacion(opcionSeleccionada, Multiplicador.PorDefecto, exclusividad));
    }

    @Test
    public void alEstablecerPuntuacionConMultiplicadorNoLoDebeAplicar() throws ParametrosInvalidosExcepcion {
        String preguntaTexto = "La canción Feelling Good fue escrita por Muse";
        Boolean esCorrecta = Boolean.TRUE;
        Opcion opcionIncorrecta = new Opcion("Verdadero", !esCorrecta);
        Opcion opcionCorrecta = new Opcion("Falso", esCorrecta);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcionIncorrecta);
        List<String> opcionSeleccionada = Arrays.asList("Falso");

        Preguntable verdaderoFalsoClasico = new VerdaderoFalsoClasico(preguntaTexto, opciones);

        Assertions.assertEquals(1, verdaderoFalsoClasico.establecerPuntuacion(opcionSeleccionada, Multiplicador.PorDos, exclusividad));
        Assertions.assertEquals(1, verdaderoFalsoClasico.establecerPuntuacion(opcionSeleccionada, Multiplicador.PorTres, exclusividad));

    }


}
