package com.breezemaxweb.catchit;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup.LayoutParams;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddProspect extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String serverResponse;
    private static final String TAG = AddProspect.class.getName();

    EditText companyName, cityTxt, addressTxt, yearEnd, serviceLine, meetPlace;
    Spinner industry;
    String [] pick_industry ={" ","Construction","Consumer Business", "Financial Services","Life Sciences","Manufacturing","Not for Profit","Professional Services","Public Sector",
    "Real Estate","Retail","Technology"};

    String sCName,sCity,sAddress,sIndustry,sYearEnd,sServiceLine,sMeetPlace;
    //Prospect next step declaration
    TextView subject,when,type,time,with;
    //Edit prospect next step
    String esubject,ewhen,etype,etime,ewith;

    String sSubject,sWhen,sMeeting,sTime,sNotes;

  final Context context = this;
    String query = "";
    String address = "";
    String city = "";
    String company = "";
    // for contact list
    ListView simpleList;
    ArrayList<ContactInfo> contactList = new ArrayList<ContactInfo>();
    ProspectContactAdapter myAdapter;
    //end contact list declaration
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__prospect);
        this.setTitle("");

        companyName = (EditText)findViewById(R.id.companyName);
        cityTxt = (EditText)findViewById(R.id.city);
        addressTxt = (EditText)findViewById(R.id.address);
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        industry = (Spinner)findViewById(R.id.industry);
        industry.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the lost_reason name list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,pick_industry);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        industry.setAdapter(aa);

        yearEnd = (EditText)findViewById(R.id.endDate);
        serviceLine = (EditText)findViewById(R.id.service);
        meetPlace = (EditText)findViewById(R.id.metWhere);

        //for next step declation
         subject = (TextView) findViewById(R.id.subject);
         when = (TextView) findViewById(R.id.when);
         type = (TextView) findViewById(R.id.type);
         time = (TextView) findViewById(R.id.time);
         with = (TextView) findViewById(R.id.with);

        //for prospect next step declarion end

        Intent intent = getIntent();
        if(intent.hasExtra("addProspect")){
            subject.setText("");
            when.setText("");
            type.setText("Add your next step by typing the + symbol");
            time.setText("");
            with.setText("");
        }
        if(intent.hasExtra("company")){
            company = intent.getStringExtra("company");
            companyName.setText(company);
            companyName.setFocusable(false);
            city = "Toronto";
            cityTxt.setText("Toronto");
            addressTxt.setText("55 Logan Ave");
            address = "55 Logan Ave";
           // industry.setText("Retail");
            industry.setSelection(9);
            yearEnd.setText("31 Dec");
            serviceLine.setText("Tax planning");
            meetPlace.setText("Toronto Board of Trades Awards Gala");

            //for prospect next step
            esubject = intent.getStringExtra("status");
            subject.setText(esubject);
            ewith  = intent.getStringExtra("meet");
            with.setText("With Who: "+ewith);
            ewhen = intent.getStringExtra("date");
            when.setText("By When: "+ewhen);
            etype = "In Person";
            type.setText("Type: "+etype);
            etime = "1:30 PM";
            time.setText("Time: "+etime);

            //for insight address query
            query = company+"+"+city;
            address += "+" + city;

        }
        /*Getting data from main activity */
        if(intent.hasExtra("activity"))
        {
            company = intent.getStringExtra("company");
            companyName.setText(company);
            city = "Toronto";
            cityTxt.setText("Toronto");
            addressTxt.setText("55 Logan Ave");
            address = "55 Logan Ave";
            industry.setSelection(5);
            yearEnd.setText("30 August");
            serviceLine.setText("Tax planning");
            meetPlace.setText("Toronto Board of Trades Awards Gala");
            // for insight address
            query = company+"+"+city;
            address += " " + city;

            //for prospect next step
            esubject = intent.getStringExtra("meeting");
            subject.setText(esubject);
            ewith = intent.getStringExtra("with");
            with.setText("With Who: "+ewith);
            etype = intent.getStringExtra("type");
            type.setText("Type: "+etype);
            etime = intent.getStringExtra("time");
            time.setText("Time: "+etime);
            ewhen = "2017-06-18";
            when.setText("By When: "+ewhen);
            //end prospect next step

        }

        //contact list
        ContactInfo p1 = new ContactInfo();
        p1.fname ="Aaron";
        p1.lname = "Bennet";
        p1.cname = "Berman and CO";
        p1.tel = "416-985-5514";
        p1.mobile = "416-725-1168";
        p1.email = "aaron@bermanco.ca";
        p1.position = "President";
        p1.notes = "Aaron is key influencer. Ex public accountant. Raises horses on family farm.";
        contactList.add(p1);

        ContactInfo p2 = new ContactInfo();
        p2.fname ="Abbey";
        p2.lname = "Christensen";
        p2.cname = "Frontier Films";
        p2.tel = "416-985-5514";
        p2.mobile = "416-725-1168";
        p2.email = "aaron@bermanco.ca";
        p2.position = "VP Finance";
        p2.notes = "Aaron is key influencer. Ex public accountant. Raises horses on family farm.";
        contactList.add(p2);

        ContactInfo p3 = new ContactInfo();
        p3.fname ="Adam";
        p3.lname = "Petty";
        p3.cname = "GT Enterprises";
        p3.tel = "416-985-5514";
        p3.mobile = "416-725-1168";
        p3.email = "aaron@bermanco.ca";
        p3.position = "Controller";
        p3.notes = "Aaron is key influencer. Ex public accountant. Raises horses on family farm.";
        contactList.add(p3);
        simpleList = (ListView) findViewById(R.id.list);

        LayoutParams list = (LayoutParams) simpleList.getLayoutParams();
        list.height = 975;
        simpleList.setLayoutParams(list);
        myAdapter = new  ProspectContactAdapter(this,contactList);
        simpleList.setAdapter(myAdapter);


    }
    public void onClick(View v) {
        if(v.getId() == R.id.browser) {
            Intent intent = new Intent(context, Insights.class);
            intent.putExtra("query",query);
            startActivity(intent);

        }
        else if(v.getId() == R.id.location ){
            Intent intent = new Intent(context, Insights.class);
            intent.putExtra("address",address);
            startActivity(intent);

        }
        else if(v.getId() == R.id.archive ){
            Intent intent = new Intent(context, Archive.class);
            startActivity(intent);

        }
    }
    //next step process

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_prospect,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.save:
                //Call the api to save the prospect
                createProspect();


                Boolean doNotCall = false;
                Boolean duplicate = false;
               // duplicate = false;

                if(doNotCall) {
                    //Check don't call list
                    final Dialog dialog = new Dialog(this);
                    dialog.setContentView(R.layout.do_not_call);
                    Button save = (Button) dialog.findViewById(R.id.save);
                    Button cancel = (Button) dialog.findViewById(R.id.cancel);
                    TextView company = (TextView) dialog.findViewById(R.id.companyName);
                    company.setText("Berman");
                    //click the save button
                    save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }

                    });
                    // click the cancel
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                    return true;
                    //end don't call list
                }
                else if(duplicate){
                    //Check duplicate
                    final Dialog dialog = new Dialog(this);
                    dialog.setContentView(R.layout.duplicate);
                    Button save = (Button) dialog.findViewById(R.id.save);
                    Button cancel = (Button) dialog.findViewById(R.id.cancel);

                    //click the save button
                    save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }

                    });
                    // click the cancel
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                    return true;
                    //end duplicate

                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createProspect() {
        String createUrl ="https://breezemaxlabs.com/RestAPI/v1/createProspect";
        StringRequest   stringRequest;
        //check the user input before pass to server
         sCName = companyName.getText().toString();
         sCity = cityTxt.getText().toString();
         sAddress = addressTxt.getText().toString();
        // sIndustry= industry.getText().toString();
         sYearEnd = yearEnd.getText().toString();
         sServiceLine = serviceLine.getText().toString();
         sMeetPlace = meetPlace.getText().toString();
        // sSubject = subject.getText().toString();
         //sWhen = when.getText().toString();
         //sMeeting = meeting.getText().toString();
         //sTime = time.getText().toString();
         //sNotes = notes.getText().toString();
        if(sCName.isEmpty())
            Toast.makeText(this,"Please fill up Company",Toast.LENGTH_LONG).show();
        if(sCity.isEmpty())
            Toast.makeText(this,"Please fill up City",Toast.LENGTH_LONG).show();
        if(sAddress.isEmpty())
            Toast.makeText(this,"Please fill up Address",Toast.LENGTH_LONG).show();
        if(sIndustry.isEmpty())
            sIndustry = "empty";
        if(sYearEnd.isEmpty())
            sYearEnd = "empty";
        if(sServiceLine.isEmpty())
            sServiceLine = "empty";
        if(sMeetPlace.isEmpty())
            sMeetPlace = "empty";
        if(sSubject.isEmpty())
            sSubject = "empty";
        if( sWhen.isEmpty())
            sWhen = "empty";
        if( sMeeting.isEmpty())
            sMeeting = "empty";
        if(sTime.isEmpty())
            sTime = "empty";
        if( sNotes .isEmpty())
            sNotes  = "empty";

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
                params.put("busn_name", sCName);
                params.put("city", sCity);
                params.put("add", sAddress);
                params.put("industry", sIndustry);
                params.put("year_end", sYearEnd);
                params.put("service", sServiceLine);
                params.put("met_add", sMeetPlace);
                params.put("subject", sSubject);
                params.put("by_when", sWhen);
                params.put("meeting_type", sMeeting);
                params.put("time", sTime);
                params.put("with_who", "empty");
                params.put("notes", sNotes);
                return params;
            }


        };

        String  REQUEST_TAG = "CreateProspect";
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest, REQUEST_TAG);

    }
    // Add Prospect Next Step
    public void addNextStep(View view){
       String cn= companyName.getText().toString();
        if(cn.isEmpty())
            companyName.setError("Company name is required");
        else if(cityTxt.getText().toString().isEmpty())
            cityTxt.setError("City name is required");
        else {
            Intent intent = new Intent(context, ProspectNextStep.class);
            intent.putExtra("addNextStep","true");
            intent.putExtra("company", companyName.getText().toString());
            startActivity(intent);
        }
    }
    // Edit prospect Next Step
    public void editNextStep(View view){
        Intent intent = new Intent(context, ProspectNextStep.class);
        intent.putExtra("company", companyName.getText().toString());
        intent.putExtra("subject", esubject);
        intent.putExtra("when", ewhen);
        intent.putExtra("meeting", etype);
        intent.putExtra("time", etime);
        intent.putExtra("with", ewith);
        startActivity(intent);
    }

    //Add more Contacts
    public void addContact(View view){
        String cn = companyName.getText().toString();
        if(cn.isEmpty()){
            Toast.makeText(this,"You have to fill up prospect information",Toast.LENGTH_LONG).show();
        }
        else{
            Intent intent = new Intent(this,AddContacts.class);
            intent.putExtra("companyName",cn);
            startActivity(intent);
        }

    }
    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(getApplicationContext(), pick_industry[position]+" Position:"+position, Toast.LENGTH_LONG).show();
        sIndustry = pick_industry[position];
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
