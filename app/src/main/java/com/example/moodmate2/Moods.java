package com.example.moodmate2;

// Class for storing mood data in the database
public class Moods {
    String rating; // Mood rating
    String date;   // Date of the mood entry
    String notes;  // Additional notes for the mood entry

    public Moods() {
        // Empty constructor required for Firebase database operations
    }

    public Moods(String rating, String date, String notes) {
        this.rating = rating;
        this.date = date;
        this.notes = notes;
    }

    // Getter for retrieving the mood rating
    public String getRating() {
        return rating;
    }

    // Setter for setting the mood rating
    public void setRating(String rating) {
        this.rating = rating;
    }

    // Getter for retrieving the date of the mood entry
    public String getDate() {
        return date;
    }

    // Setter for setting the date of the mood entry
    public void setDate(String date) {
        this.date = date;
    }

    // Getter for retrieving the additional notes for the mood entry
    public String getNotes() {
        return notes;
    }

    // Setter for setting the additional notes for the mood entry
    public void setNotes(String notes) {
        this.notes = notes;
    }
}

