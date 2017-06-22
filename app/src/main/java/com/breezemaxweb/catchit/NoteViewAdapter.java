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
 * Created by BreezeMaxWeb on 2017-05-08.
 */

public class NoteViewAdapter extends BaseAdapter {
    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<NoteInfo> list = null;
    public NoteViewAdapter(Context context, List<NoteInfo> noteList){
        mContext = context;
        list = noteList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public NoteInfo getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.note_each_item,null);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView day = (TextView) view.findViewById(R.id.date);
        title.setText(list.get(position).getSubject());
        String d = list.get(position).getDay();
        if(d.equals("0"))
            day.setText("Today");
        else if(d.equals("1"))
            day.setText("Yesterday");
        else if(d.equals("2"))
            day.setText("2 days ago");
        else if(d.equals("3"))
            day.setText("3 days ago");
        else if(d.equals("4"))
            day.setText("4 days ago");
        else if(d.equals("5"))
            day.setText("5 days ago");
        else if(d.equals("6"))
            day.setText("6 days ago");
        else if(d.equals("7"))
            day.setText("7 days ago");
        else
            day.setText("Week ago");

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddNotes.class);
                intent.putExtra("id",(list.get(position).getId()));
                intent.putExtra("subject",(list.get(position).getSubject()));
                intent.putExtra("note",(list.get(position).getNote()));
                mContext.startActivity(intent);

            }
        });

        return view;
    }

}