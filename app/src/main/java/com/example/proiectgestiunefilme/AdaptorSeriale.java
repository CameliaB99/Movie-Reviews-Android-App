package com.example.proiectgestiunefilme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class AdaptorSeriale extends BaseAdapter {
    private List<String> seriale;
    private Context context;
    private LayoutInflater layoutInflater;
    //constructor pt initializare
    public AdaptorSeriale(List<String> seriale,Context context){
        this.seriale=seriale;
        this.context=context;
        this.layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return seriale.size();
    }

    @Override
    public Object getItem(int position) {
        return seriale.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //view-panza pe care desenez
        //ce vreau sa am desenat pt itemi
        final View view = layoutInflater.inflate(R.layout.itemserial,parent,false);
        TextView tv_denumire=view.findViewById(R.id.tv_seriale);

        final String string = seriale.get(position);
        //preluam in tv din listview denumirea filmului din lista de filme
        tv_denumire.setText(string);

        return view;
    }
}
