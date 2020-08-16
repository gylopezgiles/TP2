package edu.fiuba.algo3.modelo.pregunta.verdaderofalso;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.exclusividad.Exclusividad;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;

import java.util.List;
import java.util.Optional;

public class VerdaderoFalsoClasico extends VerdaderoFalso {

    public VerdaderoFalsoClasico(String pregunta, List<Opcion> opciones) throws ParametrosInvalidosExcepcion {
        validarOpciones(opciones);
        this.pregunta = pregunta;
        this.opciones = opciones;
    }

    @Override
    public TipoPregunta obtenerTipoPregunta() {
        return TipoPregunta.VerdaderoFalsoClasico;
    }

    @Override
    public int establecerPuntuacion(List<String> nombresOpcionesSeleccionadas, MultiplicableStrategy multiplicador, Exclusividad exclusividad) {
        exclusividad.activarExclusividad();
        List<Opcion> opcionesSeleccionadas = obtenerOpcionesPorNombre(nombresOpcionesSeleccionadas);
        Optional<Opcion> opcion = opcionesSeleccionadas.stream()
                .filter(op -> op.esCorrecta())
                .findAny();
        return opcion.isPresent() ? 1 : 0;
    }

}
