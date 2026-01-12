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
}
