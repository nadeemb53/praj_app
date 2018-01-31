package org.prajwalan.app.prajwalan.register;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.prajwalan.app.prajwalan.DB.User;
import org.prajwalan.app.prajwalan.handlers.JSONHandler;
import org.prajwalan.app.prajwalan.handlers.SharedPreferencesHandler;
import org.prajwalan.app.prajwalan.utility.CommonUtilities;
import org.prajwalan.app.prajwalan.utility.NetworkConnection;
import org.prajwalan.app.prajwalan.utility.SendToServerUtility;

import org.prajwalan.app.prajwalan.R;

/**
 * Created by Nadeem on 23/12/2017.
 */
public class Login extends Fragment {

    EditText email,password;
    ImageButton loginBtn;
    ProgressDialog pd;
    Snackbar s = null;

    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate( R.layout.login, container, false);

        email = (EditText) rootView.findViewById(R.id.email);
        password = (EditText) rootView.findViewById(R.id.password);

        pd = new ProgressDialog(getContext());

        loginBtn = (ImageButton) rootView.findViewById(R.id.loginButton);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(s!=null && s.isShownOrQueued()) {
                s.dismiss();
            }
            if(!NetworkConnection.isConnectingToInternet(getContext())) {
                s = Snackbar.make(v, "Cannot Connect To Internet...", Snackbar.LENGTH_INDEFINITE).setAction("Try Again",this);
                CommonUtilities.displayErrorMessage(s);
                return;
            }
            loginClicked();
            }
        });

        return rootView;
    }

    private void loginClicked() {

        pd.setTitle("Logging In...");
        pd.setMessage("Checking Username and Password...");
        pd.setCancelable(false);
        pd.show();

        String loginEmail = email.getText().toString();
        String loginPass = password.getText().toString();

        String url = "http://www.prajwalan.org/app/loginForm.php";
        SendToServerUtility loginRequest = new SendToServerUtility(url);
        loginRequest.addFormField("email",loginEmail);
        loginRequest.addFormField("password",loginPass);

        SendLoginData requestToServer = new SendLoginData();
        requestToServer.execute(loginRequest);

    }

    class SendLoginData extends AsyncTask<SendToServerUtility,Void,String> {
        @Override
        protected String doInBackground(SendToServerUtility... params) {
            String result = "";
            try {
                SendToServerUtility loginRequest = params[0];
                result = loginRequest.send();
            }
            catch(Exception e) {
                result = "{\"success\":\"fail\",\"reason\":\"Network Problem Occurred.\"};";
            }
            return result;
        }

        @Override
        protected void onPostExecute(String response) {
            pd.hide();

            try {
                JSONHandler jsonHandler = new JSONHandler(getContext());
                boolean success = jsonHandler.getSuccess(response);

                if(success) {
                    Toast.makeText(getContext(), "Login Successful. You are Logged In.", Toast.LENGTH_LONG).show();

                    User loggedInUser = jsonHandler.getUserData(response);
                    SharedPreferencesHandler.storeUserDetails(getContext(), loggedInUser);

                    getActivity().onBackPressed();

                    getActivity().finish();
                }
                else {
                    String reason = jsonHandler.getFailureReason(response);
                    CommonUtilities.displayAlertMessage(getContext(),"Error Occurred",reason);
                }
            }
            catch (Exception e) {
                Snackbar s = Snackbar.make(loginBtn, e.toString(), Snackbar.LENGTH_LONG).setAction("Action",null);
                CommonUtilities.displayMessage(s);
            }
        }
    }

}

