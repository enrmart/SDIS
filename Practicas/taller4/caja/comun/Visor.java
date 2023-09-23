package me.uva.taller4.caja.comun;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Visor extends Remote {
    public void notifica(Acumulador a) throws RemoteException;
}
