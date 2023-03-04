/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Produit;
import entity.commande_panier;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import service.Service;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ShowMesCommandeController implements Initializable {

    @FXML
    private ListView<Produit> listView;
    
    ObservableList<Produit> data;
    
    Service ds = new Service();
    @FXML
    private Label prix;
    @FXML
    private Label etat;
    @FXML
    private Button Annuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             
        
        
        List<Produit> listProduit = new ArrayList<>();
        
        prix.setText(String.valueOf(ds.get_Commande(ListeCommandeController.ide).getPrix()));
        etat.setText(ds.get_Commande(ListeCommandeController.ide).getEtat());
        
        try {
            for (commande_panier p : ds.getAllCommandePanier(ShowCommandeClientController.idcommande)){
                
                
                listProduit.add(ds.get_produit(p.getId_produit()));
            }
            
         ObservableList<Produit> observableList = FXCollections.observableList(listProduit);
        data = (ObservableList<Produit>) observableList;
        listView.setItems(data);
        listView.setCellFactory((ListView<Produit> param) -> new ListViewProduit());
        } catch (SQLDataException ex) {
            Logger.getLogger(ShowCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void Annuler(ActionEvent event) throws SQLDataException {
        
               ds.UpdateCommandeEtat(ListeCommandeController.ide, "Annuler") ;
               Parent root;
               try {
               root = FXMLLoader.load(getClass().getResource("/gui/ShowMesCommande.fxml"));
               Stage myWindow = (Stage) listView.getScene().getWindow();
               Scene sc = new Scene(root);
               myWindow.setScene(sc);
               myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
               myWindow.show();
               } catch (IOException ex) {
               Logger.getLogger(ShowProduitController.class.getName()).log(Level.SEVERE, null, ex);
               }
        
        
    }

    
}
