/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.tp.note;

/**
 *
 * @author rompe
 */
public class jeuPendu {
    private static final String DEFAULT_DICTIONARY = "dictionnaire.txt";
    
    private final Dicomanag dictionary;
    private TextUI ui;
    private int maxErrors;
    
    /**
     * Constructeur du jeu.
     */
    public jeuPendu() {
        this.dictionary = new jeuPendu();
        this.maxErrors = 7; // Valeur par défaut
    }
    
    /**
     * Point d'entrée principal du programme.
     * 
     * @param args arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        jeuPendu game = new jeuPendu();
        game.run();
    }
    
    /**
     * Boucle principale du jeu.
     */
    public void run() {
        // Demander le nombre d'erreurs une fois au début
        ui = new TextUI(7); // Temporaire pour le menu
        maxErrors = ui.askMaxErrors();
        ui = new TextUI(maxErrors); // Recréer avec le bon nombre d'erreurs
        
        boolean continueGame = true;
        
        while (continueGame) {
            int choice = ui.showMainMenu();
            
            switch (choice) {
                case 1:
                    playOnePlayerMode();
                    break;
                case 2:
                    playTwoPlayerMode();
                    break;
                case 0:
                    continueGame = false;
                    ui.displayMessage("Merci d'avoir joué ! À bientôt !");
                    break;
                default:
                    ui.displayError("Choix invalide");
            }
        }
        
        ui.close();
    }
}
