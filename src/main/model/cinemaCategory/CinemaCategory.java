package main.model.cinemaCategory;

import main.model.Category;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Classe che rappresenta un evento di tipo Cinema
 */
public class CinemaCategory extends Category<CinemaEvent> {

    public static final String CINEMA_NAME = "Cinema";
    public static final String CINEMA_DESCRIPTION = "Categoria che ha lo scopo di proporre la visione di film di vario genere.";

    /**
     * Costruttore Category: inizializza il nome e la descrizione della categoria (figlia)
     * e la list degli eventi associati alla categoria creata
     */
    public CinemaCategory() { super(CINEMA_NAME, CINEMA_DESCRIPTION); }

    @Override
    public List<CinemaEvent> getEvents() {
        return super.getEvents();
    }

    @Override
    public void setEvents(List<CinemaEvent> events) {
        super.setEvents(events);
    }

    /**
     * Aggiunge un nuovo evento alla list di eventi
     * @param event l'evento da aggiungere
     */
    @Override
    public void addEvent(CinemaEvent event) {
        super.addEvent(event);
    }

    //dal momento che usiamo il builder, non serve questo metodo.
    public CinemaEvent createEvent(String titleIns, int numParIns, int extraNumIns, LocalDate deadLineIns, LocalDate retiredDeadLineIns, String placeIns, LocalDate dateIns, LocalTime timeIns, String durationIns, float indTeeIns, String totTeeIns, LocalDate endDateIns, LocalTime endTimeIns, String noteIns, String creator, List<String> typeOfFilmIns, float extraPastiTeeIns,  float extraRinfrescoTeeINs, float extraGadgetTeeIns){
        CinemaEvent match = new CinemaEvent(titleIns, numParIns, extraNumIns, deadLineIns, retiredDeadLineIns,  placeIns, dateIns, timeIns, durationIns, indTeeIns, totTeeIns, endDateIns, endTimeIns, noteIns, creator, typeOfFilmIns, extraPastiTeeIns,  extraRinfrescoTeeINs, extraGadgetTeeIns);
        match.addParticipant(creator);
        this.addEvent(match);
        return match;
    }

}
