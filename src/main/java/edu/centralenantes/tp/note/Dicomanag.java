/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.tp.note;

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
}
