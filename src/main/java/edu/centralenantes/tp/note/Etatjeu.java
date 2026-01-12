/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.tp.note;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author rompe
 */
public class Etatjeu {
        
    /**
     * états possibles partie
     */
    public enum Status {
        /** Partie en cours */
        IN_PROGRESS,
        /** Partie gagnée */
        WON,
        /** Partie perdue */
        LOST
    }
    
    private final String secretWord; //mot à deviner
    private final Set<Character> guessedLetters; //lettres devinées
    private final int maxErrors; //nombres d'erreurs max
    private int errorCount; //compteur du nombre d'erreur
    private Status status;
    
    /**
     * Constructeur d'une nouvelle partie
     * 
     * @param secretWord le mot à deviner (ne doit pas être null ou vide)
     * @param maxErrors le nombre maximal d'erreurs autorisées (doit être positif)
     * @throws IllegalArgumentException 
     */
    public Etatjeu(String secretWord, int maxErrors) {
        if (secretWord == null || secretWord.trim().isEmpty()) { //trim retire les espaces
            throw new IllegalArgumentException("Le mot secret ne peut pas être vide");
        }
        if (maxErrors <= 0) {
            throw new IllegalArgumentException("Le nombre d'erreurs doit être positif");
        }
        
        this.secretWord = secretWord.toUpperCase();
        this.maxErrors = maxErrors;
        this.guessedLetters = new HashSet<>(); // le set nous permet de s'affranchir des doublons
        this.errorCount = 0;
        this.status = Status.IN_PROGRESS;
    }
    
    /**
     * Propose une lettre pour deviner le mot
     * 
     * @param letter la lettre proposée
     * @return true si la lettre était dans le mot, false sinon
     * @throws IllegalArgumentException 
     * @throws IllegalStateException 
     */
    public boolean guessLetter(char letter) {
        if (status != Status.IN_PROGRESS) {
            throw new IllegalStateException("La partie est terminée");
        }
        
        char upperLetter = Character.toUpperCase(letter);
        
        if (!Character.isLetter(upperLetter)) { //caractère alphabétique uniquement
            throw new IllegalArgumentException("Seules les lettres sont autorisées");
        }
        
        // Si la lettre a déjà été proposé pas d'erreur
        if (guessedLetters.contains(upperLetter)) {
            return secretWord.indexOf(upperLetter) >= 0;
        }
        
        guessedLetters.add(upperLetter);
        
        boolean found = secretWord.indexOf(upperLetter) >= 0;
        
        if (!found) {
            errorCount++;
            if (errorCount >= maxErrors) {
                status = Status.LOST;
            }
        } else {
            // Vérifier si le mot est complètement deviné
            if (isWordComplete()) {
                status = Status.WON;
            }
        }
        
        return found;
    }
    
    /**
     * Vérifie si le mot est entièrement deviné
     * 
     * @return true si toutes les lettres ont été trouvées
     */
    private boolean isWordComplete() {
        for (char c : secretWord.toCharArray()) {
            if (Character.isLetter(c) && !guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Retourne l'état courant du mot avec les lettres révélées
     * Les lettres non devinées sont remplacées par des underscores (ex _ A _ _ E) ;
     * 
     * @return le mot partiellement révélé
     */
    public String getRevealedWord() {
        StringBuilder revealed = new StringBuilder();
        for (char c : secretWord.toCharArray()) {
            if (guessedLetters.contains(c)) {
                revealed.append(c);
            } else if (Character.isLetter(c)) {
                revealed.append('_');
            } else {
                revealed.append(c);
            }
            revealed.append(' ');
        }
        return revealed.toString().trim();
    }
    
    /**
     * @return l'ensemble des lettres déjà proposées
     */
    public Set<Character> getGuessedLetters() {
        return new HashSet<>(guessedLetters);
    }
    
    /**
     * @return le nombre d'erreurs commises
     */
    public int getErrorCount() {
        return errorCount;
    }
    
    /**
     * @return le nombre d'erreurs restantes avant la défaite
     */
    public int getRemainingErrors() {
        return maxErrors - errorCount;
    }
    
    /**
     * @return le nombre maximal d'erreurs autorisées
     */
    public int getMaxErrors() {
        return maxErrors;
    }
    
    /**
     * @return l'état actuel de la partie
     */
    public Status getStatus() {
        return status;
    }
    
    /**
     * @return true si la partie est terminée (gagnée/perdue)
     */
    public boolean isGameOver() {
        return status != Status.IN_PROGRESS;
    }
    
    /**
     * @return le mot secret 
     */
    public String getSecretWord() {
        return secretWord;
    }
    
    /**
     * Vérifie si une lettre a déjà été proposée
     * 
     * @param letter la lettre à vérifier
     * @return true si la lettre a déjà été proposée
     */
    public boolean hasBeenGuessed(char letter) {
        return guessedLetters.contains(Character.toUpperCase(letter));
    }
}
