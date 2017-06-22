package com.breezemaxweb.catchit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import java.util.ArrayList;

public class MyProspects extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ListView simpleList;
    ArrayList<ViewProspect> prospectList = new ArrayList<ViewProspect>();

    ListViewAdapter myAdapter;
    SearchView editsearch;
    ViewProspect prospect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_prospects);
        this.setTitle("PROSPECTS");
        simpleList =(ListView) findViewById(R.id.simpleListView);
        prospectList.add(new ViewProspect("BERMAN and CO.","Alex Petty","Book Discovery Meeting","February 28, 2017"));
        prospectList.add(new ViewProspect("FORNTIER FILMS INC.","Austin Green","Present Solution","February 21, 2017"));
        prospectList.add(new ViewProspect("MEGAFONE MEDIA","Alex Lu","Finalize Sale","January 15, 2017"));
        prospectList.add(new ViewProspect("TOWER MOVING","Adrian Manning","Book Discovery Meeting","January 31, 2017"));
        prospectList.add(new ViewProspect("SCUBA 200","Alex Petty","Book Discovery Meeting","February 23, 2017"));


        myAdapter = new ListViewAdapter(this,prospectList);
        simpleList.setAdapter(myAdapter);

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        myAdapter.filter(text);
        return false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_prospect,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.addProspect){
            Intent intent = new Intent(this, AddProspect.class);
            intent.putExtra("addProspect","true");
            startActivity(intent);
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }
}
