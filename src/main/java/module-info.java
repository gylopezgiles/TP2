module edu.fiuba.algo3 {
    requires javafx.controls;
    requires java.desktop;
    requires com.fasterxml.jackson.databind;
    exports edu.fiuba.algo3;
    exports edu.fiuba.algo3.interfazGrafica to javafx.graphics;
    exports edu.fiuba.algo3.modelo.pregunta.cargador;
    opens edu.fiuba.algo3.modelo.pregunta.cargador;
    exports edu.fiuba.algo3.modelo.pregunta;
}