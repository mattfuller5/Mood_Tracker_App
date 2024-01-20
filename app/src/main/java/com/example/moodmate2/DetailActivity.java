package com.example.moodmate2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    // Declare TextView variables
    TextView detailDate, detailNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set up the activity layout and enable back navigation
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize TextViews
        detailDate = findViewById(R.id.detailDate);
        detailNotes = findViewById(R.id.detailNotes);

        // Retrieve data from previous activity
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            // Display the notes and date on the TextViews
            detailNotes.setText(bundle.getString("Notes"));
            detailDate.setText(bundle.getString("Date"));
        }

        // Configure the share button
        Button button = findViewById(R.id.shareButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to share the text
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, detailNotes.getText());
                intent.setType("text/plain");

                // Check if there is an app available to handle the intent
                if (intent.resolveActivity(getPackageManager()) != null) {
                    // Start the activity to share the text
                    startActivity(intent);
                }
            }
        });
    }
}
