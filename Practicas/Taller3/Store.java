package boxClient;

import boxServer.BoxImpl;
import server.box.Accumulator;
import server.box.Box;

import java.rmi.Naming;

public class Store {
    public static void main(String [] args) {
        if (args.length == 2) {
            String host= args[0];//El host que pide al servidor la ejecucion del metodo
            int num=Integer.parseInt(args[1]);//El numero con el que se contruye el entero

            try {

                Box or = (Box) Naming.lookup("rmi://localhost/RemoteBox");
                Accumulator ac = new Accumulator(num);
                or.put(ac);

            } catch (java.rmi.RemoteException re) {
                System.err.println("<Cliente: Excepción RMI: " + re);
                re.printStackTrace();

            } catch (Exception e) {
                System.err.println("<Cliente: Excepcion: " + e);
                e.printStackTrace();
            }

        }else{
            System.out.println("Numero de argumentos erroneo");
        }
    }
}
