package com.breezemaxweb.catchit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by BreezeMaxWeb on 2017-05-02.
 */

public class NavFragment extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bottom, container, false);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) view.findViewById(R.id.navbar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.home:
                                Intent intenth = new Intent(getContext(), MainActivity.class);
                                intenth.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intenth);
                                break;
                            case R.id.contact:
                                Intent intent = new Intent(getContext(), Contacts.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                break;
                            case R.id.notes:
                                Intent intent1 = new Intent( getContext(), Notes.class);
                                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent1);
                                break;
                            case R.id.calendar:
                                Intent intent2 = new Intent(getContext(), Calendar.class);
                                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent2);
                                break;
                            case R.id.prospects:
                                Intent intent3 = new Intent(getContext(), MyProspects.class);
                                intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent3);
                                break;

                        }
                        return true;
                    }
                });

        return view;

    }
}
