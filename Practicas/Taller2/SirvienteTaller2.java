

import distribuidos.mapqueue.MultiMap;
import protocol.cliente.MalMensajeProtocoloException;
import protocol.cliente.MensajeProtocolo;
import protocol.cliente.Primitiva;

import java.util.concurrent.atomic.AtomicInteger;

public class SirvienteTaller2 implements Runnable {

    private static AtomicInteger contblock=new AtomicInteger();//Cuenta el numero de hilos bloqueados
    private final java.net.Socket socket;
    private final MultiMap<String, String> mapa;
    private final java.io.ObjectOutputStream oos;
    private final java.io.ObjectInputStream  ois;
    private final int ns;
    private static AtomicInteger nInstance=new AtomicInteger();

    public SirvienteTaller2(java.net.Socket s, MultiMap<String, String> c) throws java.io.IOException {
        this.socket = s;
        this.mapa   = c;
        this.ns     = nInstance.getAndIncrement();
        this.oos = new java.io.ObjectOutputStream(socket.getOutputStream());
        this.ois = new java.io.ObjectInputStream(socket.getInputStream());

    } // se invoca en el Servidor, usualmente

    public void run() {
        try {
            while (true) {
                String mensaje; // String multipurpose
                //Primitiva que llega desde el cliente
                MensajeProtocolo me = (MensajeProtocolo) ois.readObject();
                MensajeProtocolo ms;
                // me y ms: mensajes entrante y saliente
                System.out.println("Sirviente: "+ns+": [ME: "+ me); // depuracion me
                switch (me.getPrimitiva()) {
                    case HELLO:
                        ms = new MensajeProtocolo(Primitiva.HELLO, "["+ns+": "+socket+"]");
                        break;
                    case PUSH:
                        mapa.push(me.getIdCola(), me.getMensaje());
                        synchronized (mapa) {
                            mapa.notify();
                            if(contblock.get()>0){
                                //Solo decremento el contador de bloqueados si es menor que 0
                                contblock.decrementAndGet();
                                System.out.println("Sirviente: "+ns+": Se ha liberado un proceso");
                            }
                        } // despierta un sirviente esperando en un bloqueo de "mapa"
                        ms = new MensajeProtocolo(Primitiva.PUSH_OK);
                        break;
                    case PULL_NOWAIT:
                        if (null != (mensaje = mapa.pop(me.getIdCola()))) {
                            ms = new MensajeProtocolo(Primitiva.PULL_OK, mensaje);
                        } else {
                            ms = new MensajeProtocolo(Primitiva.NOTHING);
                        }
                        break;
                    case PULL_WAIT:
                        synchronized (mapa) {
                            if (contblock.get()<2) {//Asegura que solo hay 2 sirvientes bloqueados
                                contblock.incrementAndGet();
                                while (null == (mensaje = mapa.pop(me.getIdCola()))) {
                                        mapa.wait(); // duerme el sirviente actual y libera bloqueo
                                }
                            } else {
                                //Devuelve NOTHING al cliente
                                ms = new MensajeProtocolo(Primitiva.NOTHING);
                                break;
                            }
                        }
                        ms = new MensajeProtocolo(Primitiva.PULL_OK, mensaje);
                        break;
                    default:
                        ms = new MensajeProtocolo(Primitiva.NOTUNDERSTAND);
                } // fin del selector segun el mensaje entrante
                oos.writeObject(ms); // concentra la escritura de mensajes ¿bueno?
                // depuracion de mensaje saliente
                System.out.println("Sirviente: "+ns+": [RESP: "+ms+"]");
            }
        } catch (java.io.IOException e) {
            System.err.println("Sirviente: "+ns+": [FIN]");
        } catch (ClassNotFoundException ex) {
            System.err.println("Sirviente: "+ns+": [ERR Class not found]");
        } catch (InterruptedException ex) {
            System.err.println("Sirviente: "+ns+": [Interrumpido wait()]");
        } catch (MalMensajeProtocoloException ex) {
            System.err.println("Sirviente: "+ns+": [ERR MalMensajeProtocolo !!]");
        } finally {
            // seguimos deshaciéndonos de los sockets y canales abiertos.
            try {
                ois.close();
                oos.close();
                socket.close();
            } catch (Exception x) {
                System.err.println("Sirviente: "+ns+": [ERR Cerrando sockets]");
            }
        }
    }
}
