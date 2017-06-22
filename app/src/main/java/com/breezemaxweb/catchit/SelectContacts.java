package com.breezemaxweb.catchit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SelectContacts extends AppCompatActivity {
    TextView company;
    ListView list;
    ArrayList<ContactInfo> contactList = new ArrayList<ContactInfo>();
    SelectContactAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_contacts);
        this.setTitle("Contacts");

        company = (TextView)findViewById(R.id.companyName);
        list = (ListView)findViewById(R.id.contactList);

        Intent intent = getIntent();
        company.setText(intent.getStringExtra("company"));

        //contact list
        ContactInfo p1 = new ContactInfo();
        p1.fname ="Aaron";
        p1.lname = "Bennet";
        p1.cname = "Berman & CO.";
        p1.tel = "416-985-5514";
        p1.mobile = "416-725-1168";
        p1.email = "aaron@bermanco.ca";
        p1.position = "President";
        p1.notes = "Aaron is key influencer. Ex public accountant. Raises horses on family farm.";
        contactList.add(p1);

        ContactInfo p2 = new ContactInfo();
        p2.fname ="Abbey";
        p2.lname = "Christensen";
        p2.cname = "Berman & CO.";
        p2.tel = "416-985-5514";
        p2.mobile = "416-725-1168";
        p2.email = "aaron@bermanco.ca";
        p2.position = "VP Finance";
        p2.notes = "Aaron is key influencer. Ex public accountant. Raises horses on family farm.";
        contactList.add(p2);

        ContactInfo p3 = new ContactInfo();
        p3.fname ="Adam";
        p3.lname = "Petty";
        p3.cname = "Berman & CO.";
        p3.tel = "416-985-5514";
        p3.mobile = "416-725-1168";
        p3.email = "aaron@bermanco.ca";
        p3.position = "Controller";
        p3.notes = "Aaron is key influencer. Ex public accountant. Raises horses on family farm.";
        contactList.add(p3);

        myAdapter = new  SelectContactAdapter(this,contactList);
        list.setAdapter(myAdapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
            return super.onOptionsItemSelected(item);

    }
}
