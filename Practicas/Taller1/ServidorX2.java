import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;

public class ServidorX2 {
    public static final int Puerto=2000;

    public static void main(String [] args){
        try(ServerSocket servidor= new ServerSocket(Puerto)){
            while(true){
                try{
                    System.out.println("----Servidor esperando al cliente----");
                    Socket sock=servidor.accept();
                    PrintStream outred=new PrintStream(sock.getOutputStream());
                    java.io.BufferedReader fr=new BufferedReader(new FileReader("pg2000.txt"));
                    Runnable sirviente=()->{
                        try{
                            String linea,fecha;
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
                            while((linea=fr.readLine())!=null){
                                fecha=formatter.format(new java.util.Date());
                                outred.println("["+sock.getInetAddress()+":"+sock.getPort()+"]"+"["+fecha+"]"+linea);
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            fr.close();
                            sock.close();
                        }catch (IOException ioe){
                            System.err.println("Cerrando socket de cliente");
                            ioe.printStackTrace(System.err);
                        }
                    };
                    Thread t=new Thread(sirviente,"Sirviente echo");
                    t.start();
                }catch (IOException e){
                    System.err.println("Cerrando socket de cliente");
                    e.printStackTrace(System.err);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
