package versione1;

import java.util.ArrayList;
import java.util.Date;

public abstract class Event{

	//Attributes of the event class that are the same for all the events
	private ArrayList<Field> fields;
	private Field title = new Field("Titolo","Campo facoltativo che consiste in un nome di fantasia attribuito all’evento");
	private Field numOfPartecipants = new Field("Numero di partecipanti", "Campo obbligatorio che stabilisce il numero di persone da coinvolgere nell'evento");
	private Field registrationDeadline = new Field("Termine ultimo iscrizione", "Campo obbligatorio che inidica l'ultima data possibile per iscriversi");
	private Field place = new Field("Luogo", "Campo obbligatorio che indica l’indirizzo del luogo che ospiterà l’evento oppure, se l’evento è itinerante, il luogo di ritrovo dei partecipanti");
	private Field date = new Field("Data","Campo obbligatorio che indica la data in cui l’evento proposto deve svolgersi o, nel caso l’evento non termini nello stesso giorno in cui ha inizio, la data di inizio dell’evento");
	private Field time = new Field("Ora", "Campo obbligatorio che indica l’ora in cui i partecipanti dovranno trovarsi nel luogo “Luogo” in data “Data” per dare inizio all’evento");
	private Field duration = new Field("Durata", "Campo facoltativo che indica la durata in termini di numero (approssimativo) di ore e minuti, per gli eventi che si esauriscono in un sol giorno, o in termini di numero esatto di giorni, per gli eventi che occupano più giorni consecutivi");
	private Field indTee = new Field("Quota individuale","Campo obbligatorio che indica la spesa (o una stima della stessa) che ogni partecipante all’iniziativa dovrà sostenere (si noti che la spesa può anche essere nulla)");
	private Field teeInclude = new Field("Compreso nella quota", "Campo facoltativo che indica tutte le voci di spesa comprese nell’ammontare indicato nella “Quota individuale”");
	private Field endDate = new Field("Data conclusiva","Campo facoltativo che fissa la data di conclusione dell’evento");
	private Field endTime = new Field("Ora conclusiva", "Campo facoltativo che stima l’ora di conclusione dell’evento");
	private Field note = new Field("Note","Campo facoltativo contenente informazioni aggiuntive circa l’evento");


	/*
	Pietro: dite che potremmo fare a meno dell'array di eventi?
        Per me sì: dal momento che quando dovremo ricavare informazioni su un campo, anche per piazzarlo
        nell'interfaccia grafica, useremo il get su quel campo specifico..
        L'alternativa è avere un metodo getCampo(String nomeCampo) che itera l'array cercando
        un campo con nome = nomeCampo ed eventualmente lo restituisce.
        Qual è la soluzione migliore secondo voi?
	 */

	/*
	Da qualche parte dobbiamo mettere questo pezzo di codice, direi di metterlo nel costruttore, quando si creerà la classe figlia il costruttore della padre si occuperà di
	inserire i campi nell'arrayLit di campi
		this.fields = new ArrayList<>();
		fields.add(title);
		fields.add(numOfPartecipants);
		fields.add(registrationDeadline);
		fields.add(place);
		fields.add(date);
		fields.add(time);
		fields.add(duration);
		fields.add(indTee);
		fields.add(teeInclude);
		fields.add(endDate);
		fields.add(endTime);
		fields.add(note);
	*/

    /**
     * Costruttore vuoto: viene inizializzato l'array di campi, ciascuno dei quali con
     * nome e descrizione ma senza valore.
     */
	public Event() {
		this.fields = new ArrayList<>();
		init();
	}

    /**
     * Costruttore dei campi obbligatori: viene popolato l'attributo valore di ciascun campo obbligatorio.
     * @param numOfPartecipants numero dei partecipanti
     * @param registrationDeadline termine ultimo iscrizione
     * @param place luogo dell'evento
     * @param date data dell'evento
     * @param time ora dell'evento
     * @param indTee quota individuale
     */
	public Event(int numOfPartecipants, Date registrationDeadline, String place, Date date, Date time, float indTee) {
	    this.numOfPartecipants.setValue(numOfPartecipants);
	    this.registrationDeadline.setValue(registrationDeadline);
	    this.place.setValue(place);
	    this.date.setValue(date);
	    this.time.setValue(time);
	    this.indTee.setValue(indTee);
    }

	private void init() {
		fields.add(title);
		fields.add(numOfPartecipants);
		fields.add(registrationDeadline);
		fields.add(place);
		fields.add(date);
		fields.add(time);
		fields.add(duration);
		fields.add(indTee);
		fields.add(teeInclude);
		fields.add(endDate);
		fields.add(endTime);
		fields.add(note);
	}

    public ArrayList<Field> getFields() {
        return fields;
    }

    public Field getTitle() {
        return title;
    }

    public Field getNumOfPartecipants() {
        return numOfPartecipants;
    }

    public Field getRegistrationDeadline() {
        return registrationDeadline;
    }

    public Field getPlace() {
        return place;
    }

    public Field getDate() {
        return date;
    }

    public Field getTime() {
        return time;
    }

    public Field getDuration() {
        return duration;
    }

    public Field getIndTee() {
        return indTee;
    }

    public Field getTeeInclude() {
        return teeInclude;
    }

    public Field getEndDate() {
        return endDate;
    }

    public Field getEndTime() {
        return endTime;
    }

    public Field getNote() {
        return note;
    }
}
