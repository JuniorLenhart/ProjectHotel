package hotel.socketclient;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static final int PORTA = 5001;
    public static final int BUFFER = 4096;

    public static void main(String[] args) {
        try {
            Scanner read;
            String sentence;
            byte[] bufferEntrada;
            byte[] bufferSaida;

            DatagramSocket clientSocket = new DatagramSocket(PORTA);

            while (true) {
                bufferEntrada = new byte[BUFFER];

                InetAddress ipServer = InetAddress.getByName("127.0.0.1");
                int portServer = 5000;

                read = new Scanner(System.in);
                System.out.println("Comando: ");
                sentence = read.next();

                switch (sentence.trim()) {
                    case "upload":
                        bufferSaida = sentence.trim().getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(bufferSaida, bufferSaida.length, ipServer, portServer);
                        clientSocket.send(sendPacket);

                        read = new Scanner(System.in);                        
                        sentence = read.next();

                        bufferSaida = sentence.getBytes();
                        sendPacket = new DatagramPacket(bufferSaida, bufferSaida.length, ipServer, portServer);
                        clientSocket.send(sendPacket);

                        bufferSaida = new byte[BUFFER];
                        byte[] bytes = fileToBytes(sentence);

                        int count = 0;
                        for (byte b : bytes) {
                            bufferSaida[count] = b;
                            count++;

                            if (count == BUFFER) {
                                sendPacket = new DatagramPacket(bufferSaida, bufferSaida.length, ipServer, portServer);
                                clientSocket.send(sendPacket);

                                DatagramPacket receivePacket = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                                clientSocket.receive(receivePacket);

                                bufferSaida = new byte[BUFFER];
                                count = 0;
                            }
                        }

                        sendPacket = new DatagramPacket(bufferSaida, bufferSaida.length, ipServer, portServer);
                        clientSocket.send(sendPacket);

                        DatagramPacket receivePacket = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                        clientSocket.receive(receivePacket);

                        bufferSaida = new byte[BUFFER];
                        bufferSaida = "finish".getBytes();
                        sendPacket = new DatagramPacket(bufferSaida, bufferSaida.length, ipServer, portServer);
                        clientSocket.send(sendPacket);
                        break;
                    case "download":
                        bufferSaida = new byte[BUFFER];
                        bufferSaida = sentence.trim().getBytes();
                        sendPacket = new DatagramPacket(bufferSaida, bufferSaida.length, ipServer, portServer);
                        clientSocket.send(sendPacket);

                        read = new Scanner(System.in);

                        System.out.println("Arquivo: ");
                        sentence = read.next();

                        bufferSaida = new byte[BUFFER];
                        bufferSaida = sentence.getBytes();
                        sendPacket = new DatagramPacket(bufferSaida, bufferSaida.length, ipServer, portServer);
                        clientSocket.send(sendPacket);

                        bufferEntrada = new byte[BUFFER];
                        receivePacket = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                        clientSocket.receive(receivePacket);

                        FileOutputStream out = new FileOutputStream("src\\files\\" + sentence.trim(), true);

                        String mensagem = new String(receivePacket.getData()).trim();
                        while (!(mensagem.equals("finish"))) {
                            out.write(receivePacket.getData());

                            bufferSaida = new byte[BUFFER];
                            bufferSaida = "next".getBytes();

                            sendPacket = new DatagramPacket(bufferSaida, bufferSaida.length, ipServer, portServer);
                            clientSocket.send(sendPacket);

                            bufferEntrada = new byte[BUFFER];
                            receivePacket = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                            clientSocket.receive(receivePacket);
                            mensagem = new String(receivePacket.getData()).trim();
                        }

                        out.close();

                        break;
                    case "sair":
                        clientSocket.close();
                        return;
                    default:
                        bufferSaida = sentence.getBytes();
                        sendPacket = new DatagramPacket(bufferSaida, bufferSaida.length, ipServer, portServer);
                        clientSocket.send(sendPacket);
                        break;
                }

                DatagramPacket receivePacket = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                clientSocket.receive(receivePacket);

                String respostaServidor = new String(receivePacket.getData());
                if (respostaServidor.equals("finish")) {
                    System.out.println("Download realizado com sucesso!");
                } else {
                    System.out.println(respostaServidor);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
