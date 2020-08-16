package edu.fiuba.algo3.modelo.pregunta.verdaderofalso;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.exclusividad.Exclusividad;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;

import java.util.List;
import java.util.Optional;

public class VerdaderoFalsoClasico extends VerdaderoFalso {

    public <T> VerdaderoFalsoClasico(String pregunta, T opciones) throws ParametrosInvalidosExcepcion {
        validarOpciones((List<Opcion>) opciones);
        this.pregunta = pregunta;
        this.opciones = (List<Opcion>) opciones;
    }

    @Override
    public TipoPregunta obtenerTipoPregunta() {
        return TipoPregunta.VerdaderoFalsoClasico;
    }

    @Override
    public int establecerPuntuacion(List<Opcion> opciones, MultiplicableStrategy multiplicador, Exclusividad exclusividad) {
        exclusividad.activarExclusividad();
        Optional<Opcion> opcion = opciones.stream()
                .filter(op -> op.esCorrecta())
                .findAny();
        return opcion.isPresent() ? 1 : 0;
    }

}
