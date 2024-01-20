package com.example.moodmate2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.example.moodmate2.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    ActivityMainBinding binding;
    String rating, date, notes;
    FirebaseDatabase db;
    DatabaseReference reference;
    Button home, stats, wellbeing, updateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize buttons
        home = binding.home;
        stats = binding.stats;
        wellbeing = binding.wellBeing;
        updateBtn = binding.updateBtn;

        // Set click listeners for buttons
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Home.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Guide.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        wellbeing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Wellbeing.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        // Check and request necessary permissions
        checkPermissions();

        // Set click listener for the update button
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Permissions were already granted or are not required, perform Firebase operations
                performFirebaseOperations();
            }
        });
    }

    private void checkPermissions() {
        String[] permissions = {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE
        };

        List<String> permissionList = new ArrayList<>();

        // Check if each permission is granted, if not, add it to the permission list
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }

        // If there are permissions to request, request them
        if (!permissionList.isEmpty()) {
            String[] permissionArray = permissionList.toArray(new String[0]);
            ActivityCompat.requestPermissions(this, permissionArray, PERMISSION_REQUEST_CODE);
        } else {
            // Permissions were already granted, perform Firebase operations
            performFirebaseOperations();
        }
    }

    private void performFirebaseOperations() {
        // Get the rating, date, and notes from the input fields
        rating = binding.rating.getText().toString();
        date = binding.date.getText().toString();
        notes = binding.notes.getText().toString();

        // Check if all the required fields are not empty
        if (!rating.isEmpty() && !date.isEmpty() && !notes.isEmpty()) {
            // Validate date format
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            dateFormatter.setLenient(false);
            try {
                Date inputDate = dateFormatter.parse(date);
                // Date format is valid
                // Validate rating
                int ratingValue = Integer.parseInt(rating);
                if (ratingValue >= 1 && ratingValue <= 5) {
                    // Create a new instance of the Moods class with the input data
                    Moods moods = new Moods(rating, date, notes);
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Moods");

                    // Save the mood data to the Firebase Realtime Database
                    reference.child(date).setValue(moods).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            // Clear the input fields after successful update
                            binding.date.setText("");
                            binding.rating.setText("");
                            binding.notes.setText("");
                            Toast.makeText(MainActivity.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    // Rating is invalid
                    Toast.makeText(MainActivity.this, "Rating must be between 1 and 5", Toast.LENGTH_SHORT).show();
                }
            } catch (ParseException e) {
                // Date format is invalid
                Toast.makeText(MainActivity.this, "Date format must be DD-MM-YYYY", Toast.LENGTH_SHORT).show();
            } catch (NumberFormatException e) {
                // Rating format is invalid
                Toast.makeText(MainActivity.this, "Rating must be a number", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Handle empty input fields
            Toast.makeText(MainActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            boolean allPermissionsGranted = true;

            // Check if all the requested permissions were granted
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    allPermissionsGranted = false;
                    break;
                }
            }

            if (allPermissionsGranted) {
                // Permissions granted, perform Firebase operations
                performFirebaseOperations();
            } else {
                // Permissions not granted, handle accordingly (e.g., display an error message)
                Toast.makeText(MainActivity.this, "Permission(s) not granted. Cannot upload to Firebase.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}




