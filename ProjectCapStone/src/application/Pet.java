package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pet {
	
	public Pet(String petName, String petType, String breed, String gender, String dob){
	      this.petName = new SimpleStringProperty(petName);
	      this.petType = new SimpleStringProperty(petType);
	     this.breed = new SimpleStringProperty(breed);
	     this.gender = new SimpleStringProperty(gender);
	     this.dob = new SimpleStringProperty(dob);
	  }

	  private StringProperty petName;
	  public void setPetName(String value) { petNameProperty().set(value); }
	  public String getPetName() { return petNameProperty().get(); }
	  public StringProperty petNameProperty() {
	    return petName;
	  }
	  
	  private StringProperty petType;
	  public void setPetType(String value) { petTypeProperty().set(value); }
	  public String getPetType() { return petTypeProperty().get(); }
	  public StringProperty petTypeProperty() {
	    return petType;
	  }
	  
	  
	  private StringProperty breed;
	  public void setBreed(String value) { breedProperty().set(value); }
	  public String getBreed() { return breedProperty().get(); }
	  public StringProperty breedProperty() {
	    return breed;
	  }
	  
	  private StringProperty gender;
	  public void setGender(String value) { genderProperty().set(value); }
	  public String getGender() { return genderProperty().get(); }
	  public StringProperty genderProperty() {
	    return gender;
	  }
	  
	  private StringProperty dob;
	  public void setDob(String value) { dobProperty().set(value); }
	  public String getDob() { return dobProperty().get(); }
	  public StringProperty dobProperty() {
	    return dob;
	  }

}
