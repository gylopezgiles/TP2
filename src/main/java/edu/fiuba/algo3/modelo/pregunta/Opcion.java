package edu.fiuba.algo3.modelo.pregunta;

public class Opcion {

    private String texto;

    private Boolean esCorrecta;

    public Opcion(){

    }

    public Opcion(String texto, Boolean esCorrecta){
        this.texto = texto;
        this.esCorrecta = esCorrecta;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setEsCorrecta(Boolean esCorrecta) {
        this.esCorrecta = esCorrecta;
    }

    public String obtenerTexto(){
        return texto;
    }

    public Boolean esCorrecta(){
        return esCorrecta;
    }
}
