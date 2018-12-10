package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import versione1.Category;
import versione1.SocialNetwork;

public class SampleController {

    @FXML
    private ListView categoryListView;

    SocialNetwork socialNetwork = new SocialNetwork();

    @FXML
    private void initialize() {
        System.out.println("Carico la View...");
        for (Category category : socialNetwork.getCategories()) { // popolo automaticamente la ListView con gli elementi dell'array caegories di SocialNetwork
            categoryListView.getItems().add(category.getName());
        }
    }


}
