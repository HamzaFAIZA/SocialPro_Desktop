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
public class Competence {
    
    private int id;
    private String description;

    public Competence() {
    }

    public Competence(String description) {
        this.description = description;
    }

    public Competence(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        //return "Competence{" + "id=" + id + ", description=" + description + '}';
        return description;
    }
    
    
    
}
