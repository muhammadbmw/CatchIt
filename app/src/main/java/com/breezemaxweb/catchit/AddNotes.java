package com.breezemaxweb.catchit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddNotes extends AppCompatActivity {
    String serverResponse;
    private static final String TAG = AddNotes.class.getName();
    int id;
    EditText subject, notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        this.setTitle("");
        subject = (EditText)findViewById(R.id.title);
        notes = (EditText) findViewById(R.id.note);
        // if intent has data that means have to display details
        Intent intent = getIntent();
        if(intent.hasExtra("id")){
            id = intent.getIntExtra("id",0);
            String title = intent.getStringExtra("subject");
            String note = intent.getStringExtra("note");
            subject.setText(title);
            notes.setText(note);
        }
        else
        {

            subject.setText("");
            notes.setText("");
        }
    }
    public void onClick(View view){
        //Delete note
        String createUrl ="https://breezemaxlabs.com/RestAPI/v1/deleteNote";
        StringRequest stringRequest;

        stringRequest = new StringRequest(Request.Method.POST,createUrl,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    serverResponse = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(),serverResponse,Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }

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
                params.put("email", "nik@breeze.com");
                params.put("note_id", String.valueOf(id));

                return params;
            }


        };

        String  REQUEST_TAG = "DeleteNotes";
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest, REQUEST_TAG);
        subject.setText("");
        notes.setText("");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_note,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            final String sub = subject.getText().toString();
            final  String note = notes.getText().toString();
            if(sub.isEmpty())
                subject.setError("Subject can not be empty");
            if(note.isEmpty())
                notes.setError("Notes can not be empty");
            if(!(sub.isEmpty())&& !(note.isEmpty()) ){
                 //
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                        INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                //Toast.makeText(this,"Note is Saved",Toast.LENGTH_LONG).show();
                String createUrl ="https://breezemaxlabs.com/RestAPI/v1/createNote";
                StringRequest stringRequest;

                stringRequest = new StringRequest(Request.Method.POST,createUrl,new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            serverResponse = jsonObject.getString("message");
                            Toast.makeText(getApplicationContext(),serverResponse,Toast.LENGTH_LONG).show();


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

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
                        params.put("email", "nik@breeze.com");
                        params.put("subject", sub);
                        params.put("note", note);

                        return params;
                    }


                };

                String  REQUEST_TAG = "CreateNotes";
                AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest, REQUEST_TAG);
            }

            //subject.setText("");
            //notes.setText("");
            return true;

        }
        else if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }
}
