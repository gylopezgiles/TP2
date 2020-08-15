package edu.fiuba.algo3.modelo.pregunta.cargador;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.excepciones.TipoPreguntaNoImplementadaException;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CargadorPreguntas {

    private static final String PREGUNTAS_PATH = "src/main/resources/preguntas.json";

    private static CargadorPreguntas instancia;

    private List<Preguntable> preguntas;

    private CargadorPreguntas(String rutaArchivo) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        List<Pregunta> preguntasACargar  = Arrays.asList(mapper.readValue(new File(rutaArchivo), Pregunta[].class));

        preguntas = preguntasACargar.stream().map(preguntaACargar -> {
            try {
                return preguntaACargar.parsearPregunta();
            } catch (TipoPreguntaNoImplementadaException e) {
                e.printStackTrace();
            } catch (ParametrosInvalidosExcepcion parametrosInvalidosExcepcion) {
                parametrosInvalidosExcepcion.printStackTrace();
            }
            return null;
        }).filter(pregunta -> pregunta != null).collect(Collectors.toList());
    }

    public static CargadorPreguntas obtenerInstancia(){
        return obtenerInstancia(PREGUNTAS_PATH);
    }

    public static CargadorPreguntas obtenerInstancia(String rutaArchivo){
        if(instancia == null){
            try {
                instancia = new CargadorPreguntas(rutaArchivo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instancia;
    }

    public List<Preguntable> obtenerPreguntas(){
        return preguntas;
    }
}
