package edu.fiuba.algo3.modelo.pregunta;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VerdaderoFalsoClasico implements Preguntable {

    private static final int CANTIDAD_OPCIONES_VALIDAS = 2;
    private static final int CANTIDAD_OPCIONES_CORRECTAS = 1;

    private List<Opcion> opciones;
    private String pregunta;

    public VerdaderoFalsoClasico(String pregunta, List<Opcion> opciones) throws ParametrosInvalidosExcepcion {
        validarOpciones(opciones);
        this.pregunta = pregunta;
        this.opciones = opciones;
    }

    @Override
    public List<Opcion> obtenerOpciones() {
      return this.opciones;
    }

    @Override
    public int establecerPuntuacion(List<Opcion> opciones) {
        Optional<Opcion> opcion = opciones.stream()
                .filter(op -> op.esCorrecta())
                .findAny();
        return opcion.isPresent() ? 1 : 0;
    }

    @Override
    public String obtenerPregunta() {
        return pregunta;
    }

    private void validarOpciones(List<Opcion> opciones) throws ParametrosInvalidosExcepcion {
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
}
