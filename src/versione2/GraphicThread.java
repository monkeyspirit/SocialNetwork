package versione2;


import javafx.scene.control.ListView;
import sample.controller.SampleController;


public class GraphicThread extends Thread {

    // Attributi & Setter
    private SampleController sampleController;

    public void setSampleController(SampleController sampleController) { this.sampleController = sampleController; }

    private ListView notificationListView,categoryListView, eventListView, userEventListView;

    public void setNotificationListView(ListView notificationListView) { this.notificationListView = notificationListView; }

    public void setCategoryListView(ListView categoryListView) { this.categoryListView = categoryListView; }

    public void setEventListView(ListView eventListView) { this.eventListView = eventListView; }

    public void setUserEventListView(ListView userEventListView) { this.userEventListView = userEventListView; }

    @Override
    public void run() {


        while (true) {

            sampleController.refreshListView();

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

