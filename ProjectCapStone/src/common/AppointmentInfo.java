package common;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AppointmentInfo{
	public  AppointmentInfo(String date, String ownerName, String petName, String injection, String vaccine, String status){
	    this.date = new SimpleStringProperty(date);
	    this.ownerName = new SimpleStringProperty(ownerName);
	    this.petName = new SimpleStringProperty(petName);
	    this.injection = new SimpleStringProperty(injection);
	    this.vaccine = new SimpleStringProperty(vaccine);
	    this.status = new SimpleStringProperty(status);
	 }

	 private StringProperty date;
	 public void setDate(String value) { dateProperty().set(value); }
	 public String getDate() { return dateProperty().get(); }
	 public StringProperty dateProperty() {
	   return date;
	 }
	 
	 private StringProperty ownerName;
	 public void setOwnerName(String value) { ownerNameProperty().set(value); }
	 public String getOwnerName() { return ownerNameProperty().get(); }
	 public StringProperty ownerNameProperty() {
	   return ownerName;
	 }
	 
	 
	 private StringProperty petName;
	 public void setPetName(String value) { petNameProperty().set(value); }
	 public String getPetName() { return petNameProperty().get(); }
	 public StringProperty petNameProperty() {
	   return petName;
	 }
	 
	 private StringProperty injection;
	 public void setInjection(String value) { injectionProperty().set(value); }
	 public String getInjection() { return injectionProperty().get(); }
	 public StringProperty injectionProperty() {
	   return injection;
	 }
	 
	 private StringProperty vaccine;
	 public void setVaccine(String value) { vaccineProperty().set(value); }
	 public String getVaccine() { return vaccineProperty().get(); }
	 public StringProperty vaccineProperty() {
	   return vaccine;
	 }
	 
	 private StringProperty status;
	 public void setStatus(String value) { statusProperty().set(value); }
	 public String getStatus() { return statusProperty().get(); }
	 public StringProperty statusProperty() {
	   return status;
	 }
}


