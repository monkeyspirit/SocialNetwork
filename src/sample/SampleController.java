package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import versione1.Category;
import versione1.Event;
import versione1.SocialNetwork;
import versione1.User;
import versione2.GraphicThread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SampleController {


    private GraphicThread graphicThread;
    public void setGraphicThread(GraphicThread graphicThread) { this.graphicThread = graphicThread; }


    public static final String SOCCER_NAME = "Partite di calcio";

    // ~~~~~ Sample Stage ~~~~~~~~~~~~~

    @FXML
    private ListView categoryPreferenceListView, notificationListView,categoryListView, eventListView, userEventListView;

    @FXML
    private Tab userTb;

    @FXML
    private Label ageRangeLbl;


    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~



    private Category<? extends Event> catSelected;
    private Event eventSelected;

    private SocialNetwork socialNetwork;
    private User sessionUser;

    private List<String> catName, catPreferenceName;
    private List<String> eventName;
    private List<String> userEventName;
    private List<String> notifyName;

    private String notification;


    private Stage view, create, notify, settings;



    // ~~~~~~~~ Metodi ~~~~~~~~~~~~~

    public void setSocialNetwork(SocialNetwork socialNetwork) { this.socialNetwork = socialNetwork; }

    public void setSessionUser(User sessionUser) { this.sessionUser = sessionUser; }



    @FXML
    private void initialize() throws IOException {

        userTb.setText(sessionUser.getUsername());
        ageRangeLbl.setText(sessionUser.getAgeRange());

        catPreferenceName = new ArrayList<>();
        for(String categoryPreference : sessionUser.getCategoryPref()){
            catPreferenceName.add(categoryPreference);
        }

        categoryPreferenceListView.setItems(FXCollections.observableList(catPreferenceName));


        catName = new ArrayList<>();
        for(Category category : socialNetwork.getCategories()){
            catName.add(category.getName());
        }

        categoryListView.setItems(FXCollections.observableList(catName));



        categoryListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (!categoryListView.getSelectionModel().isEmpty()) {

                    catSelected = socialNetwork.findCategoryByName((String) categoryListView.getSelectionModel().getSelectedItem());
                    eventName = new ArrayList<>();
                    for (Event match : catSelected.getEvents()) {
                        if (match.getStateValue().toString().equals("Aperta")) {
                            eventName.add((String) match.getTitle().getValue());
                        }

                    }

                    eventListView.setItems(FXCollections.observableList(eventName));

                    eventListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {

                            if(!eventListView.getSelectionModel().isEmpty()) {
                                eventSelected = catSelected.findEventByName((String) eventListView.getSelectionModel().getSelectedItem());
                                try {
                                    openEventView();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    });

                }
            }

        });

        userEventName = socialNetwork.findEventByUserNameS(sessionUser.getUsername());
        userEventListView.setItems(FXCollections.observableList(userEventName));


        userEventListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if(!userEventListView.getSelectionModel().isEmpty()) {
                    eventSelected = socialNetwork.findEventByEventName((String) userEventListView.getSelectionModel().getSelectedItem());
                    try {
                        openEventView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });


        notifyName = sessionUser.getNotificationsMessages();
        notificationListView.setItems(FXCollections.observableList(notifyName));



        notificationListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if(!notificationListView.getSelectionModel().isEmpty()) {
                    notification = (String) notificationListView.getSelectionModel().getSelectedItem();
                    try {
                        openNotificationView(notificationListView.getSelectionModel().getSelectedIndex());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        graphicThread.setSampleController(this);

        graphicThread.setCategoryListView(categoryListView);
        graphicThread.setNotificationListView(notificationListView);
        graphicThread.setEventListView(eventListView);
        graphicThread.setUserEventListView(userEventListView);

        graphicThread.start();
    }


    /**
     * Il Metodo si occupa di aggiornare gli elementi delle listview in modo che poi il thread aggiorni la grafica
     */
    public void refreshListView(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (!categoryListView.getSelectionModel().isEmpty()) {
                    catSelected = socialNetwork.findCategoryByIndex(categoryListView.getSelectionModel().getSelectedIndex());

                    eventName = new ArrayList<>();
                    for (Event match : catSelected.getEvents()) {
                        if (match.getStateValue().toString().equals("Aperta")) {
                            eventName.add((String) match.getTitle().getValue());
                        }

                    }

                    eventListView.setItems(FXCollections.observableList(eventName));
                }

                userEventName = socialNetwork.findEventByUserNameS(sessionUser.getUsername());
                userEventListView.setItems(FXCollections.observableList(userEventName));

                notifyName = sessionUser.getNotificationsMessages();
                notificationListView.setItems(FXCollections.observableList(notifyName));


                ageRangeLbl.setText(sessionUser.getAgeRange());

                catPreferenceName = new ArrayList<>();
                for(String categoryPreference : sessionUser.getCategoryPref()){
                    catPreferenceName.add(categoryPreference);
                }

                categoryPreferenceListView.setItems(FXCollections.observableList(catPreferenceName));



            }
        });

    }






    public void openNotificationView(int notificaitonIndex) throws IOException {

        FXMLLoader loaderNotify = new FXMLLoader(Main.class.getResource("viewNotify.fxml"));
        NotificationController notificationController = new NotificationController();

        loaderNotify.setController(notificationController);

        notificationController.setSocialNetwork(socialNetwork);
        notificationController.setSessionUser(sessionUser);
        notificationController.setNotification(sessionUser.getNotifications().get(notificaitonIndex));
        notificationController.setNotificationIndex(notificaitonIndex);
        notificationController.setNotificationListView(notificationListView);


        notify = new Stage();
        notificationController.setThisStage(notify);

        Parent notifyView = loaderNotify.load();
        Scene sceneNotify = new Scene(notifyView, 500, 250);
        notify.setTitle("Notifica");
        notify.setScene(sceneNotify);
        notify.show();
    }








    /**
     * Metodo per la creazione di un nuovo evento, chiama il controller dedicato
     * @throws IOException
     */
    public void openEventView() throws IOException {

        FXMLLoader loaderEvent = new FXMLLoader(Main.class.getResource("viewEvent.fxml"));
        EventController eventController = new EventController();

        loaderEvent.setController(eventController);

        eventController.setSocialNetwork(socialNetwork);
        eventController.setEventSelected(eventSelected);
        eventController.setSessionUser(sessionUser);
        eventController.setSessionUsername(sessionUser.getUsername());



        view = new Stage();

        Parent eventView = loaderEvent.load();
        Scene sceneEvent = new Scene(eventView, 600, 400);
        view.setTitle((String)eventSelected.getTitle().getValue());
        view.setScene(sceneEvent);
        view.show();

    }







    /**
     * Metodo per l'apertura dell'editor per inserire un nuovo evento della categoria Partita di calcio
     * @param actionEvent
     * @throws IOException
     */
    public void openEventEditor(ActionEvent actionEvent) throws IOException {

        FXMLLoader loaderCreate = new FXMLLoader(Main.class.getResource("createEvent.fxml"));
        EventCreateController eventCreateController = new EventCreateController();

        loaderCreate.setController(eventCreateController);

        eventCreateController.setSocialNetwork(socialNetwork);
        eventCreateController.setCreator(sessionUser.getUsername());


        create = new Stage();

        eventCreateController.setThisStage(create);
        Parent eventCreate =  (Parent) loaderCreate.load();
        Scene scene = new Scene(eventCreate, 600, 400);

        create.setX(400); // In questo modo non è sovrapposto a quello degli eventi
        create.setTitle("Crea");
        create.setScene(scene);
        create.show();



    }


    /**
     * Il metodo si occupa di ricaricare la schermata di login e di salvare tutto,
     * @throws IOException
     */
    public void exitUser() throws IOException {

        socialNetwork.updateUserandEventsListFile();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("login.fxml"));
        LoginController loginContr = new LoginController();

        loader.setController(loginContr);

        loginContr.setSocialNetwork(socialNetwork);

        if(view != null){
            view.close();
        }
        if(create != null){
            create.close();
        }


        graphicThread.deleteThread();


        Stage newLogin = Main.getStage();

        // Imposto lo stage e la scene principali
        Parent root =  (Parent) loader.load();
        Scene scene = new Scene(root, 600, 400);
        newLogin.setTitle("Accesso");
        newLogin.setScene(scene);
        newLogin.show();
    }

    /**
     * Metodo per aprire la finestra dei settaggi del profilo utente
     */
    public void openSettings() throws IOException {

        FXMLLoader loaderSettings = new FXMLLoader(Main.class.getResource("settings.fxml"));
        SettingsController settingsController = new SettingsController();

        loaderSettings.setController(settingsController);

        settingsController.setSocialNetwork(socialNetwork);
        settingsController.setSessionUser(sessionUser);


        settings = new Stage();

        settingsController.setThisStage(settings);
        Parent settingStage =  (Parent) loaderSettings.load();
        Scene scene = new Scene(settingStage, 600, 250);

        settings.setX(200); // In questo modo non è sovrapposto ad altri
        settings.setTitle("Impostazioni");
        settings.setScene(scene);
        settings.show();
    }




}
