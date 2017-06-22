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
 * Created by BreezeMaxWeb on 2017-06-14.
 */

public class SelectProspectAdapter extends BaseAdapter {
    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<ViewProspect> prospectslist = null;
    private ArrayList<ViewProspect> arraylist;
    public SelectProspectAdapter(Context context, List<ViewProspect> plist) {
        mContext = context;
        this.prospectslist = plist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<ViewProspect>();
        this.arraylist.addAll(plist);
    }
    @Override
    public int getCount() {
        return prospectslist.size() ;
    }

    @Override
    public  ViewProspect getItem(int position) {
        return prospectslist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        view = inflater.inflate(R.layout.select_prospect_each_item,null);
        TextView textView1 = (TextView) view.findViewById(R.id.companyName);

        textView1.setText(prospectslist .get(position).getCompany());

        return view;
    }
    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        prospectslist.clear();
        if (charText.length() == 0) {
            prospectslist.addAll(arraylist);

        } else {
            for (ViewProspect plist : arraylist)
                if (plist.getCompany().toLowerCase(Locale.getDefault()).contains(charText)) {
                    prospectslist.add(plist);
                }
        }
        notifyDataSetChanged();
    }
}
