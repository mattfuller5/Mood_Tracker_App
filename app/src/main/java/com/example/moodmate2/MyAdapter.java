package com.example.moodmate2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<Moods> dataList;

    public MyAdapter(Context context, List<Moods> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Bind the data to the views in each item of the RecyclerView
        holder.recDate.setText(dataList.get(position).getDate());
        holder.recNotes.setText(dataList.get(position).getNotes());
        holder.recRating.setText(dataList.get(position).getRating());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle click events on the RecyclerView items
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Notes", dataList.get(holder.getAdapterPosition()).getNotes());
                intent.putExtra("Date", dataList.get(holder.getAdapterPosition()).getDate());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {

    TextView recDate, recRating, recNotes;
    CardView recCard;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        // Initialize the views in each item of the RecyclerView
        recCard = itemView.findViewById(R.id.recCard);
        recDate = itemView.findViewById(R.id.recDate);
        recRating = itemView.findViewById(R.id.recRating);
        recNotes = itemView.findViewById(R.id.recNotes);
    }
}
