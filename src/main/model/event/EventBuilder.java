package main.model.event;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class EventBuilder {

    //Attributi della classe Event (quelli facoltativi inizializzati ad un valore di default)
    protected String title = "";
    protected Integer numOfParticipants;
    protected Integer extraParticipants = 0;
    protected LocalDate registrationDeadline;
    protected LocalDate retireDeadline = null;
    protected String place;
    protected LocalDate date;
    protected LocalTime time;
    protected String duration = "";
    protected Float individualTee;
    protected String teeInclude = "";
    protected LocalDate endDate = null;
    protected LocalTime endTime = null;
    protected String note = "";

    protected State state;
    protected String type;
    protected String creator = "";


    public EventBuilder title(String title) {
        this.title = title;
        return this;
    }

    public EventBuilder numOfParticipants(int numOfParticipants) {
        this.numOfParticipants = numOfParticipants;
        return this;
    }

    public EventBuilder extraParticipants(int extraParticipants) {
        this.extraParticipants = extraParticipants;
        return this;
    }

    public EventBuilder registrationDeadline(LocalDate registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
        return this;
    }

    public EventBuilder retireDeadline(LocalDate retireDeadline) {
        this.retireDeadline = retireDeadline;
        return this;
    }

    public EventBuilder place(String place) {
        this.place = place;
        return this;
    }

    public EventBuilder date(LocalDate date) {
        this.date = date;
        return this;
    }

    public EventBuilder time(LocalTime time) {
        this.time = time;
        return this;
    }

    public EventBuilder duration(String duration) {
        this.duration = duration;
        return this;
    }

    public EventBuilder individualTee(Float individualTee) {
        this.individualTee = individualTee;
        return this;
    }

    public EventBuilder teeInclude(String teeInclude) {
        this.teeInclude = teeInclude;
        return this;
    }

    public EventBuilder endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public EventBuilder endTime(LocalTime entTime) {
        this.endTime = endTime;
        return this;
    }

    public EventBuilder state(State state) {
        this.state = state;
        return this;
    }

    public EventBuilder type(String type) {
        this.type = type;
        return this;
    }

    public EventBuilder creator(String creator) {
        this.creator = creator;
        return this;
    }

    public EventBuilder note(String note) {
        this.note = note;
        return this;
    }

    public abstract Event build();
}
