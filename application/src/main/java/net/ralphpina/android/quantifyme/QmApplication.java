package net.ralphpina.android.quantifyme;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;

import net.ralphpina.android.quantifyme.model.Sleep;
import net.ralphpina.android.quantifyme.model.User;

/**
 * Created by ralphpina on 3/2/14.
 */
public class QmApplication extends Application {

    /*
     * Data fields in Parse
     */

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Sleep.class);
        ParseObject.registerSubclass(User.class);
        Parse.initialize(this, "UlZC1kUlL3u7NTUHDAZEM6VY2zWNjdtRYG9Xp5dT", "7LhXe1nTMRQGENcVx8UjBDxCw8m10OvXeuIHeuQv");

        User.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL(defaultACL, true);
        User.getCurrentUser().increment("RunCount");
        User.getCurrentUser().saveInBackground();
    }
}
