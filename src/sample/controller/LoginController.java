package sample.controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.CheckBoxListCell;
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
import javafx.util.Callback;
import sample.Main;
import versione1.AgeGroup;
import versione1.Category;
import versione1.SocialNetwork;
import versione1.User;
import versione2.GraphicThread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginController {

// ~~~~~ Sample Stage ~~~~~~~~~~~~~

    @FXML
    private Button loginBtn;
    @FXML
    private CheckBox checkRgCB;
    @FXML
    private ChoiceBox<Integer> minAgeCB, maxAgeCB;
    @FXML
    private TextField usrnameTF;
    @FXML
    private Label errorLbl, preferenceCatLbl, ageRangeLbl, show;
    @FXML
    private ListView preferenceCategoryListView;


    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private AgeGroup ageGroup;
    private ArrayList<Integer> ageRangeMin;
    public static final String MISSUSERNAME = "Attenzione: inserire un nome utente";
    public static final String ERRALREADYUSE = "Attenzione: il nome utente esiste";
    public static final String ERRNORUSE = "Attenzione: il nome utente non esiste";
    private SocialNetwork social;
    private User sessionUser;
    public void setSocialNetwork(SocialNetwork social) { this.social = social; }
    private List<String> catName;

    private List<CheckBox> categoryCheck;
    private ArrayList<String> selectedCategory;


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
                   ageRangeLbl.setVisible(true);
                   preferenceCategoryListView.setVisible(true);
                   preferenceCatLbl.setVisible(true);
                   minAgeCB.setVisible(true);
                   maxAgeCB.setVisible(true);

                   ageGroup = new AgeGroup();
                   ageRangeMin = ageGroup.getNumeri();

                   minAgeCB.setItems(FXCollections.observableArrayList(ageRangeMin));

                   /**
                    * ActionListeren che permette una volta selezionato il minimo dell'età di selezionare il max, in questo modo lo popolo con i
                    * numeri maggiorni stretti del minimo, dopo i 20 anni si va di decina in decina per facilitare
                    * la scelta all'utente
                    */
                   minAgeCB.setOnAction(new EventHandler<ActionEvent>() {
                       @Override
                       public void handle(ActionEvent event) {
                           maxAgeCB.setDisable(false);
                           ArrayList<Integer> ageRangeMax = ageGroup.getMinOf(minAgeCB.getSelectionModel().getSelectedItem());
                           maxAgeCB.setItems(FXCollections.observableArrayList(ageRangeMax));
                       }
                   });

                   // Questa parte serve per creare il check box nella list view

                   selectedCategory = new ArrayList<>(); // array di stringhe delle categorie preferite
                   categoryCheck = new ArrayList<>();

                   for(Category category : social.getCategories()){
                       CheckBox catCheck = new CheckBox();
                       catCheck.setText(category.getName());
                       categoryCheck.add(catCheck);

                   }

                   preferenceCategoryListView.setItems(FXCollections.observableList(categoryCheck));


               }
               else {
                   ageRangeLbl.setVisible(false);
                   preferenceCatLbl.setVisible(false);
                   preferenceCategoryListView.setVisible(false);
                   minAgeCB.setVisible(false);
                   maxAgeCB.setVisible(false);
                   loginBtn.setText("Accedi");
               }

            }
        });

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
                sessionUser = new User(accessName);

                if(social.doesUserExist(accessName) == false){

                    //controllo che ci sia la fascia di età altrimenti non la setto
                    if (minAgeCB.getValue() != null && maxAgeCB.getValue() != null) {
                        ageGroup.setRange(minAgeCB.getValue(), maxAgeCB.getValue());
                        String ageRangeIns = ageGroup.getRange();
                        sessionUser.setAgeRange(ageRangeIns);
                    }


                    for(CheckBox check: categoryCheck){
                        if(check.isSelected()){
                            selectedCategory.add(check.getText());
                        }
                    }
                    sessionUser.setCategoryPref(selectedCategory);


                    social.registerNewUser(sessionUser); //registro un nuovo utente
                    social.loginUser(sessionUser); //loggo l'utente creato (potremmo accorpare i due metodi in uno)
                    loadSecond();
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
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/sample.fxml"));
        SampleController controller = new SampleController();

        // Imposto il controller
        loader.setController(controller);

        // Passo a controller il riferimento a social network
        controller.setSocialNetwork(social);
        controller.setSessionUser(sessionUser);

        GraphicThread graphicThread = new GraphicThread();

        controller.setGraphicThread(graphicThread);
        graphicThread.setSampleController(controller);

        Stage primaryStage = Main.getStage();

        // Imposto lo stage e la scene principali
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Bacheca");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
