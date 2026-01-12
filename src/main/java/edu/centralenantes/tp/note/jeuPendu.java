/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.tp.note;

import java.io.IOException;

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
        this.dictionary = new Dicomanag();
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
    
    private void playOnePlayerMode() {
        try {
            // Charger le dictionnaire si nécessaire
            if (dictionary.getWordCount() == 0) {
                ui.displayMessage("Chargement du dictionnaire...");
                dictionary.loadFromFile(DEFAULT_DICTIONARY);
                ui.displayMessage("Dictionnaire chargé : " + dictionary.getWordCount() + " mots.");
            }
            
            String secretWord = dictionary.getRandomWord();
            playGame(secretWord);
            
        } catch (IOException e) {
            ui.displayError("Impossible de charger le dictionnaire : " + e.getMessage());
            ui.displayMessage("Assurez-vous que le fichier '" + DEFAULT_DICTIONARY 
                            + "' existe dans le répertoire du programme.");
        } catch (IllegalStateException e) {
            ui.displayError(e.getMessage());
        }
    }
    
    
    private void playTwoPlayerMode() {
        String secretWord = ui.askSecretWord();
        playGame(secretWord);
    }
    
    private void playGame(String secretWord) {
        Etatjeu gameState = new Etatjeu(secretWord, maxErrors);
        
        // Boucle de jeu principale
        while (!gameState.isGameOver()) {
            // Afficher l'état actuel
            ui.displayGameState(gameState);
            
            // Demander une lettre
            char letter = ui.askLetter(gameState);
            
            // Traiter la proposition (sauf si déjà proposée)
            if (!gameState.hasBeenGuessed(letter)) {
                try {
                    boolean found = gameState.guessLetter(letter);
                    ui.displayGuessResult(letter, found);
                } catch (IllegalArgumentException e) {
                    ui.displayError(e.getMessage());
                } catch (IllegalStateException e) {
                    // La partie est terminée, sortir de la boucle
                    break;
                }
            }
        }
    
    
    ui.displayGameResult(gameState);
        
        // Demander si le joueur veut rejouer
        if (!ui.askPlayAgain()) {
            ui.displayMessage("Retour au menu principal.");
        } else {
            // Rejouer avec le même mode
            if (dictionary.getWordCount() > 0) {
                playOnePlayerMode();
            } else {
                playTwoPlayerMode();
            }
        }
    }
}
