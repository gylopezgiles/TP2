package edu.fiuba.algo3.modelo.pregunta.cargador;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import org.apache.log4j.Logger;

import java.io.IOException;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class CargadorImagenes {

    final static Logger log = Logger.getLogger(CargadorImagenes.class);


    private static final String IMAGENES_PATH = "src/main/resources/imagenes.json";
    private static List<Preguntable> preguntas;


    public static void cargarImagenes() {

        ObjectMapper mapper = new ObjectMapper();
        try {
            List<DireccionImagenes> direccionImagenes = Arrays.asList(mapper.readValue(new File(IMAGENES_PATH), DireccionImagenes[].class));

            preguntas = CargadorPreguntas.obtenerInstancia().obtenerPreguntas();

            for(int i = 0; i < preguntas.size(); i++){
                direccionImagenes.get(i).agregarDireccionImagen(preguntas.get(i));
            }

        } catch (IOException e) {
            preguntas = new LinkedList<>();
            log.info(String.format("Error al querer cargar archivo %s", IMAGENES_PATH));
        }
    }
}
