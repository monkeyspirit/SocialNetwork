package versione5;

import versione1.Event;
import versione1.Field;
import versione2.StateValue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CinemaEvent extends Event {

    //Costanti della classe SoccerMatchEvent
    public static final String TYPE = "CinemaEvent"; //serve per la deserializzazione di una lista di eventi
    public static final String FILMTYPE_NAME = "Genere";
    public static final String FILMTYPE_DESCRIPTION = "Indica la tipologia del film proposto, può elencare più generi";

    private Field<List<String>> types = new Field(FILMTYPE_NAME,FILMTYPE_DESCRIPTION );

    /**
     * Costruttore SoccerMatchEvent: chiama il costruttore dei campi obbligatori
     * della classe madre e inizializza l'attributo valore di gender e ageGroup.
     * @param titleIns
     * @param numParIns
     * @param deadLineIns
     * @param placeIns
     * @param dateIns
     * @param timeIns
     * @param durationIns
     * @param indTeeIns
     * @param totTeeIns
     * @param endDateIns
     * @param endTimeIns
     * @param noteIns
     * @param creatorIns
     * @param typesIns
     */
    public CinemaEvent(String titleIns, int numParIns, int extraParIns, LocalDate deadLineIns, LocalDate retiredDeadLineIns, String placeIns, LocalDate dateIns, LocalTime timeIns, String durationIns, float indTeeIns, String totTeeIns, LocalDate endDateIns, LocalTime endTimeIns, StateValue stateValue, LocalDate stateSwitch, String noteIns, String creatorIns, List<String> typesIns) {
        super(TYPE, titleIns, numParIns, extraParIns, deadLineIns, retiredDeadLineIns, placeIns, dateIns, timeIns, durationIns, indTeeIns, totTeeIns, endDateIns, endTimeIns, stateValue, stateSwitch, noteIns, creatorIns);
        this.types.setValue(typesIns);
    }

    public Field<List<String>> getTypes() { return types; }

    public void setTypes(Field<List<String>> types) { this.types = types; }
}
