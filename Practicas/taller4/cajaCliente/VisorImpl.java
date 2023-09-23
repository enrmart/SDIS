package me.uva.taller4.cajaCliente;

import me.uva.taller4.caja.comun.Acumulador;
import me.uva.taller4.caja.comun.Visor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class VisorImpl extends UnicastRemoteObject implements Visor {

    protected VisorImpl() throws RemoteException {
        super();
    }

    @Override
    public void notifica(Acumulador a) throws RemoteException {
        System.out.println("Valor de acumulador = " + a.valor());
    }
}
