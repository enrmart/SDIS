package me.uva.taller4.cajaCliente;

import me.uva.taller4.caja.comun.Almacen;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RegistrarVisor {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        Almacen almacen = (Almacen) Naming.lookup("rmi://localhost:2020/AlmacenRemoto");
        VisorImpl visor = new VisorImpl();
        almacen.observaCaja(visor, "caja1");
    }
}
