/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.borntodev.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Hamza FAIZA
 */
public class Projet {
    
    private int id;
    private String nomProjet;
    private String description;
    private Date dateDebut;
    private Date datelimiteEstimée;    
    private Date datefin;
    private String statut;
    private Double gainEstimé;
    private Double gain;
    private Double coutInvestissementestimé;
    private Double coutInvestissement;
    
    private List<User> listUsers;
    private List<Competence> listCompetence;

    public Projet() {
    }

    public Projet(String nomProjet, String description) {
        this.nomProjet = nomProjet;
        this.description = description;
    }

    public Projet(String nomProjet, String description, Date dateDebut, Date datelimiteEstimée, Date datefin, String statut, Double gainEstimé, Double gain, Double coutInvestissementestimé, Double coutInvestissement) {
        this.nomProjet = nomProjet;
        this.description = description;
        this.dateDebut = dateDebut;
        this.datelimiteEstimée = datelimiteEstimée;
        this.datefin = datefin;
        this.statut = statut;
        this.gainEstimé = gainEstimé;
        this.gain = gain;
        this.coutInvestissementestimé = coutInvestissementestimé;
        this.coutInvestissement = coutInvestissement;
        this.listUsers=new ArrayList<User>();
        this.listCompetence=new ArrayList<Competence>();
    }
    
    public Projet(int id, String nomProjet, String description, Date dateDebut, Date datelimiteEstimée, Date datefin, String statut, Double gainEstimé, Double gain, Double coutInvestissementestimé, Double coutInvestissement) {
        this.id = id;
        this.nomProjet = nomProjet;
        this.description = description;
        this.dateDebut = dateDebut;
        this.datelimiteEstimée = datelimiteEstimée;
        this.datefin = datefin;
        this.statut = statut;
        this.gainEstimé = gainEstimé;
        this.gain = gain;
        this.coutInvestissementestimé = coutInvestissementestimé;
        this.coutInvestissement = coutInvestissement;
    }

    public Double getCoutInvestissement() {
        return coutInvestissement;
    }

    public void setCoutInvestissement(Double coutInvestissement) {
        this.coutInvestissement = coutInvestissement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDatelimiteEstimée() {
        return datelimiteEstimée;
    }

    public void setDatelimiteEstimée(Date datelimiteEstimée) {
        this.datelimiteEstimée = datelimiteEstimée;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Double getGainEstimé() {
        return gainEstimé;
    }

    public void setGainEstimé(Double gainEstimé) {
        this.gainEstimé = gainEstimé;
    }

    public Double getGain() {
        return gain;
    }

    public void setGain(Double gain) {
        this.gain = gain;
    }

    public Double getCoutInvestissementestimé() {
        return coutInvestissementestimé;
    }

    public void setCoutInvestissementestimé(Double coutInvestissementestimé) {
        this.coutInvestissementestimé = coutInvestissementestimé;
    }

    public List<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    public List<Competence> getListCompetence() {
        return listCompetence;
    }

    public void setListCompetence(List<Competence> listCompetence) {
        this.listCompetence = listCompetence;
    }

    @Override
    public String toString() {
        String ch1= "Projet{" + "id=" + id + ", nomProjet=" + nomProjet + ", description=" + description + ", dateDebut=" + dateDebut + ", datelimiteEstim\u00e9e=" + datelimiteEstimée + ", datefin=" + datefin + ", statut=" + statut + ", gainEstim\u00e9=" + gainEstimé + ", gain=" + gain + ", coutInvestissementestim\u00e9=" + coutInvestissementestimé + ", coutInvestissement=" + coutInvestissement + '}';
        String ch2="";
        for(int i=0;i<listUsers.size();i++)
            ch2=ch2+" "+listUsers.get(i).toString();
        
        String ch3="";
        for(int i=0;i<listCompetence.size();i++)
                    ch3=ch3+" "+listCompetence.get(i).toString();

        String ch=ch1+" \n"+ch2+" \n"+ch3;
        return ch;
    }
    
    
    
    
    
    
}
