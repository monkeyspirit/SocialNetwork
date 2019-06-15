package main.model.cinemaCategory;

import main.model.event.Event;
import main.model.event.Field;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Rappresenta un evento di tipo cinema
 */
public class CinemaEvent extends Event {

    //Costanti della classe SoccerMatchEvent
    public static final String TYPE = "CinemaEvent"; //serve per la deserializzazione di una lista di eventi
    public static final String GENRE_NAME = "Genere";
    public static final String GENRE_DESCRIPTION = "Indica la tipologia del film proposto, può elencare più generi";
    public static final String EXTRAMEALS_NAME = "Pasti come spesa extra";
    public static final String EXTRAMEALS_DESCRIPTION = "Indica una spesa in più che si può decidere se sostenere o meno";
    public static final String GADGETEXTRA_NAME = "Gadget come spesa extra";
    public static final String GADGETEXTRA_DESCRIPTION = "Indica una spesa in più che si può decidere se sostenere o meno";
    public static final String RINFRESCHIEXTRA_NAME = "Rinfresco come spesa extra";
    public static final String RINFRESCHIEXTRA_DESCRIPTION = "Indica una spesa in più che si può decidere se sostenere o meno";


    private Field<List<String>> genres = new Field(GENRE_NAME, GENRE_DESCRIPTION);
    private Field<Float> extraMeals = new Field(EXTRAMEALS_NAME, EXTRAMEALS_DESCRIPTION);
    private Field<Float> gadgetExtra = new Field(GADGETEXTRA_NAME, GADGETEXTRA_DESCRIPTION);
    private Field<Float> rinfreschiExtra = new Field(RINFRESCHIEXTRA_NAME, RINFRESCHIEXTRA_DESCRIPTION);

    /**
     * Costruttore SoccerMatchEvent: chiama il costruttore dei campi obbligatori
     * della classe madre e inizializza l'attributo valore di gender e ageGroup.
     */
    public CinemaEvent(String titleIns, int numParIns, int extraParIns, LocalDate deadLineIns, LocalDate retiredDeadLineIns, String placeIns, LocalDate dateIns, LocalTime timeIns, String durationIns, float indTeeIns, String totTeeIns, LocalDate endDateIns, LocalTime endTimeIns, String noteIns, String creatorIns, List<String> genres, float extraMealsTee, float extraRinfrescoTeeINs, float extraGadgetTeeIns) {
        super(TYPE, titleIns, numParIns, extraParIns, deadLineIns, retiredDeadLineIns, placeIns, dateIns, timeIns, durationIns, indTeeIns, totTeeIns, endDateIns, endTimeIns, noteIns, creatorIns);
        this.genres.setValue(genres);
        this.extraMeals.setValue(extraMealsTee);
        this.gadgetExtra.setValue(extraGadgetTeeIns);
        this.rinfreschiExtra.setValue(extraRinfrescoTeeINs);
    }

    public Field<List<String>> getGenres() { return genres; }

    public Field<Float> getExtraMeals() { return extraMeals; }

    public Field<Float> getGadgetExtra() { return gadgetExtra; }

    public Field<Float> getRinfreschiExtra() { return rinfreschiExtra; }

}
