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

public class ContactFragment extends Fragment {
    ListView simpleList;
    ArrayList<ContactInfo> contactList = new ArrayList<ContactInfo>();

    ContactViewAdapter myAdapter;
    SearchView editsearch;
    View view;

    public static ContactFragment newInstance(){
        ContactFragment fragment = new ContactFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle("Contacts");

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
        p2.cname = "Frontier Films";
        p2.tel = "416-985-5514";
        p2.mobile = "416-725-1168";
        p2.email = "aaron@bermanco.ca";
        p2.position = "VP Finance";
        p2.notes = "Aaron is key influencer. Ex public accountant. Raises horses on family farm.";
        contactList.add(p2);

        ContactInfo p3 = new ContactInfo();
        p3.fname ="Adam";
        p3.lname = "Petty";
        p3.cname = "GT Enterprises";
        p3.tel = "416-985-5514";
        p3.mobile = "416-725-1168";
        p3.email = "aaron@bermanco.ca";
        p3.position = "Controller";
        p3.notes = "Aaron is key influencer. Ex public accountant. Raises horses on family farm.";
        contactList.add(p3);

        ContactInfo p4 = new ContactInfo();
        p4.fname ="Andrew";
        p4.lname = "Petty";
        p4.cname = "Inline Fiberglass";
        p4.tel = "416-985-5514";
        p4.mobile = "416-725-1168";
        p4.email = "aaron@bermanco.ca";
        p4.position = "VP Finance";
        p4.notes = "Aaron is key influencer. Ex public accountant. Raises horses on family farm.";
        contactList.add(p4);

        ContactInfo p5 = new ContactInfo();
        p5.fname ="Anthony";
        p5.lname = "Stevens";
        p5.cname = "Peel Plastics";
        p5.tel = "416-985-5514";
        p5.mobile = "416-725-1168";
        p5.email = "aaron@bermanco.ca";
        p5.position = "VP Finance";
        p5.notes = "Aaron is key influencer. Ex public accountant. Raises horses on family farm.";
        contactList.add(p5);

        ContactInfo p6 = new ContactInfo();
        p6.fname ="Becky";
        p6.lname = "Bennet";
        p6.cname = "Frontier Films";
        p6.tel = "416-985-5514";
        p6.mobile = "416-725-1168";
        p6.email = "aaron@bermanco.ca";
        p6.position = "VP Finance";
        p6.notes = "Aaron is key influencer. Ex public accountant. Raises horses on family farm.";
        contactList.add(p6);

        ContactInfo p7 = new ContactInfo();
        p7.fname ="Billy";
        p7.lname = "Petty";
        p7.cname = "GT Enterprises";
        p7.tel = "416-985-5514";
        p7.mobile = "416-725-1168";
        p7.email = "aaron@bermanco.ca";
        p7.position = "VP Finance";
        p7.notes = "Aaron is key influencer. Ex public accountant. Raises horses on family farm.";
        contactList.add(p7);

        ContactInfo p8 = new ContactInfo();
        p8.fname ="Bandon";
        p8.lname = "Stevens";
        p8.cname = "Inline Fiberglass";
        p8.tel = "416-985-5514";
        p8.mobile = "416-725-1168";
        p8.email = "aaron@bermanco.ca";
        p8.position = "VP Finance";
        p8.notes = "Aaron is key influencer. Ex public accountant. Raises horses on family farm.";
        contactList.add(p8);

        myAdapter = new  ContactViewAdapter(getActivity(),contactList);

        //enable menu items
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.contact_fragment, container, false);
        simpleList = (ListView) view.findViewById(R.id.list);
        simpleList.setAdapter(myAdapter);
        //Search the contact list
        editsearch = (SearchView) view.findViewById(R.id.searchContact);
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
        inflater.inflate(R.menu.add_contacts,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.addContact){
            Intent intent = new Intent(getContext(), AddContacts.class);
            startActivity(intent);
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }


}
