package edu.fiuba.algo3.modelo.pregunta.GroupChoice;

import edu.fiuba.algo3.modelo.excepciones.MultiplicadorExcepcion;
import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.multiplicador.MultiplicableStrategy;
import edu.fiuba.algo3.modelo.multiplicador.Multiplicador;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import javafx.print.Collation;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class GroupChoice implements Preguntable {

    private static final int CANTIDAD_OPCIONES_MINIMO = 2;
    private static final int CANTIDAD_OPCIONES_MAXIMO = 6;
    private static final boolean PERTENECE_GRUPO_UNO = Boolean.TRUE;
    private static final boolean PERTENECE_GRUPO_DOS = Boolean.FALSE;

    private List<Opcion> opciones;
    private String pregunta;
    private List<Opcion> grupoUno;
    private List<Opcion> grupoDos;

    public  GroupChoice(String pregunta, List<Opcion> opciones) throws ParametrosInvalidosExcepcion {
        agrupar(opciones);
        validarOpciones(opciones);
        this.opciones = opciones;
        this.pregunta = pregunta;
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

    private void agrupar(List<Opcion> opciones){
       this.grupoUno = opciones.stream().filter(op -> op.esCorrecta()).collect(Collectors.toList());
       this.grupoDos = opciones.stream().filter(op -> !op.esCorrecta()).collect(Collectors.toList());
    }

    private boolean tieneGrupoVacio(){
        return (this.grupoUno.isEmpty() || this.grupoDos.isEmpty());
    }

    private boolean sonIguales(List<Opcion> unGrupo, List<Opcion> otroGrupo){
        Boolean sonIguales = Boolean.TRUE;
        int i = 0;
        Collections.sort(unGrupo, (op1, op2) -> op1.obtenerTexto().compareTo(op2.obtenerTexto()));
        Collections.sort(otroGrupo, (op1, op2) -> op1.obtenerTexto().compareTo(op2.obtenerTexto()));
        if(unGrupo.size() != otroGrupo.size()){
            return !sonIguales;
        }
        Iterator<Opcion> iteradorUno = unGrupo.iterator();
        Iterator<Opcion> iteradorDos = otroGrupo.iterator();
        while (sonIguales && iteradorUno.hasNext() && iteradorDos.hasNext()){
            sonIguales = (iteradorUno.next().obtenerTexto().equals(iteradorDos.next().obtenerTexto()));
        }
        return sonIguales;
    }

    @Override
    public int establecerPuntuacion(List<Opcion> opcionesRespondidas, MultiplicableStrategy multiplicador) throws MultiplicadorExcepcion {
        if(!multiplicador.equals(Multiplicador.PorDefecto)){
            throw new MultiplicadorExcepcion("Solo se puede aplicar multiplicadores a preguntas con penalidad");
        }
        List<Opcion> grupoUnoRespondido = opcionesRespondidas.stream().filter(op -> op.esCorrecta()).collect(Collectors.toList());
        List<Opcion> grupoDosRespondido = opcionesRespondidas.stream().filter(op -> !op.esCorrecta()).collect(Collectors.toList());
        return this.sonIguales(this.grupoUno, grupoUnoRespondido) &&
               this.sonIguales(this.grupoDos, grupoDosRespondido)? 1 : 0;
    }
    @Override
    public int establecerPuntuacion(List<Opcion> opciones) throws MultiplicadorExcepcion{
        return this.establecerPuntuacion(opciones, Multiplicador.PorDefecto);
    }
}
