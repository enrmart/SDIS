package caja.comun;

public interface Caja extends java.rmi.Remote {
    public void guarda(Acumulador a) throws java.rmi.RemoteException ;
    public Acumulador  recupera() throws java.rmi.RemoteException ;
}
