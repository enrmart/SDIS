package boxServer;

import server.box.Accumulator;
import server.box.Box;

import java.rmi.RemoteException;

public class BoxImpl extends java.rmi.server.UnicastRemoteObject implements Box {

    private Accumulator obj;

    public BoxImpl() throws RemoteException {
        super();
    }

    @Override
    public void put(Accumulator accumulator) throws RemoteException {
            obj=accumulator;
    }

    @Override
    public Accumulator take() throws RemoteException {
        //Creamos una referencia a accumulator para hacer la copia 
        Accumulator d= obj;
        obj=null;
        return d;
    }
}
