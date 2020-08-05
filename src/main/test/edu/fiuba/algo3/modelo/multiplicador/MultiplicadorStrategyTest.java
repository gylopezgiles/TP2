package edu.fiuba.algo3.modelo.multiplicador;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MultiplicadorStrategyTest {

    @Test
    public void debeMultiplicarPuntosPorUno(){
        Assertions.assertEquals(1, Multiplicador.PorDefecto.aplicarMultiplicador(1));
    }

    @Test
    public void debeMultiplicarPuntosPorDos(){
        Assertions.assertEquals(2, Multiplicador.PorDos.aplicarMultiplicador(1));
    }

    @Test
    public void debeMultiplicarPuntosPorTres(){
        Assertions.assertEquals(3, Multiplicador.PorTres.aplicarMultiplicador(1));
    }
}
