package main.model;

import main.model.event.Gender;
import main.model.event.StateValue;
import main.model.event.states.Opened;
import main.model.event.states.ToRetire;
import main.model.soccerMatchCategory.SoccerMatchEvent;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ControlThreadTest {

    private SocialNetwork socialNetwork;

    public void addEvents(SocialNetwork socialNetwork, User user1, User user2){
        //creazione evento che passerà negli stati CREATO -> APERTO -> CHIUSO -> CONCLUSO
        SoccerMatchEvent soccerMatchEvent1 = new SoccerMatchEvent("Partita di calcio",
                2, 0, LocalDate.of(2018,6,12),
                LocalDate.of(2018,6,12), "Milano",
                LocalDate.of(2018,6,13), LocalTime.of(15,15),"10",
                10, "10", LocalDate.of(2018,6,13),
                LocalTime.of(15,25), "10-99", Gender.Maschile,
                StateValue.Creata, LocalDate.of(2018,6,13), "ciao", "Alice");

        soccerMatchEvent1.addParticipant(user1.getUsername());
        soccerMatchEvent1.addParticipant(user2.getUsername());


        //creazione evento che passerà negli stati APERTO -> FALLITO
        SoccerMatchEvent soccerMatchEvent2 = new SoccerMatchEvent("Torneo di calcio",
                2, 1, LocalDate.of(2018,6,12),
                LocalDate.of(2018,6,12), "Milano",
                LocalDate.of(2018,6,13), LocalTime.of(15,15),"10",
                10, "10", LocalDate.of(2018,6,13),
                LocalTime.of(15,25), "10-99", Gender.Maschile,
                StateValue.Aperta, LocalDate.of(2018,6,13), "ciao", "Bob");
        soccerMatchEvent2.setState(new Opened());

        //creazione evento che passerà negli stati DA RITIRARE -> RITIRATO
        SoccerMatchEvent soccerMatchEvent3 = new SoccerMatchEvent("Allenamento",
                2, 1, LocalDate.of(2019,6,12),
                LocalDate.of(2019,6,12), "Milano",
                LocalDate.of(2019,6,13), LocalTime.of(15,15),"10",
                10, "10", LocalDate.of(2019,6,13),
                LocalTime.of(15,25), "10-99", Gender.Maschile,
                StateValue.DaRitirare, LocalDate.of(2019,6,13), "ciao", "Bob");
        soccerMatchEvent3.setState(new ToRetire());
        socialNetwork.getSoccerMatchCategory().addEvent(soccerMatchEvent1);
        socialNetwork.getSoccerMatchCategory().addEvent(soccerMatchEvent2);
        socialNetwork.getSoccerMatchCategory().addEvent(soccerMatchEvent3);


    }
    public void populateSocialNetwork(){
        this.socialNetwork = new SocialNetwork();
        User alice = new User("Alice");
        User bob = new User("Bob");
        User eve = new User("Eve");

        addEvents(socialNetwork, alice, bob);
        List<User> users = new ArrayList<>();
        users.add(alice);
        users.add(bob);
        users.add(eve);
        alice.addCategoryPref("Partite di calcio");
        socialNetwork.setUsers(users);
        socialNetwork.loginUser(alice);
    }

    @Test
   public void createdToOpenedToClosedToEnded() {
        ControlThread control = new ControlThread();
        this.populateSocialNetwork();
        control.setSocialNetwork(this.socialNetwork);
        control.controlState();
        assertEquals(StateValue.Aperta, socialNetwork.getSoccerMatchCategory().getEvents().get(0).getStateValue());
        control.controlState();
        assertEquals(StateValue.Chiusa, socialNetwork.getSoccerMatchCategory().getEvents().get(0).getStateValue());
        control.controlState();
        assertEquals(StateValue.Conclusa, socialNetwork.getSoccerMatchCategory().getEvents().get(0).getStateValue());

    }

    @Test
   public void OpenedToFailed() {
        ControlThread control = new ControlThread();
        this.populateSocialNetwork();
        control.setSocialNetwork(this.socialNetwork);
        control.controlState();
        assertEquals(StateValue.Fallita, socialNetwork.getSoccerMatchCategory().getEvents().get(1).getStateValue());


    }

    @Test
    public void ToRetireToRetired() {
        ControlThread control = new ControlThread();
        this.populateSocialNetwork();
        control.setSocialNetwork(this.socialNetwork);
        control.controlState();
        assertEquals(StateValue.Ritirata, socialNetwork.getSoccerMatchCategory().getEvents().get(2).getStateValue());


    }

}