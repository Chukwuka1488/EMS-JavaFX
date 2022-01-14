package sample.ems.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RegistrationModel {


    private final StringProperty ID;


    private final StringProperty Firstname;


    private final StringProperty Lastname; // imageUrl


    private final StringProperty Username;


    private final StringProperty Password;


    public RegistrationModel(String id, String firstname, String lastname,
                             String username, String password) {
        this.ID = new SimpleStringProperty(id);
        this.Firstname = new SimpleStringProperty(firstname);
        this.Lastname = new SimpleStringProperty(lastname);
        this.Username = new SimpleStringProperty(username);
        this.Password = new SimpleStringProperty(password);
    }

    // getter/setter ID JavaFX
    public StringProperty IDProperty() {
        return this.ID;
    }

    public String getID() {
        return this.IDProperty().get();
    }

    public void setID(final String ID) {
        this.IDProperty().set(ID);

    }

    // getter/setter Firstname JavaFX
    public StringProperty FirstnameProperty() {
        return this.Firstname;
    }

    public String getFirstname() {
        return this.FirstnameProperty().get();
    }

    public void setFirstname(final String Firstname) {
        this.FirstnameProperty().set(Firstname);
    }

    // getter/setter Lastname JavaFX
    public StringProperty LastnameProperty() {
        return this.Lastname;
    }

    public String getLastname() {
        return this.LastnameProperty().get();
    }

    public void setLastname(final String Lastname) {
        this.LastnameProperty().set(Lastname);
    }

    // getter/setter Username JavaFX
    public StringProperty UsernameProperty() {
        return this.Username;
    }

    public String getUsername() {
        return this.UsernameProperty().get();
    }

    public void setUsername(final String Username) {
        this.UsernameProperty().set(Username);
    }

    // getter/setter Password JavaFX
    public StringProperty PasswordProperty() {
        return this.Password;
    }

    public String getPassword() {
        return this.PasswordProperty().get();
    }

    public void setPassword(final String Password) {
        this.PasswordProperty().set(Password);
    }
}
















