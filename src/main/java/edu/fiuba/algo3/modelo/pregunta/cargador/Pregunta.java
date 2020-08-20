package edu.fiuba.algo3.modelo.pregunta.cargador;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.excepciones.TipoPreguntaNoImplementadaException;
import edu.fiuba.algo3.modelo.pregunta.CreadorPregunta;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;

import java.util.List;

public class Pregunta {

    private TipoPregunta tipoPregunta;
    private DatoPregunta datoPregunta;

    public Pregunta(){
    }

    public Preguntable parsearPregunta() throws TipoPreguntaNoImplementadaException, ParametrosInvalidosExcepcion {
        return CreadorPregunta.crearPregunta(tipoPregunta, datoPregunta.obtenerTexto(), datoPregunta.obtenerOpciones());
    }

    public TipoPregunta getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(TipoPregunta tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public DatoPregunta getDatoPregunta() {
        return datoPregunta;
    }

    public String obtenerTextoPregunta() {return datoPregunta.obtenerTexto();}

    public void setDatoPregunta(DatoPregunta datoPregunta) {
        this.datoPregunta = datoPregunta;
    }

    private class DatoPregunta{
        private String texto;
        private List<Opcion> opciones;

        public DatoPregunta(){
        }

        public String getTexto() {
            return texto;
        }

        public List<Opcion> getOpciones() {
            return opciones;
        }

        public String obtenerTexto(){
            return texto;
        }

        public List<Opcion> obtenerOpciones(){
            return opciones;
        }
    }
}