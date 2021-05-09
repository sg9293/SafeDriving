package com.example.safedriving;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

public class beerSelector extends AppCompatActivity {

    static int number_of_Sam = 0;
    static int number_of_Bud = 0;
    static int number_of_Heineken = 0;
    static int number_of_Corona = 0;
    static double beer_Consumed = 0.0;
    boolean samSmall = false, budSmall = false, heinSmall = false, coronaSmall = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);

        String[] beerQuan = new String[]{"Regular (12 oz)", "Lager (16 oz)"};

        android.widget.Spinner spinner = (android.widget.Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, beerQuan);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        android.widget.Spinner spinner2 = (android.widget.Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, beerQuan);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        android.widget.Spinner spinner3 = (android.widget.Spinner) findViewById(R.id.spinner8);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, beerQuan);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        android.widget.Spinner spinner4 = (android.widget.Spinner) findViewById(R.id.spinner9);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, beerQuan);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        // Fancy button
        ElegantNumberButton fancySam = findViewById(R.id.number_button);

        fancySam.setOnClickListener((ElegantNumberButton.OnClickListener) view -> {
            number_of_Sam = Integer.parseInt(fancySam.getNumber());
        });

        ElegantNumberButton fancyBud = findViewById(R.id.number_button2);
        fancyBud.setOnClickListener((ElegantNumberButton.OnClickListener) view -> {
            number_of_Bud = Integer.parseInt(fancyBud.getNumber());
        });

        ElegantNumberButton fancyHein = findViewById(R.id.number_button11);

        fancyHein.setOnClickListener((ElegantNumberButton.OnClickListener) view -> {
            number_of_Heineken = Integer.parseInt(fancyHein.getNumber());
        });

        ElegantNumberButton fancyCorona = findViewById(R.id.number_button12);
        fancyCorona.setOnClickListener((ElegantNumberButton.OnClickListener) view -> {
            number_of_Corona = Integer.parseInt(fancyCorona.getNumber());
        });


        if (spinner.getSelectedItem().toString().equals("Regular (12 oz)")) {
            samSmall = true;
        }

        if (spinner2.getSelectedItem().toString().equals("Regular (12 oz)")) {
            budSmall = true;
        }

        if (spinner3.getSelectedItem().toString().equals("Regular (12 oz)")) {
            heinSmall = true;
        }

        if (spinner4.getSelectedItem().toString().equals("Regular (12 oz)")) {
            coronaSmall = true;
        }
    }

    public void goBackfromBeer(View view) {

        if (samSmall) {
            beer_Consumed = beer_Consumed + (number_of_Sam * (12 * 0.068));
        } else {
            beer_Consumed = beer_Consumed + (number_of_Sam * (16 * 0.068));
        }

        if (budSmall) {
            beer_Consumed = beer_Consumed + (number_of_Bud * (12 * 0.06));
        } else {
            beer_Consumed = beer_Consumed + (number_of_Bud * (16 * 0.06));
        }

        if (heinSmall) {
            beer_Consumed = beer_Consumed + (number_of_Heineken * (12 * 0.05));
        } else {
            beer_Consumed = beer_Consumed + (number_of_Heineken * (16 * 0.05));
        }

        if (coronaSmall) {
            beer_Consumed = beer_Consumed + (number_of_Corona * (12 * 0.046));
        } else {
            beer_Consumed = beer_Consumed + (number_of_Corona * (16 * 0.046));
        }

        Intent intent = new Intent(this, DrinkSelecter.class);
        startActivity(intent);

    }

    public void donewithDrinks(View view) {

        if (samSmall) {
            beer_Consumed = beer_Consumed + (number_of_Sam * (12 * 0.05));
        } else {
            beer_Consumed = beer_Consumed + (number_of_Sam * (16 * 0.05));
        }

        if (budSmall) {
            beer_Consumed = beer_Consumed + (number_of_Bud * (12 * 0.06));
        } else {
            beer_Consumed = beer_Consumed + (number_of_Bud * (16 * 0.06));
        }

        if (heinSmall) {
            beer_Consumed = beer_Consumed + (number_of_Heineken * (12 * 0.06));
        } else {
            beer_Consumed = beer_Consumed + (number_of_Heineken * (16 * 0.06));
        }

        if (coronaSmall) {
            beer_Consumed = beer_Consumed + (number_of_Corona * (12 * 0.06));
        } else {
            beer_Consumed = beer_Consumed + (number_of_Corona * (16 * 0.06));
        }

        Intent intent = new Intent(this, SummaryHour.class);
        startActivity(intent);

    }
}