package com.breezemaxweb.catchit;

        import android.content.Context;
        import android.content.Intent;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.TextView;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Locale;

/**
 * Created by BreezeMaxWeb on 2017-03-28.
 */

public class ListViewAdapter extends BaseAdapter {
    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<ViewProspect> prospectslist = null;
    private ArrayList<ViewProspect> arraylist;
    public ListViewAdapter(Context context, List<ViewProspect> plist) {
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

        view = inflater.inflate(R.layout.list_view_items,null);
        TextView textView1 = (TextView) view.findViewById(R.id.textView2);
        TextView textView2 = (TextView) view.findViewById(R.id.textView3);
        TextView textView3 = (TextView) view.findViewById(R.id.textView4);
        TextView textView4 = (TextView) view.findViewById(R.id.textView5);
        textView1.setText(prospectslist .get(position).getCompany());
        textView2.setText(prospectslist .get(position).getMeet());
        textView3.setText(prospectslist .get(position).getStatus());
        textView4.setText(prospectslist .get(position).getDate());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddProspect.class);
                intent.putExtra("company",(prospectslist.get(position).getCompany()));
                intent.putExtra("meet",(prospectslist.get(position).getMeet()));
                intent.putExtra("status",(prospectslist.get(position).getStatus()));
                intent.putExtra("date",(prospectslist.get(position).getDate()));
                mContext.startActivity(intent);
            }
        });
        //view.setBackgroundColor(colorPrimary);

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
