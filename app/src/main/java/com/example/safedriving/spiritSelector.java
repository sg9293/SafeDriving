package com.example.safedriving;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

public class spiritSelector extends AppCompatActivity {

    static int number_of_Walker = 0;
    static int number_of_Glen = 0;
    static int number_of_Barcardi = 0;
    static int number_of_Smirnoff = 0;
    static double spirit_Consumed = 0.0;
    boolean walkerSmall = false, glenSmall = false, barcardiSmall = false, smirnoffSmall = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spirit_selector);

        String[] spiritQuan = new String[]{"Single (30 ml)", "Double (60 ml)"};

        android.widget.Spinner spinner = (android.widget.Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spiritQuan);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        android.widget.Spinner spinner2 = (android.widget.Spinner) findViewById(R.id.spinner5);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spiritQuan);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        android.widget.Spinner spinner3 = (android.widget.Spinner) findViewById(R.id.spinner6);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spiritQuan);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        android.widget.Spinner spinner4 = (android.widget.Spinner) findViewById(R.id.spinner7);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spiritQuan);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        // Fancy button
        ElegantNumberButton fancyWalker = findViewById(R.id.number_button3);
        fancyWalker.setOnClickListener((ElegantNumberButton.OnClickListener) view -> {
            number_of_Walker = Integer.parseInt(fancyWalker.getNumber());
        });

        ElegantNumberButton fancyGlen = findViewById(R.id.number_button4);
        fancyGlen.setOnClickListener((ElegantNumberButton.OnClickListener) view -> {
            number_of_Glen = Integer.parseInt(fancyGlen.getNumber());
        });

        ElegantNumberButton fancyBarcardi = findViewById(R.id.number_button9);
        fancyBarcardi.setOnClickListener((ElegantNumberButton.OnClickListener) view -> {
            number_of_Barcardi = Integer.parseInt(fancyBarcardi.getNumber());
        });

        ElegantNumberButton fancySmirnoff = findViewById(R.id.number_button10);
        fancySmirnoff.setOnClickListener((ElegantNumberButton.OnClickListener) view -> {
            number_of_Smirnoff = Integer.parseInt(fancySmirnoff.getNumber());
        });

        if (spinner.getSelectedItem().toString().equals("Single (30 ml)")) {
            walkerSmall = true;
        }

        if (spinner2.getSelectedItem().toString().equals("Single (30 ml)")) {
            glenSmall = true;
        }

        if (spinner3.getSelectedItem().toString().equals("Single (30 ml)")) {
            barcardiSmall = true;
        }

        if (spinner4.getSelectedItem().toString().equals("Single (30 ml)")) {
            smirnoffSmall = true;
        }
    }

    public void goBackfromSpirit(View view) {

        if (walkerSmall) {
            spirit_Consumed = spirit_Consumed + (number_of_Walker * (1 * 0.4));
        } else {
            spirit_Consumed = spirit_Consumed + (number_of_Walker * (2 * 0.4));
        }

        if (glenSmall) {
            spirit_Consumed = spirit_Consumed + (number_of_Glen * (1 * 0.4));
        } else {
            spirit_Consumed = spirit_Consumed + (number_of_Glen * (2 * 0.4));
        }

        if (barcardiSmall) {
            spirit_Consumed = spirit_Consumed + (number_of_Barcardi * (1 * 0.4));
        } else {
            spirit_Consumed = spirit_Consumed + (number_of_Barcardi * (2 * 0.4));
        }

        if (smirnoffSmall) {
            spirit_Consumed = spirit_Consumed + (number_of_Smirnoff * (1 * 0.4));
        } else {
            spirit_Consumed = spirit_Consumed + (number_of_Smirnoff * (2 * 0.4));
        }

        Intent intent = new Intent(this, DrinkSelecter.class);
        startActivity(intent);

    }

    public void donewithDrinks1(View view) {

        if (walkerSmall) {
            spirit_Consumed = spirit_Consumed + (number_of_Walker * (1 * 0.4));
        } else {
            spirit_Consumed = spirit_Consumed + (number_of_Walker * (2 * 0.4));
        }

        if (glenSmall) {
            spirit_Consumed = spirit_Consumed + (number_of_Glen * (1 * 0.4));
        } else {
            spirit_Consumed = spirit_Consumed + (number_of_Glen * (2 * 0.4));
        }

        if (barcardiSmall) {
            spirit_Consumed = spirit_Consumed + (number_of_Barcardi * (1 * 0.4));
        } else {
            spirit_Consumed = spirit_Consumed + (number_of_Barcardi * (2 * 0.4));
        }

        if (smirnoffSmall) {
            spirit_Consumed = spirit_Consumed + (number_of_Smirnoff * (1 * 0.4));
        } else {
            spirit_Consumed = spirit_Consumed + (number_of_Smirnoff * (2 * 0.4));
        }

        Intent intent = new Intent(this, SummaryHour.class);
        startActivity(intent);

    }

}