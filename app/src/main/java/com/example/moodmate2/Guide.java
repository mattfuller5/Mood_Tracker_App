package com.example.moodmate2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.Button;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.moodmate2.databinding.ActivityStatsBinding;

public class Guide extends AppCompatActivity {

    private ActivityStatsBinding binding;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout using ViewBinding
        binding = ActivityStatsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        webView = binding.webview;

        // Create button objects
        Button Todaysmood = binding.returnTodaysMood;
        Button Home = binding.home;
        Button Wellbeing = binding.wellBeing;

        // Configure WebView settings
        WebSettings webSettings = webView.getSettings();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new Callback());
        webView.loadUrl("file:///android_asset/Guide.html");

        // Set click listeners for buttons

        Todaysmood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start MainActivity when Todaysmood button is clicked
                Intent intent = new Intent(Guide.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start Home activity when Home button is clicked
                Intent intent = new Intent(Guide.this, Home.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        Wellbeing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start Wellbeing activity when Wellbeing button is clicked
                Intent intent = new Intent(Guide.this, Wellbeing.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }

    // Custom WebViewClient to handle URL loading
    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
    }

    // Handle back button presses for WebView navigation
    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    // Clean up ViewBinding in onDestroy
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
