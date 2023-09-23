package me.uva.taller4.caja.comun;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Caja extends Remote {
    public void guarda(Acumulador acumulador) throws RemoteException;
    public Acumulador recupera() throws RemoteException;
}
