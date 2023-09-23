package me.uva.taller4.cajaServidor;

import me.uva.taller4.caja.comun.Acumulador;
import me.uva.taller4.caja.comun.Almacen;
import me.uva.taller4.caja.comun.Caja;
import me.uva.taller4.caja.comun.Visor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class AlmacenImpl extends UnicastRemoteObject implements Almacen {
    private HashMap<String, CajaImpl> listaCajas = new HashMap<>();
    private HashMap<CajaImpl, Visor> listaCajasVisores = new HashMap<>();

    protected AlmacenImpl() throws RemoteException {
        super();
    }

    @Override
    public Caja nuevaCaja(String nombreCaja) throws RemoteException {
        CajaImpl caja = new CajaImpl(this);
        if(!listaCajas.containsKey(nombreCaja)) {
            listaCajas.put(nombreCaja, caja);
            return caja;
        }
        return null;
    }

    @Override
    public boolean observaCaja(Visor v, String nombreCaja) throws RemoteException {
        if(listaCajas.containsKey(nombreCaja)) {            //comprueba si existe la caja en la lista
            CajaImpl caja = listaCajas.get(nombreCaja);     //si la caja existe se asigna a la lista de los visores
            listaCajasVisores.put(caja, v);
            return true;
        }
        return false;
    }

    public boolean setNotifica(CajaImpl caja, Acumulador a) {
        if(listaCajasVisores.containsKey(caja)) {
            Visor visor = listaCajasVisores.get(caja);
            try {
                visor.notifica(a);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
