package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import versione1.Category;
import versione1.EventSoccerMatch;
import versione1.SoccerMatch;
import versione1.SocialNetwork;

public class SampleController {


    @FXML
    private ListView categoryListView;
    @FXML
    private ListView eventListView;
    private SocialNetwork socialNetwork;

    public void setSocialNetwork( SocialNetwork socialNetwork) { this.socialNetwork = socialNetwork; }

    @FXML
    private void initialize() {
        SocialNetwork socialNetwork = new SocialNetwork();
        EventSoccerMatch eventSoccerMatch = new EventSoccerMatch();
    	SoccerMatch soccerMatch = new SoccerMatch();
    	soccerMatch.addEvent(eventSoccerMatch);
    	socialNetwork.addCategory(new SoccerMatch());
        System.out.println("Carico la View...");
        System.out.println(socialNetwork.getCategories().get(0).getName());
        for (Category category : socialNetwork.getCategories()) { // popolo automaticamente la ListView con gli elementi dell'array caetgories di SocialNetwork
            categoryListView.getItems().add(category.getName());
            for (String element : eventSoccerMatch.getStructure()) {
            	eventListView.getItems().add(element);
            }
        }
        
    }


}
