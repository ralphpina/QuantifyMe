package net.ralphpina.android.quantifyme.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by ralphpina on 3/9/14.
 */
@ParseClassName("Sleep")
public class Sleep extends ParseObject {

    public Sleep() {
    }

    public void setSleepHours(int hours) {
        put("sleepHours", hours);
    }

    public void setUser() {
        put("user", User.getCurrentUser());
    }

    public int getHours() {
        return getInt("sleepHours");
    }

    public Date getDate() {
        return getCreatedAt();
    }
}
