/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.borntodev.controller;

import com.borntodev.entities.Competence;
import com.borntodev.entities.Projet;
import com.borntodev.entities.User;
import com.borntodev.services.ProjetService;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Hamza FAIZA
 */
public class AfficherProjetsController implements Initializable {

    @FXML
    private AnchorPane AnchorPane1;
    @FXML
    private TableView<Projet> tableProjet = new TableView<Projet>();

    private final ObservableList<Projet> data = FXCollections.observableArrayList();
    @FXML
    private Label coutInv;
    @FXML
    private Label datefines;
    @FXML
    private Label datefin;
    @FXML
    private Label stat;
    @FXML
    private Label gainest;
    @FXML
    private Label gain;
    @FXML
    private Label coutInvest;
    @FXML
    private Label datedeb;
    @FXML
    private Label desc;
    @FXML
    private Label nom;
    @FXML
    private AnchorPane AnchorPaneDesc;
    @FXML
    private Button btnModif;
    @FXML
    private Button btnSupp;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtdesc;
    @FXML
    private TextField txtgainest;
    @FXML
    private TextField txtgain;
    @FXML
    private TextField txtcoutest;
    @FXML
    private TextField txtcout;
    @FXML
    private ComboBox<String> cmbStat;
    @FXML
    private DatePicker calDateDeb;
    @FXML
    private DatePicker calDateFinEst;
    @FXML
    private DatePicker calDateFin;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnEnreg;
    @FXML
    private Button btnAffecter;
    @FXML
    private Button btnCompetence;
    @FXML
    private AnchorPane AnchorPaneAffect;
    @FXML
    private Label txtIntituléAffect;
    @FXML
    private ComboBox<User> cmbEmp;
    @FXML
    private Button btnAff;
    @FXML
    private AnchorPane AnchorPaneCompetence;
    @FXML
    private Label lblIntituléComp;
    @FXML
    private ComboBox<?> cmbComp;
    @FXML
    private Button btnComp;
    @FXML
    private TableView<Competence> tableCompetence;
    @FXML
    private Button btnSuppComp;
    @FXML
    private TableView<User> tableUsers;
    @FXML
    private Button btnSuppUser;
    @FXML
    private AnchorPane AnchorPaneStauts;
    @FXML
    private Label lblEnAttente;
    @FXML
    private Label lblEnCours;
    @FXML
    private Label lblEnRepos;
    @FXML
    private Label lblTerminé;
    @FXML
    private Label lblValide;
    @FXML
    private Label lblEchec;
    @FXML
    private Label lblaux;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        initTableProjet();
        initSettings();
        describe();
        initStatusBar();
    }

    public void initTableProjet() //initialize projects table
    {

        ProjetService ps = new ProjetService();
        data.addAll(ps.getAll());
        TableColumn nomProjet = new TableColumn("Intitulé");
        nomProjet.setCellValueFactory(new PropertyValueFactory("nomProjet"));
        ObservableList listNom = FXCollections.observableArrayList();
        for (int i = 0; i < data.size(); i++) {
            listNom.add(data.get(i).getNomProjet());
        }
        nomProjet.setCellFactory(ComboBoxTableCell.forTableColumn(listNom));
        nomProjet.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Projet, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Projet, String> p) {
                p.getRowValue().setNomProjet(p.getNewValue());
                ps.update(p.getRowValue().getId(), p.getRowValue());
            }});
        TableColumn description = new TableColumn("Description");
        description.setCellValueFactory(new PropertyValueFactory("description"));
        description.setCellFactory(TextFieldTableCell.forTableColumn());
        description.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Projet, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Projet, String> p) {
                p.getRowValue().setDescription(p.getNewValue());
                ps.update(p.getRowValue().getId(), p.getRowValue());
            }});
//        TableColumn dateDeb = new TableColumn("Date de début");
//        dateDeb.setCellValueFactory(new PropertyValueFactory("dateDebut"));
//        dateDeb.setCellFactory(cel.forTableColumn());
//        dateDeb.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Projet, Str>>() {
//        @Override
//        public void handle(TableColumn.CellEditEvent<Projet, String> p) {
//            p.getRowValue().setDateDebut(Date.valueOf(p.getNewValue()));
//            ps.update(p.getRowValue().getId(), p.getRowValue());
//        }
//    });
//        TableColumn datefinEst = new TableColumn("Date de fin éstimée");
//        datefinEst.setCellValueFactory(new PropertyValueFactory("datelimiteEstimée"));
//        TableColumn dateFin = new TableColumn("Date de fin");
//        dateFin.setCellValueFactory(new PropertyValueFactory("datefin"));
        TableColumn statut = new TableColumn("Statut");
        statut.setCellValueFactory(new PropertyValueFactory("statut"));
        ObservableList listStatuts = FXCollections.observableArrayList();
        listStatuts.add("En attente");
        listStatuts.add("En cours");
        listStatuts.add("En repos");
        listStatuts.add("Terminé");
        listStatuts.add("Validé");
        listStatuts.add("Echec");
        statut.setCellFactory(ComboBoxTableCell.forTableColumn(listStatuts));
        statut.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Projet, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Projet, String> p) {
                p.getRowValue().setStatut(p.getNewValue());
                ps.update(p.getRowValue().getId(), p.getRowValue());
            }});
//        TableColumn gainEstimé = new TableColumn("Gain éstimé");
//        gainEstimé.setCellValueFactory(new PropertyValueFactory("gainEstimé"));
//        gainEstimé.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
//        gainEstimé.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Projet, Double>>() {
//            @Override
//            public void handle(TableColumn.CellEditEvent<Projet, Double> p) {
//                p.getRowValue().setGainEstimé(p.getNewValue());
//                ps.update(p.getRowValue().getId(), p.getRowValue());
//            }
//        });
//        TableColumn gain = new TableColumn("Gain");
//        gain.setCellValueFactory(new PropertyValueFactory("gain"));
//        TableColumn coutInvestissementestimé = new TableColumn("Coût investissement estimé");
//        coutInvestissementestimé.setCellValueFactory(new PropertyValueFactory("coutInvestissementestimé"));
//        TableColumn coutInvestissement = new TableColumn("Coût investissement");
//        coutInvestissement.setCellValueFactory(new PropertyValueFactory("coutInvestissement"));
//        TableColumn modifier=new TableColumn("Modifier");
//        ImageView iv=new ImageView(new Image(""));
        tableProjet.setEditable(true);
        tableProjet.getColumns().addAll(nomProjet, description, statut/*, dateDeb, datefinEst, dateFin, statut, gainEstimé, gain, coutInvestissementestimé, coutInvestissement*/);
        tableProjet.setItems(data);
        tableProjet.setPrefSize(425, 290);
    }

    public void describe() //show description of selected project 
    {

        tableProjet.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                AnchorPaneDesc.setVisible(Boolean.TRUE);
                AnchorPaneDesc.setStyle("-fx-background-color: " + "red");
                AnchorPaneDesc.setLayoutX(600);
                AnchorPaneDesc.setLayoutY(40);
                
                initButtons();
                Projet p = tableProjet.getSelectionModel().getSelectedItem();
                nom.setText(p.getNomProjet());
                desc.setText(p.getDescription());
                datedeb.setText(p.getDateDebut().toString());
                datefines.setText(p.getDatelimiteEstimée().toString());
                datefin.setText(p.getDatefin().toString());
                stat.setText(p.getStatut());
                gainest.setText(p.getGainEstimé().toString());
                gain.setText(p.getGain().toString());
                coutInvest.setText(p.getCoutInvestissementestimé().toString());
                coutInv.setText(p.getCoutInvestissement().toString());
            }
        });
    }

    public void initButtons() //initialize buttons (onActions)
    {
        btnModif.setVisible(Boolean.TRUE);
        btnModif.setLayoutX(700);
        btnModif.setLayoutY(500);
        btnSupp.setVisible(Boolean.TRUE);
        btnSupp.setLayoutX(800);
        btnSupp.setLayoutY(500);
        btnAjout.setVisible(Boolean.TRUE);
        btnAjout.setLayoutX(600);
        btnAjout.setLayoutY(500);
        btnAffecter.setVisible(Boolean.TRUE);
        btnAffecter.setLayoutX(900);
        btnAffecter.setLayoutY(500);
        btnCompetence.setVisible(Boolean.TRUE);
        btnCompetence.setLayoutX(850);
        btnCompetence.setLayoutY(550);
        btnValider.setVisible(Boolean.FALSE);
        ProjetService ps=new ProjetService();
        
        btnModif.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showTextFields();
                Projet p = tableProjet.getSelectionModel().getSelectedItem();
                fillTextFields(p);
                btnValider.setVisible(Boolean.TRUE);
            }
        });
        btnValider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Projet p = tableProjet.getSelectionModel().getSelectedItem();
                p.setNomProjet(txtnom.getText());
                p.setDescription(txtdesc.getText());
                p.setDateDebut(Date.valueOf(calDateDeb.getValue()));
                p.setDatelimiteEstimée(Date.valueOf(calDateFinEst.getValue()));
                p.setDatefin(Date.valueOf(calDateFin.getValue()));
                p.setStatut(cmbStat.getValue());
                p.setGainEstimé(Double.parseDouble(txtgainest.getText()));
                p.setGain(Double.parseDouble(txtgain.getText()));
                p.setCoutInvestissementestimé(Double.parseDouble(txtcoutest.getText()));
                p.setCoutInvestissement(Double.parseDouble(txtcout.getText()));
                ps.update(p.getId(), p);
                showLabels(p);
//                data.clear();
//                tableProjet=null;
//                initTableProjet();
            }
        });
        btnSupp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Projet p = tableProjet.getSelectionModel().getSelectedItem();
                ps.remove(p.getId());
                System.out.println("updated");
            }
        });
        btnAjout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showTextFields();
                ObservableList listStatuts = FXCollections.observableArrayList();
                listStatuts.add("En attente");
                listStatuts.add("En cours");
                listStatuts.add("En repos");
                listStatuts.add("Terminé");
                listStatuts.add("Validé");
                listStatuts.add("Echec");
                cmbStat.setItems(listStatuts);
                btnEnreg.setVisible(Boolean.TRUE);
            }});
        btnEnreg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Projet p=new Projet();
                p.setNomProjet(txtnom.getText());
                p.setDescription(txtdesc.getText());
                p.setDateDebut(Date.valueOf(calDateDeb.getValue()));
                p.setDatelimiteEstimée(Date.valueOf(calDateFinEst.getValue()));
                p.setDatefin(Date.valueOf(calDateFin.getValue()));
                p.setStatut(cmbStat.getValue());
                p.setGainEstimé(Double.parseDouble(txtgainest.getText()));
                p.setGain(Double.parseDouble(txtgain.getText()));
                p.setCoutInvestissementestimé(Double.parseDouble(txtcoutest.getText()));
                p.setCoutInvestissement(Double.parseDouble(txtcout.getText()));
                ps.add(p);
                showLabels(p);
            }});
        btnAffecter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AnchorPaneAffect.setVisible(Boolean.TRUE);
                AnchorPaneAffect.setStyle("-fx-background-color: " + "yellow");
                AnchorPaneAffect.setLayoutX(30);
                AnchorPaneAffect.setLayoutY(350);
                AnchorPaneAffect.setPrefSize(350, 200);
                txtIntituléAffect.setText(nom.getText());
                ObservableList<User> usersDate = FXCollections.observableArrayList();
                ////////////////// colonnes de "Équipe" doublont /////////////////////
                TableColumn users = new TableColumn("Équipe");
                users.setCellValueFactory(new PropertyValueFactory("username"));
                usersDate.addAll(ps.getUsersByProject(txtIntituléAffect.getText()));
                tableUsers.setEditable(true);
                tableUsers.getColumns().addAll(users);
                tableUsers.setItems(usersDate);
                tableUsers.setPrefSize(150, 100);
                
                
                ObservableList listUsers = FXCollections.observableArrayList();
                List<User> lst=ps.seggestUsers(txtIntituléAffect.getText());
                for (int i = 0; i < lst.size(); i++) {
                    listUsers.add(lst.get(i));
                }
                cmbEmp.setItems(listUsers);
                btnAff.setVisible(Boolean.TRUE);
            }});
        btnAff.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                User u=new User();
                u.setUsername(cmbEmp.getValue().getUsername());
                Projet p=new Projet();
                p.setNomProjet(txtIntituléAffect.getText());
                ps.addUserToProjet(p, u);
            }});
        btnCompetence.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AnchorPaneAffect.setVisible(Boolean.FALSE);
                AnchorPaneCompetence.setVisible(Boolean.TRUE);
                AnchorPaneCompetence.setStyle("-fx-background-color: " + "yellow");
                AnchorPaneCompetence.setLayoutX(30);
                AnchorPaneCompetence.setLayoutY(350);
                AnchorPaneCompetence.setPrefSize(350, 200);
                cmbComp.setItems(null);
                lblIntituléComp.setText(nom.getText());
                ObservableList<Competence> Competencedata = FXCollections.observableArrayList();
                Competencedata.addAll(ps.getCompetencesByProject(lblIntituléComp.getText()));
                TableColumn comp = new TableColumn("Competence");
                comp.setCellValueFactory(new PropertyValueFactory("description"));
                tableCompetence.setEditable(true);
                tableCompetence.getColumns().addAll(comp);
                tableCompetence.setItems(Competencedata);
                tableCompetence.setPrefSize(90, 100);
                ObservableList listCompetence = FXCollections.observableArrayList();
                List<Competence> lst=new ArrayList<Competence>();
                lst=ps.getCompetences();
                for (int i = 0; i < lst.size(); i++) {
                    listCompetence.add(lst.get(i));
                    cmbComp.setItems(listCompetence);
                }
            }});
        btnComp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Competence c=new Competence();
                c.setDescription(cmbComp.getValue().toString());
                Projet p=new Projet();
                p.setNomProjet(lblIntituléComp.getText());
                ps.addCompetenceToProjet(p, c);
            }});
        btnSuppComp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ps.removeCompetenceFromProject(lblIntituléComp.getText(), tableCompetence.getSelectionModel().getSelectedItem().getDescription());
            }});
        btnSuppUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ps.removeUserFromProject(txtIntituléAffect.getText(), tableUsers.getSelectionModel().getSelectedItem().getUsername());
            }});
        
    }

    public void fillTextFields(Projet p) //set values into TextFields
    {
                txtnom.setText(p.getNomProjet());
                txtdesc.setText(p.getDescription());
                calDateDeb.setValue(LocalDate.parse(p.getDateDebut().toString()));
                calDateFinEst.setValue(LocalDate.parse(p.getDatelimiteEstimée().toString()));
                calDateFin.setValue(LocalDate.parse(p.getDatefin().toString()));
                ObservableList listStatuts = FXCollections.observableArrayList();
                listStatuts.add("En attente");
                listStatuts.add("En cours");
                listStatuts.add("En repos");
                listStatuts.add("Terminé");
                listStatuts.add("Validé");
                listStatuts.add("Echec");
                cmbStat.setItems(listStatuts);
                cmbStat.setValue(p.getStatut());
                txtgainest.setText(p.getGainEstimé().toString());
                txtgain.setText(p.getGain().toString());
                txtcoutest.setText(p.getCoutInvestissementestimé().toString());
                txtcout.setText(p.getCoutInvestissement().toString());
    }
    
    public void showTextFields() //show TextFields and hide labels
    {

        txtnom.setVisible(Boolean.TRUE);
        txtdesc.setVisible(Boolean.TRUE);
        calDateDeb.setVisible(Boolean.TRUE);
        calDateFinEst.setVisible(Boolean.TRUE);
        calDateFin.setVisible(Boolean.TRUE);
        cmbStat.setVisible(Boolean.TRUE);
        txtgainest.setVisible(Boolean.TRUE);
        txtgain.setVisible(Boolean.TRUE);
        txtcoutest.setVisible(Boolean.TRUE);
        txtcout.setVisible(Boolean.TRUE);
        btnValider.setVisible(Boolean.TRUE);
        
        nom.setVisible(Boolean.FALSE);
        desc.setVisible(Boolean.FALSE);
        datedeb.setVisible(Boolean.FALSE);
        datefines.setVisible(Boolean.FALSE);
        datefin.setVisible(Boolean.FALSE);
        stat.setVisible(Boolean.FALSE);
        gainest.setVisible(Boolean.FALSE);
        gain.setVisible(Boolean.FALSE);
        coutInvest.setVisible(Boolean.FALSE);
        coutInv.setVisible(Boolean.FALSE);
    }
    
    public void showLabels(Projet p) //show labels and hide TextFields
    {
        txtnom.setVisible(Boolean.FALSE);
        txtdesc.setVisible(Boolean.FALSE);
        calDateDeb.setVisible(Boolean.FALSE);
        calDateFinEst.setVisible(Boolean.FALSE);
        calDateFin.setVisible(Boolean.FALSE);
        cmbStat.setVisible(Boolean.FALSE);
        txtgainest.setVisible(Boolean.FALSE);
        txtgain.setVisible(Boolean.FALSE);
        txtcoutest.setVisible(Boolean.FALSE);
        txtcout.setVisible(Boolean.FALSE);
        btnValider.setVisible(Boolean.FALSE);
        ProjetService ps=new ProjetService();
        Projet p2=ps.get(p.getId());
        
        nom.setVisible(Boolean.TRUE);
        desc.setVisible(Boolean.TRUE);
        datedeb.setVisible(Boolean.TRUE);
        datefines.setVisible(Boolean.TRUE);
        datefin.setVisible(Boolean.TRUE);
        stat.setVisible(Boolean.TRUE);
        gainest.setVisible(Boolean.TRUE);
        gain.setVisible(Boolean.TRUE);
        coutInvest.setVisible(Boolean.TRUE);
        coutInv.setVisible(Boolean.TRUE);
        
        nom.setText(p2.getNomProjet());
        desc.setText(p2.getDescription());
        datedeb.setText(p2.getDateDebut().toString());
        datefines.setText(p2.getDatelimiteEstimée().toString());
        datefin.setText(p2.getDatefin().toString());
        stat.setText(p2.getStatut());
        gainest.setText(p2.getGainEstimé().toString());
        gain.setText(p2.getGain().toString());
        coutInvest.setText(p2.getCoutInvestissementestimé().toString());
        coutInv.setText(p2.getCoutInvestissement().toString());
        
    }

    public void initSettings() //hide unused buttons
    {
        AnchorPane1.setLayoutY(30);
        AnchorPane1.setPrefSize(1000, 600);
        AnchorPaneDesc.setVisible(Boolean.FALSE);
        AnchorPaneAffect.setVisible(Boolean.FALSE);
        AnchorPaneCompetence.setVisible(Boolean.FALSE);
        
        btnModif.setVisible(Boolean.FALSE);
        btnSupp.setVisible(Boolean.FALSE);
        btnAjout.setVisible(Boolean.FALSE);
        btnEnreg.setVisible(Boolean.FALSE);
        btnCompetence.setVisible(Boolean.FALSE);
        btnAffecter.setVisible(Boolean.FALSE);
        btnAff.setVisible(Boolean.TRUE);
        
        txtnom.setVisible(Boolean.FALSE);
        txtdesc.setVisible(Boolean.FALSE);
        txtgainest.setVisible(Boolean.FALSE);
        txtgain.setVisible(Boolean.FALSE);
        txtcoutest.setVisible(Boolean.FALSE);
        txtcout.setVisible(Boolean.FALSE);
        cmbStat.setVisible(Boolean.FALSE);
        calDateDeb.setVisible(Boolean.FALSE);
        calDateFin.setVisible(Boolean.FALSE);
        calDateFinEst.setVisible(Boolean.FALSE);
//        btnValider.setVisible(Boolean.FALSE);
    }

    public void initStatusBar(){
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
        
//        lblaux.setText("123");
        lblEnAttente.setText(lblEnAttente.getText()+nbrEnAttence);
        lblEnCours.setText(lblEnCours.getText()+nbrEnCours);
        lblEnRepos.setText(lblEnRepos.getText()+nbrEnRepos);
        lblTerminé.setText(lblTerminé.getText()+nbrTermine);
        lblValide.setText(lblValide.getText()+nbrValide);
        lblEchec.setText(lblEchec.getText()+nbrEchec);
    }
    
    public static void main(String[] args) {

    }

}
