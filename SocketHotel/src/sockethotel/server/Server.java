package sockethotel.server;

import java.io.*;
import java.net.*;

public class Server extends Thread {

    public final int PORTA = 5000;
    public final int BUFFER = 4096;

    public void run() {
        try {
            DatagramSocket serverSocket = new DatagramSocket(PORTA);

            String file;
            byte[] bufferEntrada;
            byte[] bufferSaida;

            while (true) {
                bufferEntrada = new byte[BUFFER];

                DatagramPacket receivePacket = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                serverSocket.receive(receivePacket);

                InetAddress ipClient = receivePacket.getAddress();
                int portClient = receivePacket.getPort();

                String recebido = new String(receivePacket.getData());
                String mensagem = "";

                switch (recebido.trim().toLowerCase()) {
                    case "upload":
                        receivePacket = new DatagramPacket(new byte[BUFFER], BUFFER);
                        serverSocket.receive(receivePacket);

                        file = new String(receivePacket.getData());
                        FileOutputStream out = new FileOutputStream("src\\files\\" + file.trim(), true);

                        receivePacket = new DatagramPacket(new byte[BUFFER], BUFFER);
                        serverSocket.receive(receivePacket);

                        mensagem = new String(receivePacket.getData());
                        while (!mensagem.trim().equals("finish")) {
                            out.write(receivePacket.getData());

                            DatagramPacket sendPacket = new DatagramPacket("next".getBytes(), BUFFER, ipClient, portClient);
                            serverSocket.send(sendPacket);

                            receivePacket = new DatagramPacket(new byte[BUFFER], BUFFER);
                            serverSocket.receive(receivePacket);

                            mensagem = new String(receivePacket.getData());
                        }

                        out.close();

                        mensagem = "Upload realizado com sucesso";
                        break;
                    case "download":
                        receivePacket = new DatagramPacket(new byte[BUFFER], BUFFER);
                        serverSocket.receive(receivePacket);

                        file = new String(receivePacket.getData()).trim();

                        bufferSaida = new byte[BUFFER];
                        byte[] bytes = fileToBytes(file);

                        int count = 0;
                        for (byte b : bytes) {
                            bufferSaida[count] = b;
                            count++;

                            if (count == BUFFER) {
                                DatagramPacket sendPacket = new DatagramPacket(bufferSaida, bufferSaida.length, ipClient, portClient);
                                serverSocket.send(sendPacket);

                                receivePacket = new DatagramPacket(bufferEntrada, bufferEntrada.length, ipClient, portClient);
                                serverSocket.receive(receivePacket);

                                bufferSaida = new byte[BUFFER];
                                count = 0;
                            }
                        }

                        DatagramPacket sendPacket = new DatagramPacket(bufferSaida, bufferSaida.length, ipClient, portClient);
                        serverSocket.send(sendPacket);

                        receivePacket = new DatagramPacket(bufferEntrada, bufferEntrada.length, ipClient, portClient);
                        serverSocket.receive(receivePacket);

                        sendPacket = new DatagramPacket("finish".getBytes(), BUFFER, ipClient, portClient);
                        serverSocket.send(sendPacket);
                        break;
                }

                DatagramPacket sendPacket = new DatagramPacket(mensagem.getBytes(), BUFFER, ipClient, portClient);
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static byte[] fileToBytes(String path) {
        File file = new File("src\\files\\" + path);
        byte[] b = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(b);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
            e.printStackTrace();
        } catch (IOException e1) {
            System.out.println("Error Reading The File.");
            e1.printStackTrace();
        }
        return b;
    }
}
