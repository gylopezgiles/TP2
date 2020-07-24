package edu.fiuba.algo3.modelo.excepciones;

public class ExcepcionBase extends Exception{

    private String descripcion;

    public ExcepcionBase(String descripcion){
        this.descripcion = descripcion;
    }

    public String obtenerDescripcion(){
        return descripcion;
    }
}
