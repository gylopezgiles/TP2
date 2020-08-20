package edu.fiuba.algo3.modelo.pregunta.cargador;

import edu.fiuba.algo3.modelo.pregunta.Preguntable;

public class DireccionImagenes {
    private String imagen;

    public DireccionImagenes() { }

    public void agregarDireccionImagen(Preguntable pregunta){
        pregunta.agregarDireccionImagen(imagen);
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getImagen() {
        return imagen;
    }
}
