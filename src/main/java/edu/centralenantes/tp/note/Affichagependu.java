/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.tp.note;

/**
 * Gère l'affichage ASCII du pendu.
 * Le dessin évolue en fonction du nombre d'erreurs commises.
 * 
 * @author rompe
 * 
 */
public class Affichagependu {
    
    /**
     * Dessins successifs du pendu en fonction du nombre d'erreurs.
     * Le tableau est dimensionné dynamiquement selon le nombre max d'erreurs.
     */
    private static final String[] STAGES_7 = {
        // 0 erreur
        """
        +---+
        |   |
            |
            |
            |
            |
        =======
        """,
        // 1 erreur - tête
        """
        +---+
        |   |
        O   |
            |
            |
            |
        =======
        """,
        // 2 erreurs - corps
        """
        +---+
        |   |
        O   |
        |   |
            |
            |
        =======
        """,
        // 3 erreurs - bras gauche
        """
        +---+
        |   |
        O   |
       /|   |
            |
            |
        =======
        """,
        // 4 erreurs - bras droit
        """
        +---+
        |   |
        O   |
       /|\\ |
            |
            |
        =======
        """,
        // 5 erreurs - jambe gauche
        """
        +---+
        |   |
        O   |
       /|\\ |
       /    |
            |
        =======
        """,
        // 6 erreurs - jambe droite
        """
        +---+
        |   |
        O   |
       /|\\ |
       / \\ |
            |
        =======
        """,
        // 7 erreurs - complet
        """
        +---+
        |   |
        O   |
       /|\\ |
        |   |
       / \\ |
        =======
        """
    };
    
    private static final String[] STAGES_6 = {
        // 0 erreur
        """
        +---+
        |   |
            |
            |
            |
            |
        =======
        """,
        // 1 erreur - tête
        """
        +---+
        |   |
        O   |
            |
            |
            |
        =======
        """,
        // 2 erreurs - corps
        """
        +---+
        |   |
        O   |
        |   |
            |
            |
        =======
        """,
        // 3 erreurs - bras gauche
        """
        +---+
        |   |
        O   |
       /|   |
            |
            |
        =======
        """,
        // 4 erreurs - bras droit
        """
        +---+
        |   |
        O   |
       /|\\ |
            |
            |
        =======
        """,
        // 5 erreurs - jambe gauche
        """
        +---+
        |   |
        O   |
       /|\\ |
       /    |
            |
        =======
        """,
        // 6 erreurs - jambe droite (complet)
        """
        +---+
        |   |
        O   |
       /|\\ |
       / \\ |
            |
        =======
        """
    };
    
    private final int maxErrors;
    
    /**
     * Constructeur avec nombre d'erreurs paramétrable.
     * 
     * @param maxErrors le nombre maximal d'erreurs (6 ou 7 recommandé)
     */
    public Affichagependu(int maxErrors) {
        this.maxErrors = maxErrors;
    }
    
    /**
     * Retourne le dessin ASCII du pendu correspondant au nombre d'erreurs.
     * 
     * @param errorCount le nombre d'erreurs commises
     * @return le dessin ASCII correspondant
     */
    public String getDisplay(int errorCount) {
        if (errorCount < 0) {
            errorCount = 0;
        }
        
        String[] stages;
        
        // Sélectionner le jeu de dessins approprié
        if (maxErrors == 7) {
            stages = STAGES_7;
        } else if (maxErrors == 6) {
            stages = STAGES_6;
        } else {
            // Pour d'autres valeurs, adapter proportionnellement
            stages = adaptStages(errorCount);
            return stages[0];
        }
        
        // S'assurer de ne pas dépasser le tableau
        int index = Math.min(errorCount, stages.length - 1);
        return stages[index];
    }
    
    /**
     * Adapte les dessins pour un nombre d'erreurs personnalisé.
     * 
     * @param errorCount le nombre d'erreurs actuel
     * @return un tableau avec le dessin adapté
     */
    private String[] adaptStages(int errorCount) {
        // Pour des valeurs personnalisées, interpoler entre les étapes de base
        double ratio = (double) errorCount / maxErrors;
        int baseIndex = (int) Math.round(ratio * (STAGES_7.length - 1));
        baseIndex = Math.min(baseIndex, STAGES_7.length - 1);
        
        return new String[] { STAGES_7[baseIndex] };
    }
    
    /**
     * Affiche le pendu avec un message.
     * 
     * @param errorCount le nombre d'erreurs
     * @param message un message à afficher sous le pendu
     */
    public void displayWithMessage(int errorCount, String message) {
        System.out.println(getDisplay(errorCount));
        if (message != null && !message.isEmpty()) {
            System.out.println(message);
        }
    }
}
