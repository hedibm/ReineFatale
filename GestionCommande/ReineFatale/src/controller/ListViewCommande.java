/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller ;



import entity.Produit;
import javafx.scene.control.ListCell;

/**
 *
 * @author dell
 */
public class ListViewCommande extends ListCell<Produit> {
    
    
     @Override
     public void updateItem(Produit e, boolean empty)
    {
        super.updateItem(e,empty);
        if(e != null)
        {
            
            CommmandetemController data = new CommmandetemController();
            data.setInfo(e);
            setGraphic(data.getBox());
        }
    }
    
}
