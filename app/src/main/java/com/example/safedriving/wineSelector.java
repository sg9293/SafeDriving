package com.example.safedriving;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

public class wineSelector extends AppCompatActivity {

    static double wine_Consumed = 0.0;
    static int redNumber = 0, whiteNumber = 0, roseNumber = 0, sparklingNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_selector);

        // Fancy button
        ElegantNumberButton fancyRed = findViewById(R.id.number_button5);
        fancyRed.setOnClickListener((ElegantNumberButton.OnClickListener) view -> {
            redNumber = Integer.parseInt(fancyRed.getNumber());

        });

        ElegantNumberButton fancyWhite = findViewById(R.id.number_button6);
        fancyWhite.setOnClickListener((ElegantNumberButton.OnClickListener) view -> {
            whiteNumber = Integer.parseInt(fancyWhite.getNumber());
        });

        ElegantNumberButton fancyRose = findViewById(R.id.number_button7);
        fancyRose.setOnClickListener((ElegantNumberButton.OnClickListener) view -> {
            roseNumber = Integer.parseInt(fancyRose.getNumber());

        });

        ElegantNumberButton fancySparkling = findViewById(R.id.number_button8);
        fancySparkling.setOnClickListener((ElegantNumberButton.OnClickListener) view -> {
            sparklingNumber = Integer.parseInt(fancySparkling.getNumber());

        });

    }

    public void goBackfromWine(View view) {

        wine_Consumed = wine_Consumed + (redNumber * (5 * 0.15));
        wine_Consumed = wine_Consumed + (whiteNumber * (5 * 0.1));
        wine_Consumed = wine_Consumed + (roseNumber * (5 * 0.12));
        wine_Consumed = wine_Consumed + (sparklingNumber * (5 * 0.122));

        Intent intent = new Intent(this, DrinkSelecter.class);
        startActivity(intent);

    }

    public void donewithDrinks2(View view) {

        wine_Consumed = wine_Consumed + (redNumber * (5 * 0.15));
        wine_Consumed = wine_Consumed + (whiteNumber * (5 * 0.1));
        wine_Consumed = wine_Consumed + (roseNumber * (5 * 0.12));
        wine_Consumed = wine_Consumed + (sparklingNumber * (5 * 0.122));

        Intent intent = new Intent(this, SummaryHour.class);
        startActivity(intent);

    }

}