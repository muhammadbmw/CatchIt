package com.breezemaxweb.catchit;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddContacts extends AppCompatActivity {
    TextView cname;
    EditText fname, lname, tel, mobile, email, position, note;
    String telNumber, mobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);
        this.setTitle("Contacts");
        TextView cname = (TextView) findViewById(R.id.companyName);
        EditText fname = (EditText) findViewById(R.id.firstName);
        EditText lname = (EditText) findViewById(R.id.lastName);
        EditText tel = (EditText) findViewById(R.id.tel);
        EditText mobile = (EditText) findViewById(R.id.mobile);
        EditText email = (EditText) findViewById(R.id.email);
        EditText position = (EditText) findViewById(R.id.position);
        EditText note = (EditText) findViewById(R.id.note);

        Intent intent = getIntent();
        if(intent.hasExtra("company")) {
            cname.setText(intent.getStringExtra("company"));
            fname.setText(intent.getStringExtra("fname"));
            lname.setText(intent.getStringExtra("lname"));
            tel.setText(intent.getStringExtra("tel"));
            mobile.setText(intent.getStringExtra("mobile"));
            email.setText(intent.getStringExtra("email"));
            position.setText(intent.getStringExtra("position"));
            note.setText(intent.getStringExtra("note"));

            telNumber = "tel:" + tel.getText().toString().trim();
            mobileNumber = "tel:" + mobile.getText().toString().trim();
        }
        if(intent.hasExtra("companyName")){
            cname.setText(intent.getStringExtra("companyName"));
        }

        // add PhoneStateListener
        PhoneCallListener phoneListener = new PhoneCallListener();
        TelephonyManager telephonyManager = (TelephonyManager) this
                .getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);

    }
    //test btn linkedin
    public void testLinkedin(View v){
        Intent intent = new Intent(this,TestActivity.class);
        startActivity(intent);

    }

    public void makeCall(View v) {
        switch (v.getId()) {
            case R.id.callPhone: {
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(telNumber));
                startActivity(callIntent);
                break;
            }
            case R.id.callMobile: {
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(mobileNumber));
                startActivity(callIntent);
                break;
            }
        }
    }
    //monitor phone call activities
    private class PhoneCallListener extends PhoneStateListener {

        private boolean isPhoneCalling = false;

       // String LOG_TAG = "LOGGING 123";

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            if (TelephonyManager.CALL_STATE_RINGING == state) {
                // phone ringing
                //Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
            }

            if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
                // active
               // Log.i(LOG_TAG, "OFFHOOK");

                isPhoneCalling = true;
            }

            if (TelephonyManager.CALL_STATE_IDLE == state) {
                // run when class initial and phone call ended,
                // need detect flag from CALL_STATE_OFFHOOK
              // Log.i(LOG_TAG, "IDLE");

                if (isPhoneCalling) {

                   // Log.i(LOG_TAG, "restart app");

                    // restart app
                    Intent i = getBaseContext().getPackageManager()
                            .getLaunchIntentForPackage(
                                    getBaseContext().getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);

                    isPhoneCalling = false;
               }

            }
        }
    }

}
