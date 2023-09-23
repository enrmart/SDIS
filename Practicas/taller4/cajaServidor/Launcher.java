package me.uva.taller4.cajaServidor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Launcher {
    public static void main(String[] args) throws RemoteException {
        AlmacenImpl remote = new AlmacenImpl();
        LocateRegistry.createRegistry(2020);        //inicializar un registro en el puerto 2020
        Registry registry = LocateRegistry.getRegistry("localhost", 2020);  //asignar el registro a la direccion localhost
        registry.rebind("AlmacenRemoto", remote);
        System.out.println("Almacen remoto creado");
    }
}
