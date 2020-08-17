package edu.fiuba.algo3.modelo.pregunta.cargador;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.excepciones.TipoPreguntaNoImplementadaException;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CargadorPreguntas {

    final static Logger log = Logger.getLogger(CargadorPreguntas.class);

    private static final String PREGUNTAS_PATH = "src/main/resources/preguntas.json";

    private static CargadorPreguntas instancia;

    private List<Preguntable> preguntas;

    private CargadorPreguntas(String rutaArchivo) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Pregunta> preguntasACargar = Arrays.asList(mapper.readValue(new File(rutaArchivo), Pregunta[].class));
            preguntas = preguntasACargar.stream().map(preguntaACargar -> {
                try {
                    return preguntaACargar.parsearPregunta();
                } catch (TipoPreguntaNoImplementadaException e) {
                    log.info(e.obtenerDescripcion());
                } catch (ParametrosInvalidosExcepcion parametrosInvalidosExcepcion) {
                    log.info(String.format("Error al cargar pregunta \"%s\": %s",preguntaACargar.obtenerTextoPregunta(),parametrosInvalidosExcepcion.obtenerDescripcion()));
                }
                return null;
            }).filter(pregunta -> pregunta != null).collect(Collectors.toList());
        } catch (IOException e) {
            preguntas = new LinkedList<>();
            log.info(String.format("Error al querer cargar archivo %s", rutaArchivo));
        }

    }

    public static CargadorPreguntas obtenerInstancia(){
        return obtenerInstancia(PREGUNTAS_PATH);
    }

    public static CargadorPreguntas obtenerInstancia(String rutaArchivo){
        if(instancia == null){
            instancia = new CargadorPreguntas(rutaArchivo);
        }
        return instancia;
    }

    public List<Preguntable> obtenerPreguntas(){
        return preguntas;
    }
}
