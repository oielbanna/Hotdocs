package UI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author yasam
 */
public class FXMLMainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ImageView poster1;
    @FXML
    private ImageView poster2;
    @FXML
    private ImageView poster3;
    @FXML
    private ImageView bigP;
   
    Image image1, image2, image3, image4;
    
    @FXML
    private void handleImageBAction(ActionEvent event) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        Driver.stage.setScene(scene);
        Driver.stage.show();
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File logoFile = new File("images/poster1.png");
        image1 = new Image(logoFile.toURI().toString());
        try {
            poster1.setImage(image1);

        } catch (Exception i) {
        }
          logoFile = new File("images/poster2.png");
        image2 = new Image(logoFile.toURI().toString());
        try {
            poster2.setImage(image2);

        } catch (Exception i) {
        }
        logoFile = new File("images/poster3.png");
        image3 = new Image(logoFile.toURI().toString());
        try {
            poster3.setImage(image3);

        } catch (Exception i) {
        }
        logoFile = new File("images/bigP.gif");
        image4 = new Image(logoFile.toURI().toString());
        try {
            bigP.setImage(image4);

        } catch (Exception i) {
        }
    }    
    
}
