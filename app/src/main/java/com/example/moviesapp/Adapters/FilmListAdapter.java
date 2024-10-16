package com.example.moviesapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.moviesapp.Domains.Film;
import com.example.moviesapp.R;

import java.util.ArrayList;

public class FilmListAdapter extends RecyclerView.Adapter<FilmListAdapter.ViewHolder> {
    private ArrayList<Film> items;
    private Context context;

    // Constructor that accepts an ArrayList<Film> and Context
    public FilmListAdapter(ArrayList<Film> items, Context context) {
        this.items = items;
        this.context = context; // Initialize context in the constructor
    }

    @NonNull
    @Override
    public FilmListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.film_viewholder, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmListAdapter.ViewHolder holder, int position) {
        holder.titleTxt.setText(items.get(position).getTitle());
        RequestOptions requestOptions = new RequestOptions()
                .transform(new CenterCrop(), new RoundedCorners(30));

        Glide.with(context)
                .load(items.get(position).getPoster())
                .apply(requestOptions)
                .into(holder.pic);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click event
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();  // Return the total number of films
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt;
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.nameTxt);  // Ensure correct reference to TextView
            pic = itemView.findViewById(R.id.pic);           // Ensure correct reference to ImageView
        }
    }
}
