package main.model.soccerMatchCategory;

import main.model.Category;
import main.model.CreateParameter;
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


	public void createSoccerEvent(CreateParameter passParameter){
		SoccerMatchEvent match = (SoccerMatchEvent) new SoccerMatchEventBuilder()
				.ageRange(passParameter.getAgeRange())
				.gender(passParameter.getGender())
				.title(passParameter.getTitle())
				.numOfParticipants(passParameter.getNumPar())
				.extraParticipants(passParameter.getExtraNum())
				.registrationDeadline(passParameter.getDeadLine())
				.retireDeadline(passParameter.getRetiredDeadLine())
				.place(passParameter.getPlace())
				.date(passParameter.getDate())
				.time(passParameter.getTime())
				.duration(passParameter.getDuration())
				.individualTee(passParameter.getIndTee())
				.teeInclude(passParameter.getTotTee())
				.endDate(passParameter.getEndDate())
				.endTime(passParameter.getEndTime())
				.note(passParameter.getNote())
				.creator(passParameter.getCreator())
				.build(); //problema: questo build è chiamato su ciò che ritorna creator ossia Event...

		match.addParticipant(passParameter.getCreator());
		addEvent(match);

	}





}