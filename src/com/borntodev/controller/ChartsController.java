/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.borntodev.controller;

import com.borntodev.entities.Projet;
import com.borntodev.services.ProjetService;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Hamza FAIZA
 */
public class ChartsController implements Initializable {

    @FXML
    private AnchorPane AnchorPane1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        BarChart barChart = createBarChart();
        TitledPane t2 = new TitledPane("Bar Chart", barChart);
        Accordion accordion = new Accordion();
        accordion.getPanes().add(t2);
        AnchorPane1.getChildren().add(accordion);
        
    }    
    
    public BarChart createBarChart(){
         String[] years = {"En attente","En cours","En repos","Terminé","Validé","Echec"};
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(years));
        NumberAxis yAxis = new NumberAxis("Nombre de projets", 0.0d, 10.0d, 1.0d);
        ProjetService ps=new ProjetService();
        List<Projet> lst=ps.getAll();
        int nbrEnCours=0;
        int nbrEnAttence=0;
        int nbrEnRepos=0;
        int nbrTermine=0;
        int nbrValide=0;
        int nbrEchec=0;
        for (int i = 0; i < lst.size(); i++) {
            if(lst.get(i).getStatut().equals("En cours"))
                nbrEnCours++;
            if(lst.get(i).getStatut().equals("En attente"))
                nbrEnAttence++;
            if(lst.get(i).getStatut().equals("En repos"))
                nbrEnRepos++;
            if(lst.get(i).getStatut().equals("Terminé"))
                nbrTermine++;
            if(lst.get(i).getStatut().equals("Validé et payé"))
                nbrValide++;
            if(lst.get(i).getStatut().equals("Echec"))
                nbrEchec++;
        }
        ObservableList<BarChart.Series> barChartData = FXCollections.observableArrayList(
            new BarChart.Series("En attence", FXCollections.observableArrayList(
               new BarChart.Data(years[0], nbrEnAttence)
            )),
            new BarChart.Series("En cours", FXCollections.observableArrayList(
               new BarChart.Data(years[1], nbrEnCours )
            )),
            new BarChart.Series("En repos", FXCollections.observableArrayList(
               new BarChart.Data(years[2], nbrEnRepos)
            )),
            new BarChart.Series("Terminé", FXCollections.observableArrayList(
               new BarChart.Data(years[3], nbrTermine)
            )),
            new BarChart.Series("Validé et payé", FXCollections.observableArrayList(
               new BarChart.Data(years[4], nbrValide)
            )),
            new BarChart.Series("Echec", FXCollections.observableArrayList(
               new BarChart.Data(years[5], nbrEchec)
            ))
        );
        return  new BarChart(xAxis, yAxis, barChartData, 25.0d);
    }
    
}
