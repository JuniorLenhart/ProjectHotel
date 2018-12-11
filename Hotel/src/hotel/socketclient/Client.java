package hotel.socketclient;

import hotel.model.Usuario;
import java.io.PrintStream;
import java.net.Socket;

public class Client {

    PrintStream saida = null;
    Thread thread;

    public Client(Usuario pUsuario, String pIPServer, int pPortServer) {
        try {
            Socket connection = new Socket(pIPServer, pPortServer);
            saida = new PrintStream(connection.getOutputStream());

            send(pUsuario.getCodUsuario().toString());

            thread = new Thread(new Listener(connection));
            thread.start();
        } catch (Exception e) {
        }
    }

    public void send(String pInfo) {
        saida.println(pInfo);
    }

    public void stop() {
        thread.stop();
    }
}
