package net.ralphpina.android.quantifyme;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;

import net.ralphpina.android.quantifyme.model.User;

public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";

    private TextView profileLoginToView;
    private TextView profileName;
    private TextView profileDOB;
    private TextView profileCity;
    private TextView profileState;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profileLoginToView = (TextView) view.findViewById(R.id.loginToViewProfileTextView);
        profileName = (TextView) view.findViewById(R.id.profileNameTextView);
        profileDOB = (TextView) view.findViewById(R.id.profileDOBTextView);
        profileCity = (TextView) view.findViewById(R.id.profileCityTextView);
        profileState = (TextView) view.findViewById(R.id.profileStateTextView);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        try {
            User.getCurrentUser().fetchIfNeeded();
        } catch (ParseException e) {
            Log.e(TAG, "Could not fetch user, no connection");
        }

        updateView();

    }

    private void updateView() {
        if (!ParseAnonymousUtils.isLinked(User.getCurrentUser())) {
            profileLoginToView.setVisibility(View.VISIBLE);
            profileName.setVisibility(View.GONE);
            profileDOB.setVisibility(View.GONE);
            profileCity.setVisibility(View.GONE);
            profileState.setVisibility(View.GONE);
        } else {
            profileLoginToView.setVisibility(View.GONE);
            profileName.setVisibility(View.VISIBLE);
            profileDOB.setVisibility(View.VISIBLE);
            profileCity.setVisibility(View.VISIBLE);
            profileState.setVisibility(View.VISIBLE);

            User user = (User) User.getCurrentUser();

            Log.e(TAG, "user = " + user);
            Log.e(TAG, "user.getFirstName() = " + user.getFirstName());
            Log.e(TAG, "user.getLastName() = " + user.getLastName());
            Log.e(TAG, "user.getDateOfBirth() = " + user.getDateOfBirth());
            Log.e(TAG, "user.getCity() = " + user.getCity());
            Log.e(TAG, "user.getState() = " + user.getState());
            profileName.setText(user.getFirstName() + user.getLastName());
            profileDOB.setText(user.getDateOfBirth() != null ? user.getDateOfBirth().toString() : "DOB");
            profileCity.setText(user.getCity() != null ? user.getCity() : "City");
            profileState.setText(user.getState() != null ? user.getState() : "State");
        }
    }


}
