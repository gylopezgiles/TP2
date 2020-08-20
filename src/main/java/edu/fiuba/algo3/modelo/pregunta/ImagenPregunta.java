package edu.fiuba.algo3.modelo.pregunta;

public class ImagenPregunta {

    protected String direccionImagen;

    public void agregarDireccionImagen(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }

    public String obtenerDireccionImagen() {
        return direccionImagen;
    }
}
