package sample;

import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utilities.FileUtility;
import versione1.SocialNetwork;
import versione1.User;

import java.io.IOException;

public class LoginController {

    // ~~~~~ Sample Stage ~~~~~~~~~~~~~

    @FXML
    private Button loginBtn;
    @FXML
    private CheckBox checkRgCB;
    @FXML
    private TextField usrnameTF;
    @FXML
    private Label errorLbl;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    public static final String MISSUSERNAME = "Attenzione: inserire un nome utente";
    public static final String ERRALREADYUSE = "Attenzione: il nome utente esiste";
    public static final String ERRNORUSE = "Attenzione: il nome utente non esiste";
    private SocialNetwork social;
    private User sessionUser;
    public void setSocialNetwork(SocialNetwork social) { this.social = social; }


    /**
     * Il metodo serve per inizializzare la finstra relativa al login, il pulsante "checkRg" serve per
     * sapere se l'utente è nuovo o già registrato in caso venga cliccato il testo sul bottone cambia
     * @throws IOException
     */
    @FXML
    private void initialize() throws IOException {

        checkRgCB.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

               if(checkRgCB.isSelected()){
                   loginBtn.setText("Registrati");
               }
               else {
                   loginBtn.setText("Accedi");
               }

            }
        });


        System.out.println("Carico la View login...");


    }



    public void checkAccess() throws IOException {

        if(usrnameTF.getText().isEmpty()){
            errorLbl.setTextFill(Color.RED);
            errorLbl.setText(MISSUSERNAME);
        }
        else {
            String accessName = usrnameTF.getText();

            //Se checkRGCB è selezionato vuol dire che sto inserendo un nuovo utente
            if(checkRgCB.isSelected()){
                if(social.doesUserExist(accessName) == false){
                    sessionUser = new User(accessName);
                    social.registerNewUser(sessionUser); //registro un nuovo utente
                    social.loginUser(sessionUser); //loggo l'utente creato (potremmo accorpare i due metodi in uno)
                    loadSecond();
                    System.out.println("Utente creato: " + social.getLoggedUser().getUsername());
                }
                else{
                    errorLbl.setTextFill(Color.RED);
                    errorLbl.setText(ERRALREADYUSE);
                }
            }
            else {
                if(social.doesUserExist(accessName) == false){
                    errorLbl.setTextFill(Color.RED);
                    errorLbl.setText(ERRNORUSE);
                }
                else {
                    sessionUser = social.findUserByName(accessName);
                    social.loginUser(sessionUser); //imposto l'utente attualmente loggato
                    loadSecond();
                    System.out.println("Utente loggato: " + social.getLoggedUser().getUsername());

                }

            }
        }


    }


    /**
     * Il metodo serve per lanciare la nuova finestra dopo l'accesso o la registrazione con controllo delle credenziali
     * @throws IOException
     */
    public void loadSecond() throws IOException {

            // Carico il file per la grafica
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("sample.fxml"));
            SampleController controller = new SampleController();

            // Imposto il controller
            loader.setController(controller);

            // Passo a controller il riferimento a social network
            controller.setSocialNetwork(social);
            controller.setSessionUser(sessionUser);

            Stage primaryStage = Main.getStage();

            // Imposto lo stage e la scene principali
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root, 600, 400);
            primaryStage.setTitle("Bacheca");
            primaryStage.setScene(scene);
            primaryStage.show();
        }



}
