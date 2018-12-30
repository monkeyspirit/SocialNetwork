package versione2;
import versione1.Category;
import versione1.Event;
import versione1.EventSoccerMatch;
import versione1.SocialNetwork;
import versione2.notifications.NotificationsHandler;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ControlThread extends Thread {

    int i;
    SocialNetwork socialNetwork;
    ArrayList<Category> categories;
    ArrayList<ArrayList<Event>> events;
    NotificationsHandler notificationHandler;


    public void setSocialNetwork(SocialNetwork socialNetwork) {
        this.socialNetwork = socialNetwork;
        this.notificationHandler = socialNetwork.getNotificationsHandler();
        categories = socialNetwork.getCategories();
        events = new ArrayList<>();

        for(int j=0; j<categories.size(); j++){
            events.add(categories.get(i).getEvents());
        }

    }

    @Override
    public void run() {
        i = 0;

        while (true) {
//            System.out.println("Controllo eventi numero: " + i);

            i++;

            for (int k = 0; k < events.size(); k++) {
                for (int h = 0; h < events.get(k).size(); h++) {
                    events.get(k).get(h).controlState();

                }
            }

            if(socialNetwork.getLoggedUser()!= null) {
                try {
                    socialNetwork.getLoggedUser().setNotifications(notificationHandler.getSoccerMatchNotifications(socialNetwork.getLoggedUser(), socialNetwork.findEventByUserNameE(socialNetwork.getLoggedUser().getUsername())));

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        }

    }
}
