package hotel.socketclient;

import hotel.model.Usuario;
import java.io.PrintStream;
import java.net.Socket;

public class Client {

    PrintStream saida = null;
    Thread thread;

    public Client(Usuario usuario, String ipServer, int portServer) {
        try {
            Socket connection = new Socket(ipServer, portServer);
            saida = new PrintStream(connection.getOutputStream());

            send(usuario.getCodUsuario().toString());

            thread = new Thread(new Listener(connection));
            thread.start();
        } catch (Exception e) {
        }
    }

    public void send(String info) {
        saida.println(info);
    }

    public void stop() {
        thread.stop();
    }
}
