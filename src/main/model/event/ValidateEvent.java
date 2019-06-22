package main.model.event;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTimePicker;
import com.sun.xml.internal.txw2.TXW;
import javafx.scene.control.*;
import main.controllers.EventCreateController;
import main.model.MyUtil;
import main.model.SocialNetwork;

import javax.xml.soap.Text;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.spi.TimeZoneNameProvider;

public class ValidateEvent {

    private EventCreateController eventCreateController;
    private int counterTitleNull = 1;

    public ValidateEvent(EventCreateController eventCreateController){
        this.eventCreateController=eventCreateController;
    }

    public boolean validateCategory(String category){
        if (category == null) {
            return false;
        }
        else{
            eventCreateController.setCategoryIns(category);
            return true;
        }
    }

    public boolean validateTitle(String title, SocialNetwork socialNetwork, String category){
        if (title != null && socialNetwork.findCategoryByName(category).doesEventAlreadyExist(title) == true) {
            return false;
        } else if (title != null && socialNetwork.findCategoryByName(category).doesEventAlreadyExist(title)  == false) {
            eventCreateController.setTitleIns(title);
            return true;
        }
        else{
            String titleNull = "Titolo ("+counterTitleNull+")";
            eventCreateController.setTitleIns(titleNull);
            counterTitleNull++;
            return true;
        }
    }

    public boolean validateNumPar(String numPTxtF){
        if (numPTxtF.isEmpty() || !MyUtil.checkInteger(numPTxtF)) {
           return false;
        } else {
            eventCreateController.setNumParIns(Integer.parseInt(numPTxtF));
            return true;
        }
    }

    public boolean validateExtraNumPar(String extraNumParTxt){
        if (extraNumParTxt.isEmpty()) {
            eventCreateController.setExtraNumParIns(0);
            return true;
        } else {
            if (!MyUtil.checkInteger(extraNumParTxt)) {
                return false;
            } else {
                eventCreateController.setExtraNumParIns(Integer.parseInt(extraNumParTxt));
                return true;
            }
        }
    }

    public boolean validateDeadLine(LocalDate deadLine){
        if (deadLine == null) {
            return false;
        }
        else{
            eventCreateController.setDeadLineIns(deadLine);
            return true;
        }
    }

    public void validateRetiredDeadLine(LocalDate retiredDeadLine, LocalDate deadLineIns){
        if (retiredDeadLine == null) {
            eventCreateController.setRetiredDeadLineIns(deadLineIns);
        } else {
            eventCreateController.setRetiredDeadLineIns(retiredDeadLine);
        }
    }

    public boolean validatePlace(String placeTxtF){
        if(placeTxtF.isEmpty() || !MyUtil.checkString(placeTxtF) ){
            return false;
        }
        else{
            eventCreateController.setPlaceIns(placeTxtF);
            return true;
        }
    }

    public boolean validateDate(LocalDate date){
        if ((date == null)) {
            return false;
        } else {
            eventCreateController.setDateIns(date);
            return true;
        }
    }

    public boolean validateTime(LocalTime time, boolean dateIsVal){
        if (time == null || dateIsVal == false) {
            return false;
        } else {
            eventCreateController.setTimeIns(time);
            return true;
        }
    }

    public boolean validateTeeIndividual(boolean teeA, String individualTee){
        if (teeA) {
            if (individualTee.isEmpty() || !MyUtil.checkFloat(individualTee)) {
                return false;
            } else {
                eventCreateController.setIndTeeIns(Float.parseFloat(individualTee));
                return true;
            }
        } else {
            eventCreateController.setIndTeeIns(0);
            return true;
        }
    }

    public void validateTotTee(String tot){
        eventCreateController.setTotTeeIns(tot);
    }

    public void validateEndDate(LocalDate endDateDP, boolean dateIsVal, Integer durBigCB){
        if (endDateDP != null && dateIsVal == true) {
            eventCreateController.setEndDateIns(endDateDP);
        } else if (endDateDP == null ){
            if (durBigCB != null) {
                eventCreateController.setDurationIns(String.valueOf(durBigCB));
            }
        }
    }

    public boolean validateEndTime(LocalTime endTimeTP, LocalDate endDateIns, LocalDate dateIns, LocalTime timeIns, Integer durBigCB, Integer durLitCB){
        if (endTimeTP != null) {
            if (endTimeTP.isBefore(timeIns) && endDateIns.isEqual(dateIns)) {
                return false;
            } else {
                eventCreateController.setEndTimeIns( endTimeTP);
                return true;
            }

        } else {
            if (durBigCB!= null && durLitCB != null) {
                eventCreateController.setDurationIns(durBigCB + ":" + durLitCB);
            }
            return  true;
        }
    }

    public boolean validateEndDateEndTime(LocalDate endDateDP, LocalTime endTimeTP, String durationIns){
        if ((endDateDP != null && endTimeTP == null) && durationIns == null) {
            return false;
        } else {
           return true;
        }

    }

    public void validateNote(String note){

        eventCreateController.setNoteIns(note);
    }

    public boolean validateAge(Integer minAgeCB, Integer maxAgeCB, AgeGroup ageGroup){
        if (minAgeCB != null && maxAgeCB != null) {
            ageGroup.setRange(minAgeCB, maxAgeCB);
            eventCreateController.setAgeRangeIns(ageGroup.getRange());
            return true;
        } else {
            return false;
        }
    }

    public boolean validateGender(Gender genderCB){
        if (genderCB == null) {
            return false;
        } else {
            eventCreateController.setGenderIns(genderCB);
            return true;
        }

    }

    public void validateDurationAndTimeDate(LocalDate endDateIns, String durationIns, LocalTime endTimeIns, LocalTime timeIns, LocalDate dateIns, int durH, int durM){
        if (endDateIns == null && durationIns != null) {
            eventCreateController.setEndDateIns(dateIns.plusDays(Integer.parseInt(durationIns)));
        } else if (endDateIns == null && durationIns == null) {
            eventCreateController.setEndDateIns(dateIns.plusDays(1));
        } else {
            if (endTimeIns == null && durationIns != null) {
                eventCreateController.setEndTimeIns(timeIns.plusHours(durH).plusMinutes(durM));

            }
        }

    }

    public boolean validateTypeOfFilm(List<CheckBox> typeOfFilmCheckList){
         List<String>  typeOfFilmIns = new ArrayList<>();

        for (CheckBox check : typeOfFilmCheckList) {
            if (check.isSelected()) {
                typeOfFilmIns.add(check.getText());
            }
        }

        eventCreateController.setTypeOfFilmIns(typeOfFilmIns);

        if (typeOfFilmIns.isEmpty()) {
            return false;

        } else {
            return true;
        }
    }

    public boolean validateExtraTeeIns(String pastiExtraTF, String gadgetExtraTF, String rinfrescoExtraTF, boolean pastiCheckBox, boolean gadgetCheckBox, boolean rinfrescoCheckBox){

        boolean pasti = false;
        boolean rinfresco = false;
        boolean gadget = false;

        if (pastiCheckBox) {
            if (MyUtil.checkFloat(pastiExtraTF)) {
                eventCreateController.setExtraPastiTeeIns(Float.parseFloat(pastiExtraTF));
                pasti = true;
            } else {
                pasti = false;
            }
        } else {
            eventCreateController.setExtraPastiTeeIns(0);
            pasti = true;
        }

        if (gadgetCheckBox) {
            if (MyUtil.checkFloat(gadgetExtraTF)) {
               eventCreateController.setExtraGadgetTeeIns(Float.parseFloat(gadgetExtraTF));
                gadget = true;
            } else {
                gadget = false;
            }
        } else {
            eventCreateController.setExtraGadgetTeeIns(0);
            gadget = true;
        }

        if (rinfrescoCheckBox) {
            if (MyUtil.checkFloat(rinfrescoExtraTF)) {
                eventCreateController.setExtraRinfrescoTeeIns(Float.parseFloat(rinfrescoExtraTF));
                rinfresco = true;
            } else {
                rinfresco = false;
            }
        } else {
            eventCreateController.setExtraRinfrescoTeeIns(0);
            rinfresco = true;
        }

       return (pasti && gadget && rinfresco);

    }

}
