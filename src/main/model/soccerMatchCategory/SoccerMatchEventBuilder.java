package main.model.soccerMatchCategory;

import main.model.event.EventBuilder;
import main.model.event.Gender;

public class SoccerMatchEventBuilder extends EventBuilder {

    //Inizializzo con valori di default
    private Gender gender = Gender.Maschile;
    private String ageRange = "";

    public SoccerMatchEventBuilder() {

    }

    public SoccerMatchEventBuilder gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public SoccerMatchEventBuilder ageRange(String ageRange) {
        this.ageRange = ageRange;
        return this;
    }

    @Override
    public SoccerMatchEvent build() {
        /* qui si può controllare se l'evento è stato costruito bene prima di crearlo
         eventualmente impedendone la creazione così da togliere i controlli dal controller.
         Inoltre con degli if-case si possono restituire istanze diverse in base al numero
         di campi impostati al momento della chiamata di build()
        */
        return new SoccerMatchEvent(title, numOfParticipants, extraParticipants, registrationDeadline, retireDeadline, place, date, time, duration, individualTee, teeInclude, endDate, endTime, ageRange, gender, note, creator);
    }
}
