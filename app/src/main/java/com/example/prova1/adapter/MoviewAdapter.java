package com.example.prova1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prova1.MovieResults;
import com.example.prova1.R;

import java.util.List;

public class MoviewAdapter extends RecyclerView.Adapter<MoviewAdapter.MyViewHolder> {

    private List<MovieResults.Result> movieList;
    private Context context;

    public MoviewAdapter(List<MovieResults.Result> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_list,parent,false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(movieList.get(position).getBackdropPath()).into(holder.imageView);

        holder.textView.setText(movieList.get(position).getTitle());


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.poster);
            textView = (TextView) itemView.findViewById(R.id.titleMovie);
        }
    }
}
