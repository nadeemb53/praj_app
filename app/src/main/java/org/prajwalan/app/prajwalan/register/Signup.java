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
import org.prajwalan.app.prajwalan.handlers.SharedPreferencesHandler;
import org.prajwalan.app.prajwalan.utility.CommonUtilities;
import org.prajwalan.app.prajwalan.utility.NetworkConnection;
import org.prajwalan.app.prajwalan.utility.SendToServerUtility;

import org.prajwalan.app.prajwalan.R;
import org.prajwalan.app.prajwalan.handlers.JSONHandler;

/**
 * Created by Moiz on 09/01/2016.
 */
public class Signup  extends Fragment {

    EditText fname,lname,email,password,clg,mobile;
    ImageButton signupBtn;
    ProgressDialog pd;
    Snackbar s = null;
    User enteredUserDetails;

    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.signup, container, false);

        fname = (EditText) rootView.findViewById(R.id.fname);
        lname = (EditText) rootView.findViewById(R.id.lname);
        email = (EditText) rootView.findViewById(R.id.email);
        password = (EditText) rootView.findViewById(R.id.password);
        mobile = (EditText) rootView.findViewById(R.id.phonenumber);
        clg = (EditText) rootView.findViewById(R.id.clg);

        pd = new ProgressDialog(getContext());

        enteredUserDetails = new User();

        signupBtn = (ImageButton) rootView.findViewById(R.id.signupButton);
        signupBtn.setOnClickListener(new View.OnClickListener() {
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
            signupClicked();
            }
        });

        return rootView;
    }

    private void signupClicked() {

        pd.setTitle("Register");
        pd.setCancelable(false);
        pd.setMessage("Registering User...");
        pd.show();

        enteredUserDetails.fname = fname.getText().toString();
        enteredUserDetails.lname  = lname.getText().toString();
        enteredUserDetails.email  = email.getText().toString();
        enteredUserDetails.college = clg.getText().toString();
        enteredUserDetails.mobile  = mobile.getText().toString();
        String signupPass  = password.getText().toString();

        String url = "http://www.prajwalan.org/app/signup.php";
        SendToServerUtility signupRequest = new SendToServerUtility(url);
        signupRequest.addFormField("fname",enteredUserDetails.fname);
        signupRequest.addFormField("lname",enteredUserDetails.lname);
        signupRequest.addFormField("email",enteredUserDetails.email);
        signupRequest.addFormField("password",signupPass);
        signupRequest.addFormField("clg",enteredUserDetails.college);
        signupRequest.addFormField("mobile",enteredUserDetails.mobile);

        SendSignupData requestToServer = new SendSignupData();
        requestToServer.execute(signupRequest);

    }

    class SendSignupData extends AsyncTask<SendToServerUtility,Void,String> {
        @Override
        protected String doInBackground(SendToServerUtility... params) {
            String result = "";
            try {
                SendToServerUtility signupRequest = params[0];
                result = signupRequest.send();
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
                    Toast.makeText(getContext(),"Registration Successful. You are Logged In.",Toast.LENGTH_LONG).show();
                    SharedPreferencesHandler.storeUserDetails(getContext(), enteredUserDetails);

                    getActivity().onBackPressed();

                    getActivity().finish();
                }
                else {
                    String reason = jsonHandler.getFailureReason(response);
                    CommonUtilities.displayAlertMessage(getContext(),"Error Occurred",reason);
                }
            }
            catch (Exception e) {
                Snackbar s = Snackbar.make(signupBtn, e.toString(), Snackbar.LENGTH_LONG).setAction("Action",null);
                CommonUtilities.displayMessage(s);
            }

        }
    }
}


