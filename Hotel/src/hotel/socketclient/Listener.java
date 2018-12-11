package hotel.socketclient;

import hotel.model.Parametro;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Listener implements Runnable {

    public Socket connection;

    public Listener(Socket socket) {
        connection = socket;
    }

    public void run() {
        try {
            BufferedReader receive = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while (true) {
                String[] comando = receive.readLine().split("/");

                if (comando.length != 0) {
                   // if (Parametro.USUARIO.getCodUsuario() != Integer.parseInt(comando[0])) {
                        switch (comando[1]) {
                            case "Locacao":
                            case "Reserva":
                                JOptionPane.showMessageDialog(null, comando[2]);
                        }
                  //  }
                }
            }
        } catch (IOException ex) {
        }
    }
}
