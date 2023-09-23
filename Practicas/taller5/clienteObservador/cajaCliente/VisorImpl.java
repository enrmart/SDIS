package cajaCliente;


import caja.comun.Acumulador;
import caja.comun.Visor;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;

public class VisorImpl extends java.rmi.server.UnicastRemoteObject implements Visor {

    public VisorImpl(RMIClientSocketFactory rmicsf, RMIServerSocketFactory rmissf) throws RemoteException {
        super(0, rmicsf, rmissf); // es el constructor de UnicastRemoteObject.
    }

    public void notifica(Acumulador a) throws java.rmi.RemoteException{
        if (a!= null){
            System.out.println("Acumulador: " + a.valor());

        } else {
            System.out.println("Caja vacia");
        }

    }

}
