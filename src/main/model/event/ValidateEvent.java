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

    public boolean validateCategory(ChoiceBox<String> category){
        if (category.getSelectionModel().getSelectedItem() == null) {
            return false;
        }
        else{
            eventCreateController.setCategoryIns(category.getSelectionModel().getSelectedItem());
            return true;
        }
    }

    public boolean validateTitle(TextField title, SocialNetwork socialNetwork, String category){
        if (title.getText() != null && socialNetwork.findCategoryByName(category).doesEventAlreadyExist(title.getText()) == true) {
            return false;
        } else if (title.getText() != null && socialNetwork.findCategoryByName(category).doesEventAlreadyExist(title.getText())  == false) {
            eventCreateController.setTitleIns(title.getText());
            return true;
        }
        else{
            String titleNull = "Titolo ("+counterTitleNull+")";
            eventCreateController.setTitleIns(titleNull);
            counterTitleNull++;
            return true;
        }
    }

    public boolean validateNumPar(TextField numPTxtF){
        if (numPTxtF.getText().isEmpty() || !MyUtil.checkInteger(numPTxtF.getText())) {
           return false;
        } else {
            eventCreateController.setNumParIns(Integer.parseInt(numPTxtF.getText()));
            return true;
        }
    }

    public boolean validateExtraNumPar(TextField extraNumParTxt){
        if (extraNumParTxt.getText().isEmpty()) {
            eventCreateController.setExtraNumParIns(0);
            return true;
        } else {
            if (!MyUtil.checkInteger(extraNumParTxt.getText())) {
                return false;
            } else {
                eventCreateController.setExtraNumParIns(Integer.parseInt(extraNumParTxt.getText()));
                return true;
            }
        }
    }

    public boolean validateDeadLine(DatePicker deadLineDP){
        if (deadLineDP.getValue() == null) {
            return false;
        }
        else{
            eventCreateController.setDeadLineIns(deadLineDP.getValue());
            return true;
        }
    }

    public void validateRetiredDeadLine(DatePicker retiredDeadLineDP, LocalDate deadLineIns){
        if (retiredDeadLineDP.getValue() == null) {
            eventCreateController.setRetiredDeadLineIns(deadLineIns);
        } else {
            eventCreateController.setRetiredDeadLineIns(retiredDeadLineDP.getValue());
        }
    }

    public boolean validatePlace(TextField placeTxtF){
        if(placeTxtF.getText().isEmpty() || !MyUtil.checkString(placeTxtF.getText()) ){
            return false;
        }
        else{
            eventCreateController.setPlaceIns(placeTxtF.getText());
            return true;
        }
    }

    public boolean validateDate(DatePicker dateP){
        if ((dateP.getValue() == null)) {
            return false;
        } else {
            eventCreateController.setDateIns(dateP.getValue());
            return true;
        }
    }

    public boolean validateTime(JFXTimePicker timeTP, boolean dateIsVal){
        if (timeTP.getValue() == null || dateIsVal == false) {
            return false;
        } else {
            eventCreateController.setTimeIns(timeTP.getValue());
            return true;
        }
    }

    public boolean validateTeeIndividual(JFXCheckBox teeA, TextField indTeeTxtF){
        if (teeA.isSelected()) {
            if (indTeeTxtF.getText().isEmpty() || !MyUtil.checkFloat(indTeeTxtF.getText())) {
                return false;
            } else {
                eventCreateController.setIndTeeIns(Float.parseFloat(indTeeTxtF.getText()));
                return true;
            }
        } else {
            eventCreateController.setIndTeeIns(0);
            return true;
        }
    }

    public void validateTotTee(TextArea tot){
        eventCreateController.setTotTeeIns(tot.getText());
    }

    public void validateEndDate(DatePicker endDateDP, boolean dateIsVal, ChoiceBox<Integer> durBigCB){
        if (endDateDP.getValue() != null && dateIsVal == true) {
            eventCreateController.setEndDateIns(endDateDP.getValue());
        } else if (endDateDP.getValue() == null ){
            if (durBigCB.getValue() != null) {
                eventCreateController.setDurationIns(String.valueOf(durBigCB.getValue()));
            }
        }
    }

    public boolean validateEndTime(JFXTimePicker endTimeTP, LocalDate endDateIns, LocalDate dateIns, LocalTime timeIns, ChoiceBox<Integer> durBigCB, ChoiceBox<Integer> durLitCB){
        if (endTimeTP.getValue() != null) {
            if (endTimeTP.getValue().isBefore(timeIns) && endDateIns.isEqual(dateIns)) {
                return false;
            } else {
                eventCreateController.setEndTimeIns( endTimeTP.getValue());
                return true;
            }

        } else {
            if (durBigCB.getValue() != null && durLitCB.getValue() != null) {
                eventCreateController.setDurationIns(durBigCB.getValue() + ":" + durLitCB.getValue());
            }
            return  true;
        }
    }

    public boolean validateEndDateEndTime(DatePicker endDateDP, JFXTimePicker endTimeTP, String durationIns){
        if ((endDateDP.getValue() != null && endTimeTP.getValue() == null) && durationIns == null) {
            return false;
        } else {
           return true;
        }

    }

    public void validateNote(TextArea note){
        eventCreateController.setNoteIns(note.getText());
    }

    public boolean validateAge(ChoiceBox<Integer> minAgeCB, ChoiceBox<Integer> maxAgeCB, AgeGroup ageGroup){
        if (minAgeCB.getValue() != null && maxAgeCB.getValue() != null) {
            ageGroup.setRange(minAgeCB.getValue(), maxAgeCB.getValue());
            eventCreateController.setAgeRangeIns(ageGroup.getRange());
            return true;
        } else {
            return false;
        }
    }

    public boolean validateGender(ChoiceBox<Gender> genderCB){
        if (genderCB.getSelectionModel().getSelectedItem() == null) {
            return false;
        } else {
            eventCreateController.setGenderIns(genderCB.getSelectionModel().getSelectedItem());
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
        boolean empty = true;

        List<String> typeOfFilmIns = new ArrayList<>();

        for (CheckBox check : typeOfFilmCheckList) {
            if (check.isSelected()) {
                empty = false;
                typeOfFilmIns.add(check.getText());
            }
        }

        eventCreateController.setTypeOfFilmIns(typeOfFilmIns);

        if (empty) {
            return false;

        } else {
            return false;
        }
    }

    public boolean validateExtraTeeIns(TextField pastiExtraTF, TextField gadgetExtraTF, TextField rinfrescoExtraTF, JFXCheckBox pastiCheckBox, JFXCheckBox gadgetCheckBox, JFXCheckBox rinfrescoCheckBox){

        boolean pasti = false;
        boolean rinfresco = false;
        boolean gadget = false;

        if (pastiCheckBox.isSelected()) {
            if (MyUtil.checkFloat(pastiExtraTF.getText())) {
                eventCreateController.setExtraPastiTeeIns(Float.parseFloat(pastiExtraTF.getText()));
                pasti = true;
            } else {
                pasti = false;
            }
        } else {
            eventCreateController.setExtraPastiTeeIns(0);
            pasti = true;
        }

        if (gadgetCheckBox.isSelected()) {
            if (MyUtil.checkFloat(gadgetExtraTF.getText())) {
               eventCreateController.setExtraGadgetTeeIns(Float.parseFloat(gadgetExtraTF.getText()));
                gadget = true;
            } else {
                gadget = false;
            }
        } else {
            eventCreateController.setExtraGadgetTeeIns(0);
            gadget = true;
        }

        if (rinfrescoCheckBox.isSelected()) {
            if (MyUtil.checkFloat(rinfrescoExtraTF.getText())) {
                eventCreateController.setExtraRinfrescoTeeIns(Float.parseFloat(rinfrescoExtraTF.getText()));
                rinfresco = true;
            } else {
                rinfresco = false;
            }
        } else {
            eventCreateController.setExtraRinfrescoTeeIns(0);
            rinfresco = true;
        }

        if (pasti && gadget && rinfresco) {
            return true;
        } else {
            return false;
        }
    }

}