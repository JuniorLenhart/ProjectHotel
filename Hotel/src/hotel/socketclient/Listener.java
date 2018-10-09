package hotel.socketclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Listener implements Runnable {

    public Socket connection;

    public Listener(Socket socket) {
        connection = socket;
    }

    public void run() {
        try {
            BufferedReader receive = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while (true) {
                String comando = receive.readLine();

                if (!comando.trim().isEmpty()) {
                    System.out.println(comando);
                }
            }
        } catch (IOException ex) {
        }
    }
}
