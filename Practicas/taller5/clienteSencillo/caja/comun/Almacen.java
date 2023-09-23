package caja.comun;

public interface Almacen extends java.rmi.Remote {
    public Caja nuevaCaja(String nombreCaja) throws java.rmi.RemoteException ;
    public boolean observaCaja(Visor v, String nombreCaja) throws java.rmi.RemoteException;
}
