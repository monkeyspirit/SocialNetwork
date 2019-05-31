package main.model.cinemaCategory;

import main.model.Category;

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

}
