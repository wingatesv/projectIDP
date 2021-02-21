package common;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VaccineRecord {
	
	public VaccineRecord(String vaccineType, String injection, String date, String nextDate){
	     
	      this.vaccineType = new SimpleStringProperty(vaccineType);
	     this.injection = new SimpleStringProperty(injection);
	     this.date = new SimpleStringProperty(date);
	     this.nextDate = new SimpleStringProperty(nextDate);
	  }


	  
	  private StringProperty vaccineType;
	  public void setVaccineType(String value) { vaccineTypeProperty().set(value); }
	  public String getVaccineType() { return vaccineTypeProperty().get(); }
	  public StringProperty vaccineTypeProperty() {
	    return vaccineType;
	  }
	  
	  
	  private StringProperty injection;
	  public void setInjection(String value) { injectionProperty().set(value); }
	  public String getInjection() { return injectionProperty().get(); }
	  public StringProperty injectionProperty() {
	    return injection;
	  }
	  
	  private StringProperty date;
	  public void setDate(String value) { dateProperty().set(value); }
	  public String getDate() { return dateProperty().get(); }
	  public StringProperty dateProperty() {
	    return date;
	  }
	  
	  private StringProperty nextDate;
	  public void setNextDate(String value) { nextDateProperty().set(value); }
	  public String getNextDate() { return nextDateProperty().get(); }
	  public StringProperty nextDateProperty() {
	    return nextDate;
	  }

}
