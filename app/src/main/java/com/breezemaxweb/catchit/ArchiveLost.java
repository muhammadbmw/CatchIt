package com.breezemaxweb.catchit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ArchiveLost extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText lostReason;
    String reason;

    String [] lost_reason = {"Poor presales","Pricing out of range","Ineffective messaging","Business value mismatch",
    "Executive access","Incumbent advantage","Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive_lost);
        this.setTitle("Archive");
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner)findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the lost_reason name list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,lost_reason);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
    }
    public void onClick(View v){
            Intent intent = new Intent(this,Archive.class);
            startActivity(intent);
            finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_archive,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            reason = lostReason.getText().toString();
            Toast.makeText(this, "Archive Lost "+reason, Toast.LENGTH_LONG).show();
            return true;
        }
        else
            return super.onOptionsItemSelected(item);

    }
    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(getApplicationContext(), lost_reason[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
