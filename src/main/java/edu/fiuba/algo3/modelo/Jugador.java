package edu.fiuba.algo3.modelo;

public class Jugador {

    private String nombre;
    private int puntos;

    public Jugador(String nombre){
        this.nombre = nombre;
    }

    public int obtenerPuntos(){
        return this.puntos;
    }

    public void sumarPuntos(int puntos){
        this.puntos += puntos;
    }
}
