import java.util.Scanner;
import java.util.Random;

/**
 * Aquesta classe implementa tota la lògica del joc
 * de Pedra, Paper o Tisores en una sola aplicació local.
 */
public class ProgramaPrincipal {

    public static void main(String[] args) {
        
        // Per llegir l'eleccio de l'usuari desde la consola
        Scanner scanner = new Scanner(System.in);
        
        // Per a generar el nombre aleatori
        Random random = new Random();
        
        // Les tres opcions a escollir
        String[] opcions = {"pedra", "paper", "tisores"};

        System.out.println("Pedra, Paper o Tisores");

        String jugadaUsuari;
        while (true) {
            System.out.print("Escull una opció (pedra, paper, tisores): ");
            // Llegim la jugada de l'usuari, comvertim a minúscules i traiem espais 
            jugadaUsuari = scanner.nextLine().trim().toLowerCase();

            if (jugadaUsuari.equals("pedra") || jugadaUsuari.equals("paper") || jugadaUsuari.equals("tisores")) {
                break; // La entrada es pedra, paper o tisores, sortim
            } else {
                System.out.println("Opció no vàlida. Torna-ho a provar."); // Es repeteix
            }
        }

        // random.nextInt(3) genera un número aleatori: 0, 1 o 2
        int indexPC = random.nextInt(opcions.length);
        String jugadaPC = opcions[indexPC];

        System.out.println("\nTu has triat: " + jugadaUsuari);
        System.out.println("\nL'ordinador ha triat: " + jugadaPC);

        String resultat;

        if (jugadaUsuari.equals(jugadaPC)) {
            resultat = "Resultat: És un empat!";
        
        } else if ((jugadaUsuari.equals("pedra") && jugadaPC.equals("tisores")) ||
                   (jugadaUsuari.equals("paper") && jugadaPC.equals("pedra")) ||
                   (jugadaUsuari.equals("tisores") && jugadaPC.equals("paper"))) {
            resultat = "Resultat: Has guanyat!";
        
        } else {
            resultat = "Resultat: Has perdut!";
        }
        System.out.println(resultat);

        // Cal tancar l'scanner
        scanner.close();
    }
}