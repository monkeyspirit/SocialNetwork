package sample.controller;

import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import versione1.*;
import versione2.StateValue;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class EventCreateController {

    public static final String SOCCER_NAME = "Partite di calcio";

    // Messaggi di errore
    public static final String MISS_CATEGORY_MSG =  "Inserire una categoria";
    public static final String MISS_NUMBER_OF_PARTECIPANTS_MSG = "Inserire numero partecipanti";
    public static final String ERROR_NUMBER_REQUIRED = "Inserire un numero";
    public static final String MISS_DEADLINE_MSG = "Inserire la data di termine";
    public static final String MISS_PLACE_MSG = "Inserire il luogo";
    public static final String MISS_DATE_MSG = "Inserire la data in cui si tiene l'evento";
    public static final String MISS_TIME_MSG = "Inserire l'orario";
    public static final String ERROR_TIME_BECAUSE_ERROR_DATE_MSG = "L'orario inserito non risulta valido in quanto la data inserita non risulta valida";
    public static final String MISS_INDIVIDUAL_TEE_MSG = "Inserire la quota individuale";
    public static final String ERROR_ENDTIME_BEFORE_TIME_IF_DATE_EQUAL_ENDDATE_MSG = "L'orario di conclusione non risulta valido in quanto precedente all'orario di inizio";
    public static final String MISS_AGE_MSG = "Inserire la fascia di eta'";
    public static final String MISS_GENDER_MSG = "Inserire il genere";
    public static final String MISS_DURATION_OR_ENDDATE_MSG = "Inserire o la durata o il giorno e l'ora di termine";
    public static final String ALREADY_EXIST_EVENT_WITH_THIS_TITLE_MSG = "Esiste gia' un evento con quest titolo";




    // ~~~~~ newEvent Stage ~~~~~~~~~~~

    @FXML
    private Label retiredDeadLLbl, extraParLbl, titleLbl,durLbl, durUnitLbl, catLbl, numPLbl, deadLLbl, placeLbl,dateLbl,timeLbl,indTeeLbl, endDateLbl, endTimeLbl, ageLbl,totTeLbl, genderLbl;
    @FXML
    private TextField titleTxtF, numPTxtF, placeTxtF, indTeeTxtF, extraNumParTxt;
    @FXML
    private Button createBtn;
    @FXML
    private ChoiceBox<String> catCB;
    @FXML
    private DatePicker deadLineDP, dateDP,endDateDP, retiredDeadLineDP;
    @FXML
    private ChoiceBox<Integer> minAgeCB, maxAgeCB, durBigCB,durLitCB;
    @FXML
    private ChoiceBox<Gender> genderCB;
    @FXML
    private JFXTimePicker endTimeTP, timeTP;
    @FXML
    private TextArea noteTxtA, totTeeTxtA;
    @FXML
    private CheckBox isTeeActiveCkB;
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private AgeGroup ageGroup;
    private ArrayList<Integer> ageRangeMin;
    private String[] errorMsg = new  String[13];


    // ~~~~~~ Campi FACOLTATIVI ~~~~~~~~

    private String titleIns;
    private int extraNumIns;
    private String totTeeIns;
    private LocalDate retiredDeadLineIns;
    private LocalDate endDateIns;
    private LocalTime endTimeIns;
    private String noteIns;
    private int durH, durM, durD;
    private String durationIns;

    private boolean extraNumIsVal = false;
    private boolean endDateIsVal = false;
    private boolean endTimeIsVal = false;


    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // ~~~~~~ Campi OBBLIGATORI ~~~~~~

    private String categoryIns;
    private int numParIns;
    private LocalDate deadLineIns;
    private String placeIns;
    private LocalDate dateIns;
    private LocalTime timeIns;
    private float indTeeIns;
    private int minAge, maxAge;
    private String ageRangeIns;
    private Gender genderIns;


    private boolean catIsVal = false;
    private boolean titIsVal = false;
    private boolean numIsVal = false;
    private boolean deadLineIsVal = false;
    private boolean placeIsVal = false;
    private boolean dateIsVal = false;
    private boolean timeIsVal = false;
    private boolean indTeeIsVal = false;
    private boolean ageIsVal= false;
    private boolean genderIsVal = false;
    private boolean durIsVal = false;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    private SocialNetwork socialNetwork;
    private Stage thisStage;
    private String creator;


    // ~~~~~~~~ Metodi ~~~~~~~~~~~~~


    public void setThisStage(Stage thisStage) { this.thisStage = thisStage; }

    public void setSocialNetwork(SocialNetwork socialNetwork) { this.socialNetwork = socialNetwork; }

    public void setCreator(String creator) { this.creator = creator;  }

    /**
     * Il metodo serve per inizializzare la finestra, in particolare per impostare gli observable list nei
     * choiceBox e per rendere determinati choiceBox abilitati o meno in base alla scelta della categoria.
     * Se si seleziona Partita di Calcio viene abilitata:
     * - la selezione della fascia di età
     * - la sezione del genere
     *
     * Poichè non ci deve essere inconsistenza tra fascia di età minima e massima il choiceBox della massima
     * viene abilitato solo dopo aver selezionato la minima e con valori suoeriori (maggiore stretto) dalla
     * minima selezionata.
     *
     * @throws IOException
     */
    @FXML
    private void initialize() throws IOException {

        ageGroup = new AgeGroup();
        ageRangeMin = ageGroup.getNumeri();

        catCB.setItems(FXCollections.observableArrayList(SOCCER_NAME));

        // Imposto i giorni precedenti ad oggi non selezionabili su datePicker

        deadLineDP.setDayCellFactory(picker-> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty){
                super.updateItem(date, empty);
                setDisable(empty || date.isBefore(LocalDate.now().plusDays(1)));
            }
        });

        deadLineDP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                retiredDeadLineDP.setDisable(false);
                dateDP.setDisable(false);
                timeTP.setDisable(false);

                retiredDeadLineDP.setDayCellFactory(picker-> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty){
                        super.updateItem(date, empty);
                        setDisable(empty || (date.isBefore(LocalDate.now().plusDays(1)) || date.isAfter(deadLineDP.getValue())));
                    }
                });



                dateDP.setDayCellFactory(picker-> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty){
                        super.updateItem(date, empty);
                        setDisable(empty || date.isBefore(deadLineDP.getValue().plusDays(1)));
                    }
                });

                dateDP.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        endDateDP.setDisable(false);
                        endTimeTP.setDisable(false);

                        endDateDP.setDayCellFactory(picker-> new DateCell() {
                            @Override
                            public void updateItem(LocalDate date, boolean empty){
                                super.updateItem(date, empty);
                                setDisable(empty || date.isBefore(dateDP.getValue()));
                            }
                        });
                    }
                });


            }
        });




        /**
         * ActionListeren che permette di ottenere la lista degli eventi della categoria selezionata sulla
         * ListView delle categorie
         */
        catCB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(catCB.getSelectionModel().getSelectedItem().equals(SOCCER_NAME)){ //Se si seleziona il vuoto così non da errore
                    minAgeCB.setDisable(false);
                    minAgeCB.setItems(FXCollections.observableArrayList(ageRangeMin));
                    genderCB.setDisable(false);
                    durBigCB.setDisable(false);
                    durLitCB.setDisable(true);
                    durUnitLbl.setText("Giorno/i");
                    durBigCB.setItems(FXCollections.observableArrayList(MyUtil.getArray(1, 10)));

                }
            }
        });

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


        /**
         * ActionListener che controlla la selezione della data finale, se tale valore è uguale alla data iniziale
         * allora i ChoiceBox si settano per attendere la durata in ore e minuti, se la data finale è diversa dalla data iniziale
         * invece i ChoiceBox della durata si disattivano perchè in questo modo l'utente inserisce solo i valori esatti
         */
        endDateDP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if((endDateDP.getValue().isEqual(dateDP.getValue())) && (endTimeTP.getValue() == null)) {
                    durBigCB.setDisable(false);
                    durBigCB.setItems(FXCollections.observableArrayList(MyUtil.getArray(0, 23)));
                    durLitCB.setDisable(false);
                    if(durBigCB.getValue()!= null && durBigCB.getValue().equals(0)){
                        durLitCB.setItems(FXCollections.observableArrayList(MyUtil.getArray(1, 59)));
                    }
                    else{
                        durLitCB.setItems(FXCollections.observableArrayList(MyUtil.getArray(0, 59)));
                    }

                    durUnitLbl.setText("Ore:Minuti");
                    endTimeTP.setDisable(false);
                }
                else {
                    durBigCB.setDisable(true);
                    durLitCB.setDisable(true);
                    durUnitLbl.setText(" ");
                    endTimeTP.setDisable(false);
                }
            }
        });

        /**
         * ActionListener che nel caso in cui venga scelta l'ora finale disattiva i ChoiceBox della durata
         */
        endTimeTP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                durBigCB.setDisable(true);
                durLitCB.setDisable(true);
                durUnitLbl.setText(" ");
            }
        });


        isTeeActiveCkB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(isTeeActiveCkB.isSelected()){
                    indTeeTxtF.setDisable(false);
                    indTeeIsVal=false;
                }
                else{
                    indTeeIns=0;
                    indTeeTxtF.setDisable(true);
                    indTeeIsVal=true;
                }
            }
        });

        //Aggiungo gli elementi dell'Enum <Gender> al Choice Box del genere
        genderCB.setItems(FXCollections.observableArrayList(Gender.Maschile, Gender.Femminile));

    }


    /**
     * Metodo per la creazione effettiva dell'evento, il metotodo applicato su thisStage chiude la finestra corrente una volta
     * inseriti tutti i campi e effettuati i controlli. I campi obbligatori sono:
     * - numero dei partecipanti
     * - termine ultimo iscrizione
     * - luogo
     * - data
     * - ora
     * - quota individuale
     * Gli altri campi sono facolatitivi e quindi possono anche essere omessi.
     * In oltre si ha l'obbligo prima di creare l'evento selezionare a quale categoria appartenga.
     * Ogni volta che viene selezionato qualcosa che non va bene o si commettono erroi viene stampato un msg
     * errore relativo a cosa succede e diventa rossa la label.
     *
     * Un'aggiunta e' relativa alla durata e alla data di termine, poiche' non puo' esistere un evento
     * che inizia ma non finisce l'utente e' pregato di inserire almeno uno dei due.
     *
     * @param actionEvent
     * @throws IOException
     */
    public void createEvent(ActionEvent actionEvent) throws IOException {


        // OBBLIGATORIO
        // categoria

        // Acquisisco la categoria dal choiceBox, e se la categoria non viene selezionata non vado avanti
        // Nel caso in cui non sia selezionata diventa rossa la label e compare un msg di errore
        if (catCB.getSelectionModel().getSelectedItem() == null) {
            catLbl.setTextFill(Color.RED);
            catIsVal = false;
            JOptionPane.showMessageDialog(null, MISS_CATEGORY_MSG);
        } else {
            categoryIns = catCB.getSelectionModel().getSelectedItem();
            catLbl.setTextFill(Color.BLACK);
            catIsVal = true;


            // Acquisisco se c'e' il titolo
            // FACOLTATIVO
            //titolo
            if(titleTxtF.getText()!=null && socialNetwork.findCategoryByName(categoryIns).doesEventAlreadyExist(titleTxtF.getText()) == true){
                errorMsg[0] = ALREADY_EXIST_EVENT_WITH_THIS_TITLE_MSG;
                titleLbl.setTextFill(Color.RED);
                titIsVal = false;
            }
            else if (titleTxtF.getText()!=null && socialNetwork.findCategoryByName(categoryIns).doesEventAlreadyExist(titleTxtF.getText()) == false){
                titleIns = titleTxtF.getText();
                errorMsg[0] = null;
                titleLbl.setTextFill(Color.BLACK);
                titIsVal = true;

            }


            // Acquisisco il numero di partecipanti, controllo sia inserito e che sia un valore numerico
            // OBBLIGATORIO
            // numero partecipanti

            if (numPTxtF.getText().isEmpty() || !MyUtil.checkInteger(numPTxtF.getText())) {
                numPLbl.setTextFill(Color.RED);
                numIsVal = false;
                errorMsg[1] = MISS_NUMBER_OF_PARTECIPANTS_MSG;
            } else {
                String numPS = numPTxtF.getText();
                numPLbl.setTextFill(Color.BLACK);
                numParIns = Integer.parseInt(numPS);
                numIsVal = true;
                errorMsg[1] = null;
            }

            // Acquisisco il numero di partecipanti tollerati in più
            // FACOLTATIVO
            // tolleranza partecipanti
            if(extraNumParTxt.getText().isEmpty()){
                extraParLbl.setTextFill(Color.BLACK);
                extraNumIns = 0;
                extraNumIsVal = true;
                errorMsg[11]= null;
            }
            else{
                if(!MyUtil.checkInteger(extraNumParTxt.getText())){
                    extraParLbl.setTextFill(Color.RED);
                    extraNumIsVal = false;
                    errorMsg[11] = ERROR_NUMBER_REQUIRED;
                }
                else {
                    extraParLbl.setTextFill(Color.BLACK);
                    extraNumIns = Integer.parseInt(extraNumParTxt.getText());
                    extraNumIsVal = true;
                    errorMsg[11]= null;
                }
            }


            // Acquisco il campo data e controllo che non sia una data precedente ad oggi e vuota
            // OBBLIGATORIO
            // termine ultimo iscrizione

            if (deadLineDP.getValue() == null) {
                deadLLbl.setTextFill(Color.RED);
                deadLineIsVal = false;
                errorMsg[2] = MISS_DEADLINE_MSG;
            } else {
                deadLineIns = deadLineDP.getValue();
                deadLLbl.setTextFill(Color.BLACK);
                deadLineIsVal = true;
                errorMsg[2] = null;
            }

            // Acquisisco il termine di ritiro iscrizione
            // FACOLTATIVO
            // termine ultimo ritiro iscrizione

            if(retiredDeadLineDP.getValue() == null){
                retiredDeadLineIns = deadLineIns;
            }
            else{
                retiredDeadLineIns = retiredDeadLineDP.getValue();
            }

            // Acquisisco il luogo controllando che sia inserito e che sia una stringa
            // OBBLIGATORIO
            //luogo

            if (placeTxtF.getText().isEmpty() || !MyUtil.checkString(placeTxtF.getText())) {
                placeLbl.setTextFill(Color.RED);
                placeIsVal = false;
                errorMsg[3] = MISS_PLACE_MSG;
            } else {
                placeIns = placeTxtF.getText();
                placeLbl.setTextFill(Color.BLACK);
                placeIsVal = true;
                errorMsg[3] = null;
            }

            // Acquisisco la data dell'evento, controllo che il campo non sia vuoto, non sia prima il termine ultimo + 1
            // OBBLIGATORIO
            //data

            if ((dateDP.getValue() == null)) {
                dateLbl.setTextFill(Color.RED);
                dateIsVal = false;
                errorMsg[4] = MISS_DATE_MSG;
            } else {
                dateIns = dateDP.getValue();
                dateLbl.setTextFill(Color.BLACK);
                dateIsVal = true;
                errorMsg[4] = null;
            }

            // Acquisisco l ora a cui si verifichera
            // OBBLIGATORIO
            //ora

            if (timeTP.getValue() == null || dateIsVal == false) {
                timeLbl.setTextFill(Color.RED);
                timeIsVal = false;

                if(timeTP.getValue() == null){
                    errorMsg[5] = MISS_TIME_MSG;
                }
                else {
                    errorMsg[5] = ERROR_TIME_BECAUSE_ERROR_DATE_MSG;
                }

            } else {
                timeIns = timeTP.getValue();
                timeLbl.setTextFill(Color.BLACK);
                timeIsVal = true;
                errorMsg[5] = null;
            }

            // Acquisisco la quota individuale e controllo non sia vuota, in caso la converto in decimale, ma prima controllo
            // che il CheckBox sia selezionato o meno
            // OBBLIGATORIO
            //quota individuale

            if(isTeeActiveCkB.isSelected()){
                if (indTeeTxtF.getText().isEmpty() || !MyUtil.checkFloat(indTeeTxtF.getText())) {
                    indTeeLbl.setTextFill(Color.RED);
                    indTeeIsVal = false;
                    errorMsg[6] = MISS_INDIVIDUAL_TEE_MSG;
                } else {
                    String indTeeS = indTeeTxtF.getText();

                    indTeeIns = Float.parseFloat(indTeeS);
                    indTeeLbl.setTextFill(Color.BLACK);
                    indTeeIsVal = true;
                    errorMsg[6] = null;
                }
            }
            else{
                indTeeIns=0;
                indTeeIsVal = true;
                errorMsg[6] = null;
            }


            // Indicala voci di spesa
            // FACOLTATIVO
            // voci della quota
            totTeeIns = totTeeTxtA.getText();


            // Acquisisco se c e la data di termine e controllo che non sia prima della data dell evento
            // FACOLTATIVO -> ma se c e devo controllare la coerenza
            // data conclusiva

            if (endDateDP.getValue() != null && dateIsVal == true) {
                    endDateIns = endDateDP.getValue();
                    endDateLbl.setTextFill(Color.BLACK);
                    endDateIsVal = true;
            }
            else {
                endDateIsVal = true;
                if(durBigCB.getValue() != null){
                    durD = durBigCB.getValue();
                    durationIns = String.valueOf(durD);

                }

            }


            // Acquisisco se c e l ora di termine e controllo non sia prima dell ora impostata nello stesso giorno
            // FACOLTATIVO -> ma se c e devo controllare la coerenza
            // ora conclusiva
            if (endTimeTP.getValue() != null && timeIsVal == true) {

                if (endTimeTP.getValue().isBefore(timeIns) && endDateIns.isEqual(dateIns)) {

                    endTimeLbl.setTextFill(Color.RED);
                    endTimeIsVal = false;
                    errorMsg[7] = ERROR_ENDTIME_BEFORE_TIME_IF_DATE_EQUAL_ENDDATE_MSG;

                } else {

                    endTimeIns = endTimeTP.getValue();
                    endTimeLbl.setTextFill(Color.BLACK);
                    endTimeIsVal = true;
                    errorMsg[7] = null;

                }
            }

            else {

                endTimeIsVal = true;

                if( durBigCB.getValue()!= null && durLitCB.getValue() != null){
                    durH = durBigCB.getValue();
                    durM = durLitCB.getValue();
                    durationIns = durH+":"+durM;
                }

            }




            // Acquisisco l eta controllando che l'utente la inserisca se ha scelto partita di calcio
            // OBBLIGATORIO -> se partita di calcio
            //fascia età
            // Acquisisco il genere se partita di calcio
            // OBBLIGATORIO -> se partita di calcio
            // genere
            if (categoryIns == SOCCER_NAME) {

                if (minAgeCB.getValue() != null && maxAgeCB.getValue() != null) {
                    minAge = minAgeCB.getValue();
                    maxAge = maxAgeCB.getValue();
                    ageGroup.setRange(minAge, maxAge);
                    ageRangeIns = ageGroup.getRange();
                    ageLbl.setTextFill(Color.BLACK);
                    ageIsVal = true;
                    errorMsg[8] = null;
                } else {
                    ageLbl.setTextFill(Color.RED);
                    ageIsVal = false;
                    errorMsg[8] = MISS_AGE_MSG;
                }


                genderIns = genderCB.getSelectionModel().getSelectedItem();
                if (genderCB.getSelectionModel().getSelectedItem() == null) {
                    genderLbl.setTextFill(Color.RED);
                    genderIsVal = false;
                    errorMsg[9] = MISS_GENDER_MSG;
                } else {
                    genderLbl.setTextFill(Color.BLACK);
                    genderIsVal = true;
                    errorMsg[9] = null;
                }
            }

            // FACOLTATIVO -> ma se c e devo controllare la coerenza
            // durata --> guardare end time e end date
            if( (endDateDP.getValue() != null && endTimeTP.getValue() == null ) && durationIns == null ){
                errorMsg[10] = MISS_DURATION_OR_ENDDATE_MSG;
                durLbl.setTextFill(Color.RED);
                durIsVal = false;
            }
            else {
                errorMsg[10] = null;
                durLbl.setTextFill(Color.BLACK);
                durIsVal = true;
            }


            // FACOLTATIVO
            // noteIns
            noteIns = noteTxtA.getText();



            switch (categoryIns) {
                case SOCCER_NAME: {
                    if (catIsVal && titIsVal && numIsVal && extraNumIsVal && deadLineIsVal && placeIsVal && dateIsVal && timeIsVal && endDateIsVal && endTimeIsVal && indTeeIsVal && ageIsVal && genderIsVal && durIsVal) {

                        if(endDateIns == null &&  durationIns !=null) {
                            endDateIns = dateIns.plusDays(Integer.parseInt(durationIns));
                        }
                        else if(endDateIns == null &&  durationIns == null){
                            endDateIns = dateIns.plusDays(1);
                        }
                        else{
                            if(endTimeIns == null && durationIns !=null){
                                endTimeIns = timeIns.plusHours(durH).plusMinutes(durM);

                            }
                        }

                        SoccerMatchEvent match = new SoccerMatchEvent(titleIns, numParIns, extraNumIns, deadLineIns, retiredDeadLineIns,  placeIns, dateIns, timeIns, durationIns, indTeeIns, totTeeIns, endDateIns, endTimeIns, ageRangeIns, genderIns, StateValue.Aperta, LocalDate.now(), noteIns, creator);
                        match.addParticipant(creator);

                        socialNetwork.getSoccerMatchCategory().addEvent(match);

                        socialNetwork.writeSoccerMatchEventListOnFile();
                        socialNetwork.updateUsersListFile();

                        thisStage.close();

                    }
                    else {
                        String msgPopUp = "";
                        for(int i=1; i< errorMsg.length; i++){
                            if(errorMsg[i] != null){
                                msgPopUp += errorMsg[i] + "\n";
                            }
                        }
                        JOptionPane.showMessageDialog(null, msgPopUp);
                    }

                    break;
                }
            }
        }
    }
}