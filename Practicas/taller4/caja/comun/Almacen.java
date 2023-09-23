package me.uva.taller4.caja.comun;

import me.uva.taller4.cajaServidor.CajaImpl;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Almacen extends Remote {
    public Caja nuevaCaja(String nombreCaja) throws RemoteException;
    public boolean observaCaja(Visor v, String nombreCaja) throws RemoteException;
}
