package com.breezemaxweb.catchit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Notes extends AppCompatActivity {
    String serverResponse;
    private static final String TAG = Notes.class.getName();

    ListView listNote;
    ArrayList<NoteInfo> arrayList = new ArrayList<NoteInfo>();
    NoteViewAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        this.setTitle("Notes");
        listNote = (ListView) findViewById(R.id.list);

        noteAdapter = new NoteViewAdapter(this,arrayList);
        listNote.setAdapter(noteAdapter);

     //Server request start
        String url ="https://breezemaxlabs.com/RestAPI/v1/notes";
        StringRequest stringRequest;

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray array = jsonObject.getJSONArray("Notes");

                    int id;
                    String subject, note, date, day;
                    for(int i =0; i< array.length();i++){
                        JSONObject notes = (JSONObject) array.get(i);
                        id = notes.getInt("Id");
                        subject = notes.getString("Subject");
                        note = notes.getString("Note");
                        date = notes.getString("Date");
                        day = notes.getString("Day");
                        arrayList.add(new NoteInfo(id,subject,note,date,day));
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
                // notifying list adapter about data changes
                // so that it renders the list view with updated data
                noteAdapter.notifyDataSetChanged();

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
                return params;
            }
        };

        String  REQUEST_TAG = "ProspectRequest";
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest, REQUEST_TAG);
      // Server response end


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_notes,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addNotes) {
            Intent intent = new Intent(this, AddNotes.class);
            startActivity(intent);
            return true;

        } else
            return super.onOptionsItemSelected(item);
    }

}



