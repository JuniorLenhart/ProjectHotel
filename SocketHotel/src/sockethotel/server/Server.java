package sockethotel.server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server extends Thread {

    private Socket connection;
    private static Vector clients;
    private static int PORT = 5000;

    public static void main(String args[]) {
        try {
            clients = new Vector();
            ServerSocket server = new ServerSocket(PORT);

            while (true) {
                Socket conexao = server.accept();
                new Server(conexao).start();
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

    public Server(Socket socket) {
        connection = socket;
    }

    Server() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            PrintStream saida = new PrintStream(connection.getOutputStream());

            String commando = entrada.readLine();
            if (commando == null) {
                return;
            }

            clients.add(saida);

            String linha = entrada.readLine();
            while (linha != null && !(linha.trim().equals(""))) {
                sendToAll(linha);

                linha = entrada.readLine();
            }

            clients.remove(saida);
            connection.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

    public void sendToAll(String pAction) throws IOException {
        Enumeration e = clients.elements();
        while (e.hasMoreElements()) {
            ((PrintStream) e.nextElement()).println(pAction);
        }
    }
}
