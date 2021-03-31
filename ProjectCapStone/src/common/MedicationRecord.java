package common;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MedicationRecord {
	
	public MedicationRecord(String meddate, String time, String medication, String dosage, String frequency, String notes){
	     
		 this.meddate = new SimpleStringProperty(meddate); 
		this.time = new SimpleStringProperty(time);
	    this.medication = new SimpleStringProperty(medication);
	    this.dosage = new SimpleStringProperty(dosage);
	    this.frequency = new SimpleStringProperty(frequency);
	    this.notes = new SimpleStringProperty(notes);
	}
	
	  private StringProperty meddate;
	  public void setMedDate(String value) { meddateProperty().set(value); }
	  public String getMedDate() { return meddateProperty().get(); }
	  public StringProperty meddateProperty() {
	    return meddate;
	  }
	
	  private StringProperty time;
	  public void setTime(String value) { timeProperty().set(value); }
	  public String getTime() { return timeProperty().get(); }
	  public StringProperty timeProperty() {
	    return time;
	  }
	  
	  private StringProperty medication;
	  public void setMedication(String value) { medicationProperty().set(value); }
	  public String getMedication() { return medicationProperty().get(); }
	  public StringProperty medicationProperty() {
	    return medication;
	  }
	  
	  private StringProperty dosage;
	  public void setDosage(String value) { dosageProperty().set(value); }
	  public String getDosage() { return dosageProperty().get(); }
	  public StringProperty dosageProperty() {
	    return dosage;
	  }
	  
	  private StringProperty frequency;
	  public void setFrequency(String value) { frequencyProperty().set(value); }
	  public String getFrequency() { return frequencyProperty().get(); }
	  public StringProperty frequencyProperty() {
	    return frequency;
	  }
	  
	  private StringProperty notes;
	  public void setNotes(String value) { notesProperty().set(value); }
	  public String getNotes() { return notesProperty().get(); }
	  public StringProperty notesProperty() {
	    return notes;
	  }
}

