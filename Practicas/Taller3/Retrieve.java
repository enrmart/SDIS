package boxClient;


import server.box.Accumulator;
import server.box.Box;

import java.rmi.Naming;

public class Retrieve {
    public static void main(String [] args){
        String host = (args.length < 1) ? null : args[0];//Nombre del host que sirve el servicio
        try{
            Box or=(Box) Naming.lookup("rmi://"+host+"/RemoteBox");
            Accumulator ac=or.take();
            if(ac!=null) {
                System.out.println("El valor del acumulador es:"+ac.value());
            }else{
                System.out.println("El acumulador es nulo");
            }
        } catch (java.rmi.RemoteException re) {
            System.err.println("<Cliente: Excepción RMI: "+re);
            re.printStackTrace();
        } catch (Exception e) {
            System.err.println("<Cliente: Excepcion: "+e);
            e.printStackTrace();
        }

    }
}
