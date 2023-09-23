package cajaServidor;

import caja.comun.Acumulador;
import caja.comun.Almacen;
import caja.comun.Caja;
import caja.comun.Visor;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.util.*;

public class AlmacenImpl extends java.rmi.server.UnicastRemoteObject
        implements Almacen {

    private static Map<String,CajaImpl> mapa= new HashMap<String,CajaImpl>();
    private static Map<CajaImpl, Set<Visor>> mapavisores = new HashMap<CajaImpl,Set<Visor>>();
    RMIServerSocketFactory rmissf;
    RMIClientSocketFactory rmicsf;

    public AlmacenImpl(RMIClientSocketFactory rmicsf, RMIServerSocketFactory rmissf) throws RemoteException {
        super(0, rmicsf, rmissf); // es el constructor de UnicastRemoteObject.
        this.rmicsf = rmicsf;
        this.rmissf = rmissf;
    }

    public Caja nuevaCaja(String nombreCaja) throws java.rmi.RemoteException {
        CajaImpl caja = new CajaImpl(rmicsf, rmissf);
        mapa.put(nombreCaja,caja);
        return caja;
    }

    public  boolean observaCaja(Visor v, String nombreCaja) throws java.rmi.RemoteException {
        boolean isname = false;
        CajaImpl caja = mapa.get(nombreCaja);
        if (caja != null){
            Set<Visor> visores = mapavisores.get(caja);
            if (visores == null){
                visores = new HashSet<Visor>();
            }
            visores.add(v);
            mapavisores.put(caja,visores);
            isname = true;
        }
        return isname;
    }

    public static void cajaactualizada (CajaImpl caja, Acumulador acumulador) throws java.rmi.RemoteException {
        Set<Visor> visores = mapavisores.get(caja);
        if (visores != null){
            Iterator<Visor> it = visores.iterator();
            while(it.hasNext()){
                it.next().notifica(acumulador);
            }
        }

    }


}
