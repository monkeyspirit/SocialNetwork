package versione1;

import java.util.ArrayList;

public class SocialNetwork {

	//Attributi
	private ArrayList<Category> categories;
	private ArrayList<User> users;
	
	/**
	 * Costruttore SocialNetwork
	 */
	public SocialNetwork() {
		this.categories = new ArrayList<Category>();
		this.users = new ArrayList<>();
	}

	//Metodi
	public void addCategory(Category category) {
		this.categories.add(category);
	}
	
	public ArrayList<Category> getCategories(){
		return this.categories;
	}

	public ArrayList<User> getUsers() { return users;	}

	public void addUser(User user) { this.users.add(user);	}

	/**
	 * Metodo per la ricerca di una categoria all'interno del Social Network, la ricerca
	 * viene effettuata attraverso il nome, considerato ceh sono delle costanti non ci possono
	 * essere errori di battitura
	 * @param name
	 * @return
	 */
	public Category findCategoryByName(String name){
		Category find = null;

		for(int i=0; i<categories.size(); i++){
			if ( name.equals(categories.get(i).getName())){
				find = categories.get(i);
			}
		}

		return find;
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
			if(accessName.equals(users.get(i).getUsername())){
				exist = true;
			}
		}

		return exist;
	}

	public User findUserByName(String username){
		User finded = null;
		for(int i=0; i<users.size(); i++){
			if(username.equals(users.get(i).getUsername())){
				finded = users.get(i);
			}
		}
		return finded;
	}
}
