package com.breezemaxweb.catchit;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("");

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navbar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.home:
                                selectedFragment = MainFragment.newInstance();
                                break;
                            case R.id.contact:
                                selectedFragment = ContactFragment.newInstance();
                                break;
                            case R.id.notes:
                                selectedFragment = NotesFragment.newInstance();
                                break;
                            case R.id.calendar:
                                selectedFragment = CalendarFragment.newInstance();
                                break;
                            case R.id.prospects:
                                selectedFragment = ProspectFragment.newInstance();
                                break;

                        }
                        if(selectedFragment != null) {
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.content, selectedFragment);
                            transaction.commit();
                            return true;
                        }
                        return false;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, MainFragment.newInstance());
        transaction.commit();
    }


}
