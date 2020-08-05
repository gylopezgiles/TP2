package edu.fiuba.algo3.modelo.pregunta.GroupChoice;

import edu.fiuba.algo3.modelo.excepciones.MultiplicadorExcepcion;
import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.multiplechoice.MultipleChoiceClasico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GroupChoiceTest {

    @Test
    public void crearGroupChoiceConOpciones()throws ParametrosInvalidosExcepcion {

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";
        Boolean pertenece = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenece);
        Opcion opcion2 = new Opcion("Wellington", pertenece);
        Opcion opcion3 = new Opcion("Hamilton", pertenece);
        Opcion opcion4 = new Opcion("Canberra", !pertenece);
        Opcion opcion5 = new Opcion("Hawaii", !pertenece);
        Opcion opcion6 = new Opcion("Oslo", !pertenece);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5, opcion6);

        Preguntable pregunta= new GroupChoice(preguntaTexto, opciones);

        Assertions.assertEquals(GroupChoice.class , pregunta.getClass());
    }

    @Test
    public void groupChoicedebeTenerAlMenosDosOpcionesDeGruposDiferentes() throws ParametrosInvalidosExcepcion, MultiplicadorExcepcion {

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";
        Boolean pertenece = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenece);
        Opcion opcion2 = new Opcion("Oslo", !pertenece);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2);

        Preguntable pregunta= new GroupChoice(preguntaTexto, opciones);

        Assertions.assertEquals(1 , pregunta.establecerPuntuacion(opciones));
    }

    @Test
    public void groupChoicedebeTenerAlMenosUnaOpcionDeCadaGrupo(){

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";
        Boolean pertenece = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenece);
        Opcion opcion2 = new Opcion("Wellington", pertenece);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class,
                () -> new GroupChoice(preguntaTexto, opciones));
    }

    @Test
    public void groupChoicenoSePuedeCrearSinOpciones(){
        Assertions.assertThrows(ParametrosInvalidosExcepcion.class,
                () -> new GroupChoice("¿pregunta?", Collections.EMPTY_LIST));
    }

    @Test
    public void groupChoicenoPuedeTenerMenosDeDosOpciones(){

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";
        Boolean pertenece = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenece);
        List<Opcion> opciones = Arrays.asList(opcion1);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class,
                () -> new GroupChoice(preguntaTexto, opciones));
    }

    @Test
    public void groupChoicenoPuedeTenerMasDeSeisOpciones() {

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";
        Boolean pertenece = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenece);
        Opcion opcion2 = new Opcion("Wellington", pertenece);
        Opcion opcion3 = new Opcion("Hamilton", pertenece);
        Opcion opcion4 = new Opcion("Canberra", !pertenece);
        Opcion opcion5 = new Opcion("Hawaii", !pertenece);
        Opcion opcion6 = new Opcion("Oslo", !pertenece);
        Opcion opcion7 = new Opcion("Melbourne", !pertenece);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5, opcion6, opcion7);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class,
                () -> new GroupChoice(preguntaTexto, opciones));
    }

    @Test
    public void groupChoiceEstablecePuntuacionCorrectamente() throws ParametrosInvalidosExcepcion, MultiplicadorExcepcion {

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";
        Boolean pertenece = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenece);
        Opcion opcion2 = new Opcion("Wellington", pertenece);
        Opcion opcion3 = new Opcion("Hamilton", pertenece);
        Opcion opcion4 = new Opcion("Canberra", !pertenece);
        Opcion opcion5 = new Opcion("Hawaii", !pertenece);
        Opcion opcion6 = new Opcion("Oslo", !pertenece);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5, opcion6);

        Preguntable pregunta= new GroupChoice(preguntaTexto, opciones);

        Opcion respuesta1 = new Opcion("Auckland", pertenece);
        Opcion respuesta2 = new Opcion("Wellington", pertenece);
        Opcion respuesta3 = new Opcion("Hamilton", pertenece);
        Opcion respuesta4 = new Opcion("Canberra", !pertenece);
        Opcion respuesta5 = new Opcion("Hawaii", !pertenece);
        Opcion respuesta6 = new Opcion("Oslo", !pertenece);

        List<Opcion> opcionesAgrupadas = Arrays.asList(respuesta1, respuesta2, respuesta3, respuesta4, respuesta5, respuesta6);

        Assertions.assertEquals(1, pregunta.establecerPuntuacion(opcionesAgrupadas, Multiplicador.PorDefecto));
    }

    @Test
    public void groupChoiceEstablecePuntuacionCorrectamenteConGruposDeDiferenteTamanio() throws ParametrosInvalidosExcepcion, MultiplicadorExcepcion {

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";
        Boolean pertenece = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenece);
        Opcion opcion2 = new Opcion("Wellington", pertenece);
        Opcion opcion3 = new Opcion("Hamilton", pertenece);
        Opcion opcion4 = new Opcion("Canberra", !pertenece);
        Opcion opcion5 = new Opcion("Hawaii", !pertenece);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5);

        Preguntable pregunta= new GroupChoice(preguntaTexto, opciones);

        Opcion respuesta1 = new Opcion("Auckland", pertenece);
        Opcion respuesta2 = new Opcion("Wellington", pertenece);
        Opcion respuesta3 = new Opcion("Hamilton", pertenece);
        Opcion respuesta4 = new Opcion("Canberra", !pertenece);
        Opcion respuesta5 = new Opcion("Hawaii", !pertenece);

        List<Opcion> opcionesAgrupadas = Arrays.asList(respuesta1, respuesta2, respuesta3, respuesta4, respuesta5);

        Assertions.assertEquals(1, pregunta.establecerPuntuacion(opcionesAgrupadas, Multiplicador.PorDefecto));
    }

    @Test
    public void groupChoiceNoAsignaPuntosSiAlgunElementoNoPerteneceAlGrupo() throws ParametrosInvalidosExcepcion, MultiplicadorExcepcion {

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";
        Boolean pertenece = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenece);
        Opcion opcion2 = new Opcion("Wellington", pertenece);
        Opcion opcion3 = new Opcion("Hamilton", pertenece);
        Opcion opcion4 = new Opcion("Canberra", !pertenece);
        Opcion opcion5 = new Opcion("Hawaii", !pertenece);
        Opcion opcion6 = new Opcion("Oslo", !pertenece);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5, opcion6);

        Preguntable pregunta= new GroupChoice(preguntaTexto, opciones);

        Opcion respuesta1 = new Opcion("Auckland", pertenece);
        Opcion respuesta2 = new Opcion("Wellington", pertenece);
        Opcion respuesta3 = new Opcion("Hamilton", !pertenece);
        Opcion respuesta4 = new Opcion("Canberra", pertenece);
        Opcion respuesta5 = new Opcion("Hawaii", !pertenece);
        Opcion respuesta6 = new Opcion("Oslo", !pertenece);

        List<Opcion> opcionesAgrupadas = Arrays.asList(respuesta1, respuesta2, respuesta3, respuesta4, respuesta5, respuesta6);

        Assertions.assertEquals(0, pregunta.establecerPuntuacion(opcionesAgrupadas, Multiplicador.PorDefecto));
    }

    @Test
    public void groupChoiceNoAsignaPuntosSiNoSeAgrupanTodosLosElementosDeCadaGrupo() throws ParametrosInvalidosExcepcion, MultiplicadorExcepcion {

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";
        Boolean pertenece = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenece);
        Opcion opcion2 = new Opcion("Wellington", pertenece);
        Opcion opcion3 = new Opcion("Hamilton", pertenece);
        Opcion opcion4 = new Opcion("Canberra", !pertenece);
        Opcion opcion5 = new Opcion("Hawaii", !pertenece);
        Opcion opcion6 = new Opcion("Oslo", !pertenece);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5, opcion6);

        Preguntable pregunta= new GroupChoice(preguntaTexto, opciones);

        Opcion respuesta1 = new Opcion("Auckland", pertenece);
        Opcion respuesta2 = new Opcion("Wellington", pertenece);
        Opcion respuesta3 = new Opcion("Hamilton", pertenece);
        Opcion respuesta4 = new Opcion("Canberra", !pertenece);
        Opcion respuesta5 = new Opcion("Hawaii", !pertenece);

        List<Opcion> opcionesAgrupadas = Arrays.asList(respuesta1, respuesta2, respuesta3, respuesta4, respuesta5);

        Assertions.assertEquals(0, pregunta.establecerPuntuacion(opcionesAgrupadas, Multiplicador.PorDefecto));
    }
}
