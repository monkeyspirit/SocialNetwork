package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import versione1.SocialNetwork;

import java.io.IOException;

public class LoginController {

    // ~~~~~ Sample Stage ~~~~~~~~~~~~~

    @FXML
    private Button login;
    @FXML
    private CheckBox checkRg;
    @FXML
    private PasswordField psswd;
    @FXML
    private TextField usrname;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    private SocialNetwork social;
    public void setSocialNetwork(SocialNetwork social) { this.social = social; }


    @FXML
    private void initialize() throws IOException {

        checkRg.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

               if(checkRg.isSelected()){
                   login.setText("Registrati");
               }
               else {
                   login.setText("Accedi");
               }

            }
        });


        System.out.println("Carico la View login...");


    }



    public void checkAccess(ActionEvent event){

    }


    public void loadSecond() throws IOException {

            // Carico il file per la grafica
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("sample.fxml"));
            SampleController controller = new SampleController();

            // Imposto il controller
            loader.setController(controller);

            // Passo a controller il riferimento a social network
            controller.setSocialNetwork(social);


            Stage primaryStage = Main.getStage();

            // Imposto lo stage e la scene principali
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root, 600, 400);
            primaryStage.setTitle("Bacheca");
            primaryStage.setScene(scene);
            primaryStage.show();
        }


}
