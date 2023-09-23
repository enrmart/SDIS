package me.uva.taller4.cajaCliente;

import me.uva.taller4.caja.comun.Acumulador;
import me.uva.taller4.caja.comun.Almacen;
import me.uva.taller4.caja.comun.Caja;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ConstruirCaja {
    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException, InterruptedException {
        Almacen almacen = (Almacen) Naming.lookup("rmi://localhost:2020/AlmacenRemoto");
        Caja caja = almacen.nuevaCaja("caja1");
        caja.guarda(new Acumulador(5));
        Thread.sleep(15000);
        caja.guarda(new Acumulador(9));
    }
}
