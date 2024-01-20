package com.example.moodmate2;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.moodmate2.databinding.ActivityWellbeingBinding;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Wellbeing extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private ActivityWellbeingBinding binding;
    private GestureDetectorCompat gestureDetector;

    // Array of well-being suggestions
    private String[] wellBeingSuggestions = {
            "Take a walk in nature",
            "Practice deep breathing exercises",
            "Try meditation for 10 minutes",
            "Write down three things you are grateful for",
            "Listen to your favorite music",
            // Add more suggestions as needed
    };

    private TextView suggestionTextView;
    private int currentSuggestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWellbeingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        suggestionTextView = binding.suggestionTextView;

        gestureDetector = new GestureDetectorCompat(this, this);

        Button homeButton = binding.home;
        Button todaysMoodButton = binding.returnTodaysMood;
        Button guideButton = binding.returnStats;

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to the Home activity
                Intent intent = new Intent(Wellbeing.this, Home.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        todaysMoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to the MainActivity to set today's mood
                Intent intent = new Intent(Wellbeing.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        guideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to the Guide activity
                Intent intent = new Intent(Wellbeing.this, Guide.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        // Shuffle the array of well-being suggestions
        List<String> shuffledSuggestions = new ArrayList<>();
        Collections.addAll(shuffledSuggestions, wellBeingSuggestions);
        Collections.shuffle(shuffledSuggestions);
        wellBeingSuggestions = shuffledSuggestions.toArray(new String[0]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float distanceX = e2.getX() - e1.getX();
        float distanceY = e2.getY() - e1.getY();

        if (Math.abs(distanceX) > Math.abs(distanceY) && distanceX > 0) {
            // Swipe right: Move to the next well-being suggestion
            currentSuggestionIndex = (currentSuggestionIndex + 1) % wellBeingSuggestions.length;
            String newSuggestion = wellBeingSuggestions[currentSuggestionIndex];
            suggestionTextView.setText(newSuggestion);
            return true;
        }

        return false;
    }
}


