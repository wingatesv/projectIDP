package common;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Owner {
	
	public Owner(String firstName, String lastName, String iCnumber, String telephoneNumber, String address){
	     this.firstName = new SimpleStringProperty(firstName);
	     this.lastName = new SimpleStringProperty(lastName);
	     this.iCnumber = new SimpleStringProperty(iCnumber);
	     this.telephoneNumber = new SimpleStringProperty(telephoneNumber);
	     this.address = new SimpleStringProperty(address);
	  }

	  private StringProperty firstName;
	  public void setFirstName(String value) { firstNameProperty().set(value); }
	  public String getFirstName() { return firstNameProperty().get(); }
	  public StringProperty firstNameProperty() {
	    return firstName;
	  }
	  
	  private StringProperty lastName;
	  public void setLastName(String value) { lastNameProperty().set(value); }
	  public String getLastName() { return lastNameProperty().get(); }
	  public StringProperty lastNameProperty() {
	    return lastName;
	  }
	  
	  
	  private StringProperty iCnumber;
	  public void setICnumber(String value) { iCnumberProperty().set(value); }
	  public String getICnumber() { return iCnumberProperty().get(); }
	  public StringProperty iCnumberProperty() {
	    return iCnumber;
	  }
	  
	  private StringProperty telephoneNumber;
	  public void setTelephoneNumber(String value) { telephoneNumberProperty().set(value); }
	  public String getTelephoneNumber() { return telephoneNumberProperty().get(); }
	  public StringProperty telephoneNumberProperty() {
	    return telephoneNumber;
	  }
	  
	  private StringProperty address;
	  public void setAddress(String value) { addressProperty().set(value); }
	  public String getAddress() { return addressProperty().get(); }
	  public StringProperty addressProperty() {
	    return address;
	  }

}
