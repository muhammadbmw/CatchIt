package com.breezemaxweb.catchit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by BreezeMaxWeb on 2017-05-09.
 */

public class ContactViewAdapter extends BaseAdapter {
    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<ContactInfo> contactList = null;
    private ArrayList<ContactInfo> arraylist;
    public ContactViewAdapter(Context context, List<ContactInfo> clist) {
        mContext = context;
        this.contactList = clist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<ContactInfo>();
        this.arraylist.addAll(clist);

    }
    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public ContactInfo getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.contact_each_item,null);
        TextView textView1 = (TextView) view.findViewById(R.id.name);
        TextView textView2 = (TextView) view.findViewById(R.id.cname);
        textView1.setText(contactList .get(position).getFname() + " "+contactList .get(position).getLname());
        textView2.setText(contactList .get(position).getCname());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, AddContacts.class);
                intent.putExtra("company",contactList.get(position).getCname());
                intent.putExtra("fname",contactList.get(position).getFname());
                intent.putExtra("lname",contactList.get(position).getLname());
                intent.putExtra("tel",contactList.get(position).getTel());
                intent.putExtra("mobile",contactList.get(position).getMobile());
                intent.putExtra("email",contactList.get(position).getEmail());
                intent.putExtra("position",contactList.get(position).getPosition());
                intent.putExtra("note",contactList.get(position).getNotes());
                mContext.startActivity(intent);

            }
        });
        return view;
    }
    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        contactList.clear();
        if (charText.length() == 0) {
            contactList.addAll(arraylist);

        } else {
            for (ContactInfo clist : arraylist)
                if (clist.getFname().toLowerCase(Locale.getDefault()).contains(charText) || clist.getLname().toLowerCase(Locale.getDefault()).contains(charText)) {
                    contactList.add(clist);
                }
        }
        notifyDataSetChanged();
    }
}
