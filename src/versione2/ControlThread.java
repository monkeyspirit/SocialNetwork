package versione2;
import versione1.Category;
import versione1.Event;
import versione1.SocialNetwork;

import java.util.ArrayList;

public class ControlThread extends Thread {

    int i;
    SocialNetwork socialNetwork;
    ArrayList<Category> categories;
    ArrayList<ArrayList<Event>> events;

    public void setSocialNetwork(SocialNetwork socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    @Override
    public void run() {

        while (true) {

        	if(socialNetwork != null) {
        		for (Category cat : socialNetwork.getCategories()) {
        			cat.controlEventState();
        		}
        	}

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        }

    }
