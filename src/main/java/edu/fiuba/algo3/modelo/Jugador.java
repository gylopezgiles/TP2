package edu.fiuba.algo3.modelo;

public class Jugador {

    private String nombre;
    private int puntos;

    public Jugador(String nombre){
        this.nombre = nombre;
    }

    public String obtenerNombre(){
        return nombre;
    }

    public int obtenerPuntos(){
        return puntos;
    }

    public void sumarPuntos(int puntos){
        this.puntos += puntos;
    }
}