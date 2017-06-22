package com.breezemaxweb.catchit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by BreezeMaxWeb on 2017-06-21.
 */

public class NotesFragment extends Fragment {
    String serverResponse;
    private static final String TAG = Notes.class.getName();
    ListView listNote;
    ArrayList<NoteInfo> arrayList = new ArrayList<NoteInfo>();
    NoteViewAdapter noteAdapter;

    View view;

    public static NotesFragment newInstance(){
        NotesFragment fragment = new NotesFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Notes");

        noteAdapter = new NoteViewAdapter(getActivity(),arrayList);

        //enable menu items
        setHasOptionsMenu(true);

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
        AppSingleton.getInstance(getContext()).addToRequestQueue(stringRequest, REQUEST_TAG);
        // Server response end

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.notes_fragment, container, false);
        listNote = (ListView) view.findViewById(R.id.list);
        listNote.setAdapter(noteAdapter);

        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_notes,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addNotes) {
            Intent intent = new Intent(getContext(), AddNotes.class);
            startActivity(intent);
            return true;

        } else
            return super.onOptionsItemSelected(item);
    }


}
