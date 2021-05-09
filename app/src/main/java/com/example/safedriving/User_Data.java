package com.example.safedriving;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class User_Data extends AppCompatActivity {

    static String user_name = "";
    static double user_weight = 0.0, gender_const = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        String[] weights = new String[150];
        int min = 100;
        for (int i = 0; i < 150; i++ ) {
            weights[i] = String.valueOf(min);
            min++;
        }

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, weights);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }


    public void submit(View view) {
        EditText name = findViewById(R.id.userName);
        CheckBox isMale = findViewById(R.id.checkBox2);
        CheckBox isFemale = findViewById(R.id.checkBox);
        Spinner weight = findViewById(R.id.spinner);

        if (name.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    (CharSequence) ("Enter your Name"),
                    Toast.LENGTH_SHORT).show();
        }
        else {
            user_name = name.getText().toString();
            user_weight = Double.parseDouble(weight.getSelectedItem().toString());
            if (isMale.isChecked() && !isFemale.isChecked()) {
                gender_const = 0.73;
            } else if (isFemale.isChecked() && !isMale.isChecked()) {
                gender_const = 0.66;
            } else {
                Toast.makeText(getApplicationContext(),
                        (CharSequence) ("Select One Gender"),
                        Toast.LENGTH_SHORT).show();
            }
        }

        Intent intent = new Intent(this, DrinkSelecter.class);
        startActivity(intent);
    }


}