package com.example.moodmate2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.moodmate2.databinding.ActivityHomeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    private ActivityHomeBinding binding;
    RecyclerView recyclerView;
    List<Moods> dataList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout for this activity using view binding
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Create button objects
        Button Todaysmood = binding.returnTodaysMood;
        Button Stats = binding.returnStats;
        Button Wellbeing = binding.wellBeing;

        // Set onClickListeners for the buttons
        Todaysmood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        Stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Guide.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        Wellbeing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Wellbeing.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        //code for RecyclerView
        //Enable the up button in the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Get a reference to the RecyclerView in the layout
        recyclerView = findViewById(R.id.recyclerView);
        // Create a GridLayoutManager with one column
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Home.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Create an AlertDialog that will show a progress indicator while the data loads
        AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        // Initialize the list that will hold the data for the RecyclerView
        dataList = new ArrayList<>();

        // Create an adapter for the RecyclerView
        MyAdapter adapter = new MyAdapter(Home.this, dataList);
        recyclerView.setAdapter(adapter);

        // Get a reference to the Firebase Realtime Database and attach a ValueEventListener to it
        databaseReference = FirebaseDatabase.getInstance().getReference("Moods");
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Clear the list before adding new data to it
                dataList.clear();
                // Loop through the snapshot to get each child and add it to the list
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    Moods dataClass = itemSnapshot.getValue(Moods.class);
                    dataList.add(dataClass);
                }
                // Notify the adapter that the data has changed
                adapter.notifyDataSetChanged();
                // Hide the progress dialog
                dialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // If there is an error, dismiss the progress dialog
                dialog.dismiss();
            }
        });
    }
}


