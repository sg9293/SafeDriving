package com.example.safedriving;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DrinkSelecter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_selecter);
    }

    public void beerSelected(View view) {
        Intent intent = new Intent(this, beerSelector.class);
        startActivity(intent);
    }

    public void spiritSelected(View view) {
        Intent intent = new Intent(this, spiritSelector.class);
        startActivity(intent);
    }

    public void wineSelected(View view) {
        Intent intent = new Intent(this, wineSelector.class);
        startActivity(intent);
    }

    public void doneDrinks(View view) {
        Intent intent = new Intent(this, SummaryHour.class);
        startActivity(intent);
    }
}