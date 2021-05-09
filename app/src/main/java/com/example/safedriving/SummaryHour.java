package com.example.safedriving;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

public class SummaryHour extends AppCompatActivity {

    int n_hour, n_min;
    static double timeSpent = 0.0;
    String summary = "";
    static double alcohol_consumed = 0.0;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_hour);

        TextView summaryText = findViewById(R.id.textView7);

        summary = "Summary of your selection:\n";

        if (beerSelector.number_of_Sam != 0) {
            summary = summary + "\nYou drank " + beerSelector.number_of_Sam + " of SamAdams";
        }

        if (beerSelector.number_of_Bud != 0) {
            summary = summary + "\nYou drank " + beerSelector.number_of_Bud + " of BudLight";
        }

        if (beerSelector.number_of_Heineken != 0) {
            summary = summary + "\nYou drank " + beerSelector.number_of_Heineken + " of Heineken";
        }

        if (beerSelector.number_of_Corona != 0) {
            summary = summary + "\nYou drank " + beerSelector.number_of_Corona + " of Corona";
        }

        if (spiritSelector.number_of_Walker != 0) {
            summary = summary + "\nYou drank " + spiritSelector.number_of_Walker + " of Johnny Walker";
        }

        if (spiritSelector.number_of_Glen != 0) {
            summary = summary + "\nYou drank " + spiritSelector.number_of_Glen + " of Glen";
        }

        if (spiritSelector.number_of_Smirnoff != 0) {
            summary = summary + "\nYou drank " + spiritSelector.number_of_Smirnoff + " of Smirnoff";
        }

        if (spiritSelector.number_of_Barcardi != 0) {
            summary = summary + "\nYou drank " + spiritSelector.number_of_Barcardi + " of Barcardi";
        }

        if (wineSelector.redNumber != 0) {
            summary = summary + "\nYou drank " + wineSelector.redNumber + " of Red Wine Glasses";
        }

        if (wineSelector.whiteNumber != 0) {
            summary = summary + "\nYou drank " + wineSelector.whiteNumber + " of White Wine Glasses";
        }

        if (wineSelector.roseNumber != 0) {
            summary = summary + "\nYou drank " + wineSelector.roseNumber + " of Rose Wine Glasses";
        }

        if (wineSelector.sparklingNumber != 0) {
            summary = summary + "\nYou drank " + wineSelector.sparklingNumber + " of Champagne Wine Glasses";
        }

        summary = summary + "\n\nSelect the approx number of hours and min you have been drinking:";
//                "You have consumed %.2f of Abv beer\n" +
//                "You have consumed %.2f of Abv spirits\n" +
//                "You have consumed %.2f of Abv wine\n\n" +
//                "Select the approximate number of hours and minutes you have been drinking",
//                beerSelector.beer_Consumed, spiritSelector.spirit_Consumed, wineSelector.wine_Consumed);

        alcohol_consumed = beerSelector.beer_Consumed + spiritSelector.spirit_Consumed + wineSelector.wine_Consumed;

        summaryText.setText(summary);

        TimePicker simpleTimePicker = (TimePicker)findViewById(R.id.simpleTimePicker); // initiate a time picker
        simpleTimePicker.setIs24HourView(true); // set 24 hours mode for the time picker
//        simpleTimePicker.setHour(0);
//        simpleTimePicker.setMinute(0);

        simpleTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                n_hour = hourOfDay;
                n_min = minute;
            }
        });
    }

    public void done(View view) {

        if (n_min >= 0 && n_min < 15) {
            timeSpent = n_hour + 0.25;
        } else if (n_min >= 15 && n_min < 30) {
            timeSpent = n_hour + 0.5;
        } else if (n_min >= 30 && n_min < 45) {
            timeSpent = n_hour + 0.75;
        } else if (n_min >= 45 && n_min < 60) {
            timeSpent = n_hour + 0.75;
        } else {
            timeSpent = n_hour + 1;
        }

        Intent intent = new Intent(this, Results.class);
        startActivity(intent);
    }
}