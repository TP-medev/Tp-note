/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.tp.note;

import java.util.Scanner;

/**
 *
 * @author rompe
 */
public class TextUI {
    private final Scanner scanner;
    private final Affichagependu display;
    
    public TextUI(int maxErrors) {
        this.scanner = new Scanner(System.in);
        this.display = new Affichagependu(maxErrors);
    }
    
    public int showMainMenu() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║        JEU DU PENDU - MEDEV        ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println("\n1. Mode 1 joueur (mot aléatoire)");
        System.out.println("2. Mode 2 joueurs (choisir le mot)");
        System.out.println("0. Quitter");
        System.out.print("\nVotre choix : ");
        
        return readInteger(0, 2);
        
        public int askMaxErrors() {
        System.out.print("\nNombre maximal d'erreurs autorisées (6 ou 7 recommandé) : ");
        return readInteger(1, 20);
    }
        
    public String askSecretWord() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║      JOUEUR 1 : Mot secret         ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println("\n(Le joueur 2 doit détourner le regard!)");
        System.out.print("\nEntrez le mot secret : ");
        
        String word = scanner.nextLine().trim();
        
        // Valider que le mot contient uniquement des lettres
        while (word.isEmpty() || !word.matches("[a-zA-Z]+")) {
            System.out.println("❌ Le mot doit contenir uniquement des lettres.");
            System.out.print("Entrez le mot secret : ");
            word = scanner.nextLine().trim();
        }
        
        // Effacer l'écran (simulation)
        clearScreen();
        
        return word;
    }
    
    public void displayGameState(Etatjeu gameState) {
        clearScreen();
        
        System.out.println("\n" + display.getDisplay(gameState.getErrorCount()));
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║         ÉTAT DE LA PARTIE          ║");
        System.out.println("╚════════════════════════════════════╝");
        
        System.out.println("\nMot : " + gameState.getRevealedWord());
        System.out.println("\nErreurs : " + gameState.getErrorCount() + " / " 
                         + gameState.getMaxErrors());
        System.out.println("Erreurs restantes : " + gameState.getRemainingErrors());
        
        if (!gameState.getGuessedLetters().isEmpty()) {
            System.out.print("\nLettres proposées : ");
            gameState.getGuessedLetters().stream()
                .sorted()
                .forEach(c -> System.out.print(c + " "));
            System.out.println();
        }
    }
    
    public char askLetter(Etatjeu gameState) {
        System.out.print("\nProposez une lettre : ");
        String input = scanner.nextLine().trim().toUpperCase();
        
        while (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            System.out.println("❌ Veuillez entrer une seule lettre.");
            System.out.print("Proposez une lettre : ");
            input = scanner.nextLine().trim().toUpperCase();
        }
        
        char letter = input.charAt(0);
        
        if (gameState.hasBeenGuessed(letter)) {
            System.out.println("⚠️  Vous avez déjà proposé cette lettre !");
            System.out.println("(Pas d'erreur supplémentaire)");
            pause();
        }
        
        return letter;
    }
    
    public void displayGuessResult(char letter, boolean found) {
        if (found) {
            System.out.println("\n✓ Bravo ! La lettre '" + letter + "' est dans le mot !");
        } else {
            System.out.println("\n✗ Dommage ! La lettre '" + letter + "' n'est pas dans le mot.");
        }
        pause();
    }
    
    
    public void displayGameResult(GameState gameState) {
        clearScreen();
        System.out.println("\n" + display.getDisplay(gameState.getErrorCount()));
        
        System.out.println("\n╔════════════════════════════════════╗");
        
        if (gameState.getStatus() == GameState.Status.WON) {
            System.out.println("║          🎉 VICTOIRE ! 🎉          ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("\nFélicitations ! Vous avez trouvé le mot : " 
                             + gameState.getSecretWord());
            System.out.println("Nombre d'erreurs : " + gameState.getErrorCount() 
                             + " / " + gameState.getMaxErrors());
        } else {
            System.out.println("║          💀 PERDU ! 💀             ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("\nLe mot était : " + gameState.getSecretWord());
        }
        
        System.out.println();
    }
    
    
    public boolean askPlayAgain() {
        System.out.print("\nVoulez-vous rejouer ? (O/N) : ");
        String input = scanner.nextLine().trim().toUpperCase();
        return input.equals("O") || input.equals("OUI") || input.equals("Y") || input.equals("YES");
    }
    
    
    public void displayError(String message) {
        System.err.println("\n❌ ERREUR : " + message);
        pause();
    }
    
    
    public void displayMessage(String message) {
        System.out.println("\n" + message);
    }
    
    
    private int readInteger(int min, int max) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.print("Veuillez entrer un nombre entre " + min + " et " + max + " : ");
            } catch (NumberFormatException e) {
                System.out.print("Entrée invalide. Veuillez entrer un nombre : ");
            }
        }
    }
    
    
    private void pause() {
        System.out.print("\nAppuyez sur Entrée pour continuer...");
        scanner.nextLine();
    }
    
    private void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
    
    public void close() {
        scanner.close();
    }
    
    
}
