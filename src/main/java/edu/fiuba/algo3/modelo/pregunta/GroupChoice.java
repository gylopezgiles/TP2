package edu.fiuba.algo3.modelo.pregunta;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.exclusividad.Exclusividad;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;

import java.util.List;
import java.util.stream.Collectors;


public class GroupChoice implements Preguntable <List<List<String>>>{

    private static final int CANTIDAD_OPCIONES_MINIMO = 2;
    private static final int CANTIDAD_OPCIONES_MAXIMO = 6;

    private static final int PUNTACION_CORRECTA = 1;
    private static final int PUNTACION_INCORRECTA = 0;


    private final String pregunta;
    private final List<Opcion> opciones;
    private List<Opcion> opcionesPrimerGrupo;
    private List<Opcion> opcionesSegundoGrupo;

    public GroupChoice(String preguntaTexto, List<Opcion> opciones) throws ParametrosInvalidosExcepcion {
        separarOpcionesPorGrupo(opciones);
        validarOpciones(opciones);
        this.opciones = opciones;
        this.pregunta = preguntaTexto;
    }

    private void separarOpcionesPorGrupo(List<Opcion> opciones){
        this.opcionesPrimerGrupo = opciones.stream().filter(op -> op.esCorrecta()).collect(Collectors.toList());
        this.opcionesSegundoGrupo = opciones.stream().filter(op -> !op.esCorrecta()).collect(Collectors.toList());
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
        return (this.opcionesPrimerGrupo.isEmpty() || this.opcionesSegundoGrupo.isEmpty());
    }

    @Override
    public List<Opcion> obtenerOpciones() {
        return this.opciones;
    }

    @Override
    public int establecerPuntuacion(List<List<String>> nombresOpcionesSeleccionadas, MultiplicableStrategy multiplicador, Exclusividad exclusividad) {
        exclusividad.activarExclusividad();
        List<Opcion> respuestasPrimerGrupo = obtenerOpcionesPorNombre(nombresOpcionesSeleccionadas.get(0));
        List<Opcion> respuestasSegundoGrupo = obtenerOpcionesPorNombre(nombresOpcionesSeleccionadas.get(1));
        return ( esIgual(opcionesPrimerGrupo, respuestasPrimerGrupo) &&
                esIgual(opcionesSegundoGrupo, respuestasSegundoGrupo ) ) ? PUNTACION_CORRECTA : PUNTACION_INCORRECTA;
    }

    @Override
    public TipoPregunta obtenerTipoPregunta() {
        return TipoPregunta.GroupChoice;
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

    private List<Opcion> obtenerOpcionesPorNombre(List<String> opcionesSeleccionadas) {
        return opciones.stream().filter(op -> opcionesSeleccionadas.contains(op.obtenerTexto())).collect(Collectors.toList());
    }
}
