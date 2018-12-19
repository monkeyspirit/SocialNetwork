package sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import versione1.AgeGroup;
import versione1.EventSoccerMatch;
import versione1.Gender;
import versione1.SocialNetwork;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CreateController {

    public static final String SOCCER_NAME = "Partite di calcio";


    // ~~~~~ newEvent Stage ~~~~~~~~~~~

    @FXML
    private TextField title;
    @FXML
    private TextField numPart;
    @FXML
    private Button create;
    @FXML
    private ChoiceBox<String> catType;
    @FXML
    private DatePicker deadLine;
    @FXML
    private ChoiceBox<Integer> minAge;
    @FXML
    private ChoiceBox<Integer> maxAge;
    @FXML
    private ChoiceBox<Gender> gender;


    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    boolean complete = true;
    public AgeGroup ageGroup;
    public ArrayList<Integer> ageRangeMin;

    // ~~~~~~ Campi FACOLTATIVI ~~~~~~~~

    String titleIns;
    int numParIns;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // ~~~~~~ Campi OBBLIGATORI ~~~~~~

    String category;
    LocalDate deadLineIns;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    private SocialNetwork socialNetwork;
    private Stage thisStage;


    // ~~~~~~~~ Metodi ~~~~~~~~~~~~~


    public void setThisStage(Stage thisStage) {
        this.thisStage = thisStage;
    }

    public void setSocialNetwork(SocialNetwork socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    public SocialNetwork getSocialNetwork() {
        return socialNetwork;
    }


    @FXML
    private void initialize() throws IOException {
        ageGroup = new AgeGroup();
        ageRangeMin = ageGroup.getNumeri();

        catType.setItems(FXCollections.observableArrayList(SOCCER_NAME));

        catType.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(catType.getSelectionModel().getSelectedItem().equals(SOCCER_NAME)){
                    minAge.setDisable(false);
                    minAge.setItems(FXCollections.observableArrayList(ageRangeMin));
                    gender.setDisable(false);
                }
            }
        });

        minAge.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                maxAge.setDisable(false);
                ArrayList<Integer> ageRangeMax = ageGroup.getMinOf(minAge.getSelectionModel().getSelectedItem());
                maxAge.setItems(FXCollections.observableArrayList(ageRangeMax));
            }
        });

        gender.setItems(FXCollections.observableArrayList(Gender.Maschile, Gender.Femminile));

    }


    /**
     * Metodo per la creazione effettiva dell'evento, il metotodo applicato su thisStage chiude la finestra corrente una volta inseriti tutti
     * i campi e effettuati i controlli
     *
     * @param actionEvent
     * @throws IOException
     */
    public void createEvent(ActionEvent actionEvent) throws IOException {

        category = catType.getSelectionModel().getSelectedItem();

        titleIns = title.getText();
        numParIns = Integer.parseInt(numPart.getText());
        deadLineIns = deadLine.getValue();


        switch (category) {
            case SOCCER_NAME: {

                EventSoccerMatch match = new EventSoccerMatch(titleIns, numParIns);
                socialNetwork.getCategories().get(0).addEvent(match);
                thisStage.close();
            }
        }


    }
}
