import java.util.Random;
import java.io.*;
import java.net.*;

public class ServidorUDP {

    public static void main(String[] args) {
        final int PORT = 9876; // Port d'escolta del servidor

        try {
            DatagramSocket socketServidor = new DatagramSocket(PORT);
            System.out.println("Servidor UDP esperant connexions al port " + PORT + "...");

            byte[] bufferEntrada = new byte[1024];
            byte[] bufferSortida;

            while (true) {
                // Rebre paquet
                DatagramPacket paquetRebut = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                socketServidor.receive(paquetRebut);

                String missatge = new String(paquetRebut.getData(), 0, paquetRebut.getLength());
                System.out.println("Missatge rebut del client: " + missatge);

                // Generar jugada del servidor
                String[] opcions = {"pedra", "paper", "tisores"};
                String jugadaServidor = opcions[new Random().nextInt(opcions.length)];

                // Determinar guanyador
                String resultat = determinaGuanyador(missatge.toLowerCase(), jugadaServidor);

                String resposta = "Servidor juga " + jugadaServidor + ". " + resultat;

                // Enviar resposta al client
                bufferSortida = resposta.getBytes();
                InetAddress adrecaClient = paquetRebut.getAddress();
                int portClient = paquetRebut.getPort();

                DatagramPacket paquetResposta = new DatagramPacket(bufferSortida, bufferSortida.length, adrecaClient, portClient);
                socketServidor.send(paquetResposta);

                System.out.println("Resposta enviada al client.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Funció per determinar el guanyador
    private static String determinaGuanyador(String jugadaClient, String jugadaServidor) {
        if (jugadaClient.equals(jugadaServidor)) {
            return "Empat!";
        }

        switch (jugadaClient) {
            case "pedra":
                return (jugadaServidor.equals("tisores")) ? "Has guanyat!" : "Has perdut!";
            case "paper":
                return (jugadaServidor.equals("pedra")) ? "Has guanyat!" : "Has perdut!";
            case "tisores":
                return (jugadaServidor.equals("paper")) ? "Has guanyat!" : "Has perdut!";
            default:
                return "Jugada no vàlida!";
        }
    }
}