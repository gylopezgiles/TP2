package edu.fiuba.algo3.modelo.pregunta.verdaderofalso;

import edu.fiuba.algo3.modelo.excepciones.MultiplicadorExcepcion;
import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;

import java.util.List;
import java.util.stream.Collectors;

public abstract class VerdaderoFalso implements Preguntable {

    private static final int CANTIDAD_OPCIONES_VALIDAS = 2;
    private static final int CANTIDAD_OPCIONES_CORRECTAS = 1;

    protected List<Opcion> opciones;
    protected String pregunta;

    protected void validarOpciones(List<Opcion> opciones) throws ParametrosInvalidosExcepcion {
        if(!tieneCantidadOpcionesValida(opciones)){
            throw new ParametrosInvalidosExcepcion("Las preguntas verdadero falso clasico deben tener solo 2 opciones");
        }
        if(!tieneOpcionesCorrectas(opciones)){
            throw new ParametrosInvalidosExcepcion("Las preguntas verdadero falso clasico deben tener 1 opcion correcta");
        }
    }

    private Boolean tieneCantidadOpcionesValida(List<Opcion> opciones){
        return !opciones.isEmpty() && opciones.size() == CANTIDAD_OPCIONES_VALIDAS;
    }

    private boolean tieneOpcionesCorrectas(List<Opcion> opciones) {
        List<Opcion> opcionesCorrectas = opciones.stream().filter(op -> op.esCorrecta()).collect(Collectors.toList());
        return !opcionesCorrectas.isEmpty() && opcionesCorrectas.size() == CANTIDAD_OPCIONES_CORRECTAS;
    }

    @Override
    public List<Opcion> obtenerOpciones() {
        return this.opciones;
    }

    @Override
    public String obtenerPregunta() {
        return pregunta;
    }

    @Override
    public int establecerPuntuacion(List<Opcion> opciones) throws MultiplicadorExcepcion {
        return establecerPuntuacion(opciones, Multiplicador.PorDefecto);
    }


}
