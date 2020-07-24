package edu.fiuba.algo3.modelo.pregunta;

public class Opcion {

    private String texto;
    private Boolean correcta;

    public Opcion(String texto, Boolean correcta){
        this.texto = texto;
        this.correcta = correcta;
    }

    public String obtenerTexto(){
        return texto;
    }

    public Boolean esCorrecta(){
        return correcta;
    }
}
