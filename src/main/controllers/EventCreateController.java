package main.controllers;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.model.*;
import main.model.cinemaCategory.CinemaEventBuilder;
import main.model.event.AgeGroup;
import main.model.event.Gender;
import main.model.event.ValidateEvent;
import main.model.soccerMatchCategory.SoccerMatchEvent;
import main.model.notifications.NotificationsBuilder;
import main.model.cinemaCategory.CinemaEvent;
import main.model.cinemaCategory.FilmType;
import main.model.soccerMatchCategory.SoccerMatchEventBuilder;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventCreateController {

    public static final String SOCCER_NAME = "Partite di calcio";
    public static final String SOCCER_DESCRIPTION = "Categoria che ha lo scopo di proporre partite di calcio di vario genere.";

    public static final String CINEMA_NAME = "Cinema";
    public static final String CINEMA_DESCRIPTION = "Categoria che ha lo scopo di proporre la visione di film di vario genere.";


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
    public static final String MISS_AGE_MSG = "Inserire la fascia di eta'";
    public static final String MISS_GENDER_MSG = "Inserire il genere";
    public static final String ERROR_ENDTIME_BEFORE_TIME_IF_DATE_EQUAL_ENDDATE_MSG = "L'orario di conclusione non risulta valido in quanto precedente all'orario di inizio";
    public static final String MISS_DURATION_OR_ENDDATE_MSG = "Inserire o la durata o il giorno e l'ora di termine";
    public static final String ALREADY_EXIST_EVENT_WITH_THIS_TITLE_MSG = "Esiste gia' un evento con quest titolo";
    public static final String ERR_EXTRATEE= "Errore nelle voci di spesa extra.";

    public static final String MISS_GENDER_FILM = "Manca il genere del film.";


    ValidateEvent validateEvent;

    // ~~~~~ newEvent Stage ~~~~~~~~~~~

    @FXML
    private Label extraTeeLbl, minusLbl, typeOfFilmLbl, noteLbl, retiredDeadLLbl, extraParLbl, titleLbl,durLbl, durUnitLbl, catLbl, numPLbl, deadLLbl, placeLbl,dateLbl,timeLbl,indTeeLbl, endDateLbl, endTimeLbl, ageLbl,totTeLbl, genderLbl;
    @FXML
    private TextField titleTxtF, numPTxtF, placeTxtF, indTeeTxtF, extraNumParTxt, pastiExtraTF, gadgetExtraTF, rinfrescoExtraTF;
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
    private JFXCheckBox isTeeActiveCkB, pastiCheckBox,gadgetCheckBox,rinfrescoCheckBox;
    @FXML
    private ListView<CheckBox> typeOfFilmListView, inviteParticipantsListView;


    // Asterischi campi di soccerMatch
    @FXML
    private Label segnoGenF,segnoAge, segnoGen;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private AgeGroup ageGroup;

    private List<CheckBox> userCheckList;
    private List<CheckBox> typeOfFilmCheckList;

    private List<String> selectedUserToInvite;

    private ArrayList<Integer> ageRangeMin;

    private String[] errorMsg = new  String[12];

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
    private boolean typeOfFilmIsVal = false;
    private boolean extraNumIsVal = false;
    private boolean endTimeIsVal = false;
    private boolean endDateAndTimeIsVal = false;

    //private float extraPastiTeeIns, extraRinfrescoTeeINs, extraGadgetTeeIns;

    private boolean extraTeeIsVal=false;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    private SocialNetwork socialNetwork;
    private Stage thisStage;
    private String creator;


    // -------- Costanti da sistemare --------

    public static final String TITLE_DESCRIPTION = "Campo facoltativo che consiste in un nome di fantasia attribuito all'evento";
    public static final String NUMPLAY_DESCRIPTION = "Campo obbligatorio che stabilisce il numero di persone da coinvolgere nell'evento";
    public static final String EXTRA_PARTECIPANTS_DESCRIPTION ="Campo facoltativo che indica quanti partecipanti siano eventualmente accettabili in esubero rispetto al \"Numero di partecipanti\"";
    public static final String REGDEADLINE_DESCRIPTION = "Campo obbligatorio che inidica l'ultima data possibile per iscriversi";
    public static final String RETIRED_DEADLINE_DESCRIPTION ="Campo facoltativo che indica la data entro cui a ogni fruitore che ha aderito all’evento è concesso di cancellare la sua iscrizione e al fruitore che ha proposto l’evento di ritirare la proposta";
    public static final String PLACE_DESCRIPTION = "Campo obbligatorio che indica l'indirizzo del luogo che ospitera'  l'evento oppure, se l'evento e' itinerante, il luogo di ritrovo dei partecipanti";
    public static final String DATE_DESCRIPTION = "Campo obbligatorio che indica la data in cui l'evento proposto deve svolgersi o, nel caso l'evento non termini nello stesso giorno in cui ha inizio, la data di inizio dell'evento";
    public static final String TIME_DESCRIPTION = "Campo obbligatorio che indica l'ora in cui i partecipanti dovranno trovarsi nel luogo 'Luogo' in data 'Data' per dare inizio all'evento";
    public static final String DURATION_DESCRIPTION =  "Campo facoltativo che indica la durata in termini di numero (approssimativo) di ore e minuti, per gli eventi che si esauriscono in un sol giorno, o in termini di numero esatto di giorni, per gli eventi che occupano piu' giorni consecutivi";
    public static final String INDTEE_DESCRIPTION = "Campo obbligatorio che indica la spesa (o una stima della stessa) che ogni partecipante all'iniziativa dovra'  sostenere (si noti che la spesa puo' anche essere nulla)";
    public static final String TEEINC_DESCRIPTION = "Campo facoltativo che indica tutte le voci di spesa comprese nell'ammontare indicato nella 'Quota individuale'";
    public static final String ENDDATE_DESCRIPTION = "Campo facoltativo che fissa la data di conclusione dell'evento";
    public static final String ENDTIME_DESCRIPTION = "Campo facoltativo che stima l'ora di conclusione dell'evento";
    public static final String NOTE_DESCRIPTION = "Campo facoltativo contenente informazioni aggiuntive circa l'evento";

    public static final String GENDER_DESCRIPTION = "Sesso dei partecipanti";
    public static final String AGERANGE_DESCRIPTION = "limite inferiore e superiore di eta' dei partecipanti";



    public static final String FILMTYPE_DESCRIPTION = "Indica la tipologia del film proposto, può elencare più generi";


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


        // Metto i tollpic sui campi sempre presenti


        initializeToolPic(titleLbl, TITLE_DESCRIPTION);
        initializeToolPic(numPLbl, NUMPLAY_DESCRIPTION);
        initializeToolPic(extraParLbl, EXTRA_PARTECIPANTS_DESCRIPTION);
        initializeToolPic(deadLLbl, REGDEADLINE_DESCRIPTION);
        initializeToolPic(retiredDeadLLbl, RETIRED_DEADLINE_DESCRIPTION);
        initializeToolPic(placeLbl, PLACE_DESCRIPTION);
        initializeToolPic(durLbl, DURATION_DESCRIPTION);
        initializeToolPic(dateLbl, DATE_DESCRIPTION);
        initializeToolPic(timeLbl, TIME_DESCRIPTION);
        initializeToolPic(indTeeLbl,INDTEE_DESCRIPTION);
        initializeToolPic(totTeLbl, TEEINC_DESCRIPTION);
        initializeToolPic(endDateLbl, ENDDATE_DESCRIPTION);
        initializeToolPic(endTimeLbl, ENDTIME_DESCRIPTION);
        initializeToolPic(noteLbl, NOTE_DESCRIPTION);

        //

        userCheckList = new ArrayList<>();

        for(String username : socialNetwork.getUserThatPlayOtherCreatorEvents(creator)){

            JFXCheckBox userCheck = new JFXCheckBox();
            userCheck.setText(username);
            userCheckList.add(userCheck);
        }


        // Questa parte serve per creare il check box nella list view

        inviteParticipantsListView.setItems(FXCollections.observableList(userCheckList));


        ageGroup = new AgeGroup();
        ageRangeMin = ageGroup.getNumeri();

        List<String> catName = new ArrayList<>();
        for(Category category : socialNetwork.getCategories()){
            catName.add(category.getName());
        }

        catCB.setItems(FXCollections.observableArrayList(catName));

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

                switch(catCB.getSelectionModel().getSelectedItem()){

                    case(SOCCER_NAME): { //Se si seleziona il vuoto così non da errore

                        durBigCB.setDisable(false);
                        durLitCB.setDisable(true);

                        durUnitLbl.setText("Giorno/i");
                        durBigCB.setItems(FXCollections.observableArrayList(MyUtil.getArray(1, 10)));

                        // Imposto altri valori invisibili

                        typeOfFilmLbl.setVisible(false);
                        typeOfFilmListView.setVisible(false);
                        extraTeeLbl.setVisible(false);
                        pastiCheckBox.setVisible(false);
                        rinfrescoCheckBox.setVisible(false);
                        gadgetCheckBox.setVisible(false);
                        pastiExtraTF.setVisible(false);
                        gadgetExtraTF.setVisible(false);
                        rinfrescoExtraTF.setVisible(false);
                        segnoGenF.setVisible(false);
                        //

                        genderLbl.setVisible(true);
                        ageLbl.setVisible(true);
                        segnoAge.setVisible(true);
                        segnoGen.setVisible(true);
                        minusLbl.setVisible(true);


                        minAgeCB.setVisible(true);
                        maxAgeCB.setVisible(true);

                        genderCB.setVisible(true);

                        minAgeCB.setDisable(false);
                        minAgeCB.setItems(FXCollections.observableArrayList(ageRangeMin));

                        genderCB.setDisable(false);


                        Tooltip tooltipCategory = new Tooltip();
                        tooltipCategory.setText(SOCCER_DESCRIPTION);
                        catLbl.setTooltip(tooltipCategory);


                        // Metto i tollpic sui campi NON sempre presenti

                        initializeToolPic(ageLbl, AGERANGE_DESCRIPTION);
                        initializeToolPic(genderLbl, GENDER_DESCRIPTION);


                        //
                        break;
                    }

                    case(CINEMA_NAME): {

                        durBigCB.setDisable(false);
                        durLitCB.setDisable(true);

                        durUnitLbl.setText("Giorno/i");
                        durBigCB.setItems(FXCollections.observableArrayList(MyUtil.getArray(1, 10)));

                        //Prima imposto gli altri valori invisibili

                        genderLbl.setVisible(false);
                        ageLbl.setVisible(false);
                        segnoAge.setVisible(false);
                        segnoGen.setVisible(false);
                        minusLbl.setVisible(false);


                        minAgeCB.setVisible(false);
                        maxAgeCB.setVisible(false);

                        genderCB.setVisible(false);

                        //

                        segnoGenF.setVisible(true);
                        typeOfFilmLbl.setVisible(true);
                        typeOfFilmListView.setVisible(true);
                        extraTeeLbl.setVisible(true);
                        pastiCheckBox.setVisible(true);
                        rinfrescoCheckBox.setVisible(true);
                        gadgetCheckBox.setVisible(true);
                        pastiExtraTF.setVisible(true);
                        gadgetExtraTF.setVisible(true);
                        rinfrescoExtraTF.setVisible(true);


                        // Imposto i generi dei film

                        typeOfFilmCheckList = new ArrayList<>();



                        for(FilmType type : FilmType.values() ){

                            CheckBox filmTypeCheck = new CheckBox();
                            filmTypeCheck.setText(type.toString());
                            typeOfFilmCheckList.add(filmTypeCheck);
                        }


                        // Questa parte serve per creare il check box nella list view

                        typeOfFilmListView.setItems(FXCollections.observableList(typeOfFilmCheckList));

                        Tooltip tooltipCategory = new Tooltip();
                        tooltipCategory.setText(CINEMA_DESCRIPTION);
                        catLbl.setTooltip(tooltipCategory);


                        // Metto i tollpic sui campi NON sempre presenti

                        initializeToolPic(typeOfFilmLbl, FILMTYPE_DESCRIPTION);

                        break;
                    }
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

        ValidateEvent validateEvent = new ValidateEvent();
        validateEvent.getPassParameter().setCreator(creator);

        // OBBLIGATORIO
        // categoria
        catIsVal = validateEvent.validateCategory(catCB.getSelectionModel().getSelectedItem());
        if (!catIsVal) {
            setLabelRedError(catLbl, -1, MISS_CATEGORY_MSG);
        }
        else {
            setLabelBlackNoError(catLbl, -1);

            // Acquisisco se c'e' il titolo
            // FACOLTATIVO
            titIsVal = validateEvent.validateTitle(titleTxtF.getText(), socialNetwork);
            if( ! titIsVal ){
                setLabelRedError(titleLbl, 0, ALREADY_EXIST_EVENT_WITH_THIS_TITLE_MSG);
            }
            else {
                setLabelBlackNoError(titleLbl, 0);
            }


            // Acquisisco il numero di partecipanti, controllo sia inserito e che sia un valore numerico
            // OBBLIGATORIO
            // numero partecipanti
            numIsVal = validateEvent.validateNumPar(numPTxtF.getText());
            if(! numIsVal ){
                setLabelRedError(numPLbl, 1, MISS_NUMBER_OF_PARTECIPANTS_MSG);
            }
            else{
                setLabelBlackNoError(numPLbl, 1);
            }


            // Acquisisco il numero di partecipanti tollerati in più
            // FACOLTATIVO
            // tolleranza partecipanti
            extraNumIsVal = validateEvent.validateExtraNumPar(extraNumParTxt.getText());
            if(!extraNumIsVal){
                setLabelRedError(extraParLbl, 11, ERROR_NUMBER_REQUIRED);
            }
            else{
                setLabelBlackNoError(extraParLbl,11);
            }


            // Acquisco il campo data e controllo che non sia una data precedente ad oggi e vuota
            // OBBLIGATORIO
            // termine ultimo iscrizione
            deadLineIsVal = validateEvent.validateDeadLine(deadLineDP.getValue());
            if(!deadLineIsVal){
                setLabelRedError(deadLLbl,2, MISS_DEADLINE_MSG);
            }
            else{
               setLabelBlackNoError(deadLLbl,2);
            }


            // Acquisisco il termine di ritiro iscrizione
            // FACOLTATIVO
            // termine ultimo ritiro iscrizione
            validateEvent.validateRetiredDeadLine(retiredDeadLineDP.getValue());


            // Acquisisco il luogo controllando che sia inserito e che sia una stringa
            // OBBLIGATORIO
            //luogo
            placeIsVal = validateEvent.validatePlace(placeTxtF.getText());
            if(!placeIsVal){
                setLabelRedError(placeLbl, 3, MISS_PLACE_MSG);
            }
            else {
                setLabelBlackNoError(placeLbl, 3);
            }

            // Acquisisco la data dell'evento, controllo che il campo non sia vuoto, non sia prima il termine ultimo + 1
            // OBBLIGATORIO
            //data
            dateIsVal = validateEvent.validateDate(dateDP.getValue());
            if(!dateIsVal){
                setLabelRedError(dateLbl,4,MISS_DATE_MSG);
            }
            else{
                setLabelBlackNoError(dateLbl,4);
            }


            // Acquisisco l ora a cui si verifichera
            // OBBLIGATORIO
            //ora
            timeIsVal = validateEvent.validateTime(timeTP.getValue(), dateIsVal);
            if(!timeIsVal){
                setLabelRedError(timeLbl,5, MISS_TIME_MSG+" oppure "+ERROR_TIME_BECAUSE_ERROR_DATE_MSG);
            }
            else {
                setLabelBlackNoError(timeLbl, 5);
            }

            // Acquisisco la quota individuale e controllo non sia vuota, in caso la converto in decimale, ma prima controllo
            // che il CheckBox sia selezionato o meno
            // OBBLIGATORIO
            //quota individuale
            indTeeIsVal = validateEvent.validateTeeIndividual(isTeeActiveCkB.isSelected(), indTeeTxtF.getText());
            if(!indTeeIsVal){
                setLabelRedError(indTeeLbl, 6, MISS_INDIVIDUAL_TEE_MSG);
            }
            else {
                setLabelBlackNoError(indTeeLbl, 6);
            }


            // Indicala voci di spesa
            // FACOLTATIVO
            // voci della quota
            validateEvent.validateTotTee(totTeeTxtA.getText());


            // Acquisisco se c e la data di termine e controllo che non sia prima della data dell evento
            // FACOLTATIVO -> ma se c e devo controllare la coerenza
            // data conclusiva
            validateEvent.validateEndDate(endDateDP.getValue(), dateIsVal, durBigCB.getValue());


            // Acquisisco se c e l ora di termine e controllo non sia prima dell ora impostata nello stesso giorno
            // FACOLTATIVO -> ma se c e devo controllare la coerenza
            // ora conclusiva
            endTimeIsVal = validateEvent.validateEndTime(endTimeTP.getValue(), durBigCB.getValue(), durLitCB.getValue());
            if(!endTimeIsVal){
                setLabelRedError(endTimeLbl, 7, ERROR_ENDTIME_BEFORE_TIME_IF_DATE_EQUAL_ENDDATE_MSG);
            }
            else{
                setLabelBlackNoError(endTimeLbl,7);
            }

            // Acquisisco l eta controllando che l'utente la inserisca se ha scelto partita di calcio
            // OBBLIGATORIO -> se partita di calcio
            //fascia età
            // Acquisisco il genere se partita di calcio
            // OBBLIGATORIO -> se partita di calcio
            // genere

            switch (validateEvent.getPassParameter().getCategory()) {
                case (SOCCER_NAME): {

                    ageIsVal = validateEvent.validateAge(minAgeCB.getValue(), maxAgeCB.getValue(), ageGroup);
                    if(!ageIsVal){
                        setLabelRedError(ageLbl, 8, MISS_AGE_MSG);
                    }
                    else {
                        setLabelBlackNoError(ageLbl, 8);
                    }

                    genderIsVal = validateEvent.validateGender(genderCB.getSelectionModel().getSelectedItem());
                    if(!genderIsVal){
                        setLabelRedError(genderLbl, 9, MISS_GENDER_MSG);
                    }
                    else {
                        setLabelBlackNoError(genderLbl, 9);
                    }

                    break;
                }

                case (CINEMA_NAME): {

                    typeOfFilmIsVal = validateEvent.validateTypeOfFilm(typeOfFilmCheckList);

                    if(!typeOfFilmIsVal){
                        setLabelRedError(typeOfFilmLbl, 8, MISS_GENDER_FILM);
                    }
                    else {
                        setLabelBlackNoError(typeOfFilmLbl, 8);
                    }



                    // CheckBox voci di spesa aggiuntiva
                    extraTeeIsVal = validateEvent.validateExtraTeeIns(pastiExtraTF.getText(), gadgetExtraTF.getText(),rinfrescoExtraTF.getText(), pastiCheckBox.isSelected(), gadgetCheckBox.isSelected(), rinfrescoCheckBox.isSelected());

                    if(!extraTeeIsVal){
                        setLabelRedError(extraTeeLbl, 9, ERR_EXTRATEE);
                    }
                    else{
                        setLabelBlackNoError(extraTeeLbl, 9);
                    }




                }
            }


            // FACOLTATIVO -> ma se c e devo controllare la coerenza
            // durata --> guardare end time e end date
            endDateAndTimeIsVal = validateEvent.validateEndDateEndTime(endDateDP.getValue(), endTimeTP.getValue());
            if(!endDateAndTimeIsVal){
                setLabelRedError(durLbl, 11, MISS_DURATION_OR_ENDDATE_MSG);
            }
            else{
                setLabelBlackNoError(durLbl, 11);
            }



            // FACOLTATIVO
            // noteIns
            validateEvent.validateNote(noteTxtA.getText());

            switch (validateEvent.getPassParameter().getCategory()) {

                case (SOCCER_NAME): {

                    boolean validateEventCreation = titIsVal && numIsVal && extraNumIsVal && deadLineIsVal && placeIsVal && dateIsVal && timeIsVal && indTeeIsVal && endTimeIsVal && ageIsVal && genderIsVal && endDateAndTimeIsVal;

                    if (validateEventCreation) {

                        validateEvent.validateDurationAndTimeDate(durBigCB.getValue(), durLitCB.getValue() );

                        socialNetwork.getSoccerMatchCategory().createSoccerEvent(validateEvent.getPassParameter());


/* Non serve più
                        //SoccerMatchEvent match = socialNetwork.getSoccerMatchCategory().createEvent(titleIns, numParIns, extraNumIns, deadLineIns, retiredDeadLineIns,  placeIns, dateIns, timeIns, durationIns, indTeeIns, totTeeIns, endDateIns, endTimeIns, ageRangeIns, genderIns, noteIns, creator);
                        SoccerMatchEvent match = (SoccerMatchEvent) new SoccerMatchEventBuilder()
                                .ageRange(ageRangeIns)
                                .gender(genderIns)
                                .title(titleIns)
                                .numOfParticipants(numParIns)
                                .extraParticipants(extraNumIns)
                                .registrationDeadline(deadLineIns)
                                .retireDeadline(retiredDeadLineIns)
                                .place(placeIns)
                                .date(dateIns)
                                .time(timeIns)
                                .duration(durationIns)
                                .individualTee(indTeeIns)
                                .teeInclude(totTeeIns)
                                .endDate(endDateIns)
                                .endTime(endTimeIns)
                                .note(noteIns)
                                .creator(creator)
                                .build(); //problema: questo build è chiamato su ciò che ritorna creator ossia Event...

                        match.addParticipant(creator);

                        socialNetwork.getSoccerMatchCategory().addEvent(match);
                        */

                        userCheckInvite(validateEvent.getPassParameter().getTitle());

                        socialNetwork.writeSoccerMatchEventListOnFile();
                        socialNetwork.updateUsersListFile();

                        thisStage.close();

                    } else {
                        errorMessagePrint();
                    }

                    break;
                }

                case (CINEMA_NAME): {


                    boolean validateEventCreation = titIsVal && numIsVal && extraNumIsVal && deadLineIsVal && placeIsVal && dateIsVal && timeIsVal && indTeeIsVal && endTimeIsVal && typeOfFilmIsVal && extraTeeIsVal && endDateAndTimeIsVal;

                    if (validateEventCreation) {
                        validateEvent.validateDurationAndTimeDate(durBigCB.getValue(), durLitCB.getValue() );

                        socialNetwork.getCinemaCategory().createCinemaEvent(validateEvent.getPassParameter());
                        /*

                        //CinemaEvent cinemaEvent =  socialNetwork.getCinemaCategory().createEvent(titleIns, numParIns, extraNumIns, deadLineIns, retiredDeadLineIns,  placeIns, dateIns, timeIns, durationIns, indTeeIns, totTeeIns, endDateIns, endTimeIns, noteIns, creator, typeOfFilmIns, extraPastiTeeIns,  extraRinfrescoTeeINs, extraGadgetTeeIns);
                        CinemaEvent cinemaEvent = (CinemaEvent) new CinemaEventBuilder()
                                .genres(typeOfFilmIns)
                                .extraMeals(extraPastiTeeIns)
                                .gadgetExtra(extraGadgetTeeIns)
                                .rinfreschiExtra(extraRinfrescoTeeINs)
                                .title(titleIns)
                                .numOfParticipants(numParIns)
                                .extraParticipants(extraNumIns)
                                .registrationDeadline(deadLineIns)
                                .retireDeadline(retiredDeadLineIns)
                                .place(placeIns)
                                .date(dateIns)
                                .time(timeIns)
                                .duration(durationIns)
                                .individualTee(indTeeIns)
                                .teeInclude(totTeeIns)
                                .endDate(endDateIns)
                                .endTime(endTimeIns)
                                .note(noteIns)
                                .creator(creator)
                                .build(); //problema: questo build è chiamato su ciò che ritorna creator ossia Event...


                        cinemaEvent.addParticipant(creator);
                        socialNetwork.getCinemaCategory().addEvent(cinemaEvent);
                        */


                        userCheckInvite(validateEvent.getPassParameter().getTitle());

                        socialNetwork.writeCinemaEventListOnFile();
                        socialNetwork.updateUsersListFile();

                        thisStage.close();

                    } else {
                        errorMessagePrint();
                    }

                    break;
                }
            }
        }
    }



    private void userCheckInvite(String titleIns ) {
        List<String> selectedUserToInvite = new ArrayList<>();

        for (CheckBox check : userCheckList) {
            if (check.isSelected()) {
                selectedUserToInvite.add(check.getText());
            }
        }

        for (String username : selectedUserToInvite) {
            User sendTo = socialNetwork.findUserByName(username);
            sendTo.addNotification(NotificationsBuilder.buildNotificationInvite(titleIns));
        }
    }

    private void errorMessagePrint() {
        String msgPopUp = "";
        for (int i = 0; i < errorMsg.length; i++) {
            if (errorMsg[i] != null) {
                msgPopUp += errorMsg[i] +"\n";

            }
        }
        JOptionPane.showMessageDialog(null, msgPopUp);
    }

    /*

    // METODI SETTER
    public void setCategoryIns(String category) {
        this.categoryIns = category;
    }

    public void setTitleIns(String title){
        this.titleIns = title;
    }

    public void setNumParIns(int numPar){
        this.numParIns = numPar;
    }

    public void setExtraNumParIns(int extraNum){
        this.extraNumIns = extraNum;
    }

    public void setDeadLineIns(LocalDate deadLine) {
        this.deadLineIns = deadLine;
    }

    public void setTotTeeIns(String totTee) {
        this.totTeeIns = totTee;
    }

    public void setRetiredDeadLineIns(LocalDate retiredDeadLine) {
        this.retiredDeadLineIns = retiredDeadLine;
    }

    public void setEndDateIns(LocalDate endDate) {
        this.endDateIns = endDate;
    }

    public void setEndTimeIns(LocalTime endTime) {
        this.endTimeIns = endTime;
    }

    public void setNoteIns(String note) {
        this.noteIns = note;
    }

    public void setDurationIns(String duration) {
        this.durationIns = duration;
    }

    public void setPlaceIns(String place) {
        this.placeIns = place;
    }

    public void setDateIns(LocalDate date) {
        this.dateIns = date;
    }

    public void setTimeIns(LocalTime time) {
        this.timeIns = time;
    }

    public void setIndTeeIns(float indTee) {
        this.indTeeIns = indTee;
    }

    public void setAgeRangeIns(String ageRange) {
        this.ageRangeIns = ageRange;
    }

    public void setGenderIns(Gender gender) {
        this.genderIns = gender;
    }


    public void setTypeOfFilmIns(List<String> typeOfFilm) {
        this.typeOfFilmIns = typeOfFilm;
    }

    public void setExtraPastiTeeIns(float extraPastiTee) {
        this.extraPastiTeeIns = extraPastiTee;
    }

    public void setExtraGadgetTeeIns(float extraGadgetTee) {
        this.extraGadgetTeeIns = extraGadgetTee;
    }

    public void setExtraRinfrescoTeeIns(float extraRinfrescoTee) {
        this.extraRinfrescoTeeINs = extraRinfrescoTee;
    }
    */

    // METODI PER MODIFICA LABEL

    public void setLabelRedError(Label label, int i, String MSG){
        if(i == -1){
            label.setTextFill(Color.RED);
            JOptionPane.showMessageDialog(null, MISS_CATEGORY_MSG);
        }
        else {
            label.setTextFill(Color.RED);
            errorMsg[i] = MSG;
        }
    }

    public void setLabelBlackNoError(Label label, int i){
        if(i==-1){
            label.setTextFill(Color.BLACK);
        }
        else {
            errorMsg[i] = null;
            label.setTextFill(Color.BLACK);
        }
    }

    public void initializeToolPic( Label label, String DESCRIPTION){
        Tooltip tooltip = new Tooltip();
        tooltip.setText(DESCRIPTION);
        label.setTooltip(tooltip);

    }
}