package caja.comun;

public class Acumulador implements java.io.Serializable {
    private int acumulado;
    public Acumulador(int valorInicial)
    {   this.acumulado = valorInicial;    }
    public int valor()
    { return acumulado; }
    public int incrementa()
    { return this.acumulado++; }
}
