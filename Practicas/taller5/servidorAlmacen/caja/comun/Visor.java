package caja.comun;

public interface Visor extends java.rmi.Remote {
    public void notifica(Acumulador a) throws java.rmi.RemoteException;
}
