package net.ralphpina.android.quantifyme;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * A simple {@link android.app.Fragment} subclass.
 *
 */
public class LoginFragment extends Fragment {

    private EditText mEmail;
    private EditText mPassword;
    private Button mLoginButton;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // set the back affordance instead of nav drawer
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);

        mEmail = (EditText) view.findViewById(R.id.editTextEmail);
        mPassword = (EditText) view.findViewById(R.id.editTextPassword);
        mLoginButton = (Button) view.findViewById(R.id.buttonLogin);
        mLoginButton.setOnClickListener(new LoginButtonOnClickButtonListener());

        return view;
    }

    private void loginUser() {
        if (!isValidEmail(mEmail.getText())) {
            showErrorDialog(getActivity().getString(R.string.validEmailErrorMessage));
        } else if (TextUtils.isEmpty(mPassword.getText())) {
            showErrorDialog(getActivity().getString(R.string.passwordErrorMessage));
        } else {
            // try to log in user
            ParseUser.logInInBackground(mEmail.getText().toString(), mPassword.getText().toString(), new MyLogInCallback());
        }
    }


    /**
     * From http://stackoverflow.com/questions/1819142/how-should-i-validate-an-e-mail-address-on-android
     * @param target
     * @return
     */
    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private class LoginButtonOnClickButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            loginUser();
        }
    }

    private class MyLogInCallback extends LogInCallback {
        @Override
        public void done(ParseUser parseUser, ParseException e) {
            if (parseUser != null) {
                // TODO check for verify e-mail? prompt?
                getFragmentManager().popBackStackImmediate();
            } else {
                // Signup failed. Look at the ParseException to see what happened.
                // TODO set up logic to retry or notify user of issue
                Log.e("LoginFragment", "parse exeption code = " + e.getCode());
                switch (e.getCode()) {
                    case 101:
                        // account doesn't exist
                        showErrorDialog(getActivity().getString(R.string.noAccountErrorMessage));
                }
            }
        }
    }

    private void showErrorDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getActivity().getString(R.string.loginErrorDialogTitle));
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

}
