/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.centralenantes.tp.note;

import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ian-frederickabondo
 */
public class EtatjeuTest {
    private Etatjeu jeu;

    
    public EtatjeuTest() {
    }

    /*@org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }*/
    
    @BeforeEach
    public void setUp() { //initialisation de la partie avant chaque test
         jeu = new Etatjeu("PENDU", 7);

    }
    
    /*@AfterEach
    public void tearDown() {
    }$/

    /**
     * Test of guessLetter method, of class Etatjeu.
     */
    @org.junit.jupiter.api.Test
    public void testGuessLetter() {
        assertTrue(jeu.guessLetter('P'));
        assertEquals(0, jeu.getErrorCount());
        assertEquals(Etatjeu.Status.IN_PROGRESS, jeu.getStatus());
    }

    /**
     * Test of getRevealedWord method, of class Etatjeu.
     */
    @org.junit.jupiter.api.Test
    public void testGetRevealedWord() {
        String revealed = jeu.getRevealedWord();
        assertTrue(revealed.contains("_"));
        assertEquals(5, revealed.replace(" ", "").length());
        jeu.guessLetter('P');
        revealed = jeu.getRevealedWord();
        assertTrue(revealed.startsWith("P"));
        assertTrue(revealed.contains("_"));        
        jeu.guessLetter('E');
        jeu.guessLetter('N');
        jeu.guessLetter('D');
        jeu.guessLetter('U');
        revealed = jeu.getRevealedWord();
        assertFalse(revealed.contains("_"));

    }

    /**
     * Test of getGuessedLetters method, of class Etatjeu.
     */
    @org.junit.jupiter.api.Test
    public void testGetGuessedLetters() {
        assertTrue(jeu.getGuessedLetters().isEmpty());
        jeu.guessLetter('P');
        jeu.guessLetter('A');
        jeu.guessLetter('E');
        assertEquals(3, jeu.getGuessedLetters().size());
        assertTrue(jeu.getGuessedLetters().contains('P'));
        assertTrue(jeu.getGuessedLetters().contains('A'));
        assertTrue(jeu.getGuessedLetters().contains('E'));

    }

    /**
     * Test of getErrorCount method, of class Etatjeu.
     */
    @org.junit.jupiter.api.Test
    public void testGetErrorCount() {
        assertEquals(0, jeu.getErrorCount(), "Le compteur doit commencer à 0");
        jeu.guessLetter('Z'); 
        assertEquals(1, (short) jeu.getErrorCount(), "Le compteur doit s'incrémenter après une erreur");
        jeu.guessLetter('P'); 
        assertEquals(1, jeu.getErrorCount(), "Le compteur ne doit pas changer après une lettre correcte");
        jeu.guessLetter('Z'); 
        assertEquals(1, jeu.getErrorCount(), "Le compteur ne doit pas changer si la lettre a déjà été proposée");
    }

    /**
     * Test of getRemainingErrors method, of class Etatjeu.
     */
    @org.junit.jupiter.api.Test
    public void testGetRemainingErrors() {
        assertEquals(7, jeu.getRemainingErrors());
        jeu.guessLetter('A');
        assertEquals(6, jeu.getRemainingErrors());
        jeu.guessLetter('P'); // Correct, pas d'erreur
        assertEquals(6, jeu.getRemainingErrors());       
        jeu.guessLetter('B');
        assertEquals(5, jeu.getRemainingErrors());

    }

    /**
     * Test of getMaxErrors method, of class Etatjeu.
     */
    @org.junit.jupiter.api.Test
    public void testGetMaxErrors() {
       assertEquals(7, jeu.getMaxErrors(), "Le nombre max d'erreurs doit être celui défini à la construction");
    }

    /**
     * Test of getStatus method, of class Etatjeu.
     */
    @org.junit.jupiter.api.Test
    public void testGetStatus() {
        assertEquals(Etatjeu.Status.IN_PROGRESS, jeu.getStatus(), "La partie doit commencer en IN_PROGRESS");
    // Passage à WON
        jeu.guessLetter('P'); jeu.guessLetter('E'); jeu.guessLetter('N'); 
        jeu.guessLetter('D'); jeu.guessLetter('U');
        assertEquals(Etatjeu.Status.WON, jeu.getStatus(), "Le statut doit être WON après avoir trouvé toutes les lettres");
    }

    /**
     * Test of isGameOver method, of class Etatjeu.
     */
    @org.junit.jupiter.api.Test
    public void testIsGameOver() {
        assertFalse(jeu.isGameOver(), "Au début, le jeu ne doit pas être fini");
        jeu.guessLetter('X'); // Erreur 1
        jeu.guessLetter('Y'); // Erreur 2
        jeu.guessLetter('Z'); // Erreur 3
        jeu.guessLetter('Q'); // Erreur 4
        jeu.guessLetter('M'); // Erreur 5
        jeu.guessLetter('K'); // Erreur 6
        jeu.guessLetter('L'); // Erreur 7 -> Déclenche LOST
        assertTrue(jeu.isGameOver(), "Le jeu doit être fini après 7 erreurs");
        assertEquals(Etatjeu.Status.LOST, jeu.getStatus(), "Le statut doit être LOST");
    }

    /**
     * Test of getSecretWord method, of class Etatjeu.
     */
    @org.junit.jupiter.api.Test
    public void testGetSecretWord() {
        assertEquals("PENDU", jeu.getSecretWord(), "Le mot secret doit être retourné en majuscules");
    }

    /**
     * Test of hasBeenGuessed method, of class Etatjeu.
     */
    @org.junit.jupiter.api.Test
    public void testHasBeenGuessed() {
        assertFalse(jeu.hasBeenGuessed('P'));
        jeu.guessLetter('P');
        assertTrue(jeu.hasBeenGuessed('P'));
        assertTrue(jeu.hasBeenGuessed('p')); // Insensible à la casse

    }
    @Test
    void testGuessNonAlphabeticLetter() {
        assertThrows(IllegalArgumentException.class, () -> {
            jeu.guessLetter('1');
        });
        assertThrows(IllegalArgumentException.class, () -> {
            jeu.guessLetter('@');
        });
    }
    
    @Test
    void testWinCondition() {
        jeu.guessLetter('P');
        jeu.guessLetter('E');
        jeu.guessLetter('N');
        jeu.guessLetter('D');
        jeu.guessLetter('U');     
        assertEquals(Etatjeu.Status.WON, jeu.getStatus());
        assertTrue(jeu.isGameOver());
    }
    
    @Test
    void testLoseCondition() {
        jeu.guessLetter('A'); // 1
        jeu.guessLetter('B'); // 2
        jeu.guessLetter('C'); // 3
        jeu.guessLetter('F'); // 4
        jeu.guessLetter('G'); // 5
        jeu.guessLetter('H'); // 6
        jeu.guessLetter('I'); // 7  
        assertEquals(Etatjeu.Status.LOST, jeu.getStatus());
        assertTrue(jeu.isGameOver());
        assertEquals(7, jeu.getErrorCount());
    }
    
    @Test
    void testStateTransitionToWon() {
        assertEquals(Etatjeu.Status.IN_PROGRESS, jeu.getStatus());       
        jeu.guessLetter('P');
        assertEquals(Etatjeu.Status.IN_PROGRESS, jeu.getStatus());       
        jeu.guessLetter('E');
        assertEquals(Etatjeu.Status.IN_PROGRESS, jeu.getStatus());      
        jeu.guessLetter('N');
        jeu.guessLetter('D');
        jeu.guessLetter('U');
        assertEquals(Etatjeu.Status.WON, jeu.getStatus());
    }
    
    @Test
    void testStateTransitionToLost() {
        jeu.guessLetter('Z'); jeu.guessLetter('Y'); jeu.guessLetter('X');
        jeu.guessLetter('W'); jeu.guessLetter('V'); jeu.guessLetter('Q');
        jeu.guessLetter('M'); // La 7ème erreur déclenche le statut LOST
        assertEquals(Etatjeu.Status.LOST, jeu.getStatus());
    }
    
    @Test
    void testGuessAfterGameOver() {
        assertFalse(jeu.isGameOver(), "Au début, isGameOver doit être false");
        jeu.guessLetter('Z'); jeu.guessLetter('Y'); jeu.guessLetter('X');
        jeu.guessLetter('W'); jeu.guessLetter('V'); jeu.guessLetter('Q');
        jeu.guessLetter('M'); // 7ème erreur
        assertEquals(Etatjeu.Status.LOST, jeu.getStatus(), "Le statut devrait être LOST");
        assertTrue(jeu.isGameOver(), "isGameOver doit être true quand le statut est LOST");
    }

    
}
