/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.borntodev.utils;

/**
 *
 * @author Hamza FAIZA
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataSource {
    
    
    private String url="jdbc:mysql://localhost:3306/socialpro";
    private String user="root";
    private String pwd="";
    private Connection cnx;
    private static DataSource instance;
    
    private DataSource() {
        try {
            cnx= DriverManager.getConnection(url,user,pwd);
//            System.out.println("connected");
        } catch (SQLException ex) {
//                        System.out.println("not connected");
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getCnx() {
        return cnx;
    }
    
    public static DataSource getInstance(){
        if(instance==null)
        instance =new DataSource();
        
        return instance;
    }
    
    
    
    
}
