package cajaServidor;

import caja.comun.Acumulador;
import caja.comun.Caja;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;

public class CajaImpl extends java.rmi.server.UnicastRemoteObject
        implements Caja {
    private Acumulador acumulador;

    public CajaImpl(RMIClientSocketFactory rmicsf, RMIServerSocketFactory rmissf) throws RemoteException {
        super(0, rmicsf, rmissf); // es el constructor de UnicastRemoteObject.
        acumulador=null;
    }

    public void guarda(Acumulador a) throws RemoteException {
        this.acumulador = a;
        AlmacenImpl.cajaactualizada(this, a);

    }
    public Acumulador recupera() throws RemoteException {
        Acumulador salida = acumulador;
        acumulador = null;
        AlmacenImpl.cajaactualizada(this, null);
        return salida;
    }

}
