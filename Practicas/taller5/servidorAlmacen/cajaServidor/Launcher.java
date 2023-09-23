package cajaServidor;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Launcher {
    public static void main(String [ ] args) {
        try {
            javax.rmi.ssl.SslRMIClientSocketFactory rmicsf =
                    new javax.rmi.ssl.SslRMIClientSocketFactory();
            javax.rmi.ssl.SslRMIServerSocketFactory rmissf =
                    new javax.rmi.ssl.SslRMIServerSocketFactory();

            AlmacenImpl oRemoto = new AlmacenImpl(rmicsf, rmissf);
            LocateRegistry.createRegistry(1099);
            java.rmi.registry.Registry registro =
                    java.rmi.registry.LocateRegistry.getRegistry("localhost", 1099, rmicsf);

            // registramos el objeto, hablaremos más adelante de re-bind
            registro.rebind("AlmacenRemoto", oRemoto);

            System.err.println("Servidor preparado");
        } catch (Exception e) {
            System.err.println("Excepción del servidor: "+e.toString());
            e.printStackTrace();
        }
    }
}