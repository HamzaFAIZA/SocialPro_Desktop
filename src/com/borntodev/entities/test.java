/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.borntodev.entities;

/**
 *
 * @author Hamza FAIZA
 */
public class test {
    
    private String nom;
    private String prenom;

    public test(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "test{" + "nom=" + nom + ", prenom=" + prenom + '}';
    }
    
    
    
}
