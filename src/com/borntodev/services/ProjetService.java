/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.borntodev.services;

import com.borntodev.entities.Competence;
import com.borntodev.entities.Projet;
import com.borntodev.entities.User;
import com.borntodev.interfaces.IServices;
import com.borntodev.test.MainTest;
import com.borntodev.utils.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hamza FAIZA
 */
public class ProjetService implements IServices <Projet>{

    DataSource ds;
    PreparedStatement ste;
    
    public ProjetService() {
        ds  =DataSource.getInstance();
    }
    
    @Override
    public void add(Projet p) {
        try {
            String req="insert into projet values(null,?,?,?,?,?,?,?,?,?,?);";
            ste=ds.getCnx().prepareStatement(req);
            ste.setString(1, p.getNomProjet());
            ste.setString(2, p.getDescription());
            ste.setDate(3, p.getDateDebut());
            ste.setDate(4, p.getDatelimiteEstimée());
            ste.setDate(5, p.getDatefin());
            ste.setString(6, p.getStatut());
            ste.setDouble(7, p.getGainEstimé());
            ste.setDouble(8, p.getGain());
            ste.setDouble(9, p.getCoutInvestissementestimé());
            ste.setDouble(10, p.getCoutInvestissement()); 
            ste.executeUpdate();
//            System.out.println("success !!!");
        } catch (SQLException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(int id) {
        try {
            String req="delete from projet where id = ?";
            ste=ds.getCnx().prepareStatement(req);
            ste.setInt(1, id);
            
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(int id, Projet p) {
        try {
            String req="UPDATE `projet` SET `nom_projet`=?,`description`=?,`date_deb_projet`=?,`date_lim_estime_projet`=?,`date_fin_projet`=?,`statut`=?,`gain_estime`=?,`gain`=?,`cout_investissement_estime`=?,`cout_investissement`=? WHERE id=?";
            ste=ds.getCnx().prepareStatement(req);
            ste.setString(1, p.getNomProjet());
            ste.setString(2, p.getDescription());
            ste.setDate(3, p.getDateDebut());
            ste.setDate(4, p.getDatelimiteEstimée());
            ste.setDate(5, p.getDatefin());
            ste.setString(6, p.getStatut());
            ste.setDouble(7, p.getGainEstimé());
            ste.setDouble(8, p.getGain());
            ste.setDouble(9, p.getCoutInvestissementestimé());
            ste.setDouble(10, p.getCoutInvestissement()); 
            ste.setInt(11, id);
            
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Projet> getAll() {
        List<Projet> lstProjet=new ArrayList<Projet>();
        try {
            String req="select * from projet";
            ste=ds.getCnx().prepareStatement(req);
                        
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Projet p=new Projet();
                p.setId(rs.getInt("id"));
                p.setNomProjet(rs.getString("nom_projet"));
                p.setDescription(rs.getString("description"));
                p.setDateDebut(rs.getDate("date_deb_projet"));
                p.setDatelimiteEstimée(rs.getDate("date_lim_estime_projet"));
                p.setDatefin(rs.getDate("date_fin_projet"));
                p.setStatut(rs.getString("statut"));
                p.setGainEstimé(rs.getDouble("gain_estime"));
                p.setGain(rs.getDouble("gain"));
                p.setCoutInvestissementestimé(rs.getDouble("cout_investissement_estime"));
                p.setCoutInvestissement(rs.getDouble("cout_investissement"));
                
                lstProjet.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstProjet;
    }

    public List<Competence> getCompetencesByProject(String nomProjet) {
        List<Competence> lstCompetences=new ArrayList<Competence>();
        try {
//            String req="select c.id,c.description from competence c,projet p,competence_projet cp where p.nom_projet=cp.nom_projet and cp.competence_demande=c.description and p.nom_projet =?";
            String req="select competence_demande from competence_projet where nom_projet=?";
            ste=ds.getCnx().prepareStatement(req);
            ste.setString(1, nomProjet);
            ResultSet rs=ste.executeQuery(); 
            
            while (rs.next()) {
                Competence c=new Competence();
                c.setDescription(rs.getString("competence_demande"));
                lstCompetences.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lstCompetences;

    }

    public List<User> getUsersByProject(String nomProjet) 
    {
        List<User> lstUsers=new ArrayList<User>();
        try {
            String req="select u.id,u.username from fos_user u,projet p,user_projet up where p.nom_projet=up.nom_projet and u.username=up.user_name and p.nom_projet =?";
            ste=ds.getCnx().prepareStatement(req);
            ste.setString(1, nomProjet);
            ResultSet rs=ste.executeQuery(); 
            
            while (rs.next()) {
                User u=new User(rs.getInt("id"),rs.getString("username"));
                lstUsers.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjetService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstUsers;
    }

    public void removeCompetenceFromProject(String nom_projet, String competence) {
        try {
            String req="delete from competence_projet where nom_projet = ? and competence_demande=?";
            ste=ds.getCnx().prepareStatement(req);
            ste.setString(1, nom_projet);
            ste.setString(2, competence);
            
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeUserFromProject(String nom_projet, String username) {
        try {
            String req="delete from user_projet where nom_projet = ? and user_name=?";
            ste=ds.getCnx().prepareStatement(req);
            ste.setString(1, nom_projet);
            ste.setString(2, username);
            
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<User> seggestUsers(String nomProjet) {
//        List<User> lstUsers=this.getUsersByProject(nomProjet);
//        for(int i=0;i<lstUsers.size();i++){
        List<User>lstUsers=new ArrayList<User>();
        try {
//            String req2="select competence_demande from competence_projet where nom_projet = ? INERSECT select competence from competence_user where username=?";
            String req2="select username from competence_user where competence = any(select competence_demande from competence_projet where nom_projet=?)";
            ste=ds.getCnx().prepareStatement(req2);
            ste.setString(1, nomProjet);
//            ste.setString(2, lstUsers.get(i).getUsername());
            
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                User u=new User();
                u.setUsername(rs.getString("username"));
                lstUsers.add(u);
            }
//            int size=0;
//            if(rs != null){
//                rs.last();
//                        size=rs.getRow();
//                        if(size<0){
//                            lstUsers.remove(i);
//                        }
//                }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProjetService.class.getName()).log(Level.SEVERE, null, ex);
        }
//    }
        return lstUsers;
    }

    public void addCompetenceToProjet(Projet p, Competence c) {
        try {
            String req="insert into competence_projet values(null,?,?);";
            ste=ds.getCnx().prepareStatement(req);
            ste.setString(1, p.getNomProjet());
            ste.setString(2, c.getDescription());
             
            ste.executeUpdate();
//            System.out.println("success !!!");
        } catch (SQLException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addUserToProjet(Projet p, User u) {
        try {
            String req="insert into user_projet values(null,?,?);";
            ste=ds.getCnx().prepareStatement(req);
            ste.setString(1, p.getNomProjet());
            ste.setString(2, u.getUsername());
            
            ste.executeUpdate();
//            System.out.println("success !!!");
        } catch (SQLException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Projet get(int id) {
        Projet p=new Projet();
        try {
            String req="select * from projet where id=?";
            ste=ds.getCnx().prepareStatement(req);
            ste.setInt(1, id);
                        
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setNomProjet(rs.getString("nom_projet"));
                p.setDescription(rs.getString("description"));
                p.setDateDebut(rs.getDate("date_deb_projet"));
                p.setDatelimiteEstimée(rs.getDate("date_lim_estime_projet"));
                p.setDatefin(rs.getDate("date_fin_projet"));
                p.setStatut(rs.getString("statut"));
                p.setGainEstimé(rs.getDouble("gain_estime"));
                p.setGain(rs.getDouble("gain"));
                p.setCoutInvestissementestimé(rs.getDouble("cout_investissement_estime"));
                p.setCoutInvestissement(rs.getDouble("cout_investissement"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    
    public List<User> getUsers(){
        List<User> lstUsers=new ArrayList<User>();
        try {
            String req2="select * from fos_user ";
            ste=ds.getCnx().prepareStatement(req2);
                        
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                User u=new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                lstUsers.add(u);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProjetService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return lstUsers;
    }
    
    public List<Competence> getCompetences(){
        List<Competence> lstCompetence=new ArrayList<Competence>();
        try {
            String req2="select * from competence ";
            ste=ds.getCnx().prepareStatement(req2);
                        
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Competence c=new Competence();
                c.setId(rs.getInt("id"));
                c.setDescription(rs.getString("description"));
                lstCompetence.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProjetService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return lstCompetence;
    }
    

   
    
}
