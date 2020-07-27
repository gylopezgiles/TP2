package edu.fiuba.algo3.modelo.entrega;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Ronda;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.pregunta.CreadorPregunta;
import edu.fiuba.algo3.modelo.pregunta.Opcion;
import edu.fiuba.algo3.modelo.pregunta.Preguntable;
import edu.fiuba.algo3.modelo.pregunta.TipoPregunta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class Entrega1Test {
    @Test
    public void crearMultipleChoiceClasicoConOpciones(){
        //Given
        Opcion opcionCorrecta = new Opcion("Esta Si", Boolean.TRUE);
        Opcion opcion2 = new Opcion("Esta NO", Boolean.FALSE);
        Opcion opcion3 = new Opcion("Esta Tampoco", Boolean.FALSE);
        Opcion opcion4 = new Opcion("Esta Menos", Boolean.FALSE);
        Opcion opcion5 = new Opcion("Nope", Boolean.FALSE);
        List<Opcion> opciones = Arrays.asList(opcionCorrecta, opcion2, opcion3, opcion4, opcion5);
        String preguntaTexto = "pregunta?";

        //When
        Preguntable multipleChoiceClasico = CreadorPregunta.crearPregunta(TipoPregunta.MultpleChoiceClasico, preguntaTexto, opciones);

        //Then
        Assertions.assertEquals(5, multipleChoiceClasico.obtenerOpciones().size());
        Assertions.assertEquals(opciones, multipleChoiceClasico.obtenerOpciones());
        Assertions.assertEquals(opcionCorrecta, multipleChoiceClasico.obtenerOpciones().stream().filter(opcion -> opcion.esCorrecta()).findAny().orElse(null));
    }

}
