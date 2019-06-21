package main.model.soccerMatchCategory;

import main.model.Category;
import main.model.event.Gender;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Rappresenta la categoria Soccer Match, per eventi di partite di calcio
 */
public class SoccerMatchCategory extends Category<SoccerMatchEvent> {

	public static final String SOCCER_NAME = "Partite di calcio";
	private static final String SOCCER_DESCRIPTION = "Categoria che ha lo scopo di proporre partite di calcio di vario genere.";

	/**
	 * Costruttore SoccerMatchCategory: invoca il costruttore di Category passando come parametri
	 * il nome della categoria e la sua descrizione
	 */
	public SoccerMatchCategory() {
		super(SOCCER_NAME, SOCCER_DESCRIPTION);
	}

	@Override
	public List<SoccerMatchEvent> getEvents() {
		return super.getEvents();
	}

	@Override
	public void setEvents(List<SoccerMatchEvent> events) {
		super.setEvents(events);
	}

	/**
	 * Aggiunge un nuovo evento alla list di eventi
	 *
	 * @param event l'evento da aggiungere
	 */
	@Override
	public void addEvent(SoccerMatchEvent event) {
		super.addEvent(event);
	}

	//utilizzando il builder, questa non serve più
	public SoccerMatchEvent createEvent(String titleIns, int numParIns, int extraNumIns, LocalDate deadLineIns, LocalDate retiredDeadLineIns, String placeIns, LocalDate dateIns, LocalTime timeIns, String durationIns, float indTeeIns, String totTeeIns, LocalDate endDateIns, LocalTime endTimeIns, String ageRangeIns, Gender genderIns, String noteIns, String creator){
		SoccerMatchEvent match = new SoccerMatchEvent(titleIns, numParIns, extraNumIns, deadLineIns, retiredDeadLineIns,  placeIns, dateIns, timeIns, durationIns, indTeeIns, totTeeIns, endDateIns, endTimeIns, ageRangeIns, genderIns, noteIns, creator);


		match.addParticipant(creator);
		this.addEvent(match);
		return match;
	}


}