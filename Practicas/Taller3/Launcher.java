package boxServer;


public class Launcher {
    public static void main(String [ ] args) {
        String host = (args.length < 1) ? null : args[0];//El host que se va a obtener la referencia
        try {
            BoxImpl BoxRemote = new BoxImpl();

            // Accedemos a una referencia al registro (rmiregistry) local
            java.rmi.registry.Registry registro = java.rmi.registry.LocateRegistry.getRegistry(host);

            // registramos el objeto, hablaremos más adelante de re-bind
            registro.bind("RemoteBox", BoxRemote);

            System.err.println("Servidor preparado");
        } catch (Exception e) {
            System.err.println("Excepción del servidor: "+e.toString());
            e.printStackTrace();
        }
    }
}
