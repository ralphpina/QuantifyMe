package net.ralphpina.android.quantifyme.model;

import com.parse.ParseClassName;
import com.parse.ParseUser;

import java.util.Date;

/**
 * Created by ralphpina on 3/9/14.
 */
@ParseClassName("_User")
public class User extends ParseUser {

    public User() {
    }

    public void setFirstName(String firstName) {
        put("firstName", firstName);
    }

    public String getFirstName() {
        return getString("firstName");
    }

    public void setLastName(String lastName) {
        put("lastName", lastName);
    }

    public String getLastName() {
        return getString("lastName");
    }

    public void setDateOfBirth(Date dob) {
        put("dateOfBirth", dob);
    }

    public Date getDateOfBirth() {
        return getDate("dateOfBirth");
    }

    public void setCity(String city) {
        put("city", city);
    }

    public String getCity() {
        return getString("city");
    }

    public void setState(String state) {
        put("state", state);
    }

    public String getState() {
        return getString("state");
    }
}
