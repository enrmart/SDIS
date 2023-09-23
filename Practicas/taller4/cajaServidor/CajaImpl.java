package me.uva.taller4.cajaServidor;

import me.uva.taller4.caja.comun.Acumulador;
import me.uva.taller4.caja.comun.Caja;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CajaImpl extends UnicastRemoteObject implements Caja {
    private Acumulador acumulador;
    private AlmacenImpl almacen;

    public CajaImpl(AlmacenImpl almacen) throws RemoteException {
        super();
        this.almacen = almacen;
    }

    @Override
    public void guarda(Acumulador acumulador) throws RemoteException {
        this.acumulador = acumulador;
        almacen.setNotifica(this, acumulador);
    }

    @Override
    public Acumulador recupera() {
        Acumulador temp = acumulador;
        acumulador = null;
        return temp;
    }
}
