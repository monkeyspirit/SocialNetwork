package versione1;

import utilities.FileUtility;
import versione5.CinemaCategory;
import versione5.CinemaEvent;

import javax.swing.text.html.ListView;
import java.util.ArrayList;
import java.util.List;

public class SocialNetwork {

	//Attributi
    private List<Category<? extends Event>> categories;
	private SoccerMatchCategory soccerMatchCategory;
	private CinemaCategory cinemaCategory;
	private List<User> users;
	private User loggedUser; //utente attualmente loggato
	private FileUtility fileUtility;
//	private NotificationsBuilder notificationsHandler;

	/**
	 * Costruttore SocialNetwork
	 */
	public SocialNetwork() {
	    this.categories = new ArrayList<>();
	    this.soccerMatchCategory = new SoccerMatchCategory();
	    this.cinemaCategory = new CinemaCategory();
	    this.categories.add(soccerMatchCategory); //aggiungo la categoria all'array
		this.categories.add(cinemaCategory);
		this.fileUtility = new FileUtility();
//		this.notificationsHandler = new NotificationsBuilder();
	}

	public void addCategory(Category category) {
	    this.categories.add(category);
    }

    public List<Category<? extends Event>> getCategories() {
	    return this.categories;
    }

	public SoccerMatchCategory getSoccerMatchCategory(){
		return this.soccerMatchCategory;
	}

	public CinemaCategory getCinemaCategory(){
		return this.cinemaCategory;
	}


	public List<User> getUsers() { return this.users;	}

//	public NotificationsBuilder getNotificationsHandler() { return this.notificationsHandler; }

	public void addUser(User user) {
		this.users.add(user);
	}

	/**
	 * Metodo per la ricerca di una categoria all'interno del Social Network, la ricerca
	 * viene effettuata attraverso il nome, considerato ceh sono delle costanti non ci possono
	 * essere errori di battitura
	 * @param name
	 * @return
	 */
	public Category findCategoryByName(String name){
		Category found = null;

		for(int i=0; i<categories.size(); i++){
			if ( name.equals(categories.get(i).getName())){
				found = categories.get(i);
			}
		}

		return found;
	}

	/**
	 * Metodo per la ricerca di una categoria all'interno del Social Network attraverso
	 * l'indice della categoria, visto che scegliamo noi l'ordine e l'utente non puo modificarlo
	 * non possono verificarsi errori
	 * @param index
	 * @return categoria trovata
	 */
	public Category findCategoryByIndex(int index){
		return categories.get(index);
	}

	/**
	 * Metodo che serve per il controllo della presenza di un utente dal nome, torna un valore boleano "true"
	 * se l'utente esiste e "false" se invece non esiste
	 * @param userNameToCheck
	 * @return una costante boleana che indica se l'utente esiste o meno
	 */
	public boolean doesUserExist(String userNameToCheck){
		for (User user : users) {
			if(userNameToCheck.equalsIgnoreCase(user.getUsername()))
				return true;
		}
		return false;
	}

	/**
	 * Controlla se l'utente è già presente nella lista utenti e incaso ritorna un suo riferimento
	 * @param usernameToFind il nome dell'utente da cercare
	 * @return l'utente trovato
	 */
	public User findUserByName(String usernameToFind){
		for (User user : users) {
			if(usernameToFind.equalsIgnoreCase(user.getUsername()))
				return user;
		}
		return null;
	}

	/**
	 * Registra un nuovo utente, aggiungendolo alla lisa di utenti e aggiornando il file contenente la list
	 * @param user l'utente da registrare
	 */
	public void registerNewUser(User user) {
		addUser(user); //aggiungo utente all'array
		updateUsersListFile(); //aggiorno il file degli utenti
	}

	/**
	 * Effettua il login dell'utente aggiornando loggedUser come l'utente corrente
	 * @param loggedUser l'utente da loggare
	 */
	public void loginUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	/**
	 * legge il file contenente la lista di utenti registrati lo carica all'interno di users
	 */
	public void loadUsersListFromFile() {
		this.users = fileUtility.readUsersList();
	}

	/**
	 * Aggiorna il file contenente la lista degli utenti scrivendo al suo interno
	 * il contenuto attuale della lista di utenti dell'applicazione.
	 */
	public void updateUsersListFile () {
		fileUtility.writeUsersList(this.users);
	}

	/**
	 *  Salva su file la lista aggiornata di utenti e quella di eventi di tipo Soccer Match
	 */
	public void updateUserAndEventsListFile(){
		fileUtility.writeUsersList(this.users);
		fileUtility.writeSoccerMatchEvents(soccerMatchCategory.getEvents());
	}

	/**
	 * legge il file contenente la lista di EventSoccerMatch lo carica all'interno della lista di eventi della categoria corrispondente
	 */
	public void loadSoccerMatchEventListFromFile() {
		soccerMatchCategory.setEvents(fileUtility.readSoccerMatchEvents());
	}

	public void writeSoccerMatchEventListOnFile() {
        fileUtility.writeSoccerMatchEvents(soccerMatchCategory.getEvents());
    }

	// Metodo che serve per trovare tutti gli eventi a cui e' iscritto un dato utente e ne ritorna i nomi
	public List<String> findEventByUserNameS(String userSession){

		ArrayList<String> eventsUser = new ArrayList<>();

		for(int i=0; i<categories.size(); i++){
			Category catSel = categories.get(i);
			List<Event> eventsByCat = catSel.getEvents();
			for(int j=0; j < eventsByCat.size(); j++){
				if(eventsByCat.get(j).isUserAlreadyRegistered(userSession)== true){
					eventsUser.add((String) eventsByCat.get(j).getTitle().getValue());
				}
			}

		}

		return eventsUser;
	}

	// Simile al precedente ma in questo caso trona un arraylist di eventi, serve per la classe notification
	public List<Event> findEventByUserNameE(String userSession){

		ArrayList<Event> eventsUser = new ArrayList<>();

		for(int i=0; i<categories.size(); i++){
			Category catSel = categories.get(i);
			List<Event> eventsByCat = catSel.getEvents();
			for(int j=0; j < eventsByCat.size(); j++){
				if(eventsByCat.get(j).isUserAlreadyRegistered(userSession)== true){
					eventsUser.add(eventsByCat.get(j));
				}
			}

		}

		return eventsUser;
	}

	/**
	 * Cerca un evento a partire dal suo nome tra tutte le categorie
	 * @param eventName il nome dell'evento da cercare
	 * @return il riferimento all'evento nel caso in cui sia stato trovato
	 */
	public Event findEventByEventName(String eventName){
		for (Category<? extends Event> category : categories) {
			for (Event event : category.getEvents()) {
				if(eventName.equalsIgnoreCase(event.getTitle().getValue()))
					return event;
			}
		}
		return null;
	}

	private List<Event> getEventsCreatedByUser(String user){

		List<Event> eventsCreatedByUser = new ArrayList<>();

		for(Category<? extends Event> category : categories) {

			for (Event event : category.getEvents()) {

				if (event.getCreator().equalsIgnoreCase(user)) {
					eventsCreatedByUser.add(event);
				}
			}
		}
		return eventsCreatedByUser;
	}

	public List<String> getUserThatPlayOtherCreatorEvents(String user){

		List<String> participantsAlreadyPlay = new ArrayList<>();
		List<Event> eventsCreatedByUser = getEventsCreatedByUser(user);

		for(Event event: eventsCreatedByUser){
			for(String participant: event.getParticipants()){
				if(!participantsAlreadyPlay.contains(participant) && !participant.equalsIgnoreCase(user)){
					participantsAlreadyPlay.add(participant);
				}
			}
		}

		return participantsAlreadyPlay;

	}


}
