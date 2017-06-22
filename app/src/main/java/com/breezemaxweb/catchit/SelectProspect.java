package com.breezemaxweb.catchit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class SelectProspect extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ListView simpleList;
    ArrayList<ViewProspect> prospectList = new ArrayList<ViewProspect>();

    SelectProspectAdapter myAdapter;
    SearchView editsearch;
    ViewProspect prospect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_prospect);
        this.setTitle("Select Prospect");
        simpleList =(ListView) findViewById(R.id.list);

        prospectList.add(new ViewProspect("BERMAN and CO.","Alex Petty","Book Discovery Meeting","February 28, 2017"));
        prospectList.add(new ViewProspect("FORNTIER FILMS INC.","Austin Green","Present Solution","February 21, 2017"));
        prospectList.add(new ViewProspect("MEGAFONE MEDIA","Alex Lu","Finalize Sale","January 15, 2017"));
        prospectList.add(new ViewProspect("TOWER MOVING","Adrian Manning","Book Discovery Meeting","January 31, 2017"));
        prospectList.add(new ViewProspect("SCUBA 200","Alex Petty","Book Discovery Meeting","February 23, 2017"));

        myAdapter = new SelectProspectAdapter(this,prospectList);
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
}
