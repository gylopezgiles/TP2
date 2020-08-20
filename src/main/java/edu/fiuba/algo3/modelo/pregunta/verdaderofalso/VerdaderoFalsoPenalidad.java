package edu.fiuba.algo3.modelo.pregunta.verdaderofalso;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.exclusividad.Exclusividad;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;

import java.util.List;
import java.util.Optional;

public class VerdaderoFalsoPenalidad extends VerdaderoFalso {

    public VerdaderoFalsoPenalidad(String pregunta, List<Opcion> opciones) throws ParametrosInvalidosExcepcion {
        validarOpciones(opciones);
        this.pregunta = pregunta;
        this.opciones = opciones;
    }

    @Override
    public int establecerPuntuacion(List<String> nombresOpcionesSeleccionadas, MultiplicableStrategy multiplicador, Exclusividad exclusividad) {
        List<Opcion> opciones = obtenerOpcionesPorNombre(nombresOpcionesSeleccionadas);
        Optional<Opcion> opcion = opciones.stream()
                .filter(op -> op.esCorrecta())
                .findAny();
        int puntos = opcion.isPresent() ? 1 : -1;
        return multiplicador.aplicarMultiplicador(puntos);
    }


    @Override
    public TipoPregunta obtenerTipoPregunta() {
        return TipoPregunta.VerdaderoFalsoPenalidad;
    }


}
