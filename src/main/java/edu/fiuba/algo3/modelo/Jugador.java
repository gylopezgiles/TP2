package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.exclusividad.Exclusividad;

public class Jugador {

    private int cantidadExclusividades = 2;
    private Exclusividad exclusividad;
    private String nombre;
    private int puntos;

    public Jugador(String nombre, Exclusividad exclusividad) {
        this.nombre = nombre;
        this.exclusividad = exclusividad;
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

    public void seleccionarExclusividad() {
        exclusividad.elegirExclusividad(cantidadExclusividades);
        cantidadExclusividades--;
    }

}