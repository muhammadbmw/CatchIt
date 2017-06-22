package com.breezemaxweb.catchit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by BreezeMaxWeb on 2017-05-05.
 */

public class TodayScheduleAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    private List<ScheduleToday> todayList = null;

    public TodayScheduleAdapter(Context context, List<ScheduleToday> list){
        mContext = context;
        todayList = list;
        inflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return todayList.size();
    }

    @Override
    public ScheduleToday getItem(int position) {
        return todayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.list_schedule_today,null);
        TextView textView1 = (TextView) view.findViewById(R.id.companyName);
        TextView textView2 = (TextView) view.findViewById(R.id.meetPersonName);
        TextView textView3 = (TextView) view.findViewById(R.id.meetingName);
        TextView textView4 = (TextView) view.findViewById(R.id.meetingType);
       // TextView textView5 = (TextView) view.findViewById(R.id.time);
        String time = todayList.get(position).getTime();
        String mtype = todayList.get(position).getmType();
        String meetWith = todayList.get(position).getMeetPersonName();
        textView1.setText(todayList.get(position).getCompanyName());
        textView2.setText("With: "+meetWith);
        textView3.setText(todayList.get(position).getmName());
        textView4.setText(mtype+" - "+time);
        //textView5.setText(todayList.get(position).getTime());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddProspect.class);
                intent.putExtra("activity","MainActivity");
                intent.putExtra("company",todayList.get(position).getCompanyName());
                intent.putExtra("with",todayList.get(position).getMeetPersonName() );
                intent.putExtra("meeting",todayList.get(position).getmName());
                intent.putExtra("type",todayList.get(position).getmType());
                intent.putExtra("time",todayList.get(position).getTime());
                mContext.startActivity(intent);
            }

        });

        return view;
    }
}
