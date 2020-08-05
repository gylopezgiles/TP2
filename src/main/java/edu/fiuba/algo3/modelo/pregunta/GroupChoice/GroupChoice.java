package edu.fiuba.algo3.modelo.pregunta.GroupChoice;

import edu.fiuba.algo3.modelo.excepciones.ParametrosInvalidosExcepcion;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import javafx.print.Collation;

import java.util.Collections;
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
       Collections.sort(this.grupoUno, (op1, op2) -> op1.obtenerTexto().compareTo(op2.obtenerTexto()));
       Collections.sort(this.grupoDos, (op1, op2) -> op1.obtenerTexto().compareTo(op2.obtenerTexto()));
    }

    private boolean tieneGrupoVacio(){
        return (this.grupoUno.isEmpty() || this.grupoDos.isEmpty());
    }

    @Override
    public int establecerPuntuacion(List<Opcion> opcionesRespondidas){
        //FIXME implementar una mejor manera de ordenar y comparar las listas
        List<Opcion> grupoUnoRespondido = opcionesRespondidas.stream().filter(op -> op.esCorrecta()).collect(Collectors.toList());
        List<Opcion> grupoDosRespondido = opcionesRespondidas.stream().filter(op -> !op.esCorrecta()).collect(Collectors.toList());
        Collections.sort(grupoUnoRespondido, (op1, op2) -> op1.obtenerTexto().compareTo(op2.obtenerTexto()));
        Collections.sort(grupoDosRespondido, (op1, op2) -> op1.obtenerTexto().compareTo(op2.obtenerTexto()));
        return this.grupoUno.equals(grupoUnoRespondido) && this.grupoDos.equals(grupoDosRespondido)? 0 : 1;
    }
}
