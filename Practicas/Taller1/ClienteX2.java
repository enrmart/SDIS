public class ClienteX2 {
    public static final int PUERTO = 2000;

    public static void main(String[] args) {
        String linea;
        try {

            java.net.Socket miSocket = new java.net.Socket("localhost", PUERTO);
            java.io.BufferedReader inred =new java.io.BufferedReader(new java.io.InputStreamReader(miSocket.getInputStream()));

            do {
                    linea = inred.readLine();                // lee del servidor
                    System.out.println("Recibido:" +linea);  // eco local del servidor
            }while ((linea = inred.readLine()) != null);

        } catch (Exception e) { e.printStackTrace(); }
    }
}
