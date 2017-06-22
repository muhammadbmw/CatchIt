package com.breezemaxweb.catchit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ProspectNextStep extends AppCompatActivity {
    TextView company;
    EditText subject, when, meeting, time, notes,who;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prospect_next_step);
        this.setTitle("Next step");

        company = (TextView) findViewById(R.id.company);
        subject = (EditText) findViewById(R.id.subject);
        when = (EditText) findViewById(R.id.when);
        meeting = (EditText) findViewById(R.id.meeting);
        time = (EditText) findViewById(R.id.time);
        who = (EditText) findViewById(R.id.with);
        notes = (EditText) findViewById(R.id.notes);

        Intent intent = getIntent();
        if(intent.hasExtra("addNextStep")) {
            company.setText(intent.getStringExtra("company"));
        }
        if(intent.hasExtra("subject")){

            subject.setText(intent.getStringExtra("subject"));
            when.setText(intent.getStringExtra("when"));
            meeting.setText(intent.getStringExtra("meeting"));
            time.setText(intent.getStringExtra("time"));
            who.setText(intent.getStringExtra("with"));
        }
    }
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
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    // select contact
    public void selectContact(View view){
        Intent intent = new Intent(this, SelectContacts.class);
        startActivity(intent);
    }

}
