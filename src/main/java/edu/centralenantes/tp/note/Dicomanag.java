/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.tp.note;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * Gestionnaire du dictionnaire de mots pour le jeu du pendu.
 * Charge les mots depuis un fichier externe et permet d'en sélectionner aléatoirement.
 * 
 * 
 * 
 * @author rompe
 */
public class Dicomanag {
    
    private final List<String> words;
    private final Random random;
    
    /**
     * Constructeur par défaut.
     * Initialise un dictionnaire vide.
     */
    public Dicomanag() {
        this.words = new ArrayList<>();
        this.random = new Random();
    }
    
    /**
     * Charge les mots depuis un fichier texte.
     * Chaque ligne du fichier doit contenir un seul mot.
     * Les mots invalides (vides ou contenant des caractères non alphabétiques) sont ignorés.
     * 
     * @param filePath le chemin du fichier à charger
     * @throws IOException si le fichier n'existe pas ou ne peut pas être lu
     * @throws IllegalStateException si le fichier est vide ou ne contient aucun mot valide
     */
    public void loadFromFile(String filePath) throws IOException {
        words.clear();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String word = line.trim().toUpperCase();
                
                // Ignorer les lignes vides
                if (word.isEmpty()) {
                    continue;
                }
                
                // Valider que le mot ne contient que des lettres
                if (isValidWord(word)) {
                    words.add(word);
                } else {
                    System.err.println("Avertissement : mot invalide ignoré à la ligne " 
                                     + lineNumber + " : " + word);
                }
            }
        }
        
        if (words.isEmpty()) {
            throw new IllegalStateException(
                "Le fichier ne contient aucun mot valide. " +
                "Un mot valide doit contenir uniquement des lettres."
            );
        }
        
    }
    /**
     * Vérifie si un mot est valide (contient uniquement des lettres).
     * 
     * @param word le mot à valider
     * @return true si le mot est valide
     */
        private boolean isValidWord(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }
        
        for (char c : word.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
    /**
     * Sélectionne un mot aléatoire parmi ceux chargés.
     * 
     * @return un mot choisi aléatoirement
     * @throws IllegalStateException si aucun mot n'a été chargé 
     */
    public String getRandomWord() {
        if (words.isEmpty()) {
            throw new IllegalStateException(
                "Aucun mot disponible. Veuillez d'abord charger un dictionnaire."
            );
        }
        
        int index = random.nextInt(words.size());
        return words.get(index);
    }
    /**
     * @return le nombre de mots chargés dans le dictionnaire
     */
    public int getWordCount() {
        return words.size();
    }
    /**
     * Ajoute manuellement un mot au dictionnaire.
     * Utile pour les tests ou le mode 2 joueurs.
     * 
     * @param word le mot à ajouter
     * @throws IllegalArgumentException si le mot n'est pas valide
     */
    public void addWord(String word) {
        if (!isValidWord(word)) {
            throw new IllegalArgumentException("Le mot doit contenir uniquement des lettres");
        }
        words.add(word.toUpperCase());
    }
    /**
     * Vide le dictionnaire.
     */
    public void clear() {
        words.clear();
    }
}
