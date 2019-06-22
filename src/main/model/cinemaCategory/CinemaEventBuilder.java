package main.model.cinemaCategory;

import main.model.event.EventBuilder;

import java.util.ArrayList;
import java.util.List;

public class CinemaEventBuilder extends EventBuilder {

    private List<String> genres = new ArrayList<>();
    private Float extraMeals = Float.valueOf(0);
    private Float gadgetExtra = Float.valueOf(0);
    private Float rinfreschiExtra = Float.valueOf(0);

    public CinemaEventBuilder() {

    }

    public CinemaEventBuilder genres(List<String> genres) {
        this.genres = genres;
        return this;
    }

    public CinemaEventBuilder extraMeals(Float extraMeals) {
        this.extraMeals = extraMeals;
        return this;
    }

    public CinemaEventBuilder gadgetExtra(Float gadgetExtra) {
        this.gadgetExtra = gadgetExtra;
        return this;
    }

    public CinemaEventBuilder rinfreschiExtra(Float rinfreschiExtra) {
        this.rinfreschiExtra = rinfreschiExtra;
        return this;
    }

    @Override
    public CinemaEvent build() {
        /* qui si può controllare se l'evento è stato costruito bene prima di crearlo
         eventualmente impedendone la creazione così da togliere i controlli dal controller.
         Inoltre con degli if-case si possono restituire istanze diverse in base al numero
         di campi impostati al momento della chiamata di build()
        */
        return new CinemaEvent(title, numOfParticipants, extraParticipants, registrationDeadline, retireDeadline, place, date, time, duration, individualTee, teeInclude, endDate, endTime, note, creator, genres, extraMeals, rinfreschiExtra, gadgetExtra);
    }
}
