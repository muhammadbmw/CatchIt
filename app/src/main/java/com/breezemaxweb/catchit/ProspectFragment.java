package com.breezemaxweb.catchit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

/**
 * Created by BreezeMaxWeb on 2017-06-21.
 */

public class ProspectFragment extends Fragment {
    ListView simpleList;
    ArrayList<ViewProspect> prospectList = new ArrayList<ViewProspect>();

    ListViewAdapter myAdapter;
    SearchView editsearch;
    ViewProspect prospect;
    View view;

    public static ProspectFragment newInstance(){
        ProspectFragment fragment = new ProspectFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prospectList.add(new ViewProspect("BERMAN and CO.","Alex Petty","Book Discovery Meeting","February 28, 2017"));
        prospectList.add(new ViewProspect("FORNTIER FILMS INC.","Austin Green","Present Solution","February 21, 2017"));
        prospectList.add(new ViewProspect("MEGAFONE MEDIA","Alex Lu","Finalize Sale","January 15, 2017"));
        prospectList.add(new ViewProspect("TOWER MOVING","Adrian Manning","Book Discovery Meeting","January 31, 2017"));
        prospectList.add(new ViewProspect("SCUBA 200","Alex Petty","Book Discovery Meeting","February 23, 2017"));
        getActivity().setTitle("Prospects");

        myAdapter = new ListViewAdapter(getActivity(),prospectList);

        //enable menu items
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.prospect_fragment, container, false);
        simpleList = (ListView) view.findViewById(R.id.list);
        simpleList.setAdapter(myAdapter);
        // for search the list
        editsearch = (SearchView) view.findViewById(R.id.search);
        editsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        });

        return view;
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_prospect,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.addProspect){
            Intent intent = new Intent(getContext(), AddProspect.class);
            intent.putExtra("addProspect","true");
            startActivity(intent);
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }

}

