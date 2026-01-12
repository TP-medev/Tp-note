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
    }
}
