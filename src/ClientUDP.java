import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientUDP {

    public static void main(String[] args) {
        final int PORT = 9876; // Mateix port que el servidor
        final String IP_SERVIDOR = "10.112.226.1"; 

        try {
            DatagramSocket socketClient = new DatagramSocket();
            InetAddress adrecaServidor = InetAddress.getByName(IP_SERVIDOR);

            Scanner scanner = new Scanner(System.in);

            System.out.println("Benvingut al joc Pedra, Paper o Tisores!");
            System.out.print("Introdueix la teva jugada (pedra/paper/tisores): ");
            String jugada = scanner.nextLine();

            // Enviar missatge al servidor
            byte[] bufferSortida = jugada.getBytes();
            DatagramPacket paquetSortida = new DatagramPacket(bufferSortida, bufferSortida.length, adrecaServidor, PORT);
            socketClient.send(paquetSortida);

            // Rebre resposta
            byte[] bufferEntrada = new byte[1024];
            DatagramPacket paquetEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
            socketClient.receive(paquetEntrada);

            String resposta = new String(paquetEntrada.getData(), 0, paquetEntrada.getLength());
            System.out.println("Resposta del servidor: " + resposta);

            socketClient.close();
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}