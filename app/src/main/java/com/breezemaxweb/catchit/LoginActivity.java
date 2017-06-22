package com.breezemaxweb.catchit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.security.AccessController.getContext;

public class LoginActivity extends AppCompatActivity {
    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;

    private CheckBox checkBoxRememberMe;
    String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Matcher matcher;
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    private StringRequest stringRequest;
    private RequestQueue mRequestQueue;
    private String url = "https://breezemaxlabs.com/RestAPI/v1/login";
    private static String TAG = LoginActivity.class.getSimpleName();
    private Boolean loginValue;

    //Forget Password api
    private String fpUrl ="https://breezemaxlabs.com/RestAPI/v1/forgot";


    AlertDialogManager alert = new AlertDialogManager();
    // Session Manager Class
    SessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Session Manager
        session = new SessionManager(getApplicationContext());


        mEmailView = (EditText)findViewById(R.id.email);
        mPasswordView = (EditText)findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        Button mEmailSignInButton = (Button) findViewById(R.id.loginBtn);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        checkBoxRememberMe = (CheckBox)findViewById(R.id.checkBox);
        //Here we will validate saved preferences
        if (!session.isUserLogedOut()) {
            //user's email and password both are saved in preferences
            startHomeActivity();
        }
    }
    /**
     * Attempts to sign in to the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        //Check for a empty password
        if(TextUtils.isEmpty(password)){
            mPasswordView.setError("This field is required");
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {

           // Cache cache = new DiskBasedCache(getCacheDir(), 1024*1024);
            //Network network = new BasicNetwork(new HurlStack());
           // mRequestQueue = new RequestQueue(cache,network);
          //  mRequestQueue.start();
            stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                        Boolean loginValue = jsonObject.getBoolean("login");
                        if(loginValue){
                            if (checkBoxRememberMe.isChecked())
                                session.saveLoginDetails(email, password);
                            startHomeActivity();
                        }

                        else {
                            alert.showAlertDialog(LoginActivity.this, "Login Failed..", "Email and Password does not match", false);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    // mTextView.setText(response.toString());

                    // Log.i(TAG,"Response: "+response.toString());

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i(TAG,"Error: "+error.toString() );

                }
            })
            {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("email", email);
                    params.put("pass", password);

                    return params;
                }
            };
           // mRequestQueue.add(stringRequest);
            String  REQUEST_TAG = "LoginRequest";
            AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest, REQUEST_TAG);
        }

    }
    private void startHomeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        // return email.contains("@");
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 1;
    }
    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }

    //For Forget password
    public void forgetPassword(View view){
        // Reset errors.
        mEmailView.setError(null);
        // Store values at the time of the forget password.
        final String email = mEmailView.getText().toString();
        boolean cancel = false;
        View focusView = null;
        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }
        else {

            stringRequest = new StringRequest(Request.Method.POST, fpUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.toString());
                         String msg = jsonObject.getString("message");
                        //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                        alert.showAlertDialog(LoginActivity.this, "Forget Password ..", msg, false);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i(TAG, "Error: " + error.toString());

                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("email", email);
                    return params;
                }
            };
            String  REQUEST_TAG = "ForgetPasswordRequest";
            AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest, REQUEST_TAG);
        }

    }

}
