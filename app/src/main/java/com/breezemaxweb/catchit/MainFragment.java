package com.breezemaxweb.catchit;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by BreezeMaxWeb on 2017-06-20.
 */

public class MainFragment extends Fragment {
    ListView todayList;
    ArrayList<ScheduleToday> arrayList = new ArrayList<ScheduleToday>();
    TodayScheduleAdapter tsAdapter;

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();

    // Session Manager Class
    SessionManager session;

    View view;
    public static MainFragment newInstance(){
        MainFragment fragment = new MainFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("");

        arrayList.add(new ScheduleToday("BERMAIN and CO.","Alex Petty","Book Discovery Meeting","Call","10:30 AM"));
        arrayList.add(new ScheduleToday("FORNTIER FILMS INC.","Austin Green","Present Solution","In Person","11:30 AM"));
        arrayList.add(new ScheduleToday("MEGAFONE MEDIA","Alex Lu","Finalize Sale","In Person","12:30 PM"));
        tsAdapter = new TodayScheduleAdapter(getActivity(),arrayList);

        // Session class instance
        session = new SessionManager(getActivity());


        //enable menu items
        setHasOptionsMenu(true);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_fragment, container, false);
        todayList = (ListView) view.findViewById(R.id.list);
        todayList.setAdapter(tsAdapter);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.logout,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
              session.logoutUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
