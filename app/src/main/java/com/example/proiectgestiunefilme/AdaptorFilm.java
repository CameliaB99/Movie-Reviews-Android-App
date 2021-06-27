package com.example.proiectgestiunefilme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class AdaptorFilm extends BaseAdapter {
    private List<Film> filme;
    private Context context;
    private LayoutInflater layoutInflater;
    //constructor pt initializare
    public AdaptorFilm(List<Film> filme,Context context){
        this.filme=filme;
        this.context=context;
        this.layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return filme.size();
    }

    @Override
    public Object getItem(int position) {
        return filme.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //view-panza pe care desenez
        //ce vreau sa am desenat pt itemi
        final View view = layoutInflater.inflate(R.layout.itemfilm,parent,false);
        TextView tv_denumire=view.findViewById(R.id.tv_item_film);
        RatingBar ratingBar = view.findViewById(R.id.ratingBar2);
        final Film film = filme.get(position);
        //preluam in tv din listview denumirea filmului din lista de filme
        tv_denumire.setText(film.getDenumireFilm());
        ratingBar.setRating((float) (film.getRatingImdb()/2));
        return view;
    }
}
