package edu.fiuba.algo3.modelo.pregunta.multiplechoice;

import edu.fiuba.algo3.modelo.excepciones.MultiplicadorExcepcion;
import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class GroupChoice implements Preguntable < List<List<Opcion> >>{

    private static final int CANTIDAD_OPCIONES_MINIMO = 2;
    private static final int CANTIDAD_OPCIONES_MAXIMO = 6;

    private static final int PUNTACION_CORRECTA = 1;
    private static final int PUNTACION_INCORRECTA = 0;


    private final String pregunta;
    private final List<Opcion> opciones;
    private List<Opcion> opcionesVerdaderas;
    private List<Opcion> opcionesFalsas;

    public GroupChoice(String preguntaTexto, List<Opcion> opciones) throws ParametrosInvalidosExcepcion {
        separarOpcionesVerdaderoFalso(opciones);
        validarOpciones(opciones);
        this.opciones = opciones;
        this.pregunta = preguntaTexto;
    }

    private void separarOpcionesVerdaderoFalso(List<Opcion> opciones){
        this.opcionesVerdaderas = opciones.stream().filter(op -> op.esCorrecta()).collect(Collectors.toList());
        this.opcionesFalsas = opciones.stream().filter(op -> !op.esCorrecta()).collect(Collectors.toList());
    }

    private void validarOpciones(List<Opcion> opciones) throws ParametrosInvalidosExcepcion {
        if(!tieneCantidadOpcionesValida(opciones)){
            throw new ParametrosInvalidosExcepcion("Las preguntas Group choice deben tener 2 opciones como minimo y 6 como maximo");
        }
        if(tieneGrupoVacio()){
            throw new ParametrosInvalidosExcepcion("Las preguntas Group choice deben tener al menos una opcion de cada grupo");
        }
    }


    private Boolean tieneCantidadOpcionesValida(List<Opcion> opciones){
        return !opciones.isEmpty() && opciones.size() >= CANTIDAD_OPCIONES_MINIMO && opciones.size() <= CANTIDAD_OPCIONES_MAXIMO;
    }

    private boolean tieneGrupoVacio(){
        return (this.opcionesVerdaderas.isEmpty() || this.opcionesFalsas.isEmpty());
    }

    @Override
    public List<Opcion> obtenerOpciones() {
        return this.opciones;
    }

    @Override
    public int establecerPuntuacion(List<List<Opcion>> grupos) throws MultiplicadorExcepcion {
        return establecerPuntuacion(grupos, Multiplicador.PorDefecto);
    }

    @Override
    public int establecerPuntuacion(List<List<Opcion>> grupos, MultiplicableStrategy multiplicador) throws MultiplicadorExcepcion {
        if (multiplicador != Multiplicador.PorDefecto){
            throw new MultiplicadorExcepcion("");
        }

        List<Opcion> respuestasGrupoVerdadero = grupos.get(0);
        List<Opcion> respuestasGrupoFalso = grupos.get(1);

        return ( esIgual(opcionesVerdaderas, respuestasGrupoVerdadero) &&
                esIgual(opcionesFalsas, respuestasGrupoFalso) ) ? PUNTACION_CORRECTA : PUNTACION_INCORRECTA;
    }


    private Boolean esIgual(List<Opcion> opciones, List<Opcion> respuestas){
        return ( opciones.stream()
                         .filter(respuestas::contains)
                            .collect(Collectors
                                  .toList())
                ).size() == opciones.size() ;
    }

    @Override
    public String obtenerPregunta() {
        return this.pregunta;
    }
}
