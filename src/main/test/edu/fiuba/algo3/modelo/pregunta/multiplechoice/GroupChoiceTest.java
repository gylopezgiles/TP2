package edu.fiuba.algo3.modelo.pregunta.multiplechoice;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

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
    public void groupChoiceDebeTenerAlMenosUnaOpcionDeGruposDiferentes() throws ParametrosInvalidosExcepcion {

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";

        Boolean pertenece = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenece);
        Opcion opcion2 = new Opcion("Oslo", !pertenece);

        List<Opcion> opciones = Arrays.asList(opcion1, opcion2);

        Preguntable pregunta = new GroupChoice(preguntaTexto, opciones);

        List<Opcion> respuestaGrupoVerdadero = Arrays.asList(opcion1);
        List<Opcion> respuestaGrupoFalso = Arrays.asList(opcion2);
        List<List<Opcion>> respuestas = Arrays.asList(respuestaGrupoVerdadero, respuestaGrupoFalso);

        Assertions.assertEquals(1 , pregunta.establecerPuntuacion(respuestas));
    }

    @Test
    public void groupChoiceDebeTenerAlMenosUnaOpcionDeCadaGrupo(){

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";
        Boolean pertenece = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenece);
        Opcion opcion2 = new Opcion("Wellington", pertenece);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class,
                () -> new GroupChoice(preguntaTexto, opciones));
    }

    @Test
    public void groupChoiceNoSePuedeCrearSinOpciones(){
        Assertions.assertThrows(ParametrosInvalidosExcepcion.class,
                () -> new GroupChoice("¿pregunta?", Collections.EMPTY_LIST));
    }

    @Test
    public void groupChoiceNoPuedeTenerMenosDeDosOpciones(){

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";
        Boolean pertenece = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenece);
        List<Opcion> opciones = Arrays.asList(opcion1);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class,
                () -> new GroupChoice(preguntaTexto, opciones));
    }

    @Test
    public void groupChoiceNoPuedeTenerMasDeSeisOpciones() {

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
    public void groupChoiceEstablecePuntuacionCorrectamente() throws ParametrosInvalidosExcepcion{

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


        List<Opcion> respuestaGrupoVerdadero = Arrays.asList(opcion1, opcion2, opcion3);
        List<Opcion> respuestaGrupoFalso = Arrays.asList(opcion4, opcion5, opcion6);
        List<List<Opcion>> respuestas = Arrays.asList(respuestaGrupoVerdadero, respuestaGrupoFalso);


        Assertions.assertEquals(1, pregunta.establecerPuntuacion(respuestas));
    }

    @Test
    public void groupChoiceEstablecePuntuacionCorrectamenteConGruposDeDiferenteTamanio() throws ParametrosInvalidosExcepcion {

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";
        Boolean pertenece = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenece);
        Opcion opcion2 = new Opcion("Wellington", pertenece);
        Opcion opcion3 = new Opcion("Hamilton", pertenece);
        Opcion opcion4 = new Opcion("Canberra", !pertenece);
        Opcion opcion5 = new Opcion("Hawaii", !pertenece);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5);

        Preguntable pregunta= new GroupChoice(preguntaTexto, opciones);

        List<Opcion> respuestaGrupoVerdadero = Arrays.asList(opcion1, opcion2, opcion3);
        List<Opcion> respuestaGrupoFalso = Arrays.asList(opcion4, opcion5);
        List<List<Opcion>> respuestas = Arrays.asList(respuestaGrupoVerdadero, respuestaGrupoFalso);


        Assertions.assertEquals(1, pregunta.establecerPuntuacion(respuestas));
    }

    @Test
    public void groupChoiceNoAsignaPuntosSiAlgunElementoNoPerteneceAlGrupo() throws ParametrosInvalidosExcepcion{

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

        List<Opcion> respuestaGrupoVerdadero = Arrays.asList(opcion1, opcion2, opcion4);
        List<Opcion> respuestaGrupoFalso = Arrays.asList(opcion3, opcion5, opcion6);
        List<List<Opcion>> respuestas = Arrays.asList(respuestaGrupoVerdadero, respuestaGrupoFalso);

        Assertions.assertEquals(0, pregunta.establecerPuntuacion(respuestas));
    }

    @Test
    public void groupChoiceNoAsignaPuntosSiVariosElementosNoPertenecenAlGrupo() throws ParametrosInvalidosExcepcion{

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

        List<Opcion> respuestaGrupoVerdadero = Arrays.asList(opcion5, opcion2, opcion4);
        List<Opcion> respuestaGrupoFalso = Arrays.asList(opcion3, opcion5, opcion1);
        List<List<Opcion>> respuestas = Arrays.asList(respuestaGrupoVerdadero, respuestaGrupoFalso);

        Assertions.assertEquals(0, pregunta.establecerPuntuacion(respuestas));
    }


    @Test
    public void groupChoiceNoAsignaPuntosSiNoSeAgrupanTodosLosElementosDeCadaGrupo() throws ParametrosInvalidosExcepcion{

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

        List<Opcion> respuestaGrupoVerdadero = Arrays.asList(opcion1, opcion2, opcion3);
        List<Opcion> respuestaGrupoFalso = Arrays.asList(opcion4, opcion5);
        List<List<Opcion>> respuestas = Arrays.asList(respuestaGrupoVerdadero, respuestaGrupoFalso);

        Assertions.assertEquals(0, pregunta.establecerPuntuacion(respuestas));
    }

    @Test
    public void groupChoiceAsignaPuntosCuandoLasRespuestasEstanDesordenadasPeroEnLosGruposCorrectos() throws ParametrosInvalidosExcepcion{

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

        List<Opcion> respuestaGrupoVerdadero = Arrays.asList(opcion3, opcion2, opcion1);
        List<Opcion> respuestaGrupoFalso = Arrays.asList(opcion6, opcion4, opcion5);
        List<List<Opcion>> respuestas = Arrays.asList(respuestaGrupoVerdadero, respuestaGrupoFalso);

        Assertions.assertEquals(1, pregunta.establecerPuntuacion(respuestas));
    }
}
