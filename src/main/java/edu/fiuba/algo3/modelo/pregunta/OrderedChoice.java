package edu.fiuba.algo3.modelo.pregunta;

import edu.fiuba.algo3.modelo.excepciones.MultiplicadorExcepcion;
import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderedChoice implements Preguntable{

    private static final int CANTIDAD_OPCIONES_MINIMO = 2;
    private static final int CANTIDAD_OPCIONES_MAXIMO = 5;

    private String pregunta;
    private List<Opcion> opciones;

    public OrderedChoice(String pregunta, List<Opcion> opciones) throws ParametrosInvalidosExcepcion {
        validarOpciones(opciones);
        this.pregunta = pregunta;
        this.opciones = opciones;
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
    public List<Opcion> obtenerOpciones() {
        return this.opciones;
    }

    @Override
    public int establecerPuntuacion(List<Opcion> opciones) throws MultiplicadorExcepcion {
        return establecerPuntuacion(opciones, Multiplicador.PorDefecto);
    }

    @Override
    public int establecerPuntuacion(List<Opcion> opciones, MultiplicableStrategy multiplicador) throws MultiplicadorExcepcion {
        if(!multiplicador.equals(Multiplicador.PorDefecto)){
            throw new MultiplicadorExcepcion("Solo se puede aplicar multiplicadores a preguntas con penalidad");
        }

        return tieneElOrdenAdecuado(opciones.iterator(),this.obtenerOpciones().iterator()) ? 1: 0;
    }

    @Override
    public TipoPregunta obtenerTipoPregunta() {
        return TipoPregunta.OrderedChoice;
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
    public String obtenerPregunta() {
        return this.pregunta;
    }

    @Override
    public List<Opcion> obtenerOpcionesPorNombre(List<String> opcionesSeleccionadas) {
        return opcionesSeleccionadas.stream().map(opcionSeleccionada -> obtenerOpcionPorNombre(opcionSeleccionada)).collect(Collectors.toList());
    }

    private Opcion obtenerOpcionPorNombre(String nombre){
        Optional<Opcion> opcion = opciones.stream()
                .filter(op -> op.obtenerTexto().compareTo(nombre) == 0)
                .findFirst();
        return opcion.get();
    }
}
