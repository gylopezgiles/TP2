package edu.fiuba.algo3.modelo.multiplicador;

public enum Multiplicador implements MultiplicableStrategy {
    PorDefecto((puntos) -> puntos),
    PorDos((puntos) -> puntos*2),
    PorTres((puntos) -> puntos*3);

    private MultiplicableStrategy multiplicableStrategy;

    Multiplicador(final MultiplicableStrategy multiplicableStrategy){
        this.multiplicableStrategy = multiplicableStrategy;
    }

    @Override
    public int aplicarMultiplicador(int puntos) {
        return multiplicableStrategy.aplicarMultiplicador(puntos);
    }
}
