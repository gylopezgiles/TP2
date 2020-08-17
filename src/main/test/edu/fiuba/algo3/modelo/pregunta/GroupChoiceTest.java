package edu.fiuba.algo3.modelo.pregunta;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GroupChoiceTest {

    @Test
    public void crearGroupChoiceConOpciones()throws ParametrosInvalidosExcepcion {

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";

        Boolean pertenecePrimerGrupo  = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenecePrimerGrupo );
        Opcion opcion2 = new Opcion("Wellington", pertenecePrimerGrupo );
        Opcion opcion3 = new Opcion("Hamilton", pertenecePrimerGrupo );
        Opcion opcion4 = new Opcion("Canberra", !pertenecePrimerGrupo );
        Opcion opcion5 = new Opcion("Hawaii", !pertenecePrimerGrupo );
        Opcion opcion6 = new Opcion("Oslo", !pertenecePrimerGrupo );
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5, opcion6);

        Preguntable pregunta = new GroupChoice(preguntaTexto, opciones);

        Assertions.assertEquals(TipoPregunta.GroupChoice, pregunta.obtenerTipoPregunta());
        Assertions.assertEquals(opciones, pregunta.obtenerOpciones());
        Assertions.assertEquals(preguntaTexto, pregunta.obtenerPregunta());
    }

    @Test
    public void groupChoiceNoSePuedeCrearSinOpciones(){
        Assertions.assertThrows(ParametrosInvalidosExcepcion.class,
                () -> new GroupChoice("¿pregunta?", Collections.EMPTY_LIST));
    }

    @Test
    public void groupChoiceNoPuedeTenerMenosDeDosOpciones(){

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";

        Boolean pertenecePrimerGrupo = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenecePrimerGrupo);
        List<Opcion> opciones = Arrays.asList(opcion1);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class,
                () -> new GroupChoice(preguntaTexto, opciones));
    }

    @Test
    public void groupChoiceNoPuedeTenerMasDeSeisOpciones() {

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";

        Boolean pertenecePrimerGrupo = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenecePrimerGrupo);
        Opcion opcion2 = new Opcion("Wellington", pertenecePrimerGrupo);
        Opcion opcion3 = new Opcion("Hamilton", pertenecePrimerGrupo);
        Opcion opcion4 = new Opcion("Canberra", !pertenecePrimerGrupo);
        Opcion opcion5 = new Opcion("Hawaii", !pertenecePrimerGrupo);
        Opcion opcion6 = new Opcion("Oslo", !pertenecePrimerGrupo);
        Opcion opcion7 = new Opcion("Melbourne", !pertenecePrimerGrupo);

        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5, opcion6, opcion7);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class,
                () -> new GroupChoice(preguntaTexto, opciones));
    }

    @Test
    public void groupChoiceDebeTenerAlMenosUnaOpcionDeCadaGrupo_grupo1Vacio(){

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";

        Boolean pertenecePrimerGrupo = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", !pertenecePrimerGrupo);
        Opcion opcion2 = new Opcion("Wellington", !pertenecePrimerGrupo);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class,
                () -> new GroupChoice(preguntaTexto, opciones));
    }

    @Test
    public void groupChoiceDebeTenerAlMenosUnaOpcionDeCadaGrupo_grupo2Vacio(){

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";

        Boolean pertenecePrimerGrupo = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenecePrimerGrupo);
        Opcion opcion2 = new Opcion("Wellington", pertenecePrimerGrupo);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2);

        Assertions.assertThrows(ParametrosInvalidosExcepcion.class,
                () -> new GroupChoice(preguntaTexto, opciones));
    }

    @Test
    public void groupChoiceDebeTenerAlMenosUnaOpcionDeGruposDiferentes() throws ParametrosInvalidosExcepcion {

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";

        Boolean pertenecePrimerGrupo  = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenecePrimerGrupo );
        Opcion opcion2 = new Opcion("Oslo", !pertenecePrimerGrupo );

        List<Opcion> opciones = Arrays.asList(opcion1, opcion2);

        Preguntable pregunta = new GroupChoice(preguntaTexto, opciones);

        List<String> respuestasPrimerGrupo = Arrays.asList("Auckland");
        List<String> respuestasSegundoGrupo  = Arrays.asList("Oslo");
        List<List<String>> respuestas = Arrays.asList(respuestasPrimerGrupo, respuestasSegundoGrupo );

        Assertions.assertEquals(1 , pregunta.establecerPuntuacion(respuestas));
    }


    @Test
    public void groupChoiceEstablecePuntuacionCorrectamente() throws ParametrosInvalidosExcepcion {

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";

        Boolean pertenecePrimerGrupo = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenecePrimerGrupo);
        Opcion opcion2 = new Opcion("Wellington", pertenecePrimerGrupo);
        Opcion opcion3 = new Opcion("Hamilton", pertenecePrimerGrupo);
        Opcion opcion4 = new Opcion("Canberra", !pertenecePrimerGrupo);
        Opcion opcion5 = new Opcion("Hawaii", !pertenecePrimerGrupo);
        Opcion opcion6 = new Opcion("Oslo", !pertenecePrimerGrupo);

        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5, opcion6);

        Preguntable pregunta= new GroupChoice(preguntaTexto, opciones);

        List<String> respuestasPrimerGrupo = Arrays.asList("Auckland", "Wellington", "Hamilton");
        List<String> respuestasSegundoGrupo  = Arrays.asList("Canberra", "Hawaii", "Oslo");
        List<List<String>> respuestas = Arrays.asList(respuestasPrimerGrupo, respuestasSegundoGrupo );

        Assertions.assertEquals(1, pregunta.establecerPuntuacion(respuestas));
    }

    @Test
    public void groupChoiceEstablecePuntuacionCorrectamenteConGruposDeDiferenteTamanio() throws ParametrosInvalidosExcepcion {

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";

        Boolean pertenecePrimerGrupo = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenecePrimerGrupo);
        Opcion opcion2 = new Opcion("Wellington", pertenecePrimerGrupo);
        Opcion opcion3 = new Opcion("Hamilton", pertenecePrimerGrupo);
        Opcion opcion4 = new Opcion("Canberra", !pertenecePrimerGrupo);
        Opcion opcion5 = new Opcion("Hawaii", !pertenecePrimerGrupo);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5);

        Preguntable pregunta= new GroupChoice(preguntaTexto, opciones);

        List<String> respuestasPrimerGrupo = Arrays.asList("Auckland", "Wellington", "Hamilton");
        List<String> respuestasSegundoGrupo  = Arrays.asList("Canberra", "Hawaii");
        List<List<String>> respuestas = Arrays.asList(respuestasPrimerGrupo, respuestasSegundoGrupo );


        Assertions.assertEquals(1, pregunta.establecerPuntuacion(respuestas));
    }

    @Test
    public void groupChoiceNoAsignaPuntosSiAlgunElementoNopertenecePrimerGrupoAlGrupo() throws ParametrosInvalidosExcepcion {

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";

        Boolean pertenecePrimerGrupo = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenecePrimerGrupo);
        Opcion opcion2 = new Opcion("Wellington", pertenecePrimerGrupo);
        Opcion opcion3 = new Opcion("Hamilton", pertenecePrimerGrupo);
        Opcion opcion4 = new Opcion("Canberra", !pertenecePrimerGrupo);
        Opcion opcion5 = new Opcion("Hawaii", !pertenecePrimerGrupo);
        Opcion opcion6 = new Opcion("Oslo", !pertenecePrimerGrupo);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5, opcion6);

        Preguntable pregunta= new GroupChoice(preguntaTexto, opciones);

        List<String> respuestasPrimerGrupo = Arrays.asList("Auckland", "Wellington", "Canberra");
        List<String> respuestasSegundoGrupo  = Arrays.asList("Hamilton", "Hawaii", "Oslo");
        List<List<String>> respuestas = Arrays.asList(respuestasPrimerGrupo, respuestasSegundoGrupo );

        Assertions.assertEquals(0, pregunta.establecerPuntuacion(respuestas));
    }

    @Test
    public void groupChoiceNoAsignaPuntosSiVariosElementosNopertenecePrimerGruponAlGrupo() throws ParametrosInvalidosExcepcion {

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";

        Boolean pertenecePrimerGrupo = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenecePrimerGrupo);
        Opcion opcion2 = new Opcion("Wellington", pertenecePrimerGrupo);
        Opcion opcion3 = new Opcion("Hamilton", pertenecePrimerGrupo);
        Opcion opcion4 = new Opcion("Canberra", !pertenecePrimerGrupo);
        Opcion opcion5 = new Opcion("Hawaii", !pertenecePrimerGrupo);
        Opcion opcion6 = new Opcion("Oslo", !pertenecePrimerGrupo);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5, opcion6);

        Preguntable pregunta= new GroupChoice(preguntaTexto, opciones);

        List<String> respuestasPrimerGrupo = Arrays.asList("Hawaii", "Wellington", "Canberra");
        List<String> respuestasSegundoGrupo  = Arrays.asList("Hamilton", "Hawaii", "Auckland");
        List<List<String>> respuestas = Arrays.asList(respuestasPrimerGrupo, respuestasSegundoGrupo );

        Assertions.assertEquals(0, pregunta.establecerPuntuacion(respuestas));
    }


    @Test
    public void groupChoiceNoAsignaPuntosSiNoSeAgrupanTodosLosElementosDeCadaGrupo() throws ParametrosInvalidosExcepcion {

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";

        Boolean pertenecePrimerGrupo = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenecePrimerGrupo);
        Opcion opcion2 = new Opcion("Wellington", pertenecePrimerGrupo);
        Opcion opcion3 = new Opcion("Hamilton", pertenecePrimerGrupo);
        Opcion opcion4 = new Opcion("Canberra", !pertenecePrimerGrupo);
        Opcion opcion5 = new Opcion("Hawaii", !pertenecePrimerGrupo);
        Opcion opcion6 = new Opcion("Oslo", !pertenecePrimerGrupo);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5, opcion6);

        Preguntable pregunta= new GroupChoice(preguntaTexto, opciones);

        List<String> respuestasPrimerGrupo = Arrays.asList("Auckland", "Wellington", "Hamilton");
        List<String> respuestasSegundoGrupo  = Arrays.asList("Canberra", "Hawaii");
        List<List<String>> respuestas = Arrays.asList(respuestasPrimerGrupo, respuestasSegundoGrupo );

        Assertions.assertEquals(0, pregunta.establecerPuntuacion(respuestas));
    }

    @Test
    public void groupChoiceAsignaPuntosCuandoLasRespuestasEstanDesordenadasPeroEnLosGruposCorrectos() throws ParametrosInvalidosExcepcion {

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";

        Boolean pertenecePrimerGrupo = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenecePrimerGrupo);
        Opcion opcion2 = new Opcion("Wellington", pertenecePrimerGrupo);
        Opcion opcion3 = new Opcion("Hamilton", pertenecePrimerGrupo);
        Opcion opcion4 = new Opcion("Canberra", !pertenecePrimerGrupo);
        Opcion opcion5 = new Opcion("Hawaii", !pertenecePrimerGrupo);
        Opcion opcion6 = new Opcion("Oslo", !pertenecePrimerGrupo);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2, opcion3, opcion4, opcion5, opcion6);

        Preguntable pregunta= new GroupChoice(preguntaTexto, opciones);

        List<String> respuestasPrimerGrupo = Arrays.asList("Hamilton", "Wellington", "Auckland");
        List<String> respuestasSegundoGrupo  = Arrays.asList("Oslo", "Canberra", "Hawaii");
        List<List<String>> respuestas = Arrays.asList(respuestasPrimerGrupo, respuestasSegundoGrupo );

        Assertions.assertEquals(1, pregunta.establecerPuntuacion(respuestas));
    }
    
    @Test
    public void AlPasarleUnMultiplicadorPorDosEntoncesLanzaExcepcion() throws ParametrosInvalidosExcepcion {

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";

        Boolean pertenecePrimerGrupo = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenecePrimerGrupo);
        Opcion opcion2 = new Opcion("Hawaii", !pertenecePrimerGrupo);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2);

        Preguntable pregunta= new GroupChoice(preguntaTexto, opciones);

        List<String> respuestasPrimerGrupo = Arrays.asList("Auckland");
        List<String> respuestasSegundoGrupo  = Arrays.asList("Hawaii");
        List<List<String>> respuestas = Arrays.asList(respuestasPrimerGrupo, respuestasSegundoGrupo );

        Assertions.assertEquals(1, pregunta.establecerPuntuacion(respuestas));
    }

    @Test
    public void AlPasarleUnMultiplicadorPorTresEntoncesLanzaExcepcion() throws ParametrosInvalidosExcepcion {

        String preguntaTexto = "¿Cuales ciudades pertenecen a Nueva Zelanda y cuales no?";

        Boolean pertenecePrimerGrupo = Boolean.TRUE;
        Opcion opcion1 = new Opcion("Auckland", pertenecePrimerGrupo);
        Opcion opcion2 = new Opcion("Hawaii", !pertenecePrimerGrupo);
        List<Opcion> opciones = Arrays.asList(opcion1, opcion2);

        Preguntable pregunta= new GroupChoice(preguntaTexto, opciones);

        List<String> respuestasPrimerGrupo = Arrays.asList("Auckland");
        List<String> respuestasSegundoGrupo  = Arrays.asList("Hawaii");
        List<List<String>> respuestas = Arrays.asList(respuestasPrimerGrupo, respuestasSegundoGrupo );

        Assertions.assertEquals(1, pregunta.establecerPuntuacion(respuestas));
    }

}
