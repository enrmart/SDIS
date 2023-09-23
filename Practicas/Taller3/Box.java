package server.box;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Box extends Remote {
    public void put(Accumulator accumulator) throws RemoteException;   // línea 04
    public Accumulator take() throws RemoteException;     // línea 05
}
