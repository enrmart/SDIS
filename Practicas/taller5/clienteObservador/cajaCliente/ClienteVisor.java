package cajaCliente;

import caja.comun.Almacen;

public class ClienteVisor {
    public static void main(String [] args) {
        try {
            javax.rmi.ssl.SslRMIClientSocketFactory rmicsf =
                    new javax.rmi.ssl.SslRMIClientSocketFactory();
            javax.rmi.ssl.SslRMIServerSocketFactory rmissf =
                    new javax.rmi.ssl.SslRMIServerSocketFactory();
            java.rmi.registry.Registry reg = java.rmi.registry.LocateRegistry.getRegistry("localhost", 1099, rmicsf);
            Almacen alm = (Almacen) reg.lookup("AlamcenRemoto");
            System.out.println("Objeto 'AlmacenRemoto' :Almacen localizado");
            VisorImpl visor = new VisorImpl(rmicsf, rmissf);
            boolean cajaesta = alm.observaCaja(visor, "MisAhorros");
            if (cajaesta){
                System.out.println("Registrado Visor en 'MisAhorros'");
            } else {
                System.out.println("Caja 'MisAhorros' no existe");
                System.exit(0);
            }
            // el observador se queda ahí a la espera de los mensajes de Almacen.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
