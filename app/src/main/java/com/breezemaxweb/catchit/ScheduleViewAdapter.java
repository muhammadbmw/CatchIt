package com.breezemaxweb.catchit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by BreezeMaxWeb on 2017-03-29.
 */

public class ScheduleViewAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    private List<Schedules> slist = null;
    private ArrayList<Schedules> arraylist;
    public ScheduleViewAdapter(Context context, List<Schedules> list){
        mContext = context;
        this.slist = list;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Schedules>();
        this.arraylist.addAll(slist);
    }
    @Override
    public int getCount() {
        return slist.size();
    }

    @Override
    public Schedules getItem(int position) {
        return slist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.list_schedule,null);

        TextView textView2 = (TextView) view.findViewById(R.id.textcompany);
        TextView textView3 = (TextView) view.findViewById(R.id.textView9);
        TextView textView4 = (TextView) view.findViewById(R.id.textView10);

        textView2.setText(slist .get(position).getCompany());
        textView3.setText(slist .get(position).getMeeting());
        textView4.setText(slist .get(position).getTime());

        return view;
    }
    public void filter(int day,int month,int year) {

        slist.clear();
        if ( day==0 ||month==0 ||year==0) {
            slist.addAll(arraylist);

        } else {
            for (Schedules plist : arraylist)
                if (plist.getDay()==day && (plist.getMonth()== month) && (plist.getYear()==year)) {
                    slist.add(plist);
                }
        }
        notifyDataSetChanged();
    }
}
