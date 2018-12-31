package versione1;

import utilities.FileUtility;
import versione2.notifications.NotificationsHandler;

import java.util.ArrayList;

public class SocialNetwork {

	//Attributi
	private ArrayList<Category> categories;
	private ArrayList<User> users;
	private User loggedUser; //utente attualmente loggato
	private FileUtility fileUtility;
//	private NotificationsHandler notificationsHandler;

	
	/**
	 * Costruttore SocialNetwork
	 */
	public SocialNetwork() {
		this.categories = new ArrayList<Category>();
		this.users = new ArrayList<>();
		this.fileUtility = new FileUtility();
//		this.notificationsHandler = new NotificationsHandler();
	}

	//Metodi
	public void addCategory(Category category) {
		this.categories.add(category);
	}
	
	public ArrayList<Category> getCategories(){
		return this.categories;
	}

	public ArrayList<User> getUsers() { return this.users;	}

//	public NotificationsHandler getNotificationsHandler() { return this.notificationsHandler; }

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
	 * @param accessName
	 * @return una costante boleana che indica se l'utente esiste o meno
	 */
	public boolean doesUserExist(String accessName){
		boolean exist = false;

		for(int i=0; i<users.size(); i++){
			if(accessName.equalsIgnoreCase(users.get(i).getUsername())){
				exist = true;
			}
		}

		return exist;
	}

	// Controlla se l'utente è già presente nella lista utenti e incaso lo ritorna come valore
	public User findUserByName(String username){
		User found = null;
		for(int i=0; i<users.size(); i++){
			if(username.equalsIgnoreCase(users.get(i).getUsername())){
				found = users.get(i);
			}
		}
		return found;
	}

	/**
	 * Registra un nuovo utente, aggiungendolo alla lisa di utenti e aggiornando il file contenente la list
	 * @param user
	 */
	public void registerNewUser(User user) {
		addUser(user); //aggiungo utente all'array
		updateUsersListFile(); //aggiorno il file degli utenti
	}

	public void loginUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	/**
	 * legge il file conenente la lista di utenti registrati lo carica all'interno di users
	 */
	public void loadUsersListFromFile() {
		this.users = fileUtility.readUsersList();
	}

	/**
	 * Aggiorna il file contenente la lista degli utenti.
	 */
	private void updateUsersListFile () {
		System.out.println("Aggiorno il file della lista di utenti");
		fileUtility.writeUsersList(this.users);
	}

	// Metodo che serve per trovare tutti gli eventi a cui è iscritto un dato utente e ne ritorna i nomi
	public ArrayList<String> findEventByUserNameS(String userSession){

		ArrayList<String> eventsUser = new ArrayList<String>();

		for(int i=0; i<categories.size(); i++){
			Category catSel = categories.get(i);
			ArrayList<Event> eventsByCat = catSel.getEvents();
			for(int j=0; j < eventsByCat.size(); j++){
				if(eventsByCat.get(j).alrRegister(userSession)== true){
					eventsUser.add((String) eventsByCat.get(j).getTitle().getValue());
				}
			}

		}

		return eventsUser;
	}

	// Simile al precedente ma in questo caso trona un arraylist di eventi, serve per la classe notification
	public ArrayList<Event> findEventByUserNameE(String userSession){

		ArrayList<Event> eventsUser = new ArrayList<>();

		for(int i=0; i<categories.size(); i++){
			Category catSel = categories.get(i);
			ArrayList<Event> eventsByCat = catSel.getEvents();
			for(int j=0; j < eventsByCat.size(); j++){
				if(eventsByCat.get(j).alrRegister(userSession)== true){
					eventsUser.add(eventsByCat.get(j));
				}
			}

		}

		return eventsUser;
	}

	public Event findEventByEventName(String eventName){
		Event find = null;

		for(int i=0; i<categories.size(); i++){
			for(int k=0; k<categories.get(i).getEvents().size(); k++){
				if(eventName.equals((String) categories.get(i).getEvents().get(k).getTitle().getValue())){
					find = categories.get(i).getEvents().get(k);
				}
			}
		}

		return find;
	}


}
