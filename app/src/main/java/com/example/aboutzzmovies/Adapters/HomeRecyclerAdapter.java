package com.example.aboutzzmovies.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aboutzzmovies.Listeners.OnMovieClickListeners;
import com.example.aboutzzmovies.Models.SearchArrayObject;
import com.example.aboutzzmovies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeRecyclerAdapter extends  RecyclerView.Adapter<HomeViewHolder>{

    Context context;
    List<SearchArrayObject> list;
    OnMovieClickListeners listeners;

    public HomeRecyclerAdapter(Context context, List<SearchArrayObject> list, OnMovieClickListeners listeners) {
        this.context = context;
        this.list = list;
        this.listeners = listeners;
    }

    @NonNull

    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new HomeViewHolder(LayoutInflater.from(context).inflate(R.layout.home_movies_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.textView_movie.setText(list.get(position).getTitle());
        holder.textView_movie.setSelected(true);
        Picasso.get().load(list.get(position).getImage()).into(holder.imageView_poster);
        holder.home_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onMovieClick(list.get(position).getId());
            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}









class HomeViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView_poster ;
    TextView textView_movie;
    CardView home_container;

    public HomeViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView_poster = itemView.findViewById(R.id.imageView_poster);
        textView_movie = itemView.findViewById(R.id.textView_movie);
        home_container = itemView.findViewById(R.id.home_container);

    }
}