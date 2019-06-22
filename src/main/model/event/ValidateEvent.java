package main.model.event;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTimePicker;
import com.sun.xml.internal.txw2.TXW;
import javafx.scene.control.*;
import main.controllers.EventCreateController;
import main.model.Category;
import main.model.CreateParameter;
import main.model.MyUtil;
import main.model.SocialNetwork;

import javax.xml.soap.Text;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.spi.TimeZoneNameProvider;

public class ValidateEvent {

    //private EventCreateController eventCreateController;
    private CreateParameter passParameter;
    private int counterTitleNull = 1;

   /* public ValidateEvent(EventCreateController eventCreateController){
        this.eventCreateController=eventCreateController;
    }
    */

    public ValidateEvent() {
        passParameter = new CreateParameter();
    }

    public CreateParameter getPassParameter(){
        return passParameter;
    }

    public boolean validateCategory(String category) {
        if (category == null) {
            return false;
        } else {
            passParameter.setCategory(category);
            return true;
        }
    }

    public boolean validateTitle(String title, SocialNetwork socialNetwork) {
        if (title != null && socialNetwork.findCategoryByName(passParameter.getCategory()).doesEventAlreadyExist(title) == true) {
            return false;
        } else if (title != null && socialNetwork.findCategoryByName(passParameter.getCategory()).doesEventAlreadyExist(title) == false) {
            passParameter.setTitle(title);
            return true;
        } else {
            String titleNull = "Titolo (" + counterTitleNull + ")";
            passParameter.setTitle(titleNull);
            counterTitleNull++;
            return true;
        }
    }

    public boolean validateNumPar(String numPTxtF) {
        if (numPTxtF.isEmpty() || !MyUtil.checkInteger(numPTxtF)) {
            return false;
        } else {
            passParameter.setNumPar(Integer.parseInt(numPTxtF));
            return true;
        }
    }

    public boolean validateExtraNumPar(String extraNumParTxt) {
        if (extraNumParTxt.isEmpty()) {
            passParameter.setExtraNum(0);
            return true;
        } else {
            if (!MyUtil.checkInteger(extraNumParTxt)) {
                return false;
            } else {
                passParameter.setExtraNum(Integer.parseInt(extraNumParTxt));
                return true;
            }
        }
    }

    public boolean validateDeadLine(LocalDate deadLine) {
        if (deadLine == null) {
            return false;
        } else {
            passParameter.setDeadLine(deadLine);
            return true;
        }
    }

    public void validateRetiredDeadLine(LocalDate retiredDeadLine) {
        if (retiredDeadLine == null) {
            passParameter.setRetiredDeadLine(passParameter.getDeadLine());
        } else {
            passParameter.setRetiredDeadLine(retiredDeadLine);
        }
    }

    public boolean validatePlace(String placeTxtF) {
        if (placeTxtF.isEmpty() || !MyUtil.checkString(placeTxtF)) {
            return false;
        } else {
            passParameter.setPlace(placeTxtF);
            return true;
        }
    }

    public boolean validateDate(LocalDate date) {
        if ((date == null)) {
            return false;
        } else {
            passParameter.setDate(date);
            return true;
        }
    }

    public boolean validateTime(LocalTime time, boolean dateIsVal){
        if (time == null || dateIsVal == false) {
            return false;
        } else {
            passParameter.setTime(time);
            return true;
        }
    }

    public boolean validateTeeIndividual(boolean teeA, String individualTee){
        if (teeA) {
            if (individualTee.isEmpty() || !MyUtil.checkFloat(individualTee)) {
                return false;
            } else {
                passParameter.setIndTee(Float.parseFloat(individualTee));
                return true;
            }
        } else {
            passParameter.setIndTee(0);
            return true;
        }
    }

    public void validateTotTee(String tot){
        passParameter.setTotTee(tot);
    }

    public void validateEndDate(LocalDate endDateDP, boolean dateIsVal, Integer durBigCB){
        if (endDateDP != null && dateIsVal == true) {
            passParameter.setEndDate(endDateDP);
        } else if (endDateDP == null ){
            if (durBigCB != null) {
                passParameter.setDuration(String.valueOf(durBigCB));
            }
        }
    }

    public boolean validateEndTime(LocalTime endTimeTP, Integer durBigCB, Integer durLitCB){
        if (endTimeTP != null) {
            if (endTimeTP.isBefore(passParameter.getTime()) && passParameter.getEndDate().isEqual(passParameter.getDate())) {
                return false;
            } else {
                passParameter.setEndTime( endTimeTP);
                return true;
            }

        } else {
            if (durBigCB!= null && durLitCB != null) {
                passParameter.setDuration(durBigCB + ":" + durLitCB);
            }
            return  true;
        }
    }

    public boolean validateEndDateEndTime(LocalDate endDateDP, LocalTime endTimeTP){
        if ((endDateDP != null && endTimeTP == null) && passParameter.getDuration() == null) {
            return false;
        } else {
           return true;
        }

    }

    public void validateNote(String note){

        passParameter.setNote(note);
    }

    public boolean validateAge(Integer minAgeCB, Integer maxAgeCB, AgeGroup ageGroup){
        if (minAgeCB != null && maxAgeCB != null) {
            ageGroup.setRange(minAgeCB, maxAgeCB);
            passParameter.setAgeRange(ageGroup.getRange());
            return true;
        } else {
            return false;
        }
    }

    public boolean validateGender(Gender genderCB){
        if (genderCB == null) {
            return false;
        } else {
            passParameter.setGender(genderCB);
            return true;
        }

    }

    public void validateDurationAndTimeDate(Integer durH, Integer durM){
        if (passParameter.getEndDate() == null && passParameter.getDuration() != null) {
            passParameter.setEndDate(passParameter.getDate().plusDays(Integer.parseInt(passParameter.getDuration() )));
        } else if (passParameter.getEndDate() == null && passParameter.getDuration()  == null) {
            passParameter.setEndDate(passParameter.getDate().plusDays(1));
        } else {
            if (passParameter.getEndDate() == null && passParameter.getDuration()  != null) {
                System.out.println("QUI");
                passParameter.setEndTime(passParameter.getTime().plusHours(durH).plusMinutes(durM));

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

        passParameter.setTypeOfFilm(typeOfFilmIns);

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
                passParameter.setExtraPastiTee(Float.parseFloat(pastiExtraTF));
                pasti = true;
            } else {
                pasti = false;
            }
        } else {
            passParameter.setExtraPastiTee(0);
            pasti = true;
        }

        if (gadgetCheckBox) {
            if (MyUtil.checkFloat(gadgetExtraTF)) {
               passParameter.setExtraGadgetTee(Float.parseFloat(gadgetExtraTF));
                gadget = true;
            } else {
                gadget = false;
            }
        } else {
            passParameter.setExtraGadgetTee(0);
            gadget = true;
        }

        if (rinfrescoCheckBox) {
            if (MyUtil.checkFloat(rinfrescoExtraTF)) {
                passParameter.setExtraRinfrescoTee(Float.parseFloat(rinfrescoExtraTF));
                rinfresco = true;
            } else {
                rinfresco = false;
            }
        } else {
            passParameter.setExtraRinfrescoTee(0);
            rinfresco = true;
        }

       return (pasti && gadget && rinfresco);

    }


}
