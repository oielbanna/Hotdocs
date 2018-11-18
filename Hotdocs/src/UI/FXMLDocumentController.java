package UI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import detector.Detector;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author yasam
 */
public class FXMLDocumentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button exitB;
    @FXML
    private Button uploadB;
    @FXML
    private ImageView poster1;
    @FXML
    private ImageView bigP;
    @FXML
    private ImageView poster2;
    @FXML
    private AnchorPane window;
    private FileChooser fileChooser = new FileChooser();
    private FileChooser fileChooser2 = new FileChooser();
    private File file;
    Image image;
	ArrayList<File> list = new ArrayList();

    @FXML
    private void handleExitBAction(ActionEvent event) {

        System.exit(0);
    }

    @FXML
	private void handleSendBAction(ActionEvent event) throws IOException {
		new Detector(list);
    }

    @FXML
    private void handleUploadBAction(ActionEvent event) {

        Image image1;
        Image image2;
        //fileChooser.setCurrentDirectory(new File("./images"));
        File file = fileChooser.showOpenDialog(exitB.getScene().getWindow());
        
        while (file != null) {
            if (file != null) {
                try {
                    image1 = SwingFXUtils.toFXImage(ImageIO.read(file), null);
                    poster1.setImage(image1);
                    
                } catch (IOException e) {
                }
                
				list.add(file);
            }

            file = fileChooser.showOpenDialog(exitB.getScene().getWindow());
        }
        // message.setText(" image opened successfully");
        
        
    }

//message.setText("an IOException occured , unable to open the image");
    @FXML
    public void setDefaultImage() {
        File logoFile = new File("images/spotify.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        try {
            poster1.setImage(logoImage);

        } catch (Exception i) {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File logoFile = new File("images/bigP.gif");
        image = new Image(logoFile.toURI().toString());
        try {
            bigP.setImage(image);

        } catch (Exception i) {
        }
    }

}
