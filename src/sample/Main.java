package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import versione1.EventSoccerMatch;
import versione1.SoccerMatch;
import versione1.SocialNetwork;

public class Main extends Application {


    /**
     * Il metodo start ha il compito principale di far avviare l'interfaccia grafica e impostare
     * tutti i parametri necessari al suo funzionamento, nel nostro caso si occupa anche di creare le istnze iniziali (in mancanza
     * di un metodo di lettur scrittura da file)
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        // Carico il file per la grafica
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("sample.fxml"));
//        FXMLLoader loader = new FXMLLoader(Main.class.getResource("login.fxml")); --> Maria: non avevo niente da fare ahaha

        SampleController controller = new SampleController();

        // Creo le istanze principali del mio programma --> quando inseriremo il metodo di lettura
        // e scrittura da file qui ci vorra una struttura di controllo come un case o un if
        // e queste righe saranno in una parte di codice alternativa
        SocialNetwork social = new SocialNetwork();
        SoccerMatch soccer_match = new SoccerMatch();
        EventSoccerMatch eventSoccerMatch = new EventSoccerMatch();
        soccer_match.addEvent(eventSoccerMatch);
        social.addCategory(soccer_match);


        // Imposto il controller
        loader.setController(controller);

        // Passo a controller il riferimento a social network
        controller.setSocialNetwork(social);

        // Imposto lo stage e la scene principali
        Parent root =  (Parent) loader.load();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Bacheca");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {

//        SocialNetwork social = new SocialNetwork();
//		SoccerMatch soccer_match = new SoccerMatch();
//		social.addCategory(soccer_match);
//		EventSoccerMatch evento = new EventSoccerMatch();
//		String messaggio = "Scegli che categoria visualizzare tra le seguenti: \n 1 - " + soccer_match.getName();
//		String output = null;
//		int input = Integer.parseInt( JOptionPane.showInputDialog(messaggio) );
//		if(input == 1) {
//			output = evento.getTitle().getName() +"\n"+
//				     evento.getNumOfPartecipants().getName() +"\n"+
//					 evento.getRegistrationDeadline().getName() +"\n"+
//					 evento.getPlace().getName();
//					//etc..
//		}
//		JOptionPane.showMessageDialog(null, output);

        launch(args);

    }

}
