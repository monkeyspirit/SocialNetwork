package main.model;


import javafx.scene.control.ListView;
import main.controllers.MainController;


public class GraphicThread extends Thread {

    // Attributi & Setter
    private MainController mainController;

    public void setMainController(MainController mainController) { this.mainController = mainController; }

    private ListView notificationListView,categoryListView, eventListView, userEventListView;

    public void setNotificationListView(ListView notificationListView) { this.notificationListView = notificationListView; }

    public void setCategoryListView(ListView categoryListView) { this.categoryListView = categoryListView; }

    public void setEventListView(ListView eventListView) { this.eventListView = eventListView; }

    public void setUserEventListView(ListView userEventListView) { this.userEventListView = userEventListView; }

    @Override
    public void run() {


        while (true) {

            mainController.refreshListView();

            categoryListView.refresh();
            if(!categoryListView.getSelectionModel().isEmpty()){
                eventListView.refresh();
            }

            userEventListView.refresh();

            notificationListView.refresh();


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}

