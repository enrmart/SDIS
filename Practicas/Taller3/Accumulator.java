package server.box;

import java.io.Serializable;

public class Accumulator implements Serializable {
    private int acumulado;
    public Accumulator(int valorInicial) {   this.acumulado = valorInicial;    }
    public int value() { return acumulado; }
    public int increment() { return this.acumulado++; }
}