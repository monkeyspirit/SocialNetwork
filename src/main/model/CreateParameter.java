package main.model;

import javafx.scene.control.CheckBox;
import main.model.event.Gender;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CreateParameter {

    private String category;
    private String creator;
    private String title;
    private int numPar;
    private int extraNum;
    private LocalDate deadLine;
    private LocalDate retiredDeadLine;
    private String place;
    private LocalDate date;
    private LocalTime time;
    private float indTee;
    private String totTee;
    private LocalDate endDate;
    private LocalTime endTime;
    private String note;
    private String duration;
    //Solo per cinema
    private List<String> typeOfFilm;
    private float extraPastiTee, extraRinfrescoTee, extraGadgetTee;
    //Solo per calcio
    private String ageRange;
    private Gender gender;


    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory(){
        return category;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumPar() {
        return numPar;
    }

    public void setNumPar(int numPar) {
        this.numPar = numPar;
    }

    public int getExtraNum() {
        return extraNum;
    }

    public void setExtraNum(int extraNum) {
        this.extraNum = extraNum;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    public LocalDate getRetiredDeadLine() {
        return retiredDeadLine;
    }

    public void setRetiredDeadLine(LocalDate retiredDeadLine) {
        this.retiredDeadLine = retiredDeadLine;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public float getIndTee() {
        return indTee;
    }

    public void setIndTee(float indTee) {
        this.indTee = indTee;
    }

    public String getTotTee() {
        return totTee;
    }

    public void setTotTee(String totTee) {
        this.totTee = totTee;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<String> getTypeOfFilm() {
        return typeOfFilm;
    }

    public void setTypeOfFilm(List<String> typeOfFilm) {
        this.typeOfFilm = typeOfFilm;
    }

    public float getExtraPastiTee() {
        return extraPastiTee;
    }

    public void setExtraPastiTee(float extraPastiTee) {
        this.extraPastiTee = extraPastiTee;
    }

    public float getExtraRinfrescoTee() {
        return extraRinfrescoTee;
    }

    public void setExtraRinfrescoTee(float extraRinfrescoTee) {
        this.extraRinfrescoTee = extraRinfrescoTee;
    }

    public float getExtraGadgetTee() {
        return extraGadgetTee;
    }

    public void setExtraGadgetTee(float extraGadgetTee) {
        this.extraGadgetTee = extraGadgetTee;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
