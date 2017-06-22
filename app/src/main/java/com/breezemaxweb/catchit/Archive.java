package com.breezemaxweb.catchit;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Archive extends AppCompatActivity {
    EditText saleValue, closeDate;
    String value,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        this.setTitle("Archive");

        saleValue = (EditText)findViewById(R.id.saleValue);
        closeDate = (EditText)findViewById(R.id.closeDate);
    }
    public void onClick(View v){
        Intent intent = new Intent(this,ArchiveLost.class);
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
            value = saleValue.getText().toString();
            date = closeDate.getText().toString();
            Toast.makeText(this, "Archive Won "+value+" "+date, Toast.LENGTH_LONG).show();
            return true;
        }
        else if(item.getItemId() == android.R.id.home){
            onBackPressed();
           // NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        else
            return super.onOptionsItemSelected(item);


    }

}
