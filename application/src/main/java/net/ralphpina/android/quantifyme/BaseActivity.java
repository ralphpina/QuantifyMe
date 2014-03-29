package net.ralphpina.android.quantifyme;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

;

public class BaseActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        GraphCellFragment.OnFragmentInteractionListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        ParseAnalytics.trackAppOpened(getIntent());

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();

        switch (position) {
            case 0:
                mTitle = getString(R.string.profile);
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new ProfileFragment())
                        .commit();
                break;
            case 1:
                mTitle = getString(R.string.home);
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new MainFragment())
                        .commit();
                break;
            case 2:
                mTitle = getString(R.string.tutorial);
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new MainFragment())
                        .commit();
                break;
            case 3:
                mTitle = getString(R.string.settings);
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new MainFragment())
                        .commit();
                break;
        }

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 0:
                mTitle = getString(R.string.profile);
                break;
            case 1:
                mTitle = getString(R.string.home);
                break;
            case 2:
                mTitle = getString(R.string.tutorial);
                break;
            case 3:
                mTitle = getString(R.string.settings);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.e("BaseActivity", "++ onCreateOptionsMenu() ++");
        if (!mNavigationDrawerFragment.isDrawerOpen() &&
                mNavigationDrawerFragment.getDrawerToggle().isDrawerIndicatorEnabled()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            // if user is signed in, don't show the sign in text
            if (!ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
                MenuItem item = menu.findItem(R.id.action_sign_in);
                item.setVisible(false);
            }
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (mNavigationDrawerFragment.getDrawerToggle().isDrawerIndicatorEnabled() &&
                mNavigationDrawerFragment.getDrawerToggle().onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case android.R.id.home:
                //called when the up affordance/carat in actionbar is pressed
                onBackPressed();
                return true;

            case R.id.action_sign_in:
                mNavigationDrawerFragment.getDrawerToggle().setDrawerIndicatorEnabled(false);
                mTitle = getString(R.string.login);
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new LoginFragment())
                        .addToBackStack(null)
                        .commit();
                invalidateOptionsMenu();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mNavigationDrawerFragment.getDrawerToggle().setDrawerIndicatorEnabled(true);
        invalidateOptionsMenu();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
