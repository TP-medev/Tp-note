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
    
}
