package edu.fiuba.algo3.modelo.pregunta;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;

import java.util.Iterator;
import java.util.List;

public class OrderedChoice implements Preguntable <List<Opcion>>{

    private static final int CANTIDAD_OPCIONES_MINIMO = 2;
    private static final int CANTIDAD_OPCIONES_MAXIMO = 5;

    private String pregunta;
    private List<Opcion> opciones;

    public <T> OrderedChoice(String pregunta, T opciones) throws ParametrosInvalidosExcepcion {
        validarOpciones((List<Opcion>)opciones);
        this.pregunta = pregunta;
        this.opciones = (List<Opcion>) opciones;
    }

    private void validarOpciones(List<Opcion> opciones) throws ParametrosInvalidosExcepcion {
        if(!tieneCantidadOpcionesValida(opciones)){
            throw new ParametrosInvalidosExcepcion("Las preguntas ordered choice deben tener 2 opciones como minimo y 5 como maximo");
        }

    }

    private Boolean tieneCantidadOpcionesValida(List<Opcion> opciones){
        return !opciones.isEmpty() && opciones.size() >= CANTIDAD_OPCIONES_MINIMO && opciones.size() <= CANTIDAD_OPCIONES_MAXIMO;
    }

    @Override
    public int establecerPuntuacion(List<Opcion> opciones) {
        return establecerPuntuacion(opciones, Multiplicador.PorDefecto);
    }

    @Override
    public int establecerPuntuacion(List<Opcion> opciones, MultiplicableStrategy multiplicador) {
        return tieneElOrdenAdecuado(opciones.iterator(),this.obtenerOpciones().iterator()) ? 1: 0;
    }

    boolean tieneElOrdenAdecuado(Iterator<Opcion> opcionesSeleccionadas, Iterator<Opcion> opcionesOrdenadas){
        Opcion opcionSeleccionada = opcionesSeleccionadas.next();
        Opcion opcionOrdenada = opcionesOrdenadas.next();
        if(opcionesSeleccionadas.hasNext() && opcionSeleccionada.obtenerTexto() == opcionOrdenada.obtenerTexto()){
            return tieneElOrdenAdecuado(opcionesSeleccionadas,opcionesOrdenadas);
        }
        return opcionSeleccionada.obtenerTexto() == opcionOrdenada.obtenerTexto();

    }

    @Override
    public List<Opcion> obtenerOpciones(){
        return opciones;
    }

    @Override
    public String obtenerPregunta(){
        return pregunta;
    }
}
